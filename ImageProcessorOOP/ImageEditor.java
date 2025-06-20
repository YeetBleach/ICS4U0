import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import javax.imageio.ImageIO;

public class ImageEditor {
    private BufferedImage originalImg;
    private BufferedImage modifiedImg;
    private Color backgroundColor = Color.WHITE;
    private boolean filtered;

    private BufferedImage deepCopy(BufferedImage img) {
        BufferedImage copy = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = copy.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return copy;
    }

    public boolean loadImage(File file) throws IOException {
        originalImg = ImageIO.read(file);
        modifiedImg = deepCopy(originalImg);
        return originalImg != null;
    }

    public void saveImage(File file) throws IOException {
        BufferedImage output = new BufferedImage(modifiedImg.getWidth(), modifiedImg.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = output.createGraphics();
        g.setColor(backgroundColor);
        g.fillRect(0, 0, output.getWidth(), output.getHeight());
        g.drawImage(modifiedImg, 0, 0, null);
        g.dispose();
        ImageIO.write(output, "png", file);
    }
    public void resetImage(){
        modifiedImg = deepCopy(originalImg); // Reverts back to a deep copy
        filtered = false;
    }

    public void applyFilter(Color filter) {
        if (filtered) {
            modifiedImg = deepCopy(originalImg);
        }
        for(int row = 0; row < modifiedImg.getWidth(); row++){
            for(int col = 0; col < modifiedImg.getHeight(); col++){
                int rgb = modifiedImg.getRGB(row, col);
                Color color = new Color(rgb);
                int red = (filter == Color.RED ? color.getRed() : 0);
                //Retains only the red component of the image while setting the green and blue components to zero.
                int green = (filter == Color.GREEN ? color.getGreen() : 0);
                //Retains only the green component of the image while setting the red and blue components to zero.
                int blue = (filter== Color.BLUE ? color.getBlue() : 0);
                //Retains only the blue component of the image while setting the red and green components to zero.


                color = new Color(red, green, blue, color.getAlpha());
                modifiedImg.setRGB(row, col, color.getRGB());
            }
        }
        filtered = true;

    }

    //Greyscale formula: Gray = 0.3 * Red + 0.59 * Green + 0.11 * Blue
    public void convertToGreyscale() {
        if (filtered){
            modifiedImg=deepCopy(originalImg);
        }
        for (int row = 0; row < modifiedImg.getWidth(); row++) {
            for (int col = 0; col < modifiedImg.getHeight(); col++) {
                int rgb = modifiedImg.getRGB(row, col);
                Color color = new Color(rgb, true);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int gray = (int) (0.3 * red + 0.59 * green + 0.11 * blue);
                Color grayColor = new Color(gray, gray, gray, color.getAlpha());
                modifiedImg.setRGB(row, col, grayColor.getRGB());
            }
        }
        filtered = true;
    }

    public void jumbleImage(){
        int width = modifiedImg.getWidth();
        int height = modifiedImg.getHeight();

        BufferedImage topLeft = modifiedImg.getSubimage(0, 0, width/2, height/2);
        BufferedImage topRight = modifiedImg.getSubimage(width/2, 0, width/2, height/2);
        BufferedImage bottomLeft = modifiedImg.getSubimage(0, height/2, width/2, height/2);
        BufferedImage bottomRight = modifiedImg.getSubimage(width/2, height/2, width/2, height/2);

        BufferedImage[] quadrants = {topLeft, topRight, bottomLeft, bottomRight};

        List<BufferedImage> quadrantsList = Arrays.asList(quadrants);
        Collections.shuffle(quadrantsList);

        BufferedImage jumbledImg = new BufferedImage(width, height, modifiedImg.getType());
        Graphics2D g = jumbledImg.createGraphics();
        g.drawImage(quadrantsList.get(0), 0, 0, null);
        g.drawImage(quadrantsList.get(1), width/2, 0, null);
        g.drawImage(quadrantsList.get(2), 0, height/2, null);
        g.drawImage(quadrantsList.get(3), width/2, height/2, null);
        g.dispose();
        modifiedImg = jumbledImg;
    }
    


    public void setBackgroundColor(Color color) {
        backgroundColor = color;
    }
    public Color getBackgroundColor() {
        return backgroundColor;
    }
    public BufferedImage getDisplayImage() {
        return deepCopy(modifiedImg);

    }
}
