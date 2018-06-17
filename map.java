import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Map extends JPanel implements ActionListener {

    private Timer timer;
    private Figur figur;
    private final int DELAY = 10;


    public Map() {
        initMap();
        System.out.println("Created Board");
    }


private void initMap() {

    addKeyListener(new TAdapter());
    setFocusable(true);
    setBackground(Color.black);
    setDoubleBuffered(true);

    figur = new Figur();

    timer = new Timer(DELAY, this);
    timer.start();
}

@Override
public void paintComponent(Graphics g) {
    super.paintComponent(g);

    doDrawing(g);

    Toolkit.getDefaultToolkit().sync();
}

private void doDrawing(Graphics g) {

    Graphics2D g2d = (Graphics2D) g;

    g2d.drawImage(figur.getImage(), figur.getX(),
        figur.getY(), this);
}


private class TAdapter extends KeyAdapter {

    @Override
    public void keyReleased(KeyEvent e) {
        figur.keyReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        figur.keyPressed(e);
    }
}


    @Override
    public void actionPerformed(ActionEvent e) {

        step();
    }

    private void step() {

        figur.move();

        repaint(figur.getX()-1, figur.getY()-1,
                figur.getWidth()+2, figur.getHeight()+2);
    }


}
