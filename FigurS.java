import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class FigurS extends Sprite {
    private String name;

    public FigurS(int x, int y, String name) {
        super(x, y);
        initFigur(name);
    }

    private void initFigur(String name) {
        this.name = name;
        loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_unter.png");
        getImageDimensions();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_E) {
            System.out.println("Change to next player");
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        //Do nothing for now
    }

    public String getName(){
        return this.name;
    }

}
