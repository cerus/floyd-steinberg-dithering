import com.github.regarrzo.fsd.ColorPalette;
import com.github.regarrzo.fsd.Ditherer;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Test {

    public static void main(final String[] args) {

        final Color[] palette_colors = {new Color(255, 255, 255), new Color(155, 155, 155), new Color(0, 0, 0)};

        final ColorPalette palette = new ColorPalette(palette_colors);

        final Ditherer ditherer = new Ditherer(palette);

        final BufferedImage img;
        try {
            img = ImageIO.read(new File("src/test/java/test_image.jpg"));

            ditherer.dither(img);

            final File output_file = new File("out.png");
            ImageIO.write(img, "png", output_file);

        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
