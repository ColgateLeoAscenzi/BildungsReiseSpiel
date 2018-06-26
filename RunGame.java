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
        JPanel maincontainer = new JPanel();
        JPanel lowercontainer = new JPanel();

        Map map = new Map();
        Figur[] Figuren = map.getFiguren();

        HealthContainer hp1 = new HealthContainer(Figuren[0]);
        HealthContainer hp2 = new HealthContainer(Figuren[1]);

        map.setPreferredSize(new Dimension(800, 800));
        lowercontainer.setPreferredSize(new Dimension(800, 32));
        hp1.setPreferredSize(new Dimension(400,32));
        hp2.setPreferredSize(new Dimension(400,32));

        maincontainer.setLayout(new BoxLayout(maincontainer, BoxLayout.Y_AXIS));
        lowercontainer.setLayout(new BoxLayout(lowercontainer, BoxLayout.X_AXIS));

        lowercontainer.add(hp1); lowercontainer.add(hp2);
        maincontainer.add(map);
        maincontainer.add(lowercontainer);

        pack();
        add(maincontainer);
        setTitle("Bildungsreise Battle Royale");
        setSize(800,832);


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
