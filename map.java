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



public class Map extends JPanel implements ActionListener {

    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 300;
    private final int DELAY = 10;
    private Timer timer;
    private Figur Figur1;
    private Figur Figur2;
    private Figur[] Figuren;
    private String mapName;
    private HealthContainer hp1;
    private HealthContainer hp2;

    public Map() {

        initMap();
    }

    private void initMap() {

        this.mapName = "donaueschingenmap";
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        Figuren = new Figur[2];

        Figur1 = new Figur(ICRAFT_X, ICRAFT_Y, "leo", true);
        Figur2 = new Figur(ICRAFT_X+560, ICRAFT_Y, "elizabeth", false);
        Figuren[0] = Figur1;
        Figuren[1] = Figur2;

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public Figur[] getFiguren(){
        return this.Figuren;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        ImageIcon ii = new ImageIcon("resources/backgrounds/"+this.mapName+".png");
        Image image = ii.getImage();

        g2d.drawImage(image, 0, 0, this);

        for(int i = 0; i < Figuren.length; i++){
            if(Figuren[i].getfClass().equals("ranged")){
                List<Missile> missiles = Figuren[i].getMissiles();

                for (Missile missile : missiles) {

                    g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
                }
            }
            else if(Figuren[i].getfClass().equals("aoe")){
                AOE tempAOE = Figuren[i].getAOE();
                if(tempAOE != null){
                    g2d.drawImage(tempAOE.getImage(), tempAOE.getX(), tempAOE.getY(), this);
                }
            }
            else{
                //melee
            }

            g2d.drawImage(Figuren[i].getImage(), Figuren[i].getX(), Figuren[i].getY(), this);

        }


    }

    public void addHealthContainer1(HealthContainer hp){
        this.hp1 = hp;
    }

    public void addHealthContainer2(HealthContainer hp){
        this.hp2 = hp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        updateMissiles();
        updateFigur();
        repaint();
    }

    private void updateMissiles() {

        for(int x = 0; x < Figuren.length; x++){

            checkMeleeCollision();

            AOE tempAOE = Figuren[x].getAOE();
            checkAOECollision(tempAOE);



            List<Missile> missiles = Figuren[x].getMissiles();

            for (int i = 0; i < missiles.size(); i++) {

                Missile missile = missiles.get(i);

                //handle collision
                checkMissileCollision(missile, missiles);


                if (missile.isVisible()) {
                    missile.move();
                } else {

                    missiles.remove(i);
                }
            }
        }

    }

    private void checkMissileCollision(Missile missile, List<Missile> missiles){
        if(missile.getParent().equals(Figuren[0].getName())){
            if(missile.getHitbox().intersects(Figuren[1].getHitbox())){
                //damages player 2
                Figuren[1].setHealth(Figuren[1].getHealth()-missile.getDamage());
                this.hp1.updateHealth(Figuren[1].getHealth()-missile.getDamage());
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

    private void checkAOECollision(AOE aoe){
        if(aoe != null){
            if(aoe.getParent().equals(Figuren[0].getName())){
                if(aoe.getHitbox().intersects(Figuren[1].getHitbox())){
                    //applies damage
                    Figuren[1].setHealth(Figuren[1].getHealth()-aoe.getDamage());
                    //removes aoe
                    Figuren[0].setAOE(null);
                    //checks death
                    if(Figuren[1].getHealth() <= 0){
                        System.out.println(Figuren[1].getName()+" was slain!");
                    }
                }
            }
            else{
                if(aoe.getHitbox().intersects(Figuren[0].getHitbox())){
                    //applies damage
                    Figuren[0].setHealth(Figuren[0].getHealth()-aoe.getDamage());
                    //removes AOE
                    Figuren[1].setAOE(null);
                    //checks death
                    if(Figuren[0].getHealth() <= 0){
                        System.out.println(Figuren[0].getName()+" was slain!");
                    }
                }
            }
        }

    }

    public void checkMeleeCollision(){
        //checks if player 1 is attacking
        if(Figuren[0].getMelee()){
            if(Figuren[0].getHitbox().intersects(Figuren[1].getHitbox())){
                //Apply sword damage
                Figuren[1].setHealth(Figuren[1].getHealth()-Figuren[0].getDamage());
                //Change attacking to false
                Figuren[0].setMelee(false);
                //removes the damage so you have to move to reset attacking
                Figuren[0].setDamage(0);
                //Check death
                if(Figuren[1].getHealth() <= 0){
                    System.out.println(Figuren[1].getName()+" was slain!");
                }
            }
        }
        else if(Figuren[1].getMelee()){
            if(Figuren[1].getHitbox().intersects(Figuren[0].getHitbox())){
                //Apply sword damage
                Figuren[0].setHealth(Figuren[0].getHealth()-Figuren[1].getDamage());
                //Change attacking to false
                Figuren[1].setMelee(false);
                //removes the damage so you have to move to reset attacking
                Figuren[1].setDamage(1);
                //Check death
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
