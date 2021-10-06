package com.github.regarrzo.fsd;

import java.awt.Color;

public class VectorRGB {

    public int r;
    public int g;
    public int b;
    public int a;

    public VectorRGB(final int r, final int g, final int b, final int a) {
        this.r = r;
        this.b = b;
        this.g = g;
        this.a = a;
    }

    public VectorRGB(final Color color) {
        this.r = color.getRed();
        this.b = color.getBlue();
        this.g = color.getGreen();
        this.a = color.getAlpha();
    }

    public VectorRGB(final int rgb) {
        final Color color = new Color(rgb);
        this.r = color.getRed();
        this.b = color.getBlue();
        this.g = color.getGreen();
        this.a = color.getAlpha();
    }

    public int toRGB() {
        return new Color(this.r, this.g, this.b, this.a).getRGB();
    }

    public Color toColor() {
        return new Color(this.r, this.g, this.b, this.a);
    }

    public VectorRGB subtract(final VectorRGB other) {
        return new VectorRGB(this.r - other.r, this.g - other.g, this.b - other.b, this.a - other.a);
    }

    public VectorRGB add(final VectorRGB other) {
        return new VectorRGB(this.r + other.r, this.g + other.g, this.b + other.b, this.a + other.a);
    }

    public int fastDifferenceTo(final VectorRGB other) {
        final VectorRGB difference = this.subtract(other);
        return Math.abs(difference.r) + Math.abs(difference.g) + Math.abs(difference.b) + Math.abs(difference.a);
    }

    public VectorRGB scalarMultiply(final float scalar) {
        return new VectorRGB((int) (this.r * scalar), (int) (this.g * scalar), (int) (this.b * scalar), (int) (this.a * scalar));
    }

    public VectorRGB clip(final int minimum, final int maximum) {
        final VectorRGB clipped = new VectorRGB(this.r, this.g, this.b, this.a);
        if (clipped.r > maximum) {
            clipped.r = maximum;
        } else if (clipped.r < minimum) {
            clipped.r = minimum;
        }

        if (clipped.g > maximum) {
            clipped.g = maximum;
        } else if (clipped.g < minimum) {
            clipped.g = minimum;
        }

        if (clipped.b > maximum) {
            clipped.b = maximum;
        } else if (clipped.b < minimum) {
            clipped.b = minimum;
        }

        if (clipped.a > maximum) {
            clipped.a = maximum;
        } else if (clipped.a < minimum) {
            clipped.a = minimum;
        }

        return clipped;

    }
}
