package com.company;


public class TikTakModel {
    private int fieldSize=3;
    private char field[][];

    private int winnerLength=3;
    private static final char X ='X';
    private static final char O ='O';
    public  static final char N =' ';
    private char winner=N;
    private char current= X;

    public char getCurrent(){return current;}

    class Point {
        int x,y;
        void set(int xx,int yy){x=xx;y=yy;}
        public String toString() { return "(" +x+"," + y +")"; }
    };
    private Point win0=new Point(),win1=new Point();
    public boolean isWinner() { return winner!=N; }
    public Point getWin0(){return win0;}
    public Point getWin1(){return win1;}


    private TikTakPresentation presentation=null;

    public TikTakModel(int fieldSize, int winnerLength, TikTakPresentation p) {
        this.fieldSize = fieldSize;
        this.winnerLength = winnerLength;
        this.presentation=p;
        init();
    }

    public int getFieldSize() {
        return fieldSize;
    }

    private void init() {
        this.field=new char[this.fieldSize][];
        for(int i=0;i<this.fieldSize;i++){
            this.field[i]=new char[this.fieldSize];
            for(int j=0;j<this.fieldSize;j++) {
                this.field[i][j]=N;
            }
        }
        presentation.redraw();

    }
    public void newGame() {
        for(int i=0;i<this.fieldSize;i++){
            for(int j=0;j<this.fieldSize;j++) {
                this.field[i][j]=N;
            }
        }
        winner=N;
        current= X;
        presentation.redraw();
    }

    public char getState(int x, int y){
        if(x<=0 || x>fieldSize || y<=0 || y>fieldSize){
            throw new IllegalArgumentException();
        }
        return field[x-1][y-1];
    }
    public boolean step(int x,int y){
        if(getState(x,y) != N) return false;
        field[x-1][y-1]=current;

        current=(current==X)?O:X;

        checkWinner();

        if(winner!=N)
            System.out.println("winner="+winner+" "+win0+" "+win1);

        presentation.redraw();
        return true;
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
        int stepx=(x0<x1)?1:(x0>x1)?-1:0;
        int stepy=(y0<y1)?1:(y0>y1)?-1:0;

        int x=x0;
        int y=y0;
        char cur=field[x][y];
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
