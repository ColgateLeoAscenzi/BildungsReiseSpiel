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


public class HealthContainer extends JPanel{
    public HealthContainer() {

        initMap();

    }

    private void initMap() {
        setFocusable(true);
        setBackground(Color.GREEN);

    }

}
