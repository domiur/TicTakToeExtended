package com.company.Model;

public class Field {

    private int fieldSize=3;
    private char field[][];

    private static final char X ='X';
    private static final char O ='O';
    public  static final char N =' ';
    private char current= X;

    public char getCurrent(){return current;}

    public Field(int fieldSize) {
        this.fieldSize = fieldSize;
        makeNewField();
    }
    public int getFieldSize() {
            return fieldSize;
        }

    private void makeNewField() {
        field=new char[fieldSize][];
        for(int i=0;i<fieldSize;i++){
            field[i]=new char[fieldSize];
            for(int j=0;j<fieldSize;j++) {
                field[i][j]=N;
            }
        }
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
        return true;
    }
}
