package com.company.Model;

public class CheckWinnerSimple implements CheckWinner {
    Board board;
    int winnerLength;
    Point start=new Point();
    Point end =new Point();

    @Override
    public BoardEntry checkWinner(Board board,int winnerLength) {
        this.board=board;
        this.winnerLength=winnerLength;

        if(checkWinner()){
            return start.get();
        }
        else {
            return BoardEntry.N;
        }
    }



    private boolean checkWinner(){
        int fieldSize=board.getBoardSize();

        //horizontal
        for(int k=0;k<fieldSize;k++) {
            if (checkLine(k, 0, k, fieldSize - 1)) return true;
        }
        //vertical
        for(int k=0;k<fieldSize;k++) {
            if (checkLine(0, k, fieldSize - 1,k)) return true;
        }

        //main diagonal
        if (checkLine(0, 0, fieldSize-1,fieldSize - 1)) return true;
        //triangles
        for(int k=fieldSize-winnerLength;k>0;k--) {
            if (checkLine(k, 0, fieldSize -1, fieldSize - 1 - k )) return true;
            if (checkLine(0, k, fieldSize - 1 - k, fieldSize - 1)) return true;
        }

        //second main diagonal
        if (checkLine(0, fieldSize-1, fieldSize - 1, 0)) return true;
        //triangles
        for(int k=winnerLength-1;k<fieldSize;k++) {
            if (checkLine(0, k, k, 0)) return true;
            if (checkLine(fieldSize - 1 - k, fieldSize - 1, fieldSize - 1, fieldSize - 1 - k)) return true;
        }

        return false;
    }

    private boolean checkLine(int x0,int y0,int x1,int y1) {
        int size = board.getBoardSize();
        if (x0 < 0 || x0 >= size
                || y0 < 0 || y0 >= size
                || x1 < 0 || x1 >= size
                || y1 < 0 || y1 >= size) {
            throw new IllegalArgumentException();
        }

        int stepx = (x0 < x1) ? 1 : (x0 > x1) ? -1 : 0;
        int stepy = (y0 < y1) ? 1 : (y0 > y1) ? -1 : 0;

        start.set(x0, y0, stepx, stepy);
        end.set(start);

        int n = 0;
        while (end.get() != BoardEntry.OUT) {
            if (start.get() != end.get()) {
                if (n >= winnerLength) {
                    return true;
                } else {
                    start.set(end);
                    n = 0;
                }
            }

            if (end.get() != BoardEntry.N) n++;
            end.step();
        }

        if (n >= winnerLength) {
            return true;
        }

        return false;
    }

    class Point {
        int x, y;
        int stepx, stepy;

        public Point() {
            this.x = 0;
            this.y = 0;
            this.stepx = 0;
            this.stepy = 0;
        }

        public BoardEntry step() {
            x += stepx;
            y += stepy;
            if (x >= board.getBoardSize() || y >= board.getBoardSize() || x < 0 || y < 0) {
                x -= stepx;
                y -= stepy;
                return BoardEntry.OUT;
            }
            return board.getState(x, y);
        }

        public BoardEntry get() {
            return board.getState(x, y);
        }

        public void set(Point p) {
            x = p.x;
            y = p.y;
            stepx = p.stepx;
            stepy = p.stepy;
        }

        public void set(int x, int y, int stepx, int stepy) {
            this.x = x;
            this.y = y;
            this.stepx = stepx;
            this.stepy = stepy;
        }
    }


}
