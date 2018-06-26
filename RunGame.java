import java.awt.EventQueue;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class RunGame extends JFrame {

    public RunGame() {

        initUI();
    }

    private void initUI() {
        JPanel container = new JPanel();
        JPanel hp = new HealthContainer();
        JPanel map = new Map();

        map.setPreferredSize(new Dimension(800, 790));
        hp.setPreferredSize(new Dimension(800,10));

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(map);
        container.add(hp);

        pack();
        add(container);
        setTitle("Bildungsreise Battle Royale");
        setSize(800,900);


        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            RunGame ex = new RunGame();
            ex.setVisible(true);
        });
    }
}
