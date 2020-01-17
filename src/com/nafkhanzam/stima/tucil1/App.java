package com.nafkhanzam.stima.tucil1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class App {

    private static final String TITLE = "Convex Hull - Bruteforce Algorithm v1.0 ~ Moch. Nafkhan Alzamzami (13518132)";
    private static final int WIDTH = 1080;
    private static final int HEIGHT = 720;
    private static final int MAX_X = 100;
    private static final int MAX_Y = 100;

    private JFrame frame;
    private JPanel panel;
    private JTextField field;
    private JLabel label;
    private JButton btn;
    private JCheckBox check;
    private Chart chart;
    private JTextPane outputPane;

    public App() {
        _initFrame();
        _initComponents();
        _showFrame();
        _apply();
    }

    public void randomize(int n) {
        _timeElapsedText(this.chart.executeBruteForceAlgorithm(n), this.chart.alg.getComplexityString());
    }

    private void _timeElapsedText(long time, String complexity) {
        boolean ns = true;
        boolean s = false;
        long ns_ms = 1000000;
        long ms_s = 1000;
        if (time > ns_ms) {
            time /= ns_ms;
            ns = false;
            if (time > ms_s) {
                time /= ms_s;
                s = true;
            }
        }
        _setText(String.format("<html>Time complexity: %s<br>Time elapsed: %d %ss.", complexity, time, s ? "" : ns ? "n" : "m"));
    }

    private void _errorText(String text) {
        this.label.setForeground(Color.RED);
        this.label.setText(text);
    }

    private void _setText(String text) {
        this.label.setForeground(Color.BLACK);
        this.label.setText(text);
    }

    private boolean _running = false;
    private void _apply() {
        if (_running) {
            return;
        }
        this.btn.setEnabled(false);
        _running = true;
        _setText("Loading...");
        new Thread(() -> {
            try {
                int n = Integer.parseInt(this.field.getText());
                if (n < 0) {
                    throw new RuntimeException("N must be a non negative value!");
                }

                if (n > 10000) {
                    throw new RuntimeException("<html>N is too big!<br />This is bruteforce bruh.<br />Please understand!</html>");
                }

                randomize(n);
                _printOutput();
            } catch (NumberFormatException e) {
                _errorText("N must be a number!");
            } catch (Exception e) {
                _errorText(e.getMessage());
            }

            this.btn.setEnabled(true);
            _running = false;
        }).start();
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

        this.field = new JTextField("5");
        this.field.setBounds(650, 20, 200, 40);
        this.field.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    _apply();
                }
            }

        });
        this.panel.add(this.field);

        this.btn = new JButton("Randomize!");
        this.btn.setBounds(650, 70, 200, 40);
        this.btn.addActionListener(e -> _apply());
        this.panel.add(this.btn);

        this.label = new JLabel();
        this.label.setBounds(650, 120, 200, 80);
        this.label.setVerticalAlignment(JLabel.TOP);
        this.panel.add(this.label);

        this.check = new JCheckBox("Paint Coordinates");
        this.check.setSelected(true);
        this.check.setBounds(850, 20, 200, 40);
        this.check.addActionListener(e -> {
            this.chart.useString = this.check.isSelected();
            if (!_running) {
                this.chart.repaint();
            }
        });
        this.panel.add(this.check);

        JLabel outputLbl = new JLabel("Output:");
        outputLbl.setBounds(650, 220, 200, 40);
        this.panel.add(outputLbl);

        this.outputPane = new JTextPane();
        this.outputPane.setEditable(false);
        this.outputPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JScrollPane sp = new JScrollPane(this.outputPane);
        sp.setBounds(650, 260, 100, 350);
        this.panel.add(sp);

        JLabel outputLbl2 = new JLabel("Computer Specs:");
        outputLbl2.setBounds(750, 220, 200, 40);
        this.panel.add(outputLbl2);

        JTextPane specPane = new JTextPane();
        specPane.setEditable(false);
        specPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        specPane.setText(_getPrettyComputerSpecs());
        JScrollPane sp2 = new JScrollPane(specPane);
        sp2.setBounds(750, 260, 300, 350);
        this.panel.add(sp2);
    }

    private String _getPrettyComputerSpecs() {
        StringBuilder sb = new StringBuilder();

        long kilobytes = 1024;
        long megabytes = kilobytes * 1024;

        String nameOS = "os.name";
        String versionOS = "os.version";
        String architectureOS = "os.arch";
        sb.append("Name of the OS: " +
            System.getProperty(nameOS));
        sb.append("\n");
        sb.append("Version of the OS: " +
            System.getProperty(versionOS));
        sb.append("\n");
        sb.append("Architecture of The OS: " +
            System.getProperty(architectureOS));
        /* Total number of processors or cores available to the JVM */
        sb.append("\n");
        sb.append("Available processors: " +
            Runtime.getRuntime().availableProcessors() + " cores");

        sb.append("\n");
        /* Total amount of free memory available to the JVM */
        sb.append("Free memory: " +
            Runtime.getRuntime().freeMemory() / (float) megabytes + " MB");

        sb.append("\n");
        /* This will return Long.MAX_VALUE if there is no preset limit */
        long maxMemory = Runtime.getRuntime().maxMemory();
        /* Maximum amount of memory the JVM will attempt to use */
        sb.append("Maximum memory: " +
            (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory / (float) megabytes) + " MB");

        sb.append("\n");
        /* Total memory currently available to the JVM */
        sb.append("Total memory available to JVM: " +
            Runtime.getRuntime().totalMemory() / (float) megabytes + " MB");

        return sb.toString();
    }

    private void _printOutput() {
        String tab = "  ";
        String result = "[\n" + tab + String.join(",\n" + tab, this.chart.ansPoints.stream().map(v -> v.toString()).collect(Collectors.toList())) + "\n]";
        this.outputPane.setText(result);
    }

    private void _showFrame() {
        this.frame.setVisible(true);
    }

}