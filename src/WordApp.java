import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordApp {

    public static void main(String[] args) {

        MyFrame frame = new MyFrame();
    }

    static class MyFrame extends JFrame {

        private MyPanel panel = new MyPanel();

        public MyFrame(){

            setTitle("Word Processor");
            setBounds(600,200,500,400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            add(panel);
            setVisible(true);

        }
    }

    static class MyPanel extends JPanel{

        //---------Menu Bar-------------------------

        private JPanel menuPanel = new JPanel();
        private JMenuBar menuBar = new JMenuBar();
        private JMenu fontWord = new JMenu("Font");
        private JMenu styleWord = new JMenu("Style");
        private JMenu sizeWord = new JMenu("Size");

        //-------------------------------------------

        private JMenuItem arial = new JMenuItem("Arial");
        private JMenuItem courier = new JMenuItem("Courier");
        private JMenuItem verdana = new JMenuItem("Verdana");

        private JMenuItem bold = new JMenuItem("Bold");
        private JMenuItem italic = new JMenuItem("Italic");

        private JMenuItem size_12 = new JMenuItem("12");
        private JMenuItem size_16 = new JMenuItem("16");
        private JMenuItem size_20 = new JMenuItem("20");
        private JMenuItem size_24 = new JMenuItem("24");

        //--------Text Area---------------------------

        private JTextPane myArea = new JTextPane();

        public MyPanel() {

            setLayout(new BorderLayout());

            //----Menu Bar------------------------------

            menuBar.add(fontWord);
            menuBar.add(styleWord);
            menuBar.add(sizeWord);
            menuPanel.add(menuBar);
            add(menuPanel,BorderLayout.NORTH);

            //-------------------------------------------

            fontWord.add(arial);
            arial.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    myArea.setFont(new Font("Arial",Font.PLAIN,12));
                }
            });
            fontWord.add(courier);
            courier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    myArea.setFont(new Font("Courier",Font.PLAIN,12));
                }
            });
            fontWord.add(verdana);
            verdana.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    myArea.setFont(new Font("Verdana",Font.PLAIN,12));
                }
            });

            styleWord.add(bold);
            styleWord.add(italic);

            sizeWord.add(size_12);
            sizeWord.add(size_16);
            sizeWord.add(size_20);
            sizeWord.add(size_24);

            //-----Text Area-----------------------------

            add(myArea,BorderLayout.CENTER);

        }
    }
}
