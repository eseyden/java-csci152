import javax.swing.*;
public class SwingDemo {
    SwingDemo() {
        JFrame jfrm = new JFrame("A Simple Swing App");

        jfrm.setSize(276, 100);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel jlab = new JLabel("Swing means GUIs");
        jlab.setHorizontalAlignment(JLabel.CENTER);
        jfrm.add(jlab);

        jfrm.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingDemo();
            }
        });
    }
}
