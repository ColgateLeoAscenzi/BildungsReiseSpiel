import java.awt.EventQueue;
import javax.swing.JFrame;

public class RunGame extends JFrame {

    public RunGame() {

        initUI();
    }

    private void initUI() {

        add(new Map());

        setTitle("Map");
        setSize(800, 800);

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
