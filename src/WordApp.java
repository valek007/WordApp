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

            //-----Text Area-----------------------------

            add(myArea,BorderLayout.CENTER);

            //-----Menu Items---------------------------

            menuConfig("Arial","font","",0,0);
            menuConfig("Courier","font","",0,0);
            menuConfig("Verdana","font","",0,0);

            menuConfig("Bold","style","",0,0);
            menuConfig("Italic","style","",0,0);

            menuConfig("12","size","",0,0);
            menuConfig("16","size","",0,0);
            menuConfig("20","size","",0,0);
            menuConfig("24","size","",0,0);

        }

        public void menuConfig(String label, String menu, String word_font, int word_style, int word_size){

            JMenuItem menu_element = new JMenuItem(label);

            if(menu.equals("font")) fontWord.add(menu_element);
            else if(menu.equals("style")) styleWord.add(menu_element);
            else if(menu.equals("size")) sizeWord.add(menu_element);


        }
    }
}
