package org.zendev.lib.system.hardware;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Screen {

    public static boolean takeScreenShot(String savePath) throws AWTException, IOException {
        return takeScreenShot(savePath, new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
    }

    public static boolean takeScreenShot(String savePath, Rectangle rectangle) throws AWTException, IOException {
        BufferedImage image = new Robot().createScreenCapture(rectangle);
        ImageIO.write(image, "png", new File(savePath));

        return true;
    }

    public static double getWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }

    public static double getHeight() {
        return Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    }
}
