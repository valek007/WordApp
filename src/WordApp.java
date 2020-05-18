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
        private Font letters;

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

            menuConfig("Arial","font","Arial",9,10);
            menuConfig("Comic Sans","font","Comic Sans MS",9,10);
            menuConfig("Verdana","font","Verdana",9,10);

            menuConfig("Bold","style","",Font.BOLD,10);
            menuConfig("Italic","style","",Font.ITALIC,10);

            menuConfig("12","size","",9,12);
            menuConfig("16","size","",9,16);
            menuConfig("20","size","",9,20);
            menuConfig("24","size","",9,24);

        }

        public void menuConfig(String label, String menu, String word_font, int word_style, int word_size){

            JMenuItem menu_element = new JMenuItem(label);

            if(menu.equals("font")) fontWord.add(menu_element);
            else if(menu.equals("style")) styleWord.add(menu_element);
            else if(menu.equals("size")) sizeWord.add(menu_element);

            menu_element.addActionListener(new Events_Manager(label,word_font,word_style,word_size));
        }

        private class Events_Manager implements ActionListener{

            private String menu_element, word_font;
            private int word_style, word_size;

            public Events_Manager(String menu_element, String word_font, int word_style, int word_size) {
                this.menu_element = menu_element;
                this.word_font = word_font;
                this.word_style = word_style;
                this.word_size = word_size;
            }

            @Override
            public void actionPerformed(ActionEvent e) {

                letters = myArea.getFont();

                if(menu_element=="Arial"||menu_element=="Comic Sans"||menu_element=="Verdana"){
                    word_style=letters.getStyle();
                    word_size=letters.getSize();
                }else if(menu_element=="Bold"||menu_element=="Italic"){
                    if(letters.getStyle()==1||letters.getStyle()==2){
                        word_style=3;
                    }
                    word_font=letters.getFontName();
                    word_size=letters.getSize();
                }else{
                    word_font=letters.getFontName();
                    word_style=letters.getStyle();
                }


                myArea.setFont(new Font(word_font,word_style,word_size));
            }
        }
    }
}
