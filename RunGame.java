import java.awt.EventQueue;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;


public class RunGame extends JFrame {
    public RunGame() {
        initSelect();
    }
    public RunGame(String char1, String char2) {
        initUI(char1, char2);
    }

    private void initSelect(){
        JPanel selectcontainer = new JPanel();
        MenschSelect select = new MenschSelect();
        selectcontainer.setPreferredSize(new Dimension(980, 834));
        select.setPreferredSize(new Dimension(980, 834));
        selectcontainer.add(select);
        pack();

        add(selectcontainer);
        setTitle("Choose Your Character!");
        setSize(980,834);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    private void initUI(String char1, String char2) {
        JPanel maincontainer = new JPanel();
        JPanel lowercontainer = new JPanel();

        Map map = new Map(char1, char2);
        Figur[] Figuren = map.getFiguren();

        HealthContainer hp1 = new HealthContainer(Figuren[0]);
        HealthContainer hp2 = new HealthContainer(Figuren[1]);

        map.addHealthContainer1(hp1);
        map.addHealthContainer2(hp2);

        map.setPreferredSize(new Dimension(980, 800));
        lowercontainer.setPreferredSize(new Dimension(980, 34));
        hp1.setPreferredSize(new Dimension(490 ,34));
        hp2.setPreferredSize(new Dimension(490,34));

        maincontainer.setLayout(new BoxLayout(maincontainer, BoxLayout.Y_AXIS));
        lowercontainer.setLayout(new BoxLayout(lowercontainer, BoxLayout.X_AXIS));

        lowercontainer.add(hp1); lowercontainer.add(hp2);
        maincontainer.add(map);
        maincontainer.add(lowercontainer);

        pack();
        add(maincontainer);
        setTitle("Bildungsreise Battle Royale");
        setSize(980,834);


        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            if(args.length == 0){
                RunGame ex = new RunGame();
                ex.setVisible(true);
            }
            else{
                String char1 = args[0];
                String char2 = args[1];
                RunGame ex = new RunGame(char1, char2);
                ex.setVisible(true);
            }

        });
    }
}
