package com.company.Model;

public class Model {

    private Board tikTakBoard;

    private BoardEntry current = BoardEntry.X;

    private int winnerLength=3;
    private BoardEntry winner =BoardEntry.N;
    public boolean isWinner() { return winner!=BoardEntry.N; }

    public Model(int fieldSize, int winnerLength) throws IllegalArgumentException{
        newGame(fieldSize, winnerLength);
    }

    private void newGame(int fieldSize, int winnerLength) throws IllegalArgumentException{
        if(fieldSize<winnerLength || winnerLength<3) throw new IllegalArgumentException();
        tikTakBoard=new Board(fieldSize);
        this.winnerLength = winnerLength;

        current = BoardEntry.X;
        winner =BoardEntry.N;
    }

    public boolean step(int x,int y)throws IllegalArgumentException{
        if(tikTakBoard.getState(x,y)==BoardEntry.N){
            tikTakBoard.setState(x,y,current);
            current=(current==BoardEntry.X)?BoardEntry.O:BoardEntry.X;
            return true;
        }
        return false;
    }


/*
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
            int stepx=(x0<x1)?1:(x0>x1)?-1:0;
            int stepy=(y0<y1)?1:(y0>y1)?-1:0;

            int x=x0;
            int y=y0;
            char end=field[x][y];
            win0.set(x,y);
            int n=0;

            while(x>=0 && y>=0 && x<fieldSize && y<fieldSize){
                if (field[x][y] != end) {
                    if (n >= winnerLength) {
                        winner = end;
                        win1.set(x-stepx,y-stepy);
                        return true;
                    } else {
                        win0.set(x, y);
                        n = 0;
                    }
                }
                if (field[x][y] != N) n++;
                end = field[x][y];
                x+=stepx;
                y+=stepy;
            }
            if (n >= winnerLength) {
                winner = end;
                win1.set(x-stepx,y-stepy);
                return true;
            }

            return false;
        }


    }
*/
}
