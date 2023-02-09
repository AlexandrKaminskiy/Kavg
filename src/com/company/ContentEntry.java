package com.company;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ContentEntry extends JPanel {

    private final int windowWidth;
    private final int windowHeight;
    private final int diameter;
    private final int vecQuantity;
    private final int colorQuantity;
    private List<ObjectDefinition> objectDefinitions;
    private Map<Integer, Color> colors;
    private Algorithm algorithm;

    public ContentEntry(int windowWidth, int windowHeight, int diameter, int vecQuantity, int colorQuantity) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.diameter = diameter;
        this.colorQuantity = colorQuantity;
        this.vecQuantity = vecQuantity;
        objectDefinitions = new ArrayList<>();
        colors = new HashMap<>();
        Random random = new Random();
        for (int i = 0; i < colorQuantity; i++) {
            colors.put(i, new Color(random.nextInt(0, 256),
                    random.nextInt(0, 256),
                    random.nextInt(0, 256)));
        }
        algorithm = new Algorithm(windowWidth, windowHeight,vecQuantity,colorQuantity);
        objectDefinitions = algorithm.iteration();
        Timer timer = new Timer(500, (event)->{
            if (!algorithm.resetCenters()) {
                objectDefinitions = algorithm.iteration();
            }
            repaint();
        });
        timer.start();

        setFocusable(true);
        repaint();


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        objectDefinitions.forEach(od -> {
            int d = od.isCenter() ? 20 : diameter;
            g.setColor(colors.get(od.getClas()));
            g.fillOval(od.getX(), od.getY(), d, d);
        });
    }

}
