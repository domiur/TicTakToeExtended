package com.company.Model;

public class Field {
    private int fieldSize=3;
    private FieldEntry field[][];

    public Field(int fieldSize) {
        this.fieldSize = fieldSize;
        makeNewField();
    }
    public int getFieldSize() {
            return fieldSize;
        }

    private void makeNewField() {
        field=new FieldEntry[fieldSize][];
        for(int i=0;i<fieldSize;i++){
            field[i]=new FieldEntry[fieldSize];
            for(int j=0;j<fieldSize;j++) {
                field[i][j]=FieldEntry.N;
            }
        }
    }

    public FieldEntry getState(int x, int y){
        if(x<=0 || x>fieldSize || y<=0 || y>fieldSize){
            throw new IllegalArgumentException();
        }
        return field[x-1][y-1];
    }

    public boolean setState(int x,int y,FieldEntry e){
        if(getState(x,y) != FieldEntry.N) return false;
        field[x-1][y-1]=e;
        return true;
    }
}
