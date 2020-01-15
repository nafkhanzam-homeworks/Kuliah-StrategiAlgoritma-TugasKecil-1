package com.nafkhanzam.stima.tucil1;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App {

    private static final String TITLE = "Convex Hull - Bruteforce Algorithm v1.0 ~ Moch. Nafkhan Alzamzami (13518132)";
    private static final int WIDTH = 1080;
    private static final int HEIGHT = 720;
    private static final int MAX_X = 100;
    private static final int MAX_Y = 100;

    private JFrame frame;
    private JPanel panel;
    private Chart chart;

    public App() {
        _initFrame();
        _initComponents();
        _showFrame();
    }

    public void randomize(int n) {
        this.chart.randomize(n);
    }

	private void _initFrame() {
        this.frame = new JFrame(TITLE);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLayout(null);
        this.frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);

        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.frame.setContentPane(this.panel);
    }

    private void _initComponents() {
        this.chart = new Chart(MAX_X, MAX_Y);
        this.chart.setBounds(20, 20, 600, 600);
        this.panel.add(this.chart);


    }

    private void _showFrame() {
        this.frame.setVisible(true);
    }

}