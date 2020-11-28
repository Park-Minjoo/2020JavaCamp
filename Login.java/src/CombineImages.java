import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CombineImages {
	public static void main(String[] args) throws IOException {
		int imagesCount = 4;
		BufferedImage images[] = new BufferedImage[imagesCount];
		for (int j = 0; j < images.length; j++) {
			images[j] = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = images[j].createGraphics();
			g2d.drawRect(10, 10, 80, 80);
			g2d.dispose();
		}

		int heightTotal = 0;
		for (int j = 0; j < images.length; j++) {
			heightTotal += images[j].getHeight();
		}

		int heightCurr = 0;
		BufferedImage concatImage = new BufferedImage(100, heightTotal, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = concatImage.createGraphics();
		for (int j = 0; j < images.length; j++) {
			g2d.drawImage(images[j], 0, heightCurr, null);
			heightCurr += images[j].getHeight();
		}
		g2d.dispose();

		ImageIO.write(concatImage, "png", new File("/Users/minjoo/Desktop/1.jpeg")); // export concat image
		ImageIO.write(images[0], "png", new File("/Users/minjoo/Desktop/moon.jpg"));

	}
}