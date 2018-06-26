import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Figur extends Sprite {

    private int dx;
    private int dy;
    private int speed;
    private int health;
    private String name;
    private List<Missile> missiles;
    private int imgC;
    private String direction;

    public Figur(int x, int y, String name) {
        super(x, y);

        initFigur(name);
    }

    private void initFigur(String name) {

        //handles speed
        this.speed = 2;
        if(name.equals("eli")){
            speed = 4;
        }

        //handles health
        this.health = 10;
        if(name.equals("jack")){
            health = 15;
        }

        //starting direction
        this.direction = "R";

        //handles name
        this.name = name;

        //counts direction
        imgC = 0;

        //holds missiles if ranged
        missiles = new ArrayList<>();

        //sets up image and gets dimensions and hitboxes
        loadImage("resources/sprites/leo_figur/leo_recht.png");
        getImageDimensions();
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public List<Missile> getMissiles() {
        return missiles;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_E) {
            fire();
        }

        if (key == KeyEvent.VK_A) {
            dx = -this.speed;
            if(!direction.equals("L")){
                imgC = 5;
                direction = "L";
            }
            if (imgC == 5){
                imgC = 0;
                loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_links.png");
            }
            else{
                imgC += 1;
                loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_links1.png");
            }
        }

        if (key == KeyEvent.VK_D) {
            dx = this.speed;
            if(!direction.equals("R")){
                imgC = 5;
                direction = "R";
            }
            if (imgC == 5){
                imgC = 0;
                loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_recht.png");
            }
            else{
                imgC += 1;
                loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_recht1.png");
            }
        }

        if (key == KeyEvent.VK_W) {
            dy = -this.speed;
        }

        if (key == KeyEvent.VK_S) {
            dy = this.speed;
        }
    }

    public void fire() {
        missiles.add(new Missile(x + width, y + height / 2));
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            if(dx == -this.speed){
                dx = 0;
            }

        }

        if (key == KeyEvent.VK_D) {
            if(dx == this.speed){
                dx = 0;
            }

        }

        if (key == KeyEvent.VK_W) {
            if(dy == -this.speed){
                dy = 0;
            }
        }

        if (key == KeyEvent.VK_S) {
            if(dy == this.speed){
                dy = 0;
            }
        }
    }
}
