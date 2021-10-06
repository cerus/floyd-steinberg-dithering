package com.github.regarrzo.fsd;

import java.awt.image.BufferedImage;

public class Ditherer {

    private ColorPalette palette;

    public Ditherer(final ColorPalette palette) {
        this.setPalette(palette);
    }

    public ColorPalette getPalette() {
        return this.palette;
    }

    public void setPalette(final ColorPalette palette) {
        this.palette = palette;
    }

    public void dither(final BufferedImage img) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (img.getRGB(x, y) >> 24 == 0x0) {
                    continue;
                }

                final VectorRGB current_color = new VectorRGB(img.getRGB(x, y));
                final VectorRGB closest_match = this.palette.getClosestMatch(current_color);
                final VectorRGB error = current_color.subtract(closest_match);

                img.setRGB(x, y, closest_match.toRGB());

                if (!(x == img.getWidth() - 1)) {
                    img.setRGB(x + 1, y,
                            ((new VectorRGB(img.getRGB(x + 1, y)).add(error.scalarMultiply(7f / 16f)))
                                    .clip(0, 255).toRGB()));

                    if (!(y == img.getHeight() - 1)) {
                        img.setRGB(x + 1, y + 1,
                                ((new VectorRGB(img.getRGB(x + 1, y + 1)).add(error.scalarMultiply(1f / 16f)))
                                        .clip(0, 255).toRGB()));
                    }
                }

                if (!(y == img.getHeight() - 1)) {

                    img.setRGB(x, y + 1,
                            ((new VectorRGB(img.getRGB(x, y + 1)).add(error.scalarMultiply(3f / 16f)))
                                    .clip(0, 255).toRGB()));

                    if (!(x == 0)) {
                        img.setRGB(x - 1, y + 1, ((new VectorRGB(img.getRGB(x - 1, y + 1))
                                .add(error.scalarMultiply(5f / 16f)).clip(0, 255).toRGB())));

                    }
                }

            }

        }

    }

}
