import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class BackgroundImageManager {

    static BufferedImage loadImage(File imageFile) {

        BufferedImage image = null;
        try {
            image = ImageIO.read(imageFile);
        }
        catch (IOException ie) {
            ie.printStackTrace();
        }

        if (image == null) {
            throw new NullPointerException();
        }

        return image;
    }

}
