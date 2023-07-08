java


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StarPattern extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int NUM_STARS = 100;
    private static final int DELAY = 10;

    private double angle = 0;

    public StarPattern() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        startAnimation();
    }

    private void startAnimation() {
        Thread animationThread = new Thread(() -> {
            while (true) {
                angle += 0.01;
                repaint();
                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        animationThread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        for (int i = 0; i < NUM_STARS; i++) {
            double radius = i * 4;
            double x = centerX + radius * Math.cos(angle * (i + 1));
            double y = centerY + radius * Math.sin(angle * (i + 1));

            int starSize = 10 - i / 10;
            Color starColor = new Color(i * 2, 255 - i * 2, 255);
            g2d.setColor(starColor);
            g2d.fillRect((int) x, (int) y, starSize, starSize);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Star Pattern");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new StarPattern());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}