package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Created by mdoroshenko on 24/6/16.
 */
public class TikTakText implements Draw,Runnable {
    private TikTakModel model;
    Scanner in;

    public TikTakText(TikTakModel model) {
        this.model = model;
        in=new Scanner(System.in);
        redraw();
    }

    @Override
    public void redraw() {
        for(int x=1;x<=model.getFieldSize();x++){
            for(int y=1;y<=model.getFieldSize();y++) {
                char c=model.getState(x,y);
                System.out.print(c==model.N?'.':c);
            }
            System.out.println();
        }
        System.out.println("enter x y:");
    }

    @Override
    public void run() {
        while (in.hasNext()){
            int x=in.nextInt();
            int y=in.nextInt();
            if(!model.step(x,y)){
                System.out.println("not possible to step. use other x,y");
            }
        }
    }

    class TikTakUIPanel extends JPanel {
            private JButton newGame;
            private JButton closeGame;
            private ArrayList<JButton> buttons=new ArrayList<>();

            public TikTakUIPanel() {
                setLayout(new BorderLayout());

                JPanel but = new JPanel();
                newGame = new JButton("New Game");
                newGame.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        model.newGame();
                        redraw();
                    }
                });
                closeGame = new JButton("Close");
                closeGame.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        exit(0);
                    }
                });
                but.add(newGame);
                but.add(closeGame);
                add(but, BorderLayout.NORTH);

                JPanel field = new JPanel(new GridLayout(3, 3));
                field.add(addButton(1,1));
                field.add(addButton(1,2));
                field.add(addButton(1,3));
                field.add(addButton(2,1));
                field.add(addButton(2,2));
                field.add(addButton(2,3));
                field.add(addButton(3,1));
                field.add(addButton(3,2));
                field.add(addButton(3,3));
                add(field, BorderLayout.CENTER);
            }

            public void redraw() {
                for(JButton bt:buttons){
                    int x=(int)bt.getClientProperty("x");
                    int y=(int)bt.getClientProperty("y");
                    bt.setText(""+model.getState(x,y)+"");
                }

            }

            private JButton addButton(final int x,final int y) {
                final JButton bt = new JButton("");
                buttons.add(bt);
                bt.putClientProperty("x",x);
                bt.putClientProperty("y",y);

                bt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        char cur=model.getCurrent();
                        model.step(x,y);
                    }
                });
                return bt;
            }


        }

    }
