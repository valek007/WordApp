import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class WordApp {

    public static void main(String[] args) {

        MyFrame frame = new MyFrame();
    }

    static class MyFrame extends JFrame {

        private MyPanel panel = new MyPanel();
        private Toolkit myScreen = Toolkit.getDefaultToolkit();
        private Image iconImage = myScreen.getImage("out/production/WordProcessor/resources/pencil.png");

        public MyFrame(){

            setTitle("Word Processor");
            setIconImage(iconImage);
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
        private JPopupMenu popMenu = new JPopupMenu();
        private JToolBar toolBar = new JToolBar();
        private static final String RESOURCES = "out/production/WordProcessor/resources/";

        public MyPanel() {

            setLayout(new BorderLayout());

            getRadioElements();

            //----Menu Bar------------------------------
            menuBar.add(fontWord);
            menuBar.add(styleWord);
            menuBar.add(sizeWord);
            menuPanel.add(menuBar);
            add(menuPanel,BorderLayout.NORTH);

            //-----Text Area-----------------------------

            add(myArea,BorderLayout.CENTER);
            myArea.setComponentPopupMenu(popMenu);

            configToolBar("bold.png").addActionListener(new StyledEditorKit.BoldAction());
            configToolBar("italic.png").addActionListener(new StyledEditorKit.ItalicAction());
            configToolBar("underlined.png").addActionListener(new StyledEditorKit.UnderlineAction());
            toolBar.addSeparator();
            configToolBar("red.png").addActionListener(new StyledEditorKit.ForegroundAction("Red",Color.RED));
            configToolBar("yellow.png").addActionListener(new StyledEditorKit.ForegroundAction("Yellow",Color.YELLOW));
            configToolBar("blue.png").addActionListener(new StyledEditorKit.ForegroundAction("Blue",Color.BLUE));
            toolBar.addSeparator();
            configToolBar("right-text.png").addActionListener(new StyledEditorKit.AlignmentAction("left-text",2));
            configToolBar("left-text.png").addActionListener(new StyledEditorKit.AlignmentAction("center-text",0));
            configToolBar("center-text.png").addActionListener(new StyledEditorKit.AlignmentAction("right-text",1));
            configToolBar("justified-text.png").addActionListener(new StyledEditorKit.AlignmentAction("justiefied-text",3));

            toolBar.setOrientation(1);
            add(toolBar, BorderLayout.WEST);

            //-----Menu Items---------------------------

            menuConfig("Arial","font","Arial",9,10,"");
            menuConfig("Comic Sans","font","Comic Sans MS",9,10,"");
            menuConfig("Verdana","font","Verdana",9,10,"");

            menuConfig("Bold","style","",Font.BOLD,10,"out/production/WordProcessor/resources/bold.png");
            menuConfig("Italic","style","",Font.ITALIC,10,"out/production/WordProcessor/resources/italic.png");

            menuConfig("Cut","edit","",9,12,"out/production/WordProcessor/resources/cut.png");
            menuConfig("Copy","edit","",9,12,"out/production/WordProcessor/resources/copy.png");
            menuConfig("Paste","edit","",9,12,"out/production/WordProcessor/resources/paste.png");

        }
        public JButton configToolBar(String icon_url){

            return (JButton) toolBar.add(new JButton(new ImageIcon(RESOURCES+icon_url)));
        }

        public void menuConfig(String label, String menu, String word_font, int word_style, int word_size, String icon){

                JCheckBoxMenuItem menu_check_element = new JCheckBoxMenuItem(label, new ImageIcon(icon));
                JMenuItem menu_element = new JMenuItem(label, new ImageIcon(icon));

            if(menu.equals("font")){
                fontWord.add(menu_element);
                menu_element.addActionListener(new StyledEditorKit.FontFamilyAction(menu,word_font));
            }
            else if(menu.equals("style")){
                styleWord.add(menu_check_element);
                if(word_style==Font.BOLD){
                    menu_check_element.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.CTRL_DOWN_MASK));
                    menu_check_element.addActionListener(new StyledEditorKit.BoldAction());
                }
                else{
                    menu_check_element.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,InputEvent.CTRL_DOWN_MASK));
                    menu_check_element.addActionListener(new StyledEditorKit.ItalicAction());
                }
            }
            else if(menu.equals("edit")){
                popMenu.add(menu_element);
                if(label.equals("Cut")) menu_element.addActionListener(new DefaultEditorKit.CutAction());
                else if(label.equals("Copy")) menu_element.addActionListener(new DefaultEditorKit.CopyAction());
                else if(label.equals("Paste")) menu_element.addActionListener(new DefaultEditorKit.PasteAction());
            }
        }

        private void getRadioElements(){

            ButtonGroup size_group = new ButtonGroup();
            String name = "";

            for(int i=12;i<25;i=i+4){

                JRadioButtonMenuItem num = new JRadioButtonMenuItem(name+i);
                num.addActionListener(new StyledEditorKit.FontSizeAction("size",i));
                size_group.add(num);
                sizeWord.add(num);
            }
        }
    }
}
