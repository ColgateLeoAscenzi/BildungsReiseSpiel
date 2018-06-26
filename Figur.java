import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class Figur extends Sprite {

    private int dx;
    private int dy;
    private int speed;
    private int health;
    private String name;
    private List<Missile> missiles;
    private int imgC;
    private String direction;
    private long cooldown;
    private boolean isPlayerOne;

    public Figur(int x, int y, String name, boolean isPlayerOne) {
        super(x, y);
        this.isPlayerOne = isPlayerOne;
        initFigur(name);
    }

    private void initFigur(String name) {
        //handles cooldowns
        this.cooldown = System.currentTimeMillis();



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
        loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_recht.png");
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
        if(this.isPlayerOne){
            if (key == KeyEvent.VK_E) {
                if((System.currentTimeMillis()) - this.cooldown > 300){
                    fire();
                    this.cooldown = System.currentTimeMillis();
                }

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
                if(!direction.equals("U")){
                    imgC = 5;
                    direction = "U";
                }
                if (imgC == 5){
                    imgC = 0;
                    loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_uber.png");
                }
                else{
                    imgC += 1;
                    loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_uber1.png");
                }
            }

            if (key == KeyEvent.VK_S) {
                dy = this.speed;
                if(!direction.equals("D")){
                    imgC = 5;
                    direction = "D";
                }
                if (imgC == 5){
                    imgC = 0;
                    loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_unter.png");
                }
                else{
                    imgC += 1;
                    loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_unter1.png");
                }
            }
        }
        else{
            if (key == KeyEvent.VK_O) {
                if((System.currentTimeMillis()) - this.cooldown > 300){
                    fire();
                    this.cooldown = System.currentTimeMillis();
                }

            }

            if (key == KeyEvent.VK_J) {
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
            if (key == KeyEvent.VK_L) {
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


            if (key == KeyEvent.VK_I) {
                dy = -this.speed;
                if(!direction.equals("U")){
                    imgC = 5;
                    direction = "U";
                }
                if (imgC == 5){
                    imgC = 0;
                    loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_uber.png");
                }
                else{
                    imgC += 1;
                    loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_uber1.png");
                }
            }

            if (key == KeyEvent.VK_K) {
                dy = this.speed;
                if(!direction.equals("D")){
                    imgC = 5;
                    direction = "D";
                }
                if (imgC == 5){
                    imgC = 0;
                    loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_unter.png");
                }
                else{
                    imgC += 1;
                    loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_unter1.png");
                }
            }
        }

    }

    public void fire() {
        Missile tempMissile = new Missile(x + width / 2, y + height / 2, this.direction);
        tempMissile.setParent(this.name);
        missiles.add(tempMissile);
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        if(this.isPlayerOne){
            if (key == KeyEvent.VK_A) {
                if(dx == -this.speed){
                    dx = 0;
                    loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_links.png");
                }

            }

            if (key == KeyEvent.VK_D) {
                if(dx == this.speed){
                    dx = 0;
                    loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_recht.png");
                }

            }

            if (key == KeyEvent.VK_W) {
                if(dy == -this.speed){
                    dy = 0;
                    loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_uber.png");
                }
            }

            if (key == KeyEvent.VK_S) {
                if(dy == this.speed){
                    dy = 0;
                    loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_unter.png");
                }
            }
        }
        else{
            if (key == KeyEvent.VK_J) {
                if(dx == -this.speed){
                    dx = 0;
                    loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_links.png");
                }

            }

            if (key == KeyEvent.VK_L) {
                if(dx == this.speed){
                    dx = 0;
                    loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_recht.png");
                }

            }

            if (key == KeyEvent.VK_I) {
                if(dy == -this.speed){
                    dy = 0;
                    loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_uber.png");
                }
            }

            if (key == KeyEvent.VK_K) {
                if(dy == this.speed){
                    dy = 0;
                    loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_unter.png");
                }
            }
        }

    }

    public String getName(){
        return this.name;
    }
}
