package com.github.regarrzo.fsd;

import java.awt.Color;

public class ColorPalette {

    private final VectorRGB[] colors;

    public ColorPalette(final VectorRGB[] colors) {
        this.colors = colors;
    }

    public ColorPalette(final Color[] colors) {
        this.colors = new VectorRGB[colors.length];

        for (int i = 0; i < colors.length; i++) {
            this.colors[i] = new VectorRGB(colors[i]);
        }
    }

    public VectorRGB getClosestMatch(final VectorRGB color, final boolean transparent) {
        int minimum_index = 0;
        int minimum_difference = this.colors[0].fastDifferenceTo(color);

        for (int i = 1; i < this.colors.length; i++) {

            final int current_difference = this.colors[i].fastDifferenceTo(color);

            if (current_difference < minimum_difference) {
                if (this.colors[i].a == 0 && !transparent) {
                    continue;
                }
                minimum_difference = current_difference;
                minimum_index = i;
            }
        }

        return this.colors[minimum_index];
    }

}
