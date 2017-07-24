package com.company;

import java.awt.*;

public class Main {

    private static void xx(Integer x,Integer y){x=1;y=1;}
    public static void main(String[] args) {
        final TikTakPresentation presentation=new TikTakPresentation();
        final TikTakModel model=new TikTakModel(5,3,presentation);


        EventQueue.invokeLater(
                new Runnable() {
                    public void run() {
                        TikTakUI ui = new TikTakUI(model);
                        presentation.addPresentation(ui);
                        ui.setVisible(true);
                    }
                }
        );
        EventQueue.invokeLater(
                new Runnable() {
                    public void run() {
                        TikTakUI ui1 = new TikTakUI(model);
                        presentation.addPresentation(ui1);
                        ui1.setVisible(true);
                    }
                }
        );
        {
            TikTakText ui2 = new TikTakText(model);
            presentation.addPresentation(ui2);
            (new Thread(ui2)).start();

        }
    }
}
