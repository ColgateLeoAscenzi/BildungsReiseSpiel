import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Map extends JPanel implements ActionListener {

    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int DELAY = 10;
    private Timer timer;
    private Figur Figur1;
    private Figur Figur2;
    private Figur[] Figuren;

    public Map() {

        initMap();
    }

    private void initMap() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);
        setDoubleBuffered(true);
        Figuren = new Figur[2];

        Figur1 = new Figur(ICRAFT_X, ICRAFT_Y, "leo", true);
        Figur2 = new Figur(ICRAFT_X+400, ICRAFT_Y, "kevin", false);
        Figuren[0] = Figur1;
        Figuren[1] = Figur2;

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(Figur1.getImage(), Figur1.getX(), Figur1.getY(), this);

        g2d.drawImage(Figur2.getImage(), Figur2.getX(), Figur2.getY(), this);

        for(int i = 0; i < Figuren.length; i++){
            List<Missile> missiles = Figuren[i].getMissiles();

            for (Missile missile : missiles) {

                g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
            }
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        updateMissiles();
        updateFigur();

        repaint();
    }

    private void updateMissiles() {

        for(int x = 0; x < Figuren.length; x++){

            List<Missile> missiles = Figuren[x].getMissiles();

            for (int i = 0; i < missiles.size(); i++) {

                Missile missile = missiles.get(i);

                if (missile.isVisible()) {

                    missile.move();
                } else {

                    missiles.remove(i);
                }
            }
        }

    }

    private void updateFigur() {
        Figur1.move();
        Figur2.move();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            Figur1.keyReleased(e);
            Figur2.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            Figur1.keyPressed(e);
            Figur2.keyPressed(e);
        }
    }
}
