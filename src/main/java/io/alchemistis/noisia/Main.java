package io.alchemistis.noisia;

import javax.swing.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        NoiseGenerator noiseGenerator = new NoiseGenerator(8, 0.5, 2, new Random().nextInt());
        SwingUtilities.invokeLater(() -> display(noiseGenerator));
    }

    private static void display(NoiseGenerator noiseGenerator) {
        JFrame frame = new JFrame("Noisia");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Canvas canvas = new Canvas(noiseGenerator, frame.getWidth(), frame.getHeight());
        frame.getContentPane().add(canvas);

        frame.setResizable(false);
        frame.setVisible(true);
    }
}
