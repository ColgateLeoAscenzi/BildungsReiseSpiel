import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class Figur extends Sprite {

    private int dx;
    private int dy;
    private int speed;
    private int health;
    private int oDamage;
    private int damage;
    private int mRange;
    private int aRange;
    private int cdTime;
    private String name;
    private List<Missile> missiles;
    private AOE AOE;
    private int imgC;
    private String direction;
    private long cooldown;
    private boolean isPlayerOne;
    private boolean isMelee;
    private Rectangle hitbox;
    private String fClass;

    public Figur(int x, int y, String name, boolean isPlayerOne) {
        super(x, y);
        this.isPlayerOne = isPlayerOne;
        initFigur(name);
    }

    private void initFigur(String name) {
        //handles cooldowns
        this.cooldown = System.currentTimeMillis();
        this.isMelee = false;
        //basic class assignments
        if(name.equals("leo") || name.equals("kevin") || name.equals("eli") || name.equals("gabby") || name.equals("alex") || name.equals("jack")){
            this.fClass = "ranged";
            this.speed = 2;
            this.health = 10;
            this.damage = 2;
            this.aRange = 0;
            this.mRange = 0;
            this.cdTime = 300;
        }
        else if(name.equals("matthew") || name.equals("michael")){
            this.fClass = "aoe";
            this.speed = 3;
            this.health = 10;
            this.damage = 1;
            this.aRange = 30;
            this.mRange = 0;
            this.cdTime = 200;
        }
        else if(name.equals("elizabeth") || name.equals("dawson")){
            this.fClass = "melee";
            this.speed = 2;
            this.health = 15;
            this.damage = 3;
            this.aRange = 0;
            this.mRange = 12;
            this.cdTime = 5;
        }

        //handles specific cases
        if(name.equals("gabby")){
            this.damage = 3;
            this.health = 8;
            this.cdTime = 600;
        }
        if(name.equals("eli")){
            this.speed = 4;
            this.damage = 1;
            this.health = 6;
        }
        if(name.equals("matthew")){
            this.speed = 3;
        }
        if(name.equals("elizabeth")){
            this.health = 14;
            this.damage = 3;
        }
        if(name.equals("dawson")){
            this.mRange = 20;
            this.damage = 3;
            this.health = 12;
        }

        if(name.equals("jack")){
            this.health = 15;
            this.speed = 1;
            this.damage = 5;
        }

        this.oDamage = this.damage;
        //starting direction
        this.direction = "R";

        //handles name
        this.name = name;

        //counts direction
        imgC = 0;

        //holds missiles if ranged
        missiles = new ArrayList<>();

        AOE = null;

        //sets up image and gets dimensions and hitboxes
        loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_recht.png");
        getImageDimensions();
        this.hitbox = this.getBounds();
    }

    public Rectangle getHitbox(){
        this.hitbox = this.getBounds();
        return this.hitbox;
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public List<Missile> getMissiles() {
        return missiles;
    }

    public AOE getAOE(){
        return AOE;
    }

    public boolean getMelee(){
        return isMelee;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        if(this.isPlayerOne){
            if (key == KeyEvent.VK_E) {
                if((System.currentTimeMillis()) - this.cooldown > this.cdTime){
                    if(this.fClass.equals("ranged")){
                        fire();
                    }
                    else if(this.fClass.equals("aoe")){
                        burst();
                    }
                    else{
                        strike();
                    }
                    this.cooldown = System.currentTimeMillis();
                }

            }

            if (key == KeyEvent.VK_A) {
                this.AOE = null;
                this.damage = this.oDamage;
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
                this.AOE = null;
                this.damage = this.oDamage;
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
                this.AOE = null;
                this.damage = this.oDamage;
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
                this.AOE = null;
                this.damage = this.oDamage;
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

        //PLAYER TWOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
        else{
            if (key == KeyEvent.VK_O) {
                if((System.currentTimeMillis()) - this.cooldown > this.cdTime){
                    if(this.fClass.equals("ranged")){
                        fire();
                    }
                    else if(this.fClass.equals("aoe")){
                        burst();
                    }
                    else{
                        strike();
                    }
                    this.cooldown = System.currentTimeMillis();
                }

            }

            if (key == KeyEvent.VK_J) {
                this.AOE = null;
                this.damage = this.oDamage;
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
                this.AOE = null;
                this.damage = this.oDamage;
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
                this.AOE = null;
                this.damage = this.oDamage;
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
                this.AOE = null;
                this.damage = this.oDamage;
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
        Missile tempMissile = new Missile(x + width / 2, y + height / 2, this.direction, this.name);
        missiles.add(tempMissile);
    }

    public void burst(){
        AOE tempAOE = new AOE(x-width/4, y-width/4, this.name);
        this.AOE = tempAOE;
        loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_burst.png");

    }

    public void strike(){
        this.isMelee = true;
        if(this.direction.equals("R")){
            loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_recht2.png");
        }
        if(this.direction.equals("L")){
            loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_links2.png");
        }
        if(this.direction.equals("D")){
            loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_unter2.png");
        }
        if(this.direction.equals("U")){
            loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_uber2.png");
        }

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

    public void setHealth(int nHealth){
        this.health = nHealth;
    }

    public int getHealth(){
        return this.health;
    }

    public int getDamage(){
        return this.damage;
    }

    public String getfClass(){
        return this.fClass;
    }

    public void setAOE(AOE aoe){
        this.AOE = aoe;
    }

    public void setMelee(boolean isMelee){
        this.isMelee = isMelee;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }
}
