package com.company;

import java.awt.*;

public class Main {

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

    }
}
