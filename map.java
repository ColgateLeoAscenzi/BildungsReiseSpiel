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
    private final int ICRAFT_Y = 300;
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
        Figur2 = new Figur(ICRAFT_X+560, ICRAFT_Y, "kevin", false);
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

                //handle collision
                checkCollision(missile, missiles);


                if (missile.isVisible()) {
                    missile.move();
                } else {

                    missiles.remove(i);
                }
            }
        }

    }

    private void checkCollision(Missile missile, List<Missile> missiles){
        if(missile.getParent().equals(Figuren[0].getName())){
            if(missile.getHitbox().intersects(Figuren[1].getHitbox())){
                //damages player 2
                Figuren[1].setHealth(Figuren[1].getHealth()-missile.getDamage());
                //removes the missile
                missiles.remove(missile);
                //checks if player 2 died
                if(Figuren[1].getHealth() <= 0){
                    System.out.println(Figuren[1].getName()+" was slain!");
                }
            }
        }
        else{
            if(missile.getHitbox().intersects(Figuren[0].getHitbox())){
                //damages player 1
                Figuren[0].setHealth(Figuren[0].getHealth()-missile.getDamage());
                //removes the missile
                missiles.remove(missile);
                //chekcs if player 1 died
                if(Figuren[0].getHealth() <= 0){
                    System.out.println(Figuren[0].getName()+" was slain!");
                }
            }
        }

    }

    private void updateFigur() {
        for(int i = 0; i < Figuren.length; i++){
            if(Figuren[i].getHealth() > 0){
                Figuren[i].move();
            }
        }
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
