import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class ImageProcessor {
    private JFrame frame;
    private ImageEditor editor;
    private ImagePanel panel;

    public static void main(String[] args) {
        new ImageProcessor().start();
    }

    public void start() {
        editor = new ImageEditor();
        panel = new ImagePanel();
        setupUI();
    }

    private BufferedImage fetchModifiedImage() {
        try {
            Field f = ImageEditor.class.getDeclaredField("modifiedImg");
            f.setAccessible(true);
            return (BufferedImage) f.get(editor);
        } catch (Exception e) {
            return null;
        }
    }

    public void setupUI() {
        frame = new JFrame("Image Processor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JButton resetButton = new JButton("Reset Image");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenu colorFilter = new JMenu("Color Filter");
        JMenuItem redFilter = new JMenuItem("Red Filter");
        JMenuItem blueFilter = new JMenuItem("Blue Filter");
        JMenuItem greenFilter = new JMenuItem("Green Filter");
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        colorFilter.add(redFilter);
        colorFilter.add(blueFilter);
        colorFilter.add(greenFilter);
        menuBar.add(fileMenu);
        menuBar.add(colorFilter);
        menuBar.add(resetButton);
        frame.setJMenuBar(menuBar);

        frame.add(panel, BorderLayout.CENTER);

        JPanel controls = new JPanel();
        JButton grayButton = new JButton("Grayscale");
        JButton jumbleButton = new JButton("Jumble");
        controls.add(grayButton);
        controls.add(jumbleButton);

        JSlider zoomSlider = new JSlider(50, 200, 100);
        zoomSlider.setMajorTickSpacing(50);
        zoomSlider.setMinorTickSpacing(10);
        zoomSlider.setPaintTicks(true);
        zoomSlider.setPaintLabels(true);
        zoomSlider.addChangeListener(e -> panel.setZoom(zoomSlider.getValue() / 100.0));
        controls.add(new JLabel("Zoom:"));
        controls.add(zoomSlider);

        frame.add(controls, BorderLayout.SOUTH);

        openItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    if (editor.loadImage(file)) {
                        zoomSlider.setValue(100);
                        panel.setImage(fetchModifiedImage());
                    }
                } catch (IOException ex) {}
            }
        });

        saveItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                try {
                    editor.saveImage(chooser.getSelectedFile());
                } catch (IOException ex) {}
            }
        });

        grayButton.addActionListener(e -> {
            editor.convertToGreyscale();
            panel.setImage(fetchModifiedImage());
        });

        redFilter.addActionListener(e -> {
            editor.applyFilter(Color.RED);
            panel.setImage(fetchModifiedImage());
        });

        greenFilter.addActionListener(e -> {
            editor.applyFilter(Color.GREEN);
            panel.setImage(fetchModifiedImage());
        });

        blueFilter.addActionListener(e -> {
            editor.applyFilter(Color.BLUE);
            panel.setImage(fetchModifiedImage());
        });

        resetButton.addActionListener(e -> {
            editor.resetImage();
            panel.setImage(fetchModifiedImage());
        });

        jumbleButton.addActionListener(e -> {
            editor.jumbleImage();
            panel.setImage(fetchModifiedImage());

        });

        frame.setVisible(true);
    }
}
