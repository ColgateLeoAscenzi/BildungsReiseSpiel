import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.io.IOException;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import java.lang.Runtime;



public class MenschSelect extends JPanel implements ActionListener {

        private final int SX1 = 200;
        private final int SY1 = 300;
        private final int SX2 = 600;
        private final int SY2 = 300;

        private FigurS Figur1S;
        private FigurS Figur2S;
        private FigurS[] Figuren;

        private boolean isStarted;

        public MenschSelect() {

            initMenschSelect();
        }

        private void initMenschSelect() {
            addKeyListener(new TAdapter());
            setFocusable(true);
            setBackground(Color.GRAY);
            setDoubleBuffered(true);
            Figuren = new FigurS[2];

            Figur1S = new FigurS(SX1, SY1, "leo", true);
            Figur2S = new FigurS(SX2, SY2, "dawson", false);
            Figuren[0] = Figur1S;
            Figuren[1] = Figur2S;

            Timer timer = new Timer(10, this);
            timer.start();

            this.isStarted = false;

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            doDrawing(g);

            Toolkit.getDefaultToolkit().sync();
        }

        private void doDrawing(Graphics g) {

            Graphics2D g2d = (Graphics2D) g;
            for(int i = 0; i < Figuren.length; i++){
                g2d.drawImage(Figuren[i].getImage(), Figuren[i].getX(), Figuren[i].getY(), this);
            }

            //ImageIcon ii = new ImageIcon("resources/backgrounds/"+this.mapName+".png");
            //Image image = ii.getImage();

            //g2d.drawImage(image, 0, 0, this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //Check for completeness

            if(Figuren[0].getIsLockedIn() && Figuren[1].getIsLockedIn()){
                //START GAME
                Figuren[0].setIsLockedIn(false);
                System.out.println("Start Game!");
                this.isStarted = true;
                try
                    {
                        Thread.sleep(1000);
                        StartGame();
                    }
                    catch(InterruptedException ex)
                    {
                        Thread.currentThread().interrupt();
                    }

            }
            //update Champions
            repaint();
        }

        private void StartGame(){
            Runtime rt = Runtime.getRuntime();
            try {
                rt.exec("java RunGame "+Figuren[0].getName()+" "+Figuren[1].getName());
                rt.exit(0);

            } catch (IOException IE) {
                // TODO Auto-generated catch block
                IE.printStackTrace();
            }
        }

        private class TAdapter extends KeyAdapter {

            @Override
            public void keyReleased(KeyEvent e) {
                Figur1S.keyReleased(e);
                Figur2S.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                Figur1S.keyPressed(e);
                Figur2S.keyPressed(e);
            }
        }

        public boolean getIsStarted(){
            return this.isStarted;
        }


}
