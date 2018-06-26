import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;



public class MenschSelect extends JPanel implements ActionListener {

        private Figur Figur1S;
        private Figur Figur2S;

        public MenschSelect() {

            initMenschSelect();
        }

        private void initMenschSelect() {
            addKeyListener(new TAdapter());
            setFocusable(true);
            setBackground(Color.GRAY);
            setDoubleBuffered(true);

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            doDrawing(g);

            Toolkit.getDefaultToolkit().sync();
        }

        private void doDrawing(Graphics g) {

            Graphics2D g2d = (Graphics2D) g;

            //ImageIcon ii = new ImageIcon("resources/backgrounds/"+this.mapName+".png");
            //Image image = ii.getImage();

            //g2d.drawImage(image, 0, 0, this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            //update Champions
            repaint();
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


}
