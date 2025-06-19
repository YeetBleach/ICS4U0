import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {
    private JLabel imageLabel;
    private BufferedImage originalImage;
    private double zoomFactor = 1.0;
    private float alpha = 1.0f;

    public ImagePanel() {
        setLayout(new BorderLayout());
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(new JScrollPane(imageLabel), BorderLayout.CENTER);
    }

    public void setImage(BufferedImage img) {
        originalImage = img;
        updateImage();
    }

    public void setZoom(double zoom) {
        zoomFactor = zoom;
        updateImage();
    }

    public void setTransparency(float a) {
        alpha = a;
        updateImage();
    }

    public void updateImage() {
        if (originalImage == null) {
            return;
        }
        int w = (int) (originalImage.getWidth() * zoomFactor);
        int h = (int) (originalImage.getHeight() * zoomFactor);
        BufferedImage buf = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = buf.createGraphics();
        g.fillRect(0, 0, w, h);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g.drawImage(originalImage, 0, 0, w, h, null);
        g.dispose();
        imageLabel.setIcon(new ImageIcon(buf));
        revalidate();
    }
}
