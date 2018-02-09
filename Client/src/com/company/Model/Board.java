package com.company.Model;

public class Board {
    private int boardSize =3;
    private BoardEntry board[][];

    public Board(int boardSize) throws IllegalArgumentException{
        makeNewBoard(boardSize);
    }
    public int getBoardSize() {
            return boardSize;
        }

    private void makeNewBoard(int boardSize)throws IllegalArgumentException{
        if(boardSize<3) throw new IllegalArgumentException();

        this.boardSize = boardSize;
        board =new BoardEntry[boardSize][];
        for(int i = 0; i< boardSize; i++){
            board[i]=new BoardEntry[boardSize];
            for(int j = 0; j< boardSize; j++) {
                board[i][j]= BoardEntry.N;
            }
        }
    }

    public BoardEntry getState(int x, int y)throws IllegalArgumentException{
        if(x<0 || x>= boardSize || y<0 || y>= boardSize){
            throw new IllegalArgumentException();
        }
        return board[x][y];
    }

    public void setState(int x,int y,BoardEntry e)throws IllegalArgumentException{
        if(x<0 || x>= boardSize || y<0 || y>= boardSize){
            throw new IllegalArgumentException();
        }
        board[x][y]=e;
    }
}
