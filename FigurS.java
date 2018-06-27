import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class FigurS extends Sprite {
    private String name;
    private boolean isPlayerOne;
    private boolean isLockedIn;
    private String[] players;
    private int currI;
    private boolean pleaseStart;

    public FigurS(int x, int y, String name, boolean isPlayerOne) {
        super(x, y);
        this.pleaseStart = false;
        this.players = new String[]{"leo","dawson","gabby","eli","alex","michael","matthew","elizabeth","kevin","jack"};
        if(isPlayerOne){
            currI = 0;
        }
        else{
            currI = 1;
        }
        this.isPlayerOne = isPlayerOne;
        this.isLockedIn = false;
        initFigur(name);
    }

    private void initFigur(String name) {
        this.name = name;
        System.out.println(name);
        loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_R.png");
        getImageDimensions();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(this.isPlayerOne){
            if (key == KeyEvent.VK_E) {
                System.out.println(this.name+" is locked in!");
                loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_S.png");
                this.isLockedIn = true;
            }
            if(!this.isLockedIn){
                if(key == KeyEvent.VK_D){
                    if(currI < 9){
                        currI += 1;
                    }
                    else{
                        currI = 0;
                    }
                    this.name = players[currI];
                    loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_R.png");

                    System.out.println("Switching Right to: "+this.name);

                }
                if(key == KeyEvent.VK_A){
                    if(currI > 0){
                        currI -= 1;
                    }
                    else{
                        currI = 9;
                    }
                    this.name = players[currI];
                    loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_R.png");

                    System.out.println("Switching Left to: "+this.name);
                }

            }

            //START GAME
            if(key == KeyEvent.VK_SPACE){
                this.pleaseStart = true;
            }
        }
        else{
            if (key == KeyEvent.VK_O) {
                System.out.println(this.name+" is locked in!");
                loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_S.png");
                this.isLockedIn = true;
            }
            if(!this.isLockedIn){
                if(key == KeyEvent.VK_L){
                    if(currI < 9){
                        currI += 1;
                    }
                    else{
                        currI = 0;
                    }
                    this.name = players[currI];
                    loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_R.png");

                    System.out.println("Switching Right to: "+this.name);
                }
                if(key == KeyEvent.VK_J){
                    if(currI > 0){
                        currI -= 1;
                    }
                    else{
                        currI = 9;
                    }
                    this.name = players[currI];
                    loadImage("resources/sprites/"+this.name+"_figur/"+this.name+"_R.png");

                    System.out.println("Switching Left to: "+this.name);
                }
            }

        }


    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        //Do nothing for now
    }

    public String getName(){
        return this.name;
    }

    public boolean getIsPlayerOne(){
        return this.isPlayerOne;
    }

    public boolean getIsLockedIn(){
        return this.isLockedIn;
    }

    public void setIsLockedIn(boolean iLI){
        this.isLockedIn = iLI;
    }
    public boolean getPleaseStart(){
        return this.pleaseStart;
    }

}
