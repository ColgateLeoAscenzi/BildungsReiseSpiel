import java.awt.Rectangle;
import java.util.HashMap;

public class AOE extends Sprite {

    private String parent;
    private Rectangle hitbox;
    private int damage;

    public AOE(int x, int y, String parent) {
        super(x, y);
        this.parent = parent;
        initAOE();
    }

    private void initAOE() {
        loadImage("resources/sprites/"+this.parent+"_figur/"+this.parent+"_aoe.png");
        getImageDimensions();
        this.hitbox = this.getBounds();
        this.damage = 2;
    }

    public Rectangle getHitbox(){
        this.hitbox = this.getBounds();
        return this.hitbox;
    }

    public void setParent(String parent){
        //sets the parent of the AOE and their respective damage
        this.parent = parent;
    }

    public String getParent(){
        return this.parent;
    }

    public int getDamage(){
        return this.damage;
    }

}
