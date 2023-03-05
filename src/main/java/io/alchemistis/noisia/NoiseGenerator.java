package io.alchemistis.noisia;

public class NoiseGenerator {
    private final int octaves;
    private final double persistence;
    private final double lacunarity;
    private final int seed;

    public NoiseGenerator(int octaves, double persistence, double lacunarity, int seed) {
        this.octaves = octaves;
        this.persistence = persistence;
        this.lacunarity = lacunarity;
        this.seed = seed;
    }

    public double noise(double x, double y) {
        double result = 0;
        double amplitude = 1;
        double frequency = 1;
        double maxAmplitude = 0;

        for (int i = 0; i < octaves; i++) {
            result += interpolateNoise(x * frequency, y * frequency) * amplitude;
            maxAmplitude += amplitude;
            amplitude *= persistence;
            frequency *= lacunarity;
        }

        return result / maxAmplitude;
    }

    private double interpolateNoise(double x, double y) {
        int xi = (int) x;
        int yi = (int) y;
        double xf = x - xi;
        double yf = y - yi;

        double v1 = smoothNoise(xi, yi);
        double v2 = smoothNoise(xi + 1, yi);
        double v3 = smoothNoise(xi, yi + 1);
        double v4 = smoothNoise(xi + 1, yi + 1);

        double i1 = interpolate(v1, v2, xf);
        double i2 = interpolate(v3, v4, xf);

        return interpolate(i1, i2, yf);
    }

    private double smoothNoise(int x, int y) {
        double corners = (noise(x - 1, y - 1) + noise(x + 1, y - 1) + noise(x - 1, y + 1) + noise(x + 1, y + 1)) / 16.0;
        double sides = (noise(x - 1, y) + noise(x + 1, y) + noise(x, y - 1) + noise(x, y + 1)) / 8.0;
        double center = noise(x, y) / 4.0;
        return corners + sides + center;
    }

    private double noise(int x, int y) {
        int n = x + y * 57 + seed;
        n = (n << 13) ^ n;
        return (1.0 - ((n * (n * n * 15731 + 789221) + 1376312589) & 0x7fffffff) / 1073741824.0);
    }

    private double interpolate(double a, double b, double x) {
        double ft = x * Math.PI;
        double f = (1 - Math.cos(ft)) * 0.5;
        return a * (1 - f) + b * f;
    }
}
