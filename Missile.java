import java.awt.Rectangle;
import java.util.HashMap;

public class Missile extends Sprite {

    private final int BOARD_WIDTH = 800;
    private final int BOARD_HEIGHT = 800;
    private final int MISSILE_SPEED = 5;
    private String parent;
    private String direction;
    private Rectangle hitbox;
    private int damage;
    private HashMap<String, Integer> parentDamage;

    public Missile(int x, int y, String direction, String parent) {
        super(x, y);
        this.parent = parent;
        this.direction = direction;
        initMissile();
    }

    private void initMissile() {
        this.damage = 1;
        loadImage("resources/sprites/"+this.parent+"_figur/"+this.parent+"_missile"+this.direction+".png");
        getImageDimensions();
        this.hitbox = this.getBounds();
        this.parentDamage = new HashMap<String, Integer>();
        //list of peoples names in a string
        String[] ppN = new String[]{"leo","kevin","gabby","eli","alex","michael","matthew","elizabeth","dawson","jack"};
        //list of the damages per each person
        int[] ppD = new int[]{2, 2, 3, 1, 2, 2, 2, 3, 4, 5};
        for(int i = 0; i < ppN.length; i++){
            this.parentDamage.put(ppN[i],ppD[i]);
        }
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
        //sets the parent of the missile and their respective damage
        this.parent = parent;
        damage = this.parentDamage.get(parent);
    }

    public String getParent(){
        return this.parent;
    }

    public int getDamage(){
        return this.damage;
    }

}
