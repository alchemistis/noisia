package io.alchemistis.noisia;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel {
    private final NoiseGenerator noiseGenerator;
    private final BufferedImage heightMap;

    public Canvas(NoiseGenerator noiseGenerator, int width, int height) {
        this.noiseGenerator = noiseGenerator;
        this.setBounds(0, 0, width, height);
        this.heightMap = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        renderHeightMap();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(heightMap, 0, 0, null);
    }

    protected void renderHeightMap() {
        for (int x = 0; x < heightMap.getWidth(); x++) {
            for (int y = 0; y < heightMap.getHeight(); y++) {
                heightMap.setRGB(x, y, Color.blue.getRGB());

                // Get noise value for current pixel
                double noiseValue = noiseGenerator.noise(x * 0.02, y * 0.02);

                // Height map colors
                if (noiseValue > .01) {
                    int r = 234, g = 179, b = 114;

                    if (noiseValue > .02) {
                        r = 164;
                        g = 128;
                        b = 96;
                    }

                    if (noiseValue > .05) {
                        r = 96;
                        g = 179;
                        b = 0;
                    }

                    if (noiseValue > .1) {
                        r = 72;
                        g = 134;
                        b = 0;
                    }

                    if (noiseValue > .16) {
                        r = 128;
                        g = 128;
                        b = 128;
                    }

                    if (noiseValue > .24) {
                        r = 108;
                        g = 108;
                        b = 108;
                    }

                    if (noiseValue > .32) {
                        r = 255;
                        g = 255;
                        b = 255;
                    }

                    Color color = new Color(r, g, b, 255);
                    heightMap.setRGB(x, y, color.getRGB());
                }
            }
        }
    }
}
