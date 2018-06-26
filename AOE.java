import java.awt.Rectangle;
import java.util.HashMap;

public class AOE extends Sprite {

    private String parent;
    private Rectangle hitbox;
    private int damage;

    public AOE(int x, int y) {
        super(x, y);
        initAOE();
    }

    private void initAOE() {
        loadImage("resources/sprites/michael_figur/michael_aoe.png");
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
