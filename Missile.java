import java.awt.Rectangle;

public class Missile extends Sprite {

    private final int BOARD_WIDTH = 800;
    private final int BOARD_HEIGHT = 800;
    private final int MISSILE_SPEED = 5;
    private String parent;
    private String direction;
    private Rectangle hitbox;
    private int damage;

    public Missile(int x, int y, String direction) {
        super(x, y);
        this.direction = direction;
        initMissile();
    }

    private void initMissile() {
        this.damage = 1;
        loadImage("resources/sprites/leo_figur/leo_missile"+this.direction+".png");
        getImageDimensions();
        this.hitbox = this.getBounds();
    }

    public Rectangle getHitbox(){
        this.hitbox = this.getBounds();
        return this.hitbox;
    }

    public void move() {
        if(this.direction.equals("R")){
            x += MISSILE_SPEED;
        }
        if(this.direction.equals("L")){
            x -= MISSILE_SPEED;
        }
        if(this.direction.equals("U")){
            y -= MISSILE_SPEED;
        }
        if(this.direction.equals("D")){
            y += MISSILE_SPEED;
        }



        if (x > BOARD_WIDTH) {
            visible = false;
        }
        if (x < 0) {
            visible = false;
        }
        if (y > BOARD_HEIGHT) {
            visible = false;
        }
        if (y < 0) {
            visible = false;
        }
    }

    public void setParent(String parent){
        this.parent = parent;
        if(parent.equals("kevin")){
            damage = 2;
        }
    }

    public String getParent(){
        return this.parent;
    }

    public int getDamage(){
        return this.damage;
    }

}
