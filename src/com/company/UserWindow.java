package com.company;

import javax.swing.*;

public class UserWindow extends JFrame {

    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 750;

    public UserWindow() {

        setBounds(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
        setContentPane(new ContentEntry(WINDOW_WIDTH, WINDOW_HEIGHT, 5, 10000, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }
}
