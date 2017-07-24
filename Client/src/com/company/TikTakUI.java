package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import static java.lang.System.exit;


interface Draw{
    void redraw();
}

public class TikTakUI extends JFrame implements Draw {
    private TikTakModel model;
    private TikTakUIPanel panel;

    public TikTakUI(TikTakModel model) throws HeadlessException {
        super("TicTakModel");
        this.model = model;
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        init();
    }

    private void init() {
        panel = new TikTakUIPanel();
        add(panel);

        addComponentListener(new ComponentAdapter() {
                                 @Override
                                 public void componentResized(ComponentEvent e) {
                                     super.componentResized(e);
                                     panel.drawWinnerLine();
                                 }
                             }
        );
    }

    @Override
    public void redraw() {
        panel.redraw();

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

            JPanel field = new JPanel(new GridLayout(model.getFieldSize(),model.getFieldSize()));
            for(int x=1;x<=model.getFieldSize();x++) {
                for (int y = 1; y <= model.getFieldSize(); y++) {
                    field.add(addButton(x, y));
                }
            }
            add(field, BorderLayout.CENTER);
        }


        public void redraw() {
           for(JButton bt:buttons){
               int x=(int)bt.getClientProperty("x");
               int y=(int)bt.getClientProperty("y");
               bt.setText(""+model.getState(x,y)+"");
           }
            drawWinnerLine();

        }


        private void drawWinnerLine() {
            if(model.isWinner()){
                getGraphics().drawLine(model.getWin0().x,model.getWin0().y,model.getWin1().x*100,model.getWin1().y*100);
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