package com.company.Model;

/**
 * Created by mdoroshenko on 09/02/18.
 */
public class CheckWinnerSimple implements CheckWinner {
    Board board;
    @Override
    public BoardEntry checkWinner(Board board) {
        this.board=board;
        return BoardEntry.N;
    }



    class Point{
        int x,y;
        int stepx,stepy;

        public Point(int x, int y, int stepx, int stepy) {
            this.x = x;
            this.y = y;
            this.stepx = stepx;
            this.stepy = stepy;
        }
        public BoardEntry step(){
            x+=stepx;
            y+=stepy;
            return board.getState(x,y);
        }
    }
    private boolean checkWinner(){
        for(int k=0;k<fieldSize;k++) {
            if (checkLine(k, 0, k, fieldSize - 1)) return true;
        }
        for(int k=0;k<fieldSize;k++) {
            if (checkLine(0, k, fieldSize - 1,k)) return true;
        }

        if (checkLine(0, 0, fieldSize-1,fieldSize - 1)) return true;
        for(int k=fieldSize-winnerLength;k>0;k--) {
            if (checkLine(k, 0, fieldSize -1, fieldSize - 1 - k )) return true;
            if (checkLine(0, k, fieldSize - 1 - k, fieldSize - 1)) return true;
        }
        if (checkLine(0, fieldSize-1, fieldSize - 1, 0)) return true;
        for(int k=winnerLength-1;k<fieldSize;k++) {
            if (checkLine(0, k, k, 0)) return true;
            if (checkLine(fieldSize - 1 - k, fieldSize - 1, fieldSize - 1, fieldSize - 1 - k)) return true;
        }

        return false;
    }
    private boolean checkLine(int x0,int y0,int x1,int y1){
        int size=board.getBoardSize();
        if(x0<0 || x0>= size
                || y0<0 || y0>= size
                || x1<0 || x1>= size
                || y1<0 || y1>= size){
            throw new IllegalArgumentException();
        }

        int stepx=(x0<x1)?1:(x0>x1)?-1:0;
        int stepy=(y0<y1)?1:(y0>y1)?-1:0;

        int x=x0;
        int y=y0;
        BoardEntry cur=board.getState(x,y);
        win0.set(x,y);
        int n=0;

        while(x>=0 && y>=0 && x<fieldSize && y<fieldSize){
            if (field[x][y] != cur) {
                if (n >= winnerLength) {
                    winner = cur;
                    win1.set(x-stepx,y-stepy);
                    return true;
                } else {
                    win0.set(x, y);
                    n = 0;
                }
            }
            if (field[x][y] != N) n++;
            cur = field[x][y];
            x+=stepx;
            y+=stepy;
        }
        if (n >= winnerLength) {
            winner = cur;
            win1.set(x-stepx,y-stepy);
            return true;
        }

        return false;
    }



}
