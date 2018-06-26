import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;


public class HealthContainer extends JPanel{

    private int health;

    public HealthContainer(Figur figur) {

        this.health = figur.getHealth();
        initHealthContainer(figur);

    }

    private void initHealthContainer(Figur figur) {
        setFocusable(true);
        setBackground(Color.GRAY);


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        ImageIcon ii = new ImageIcon("resources/sprites/misc/heart.png");
        Image image = ii.getImage();

        g2d.drawImage(image, 0, 0, this);

    }


}
