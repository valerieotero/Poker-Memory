import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class MenuItems extends MemoryFrame {

        private static final long serialVersionUID = 1L;

        public MenuItems() {
        super();
        JMenuBar menuBar = this.getJMenuBar();
        JMenu memoryMenu = null;
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            if (menuBar.getMenu(i).getText().equals("Memory")) {
                memoryMenu = menuBar.getMenu(i);
                break;
            }
        }

        ActionListener menuHandler = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if(e.getActionCommand().equals("Flush Level")) newGame("FlushLevel");
                    else if(e.getActionCommand().equals("Straight Level")) newGame("StraightLevel");
                    else if(e.getActionCommand().equals("Combo Level")) newGame("ComboLevel");
                } catch (IOException e2) {
                    e2.printStackTrace(); throw new RuntimeException("IO ERROR");
                }
            }
        };

        JMenuItem flushLevelMenuItem = new JMenuItem("Flush Level");
        flushLevelMenuItem.addActionListener(menuHandler);
        memoryMenu.add(flushLevelMenuItem);

        JMenuItem straightLevelMenuItem = new JMenuItem("Straight Level");
        straightLevelMenuItem.addActionListener(menuHandler);
        memoryMenu.add(straightLevelMenuItem);

        JMenuItem comboLevelMenuItem = new JMenuItem("Combo Level");
        comboLevelMenuItem.addActionListener(menuHandler);
        memoryMenu.add(comboLevelMenuItem);
    }

    /**
     * Prepares a new game (first game or non-first game)
     * @throws IOException
     */
    public void newGame(String difficultyMode) throws IOException
    {
        // Reset the turn counter label
        this.getTurnCounterLabel().reset();

        // make a new card field with cards, and add it to the window

        if(difficultyMode.equalsIgnoreCase("easyLevel")) {
            this.setGameLevel(new EasyLevel(this.getTurnCounterLabel(), this));
            this.getLevelDescriptionLabel().setText("Easy Level");
            this.getTurnCounterLabel().reset();

          
        }
        else if(difficultyMode.equalsIgnoreCase("equalpair")){
                this.setGameLevel(new EqualPairLevelWithScore(this.getTurnCounterLabel(), this));
            this.getLevelDescriptionLabel().setText("Equal Pair Score");
            this.getTurnCounterLabel().reset();

           
        }
        else if(difficultyMode.equalsIgnoreCase("rankTrio")){
                this.setGameLevel(new RankTrioLevelWithScore(this.getTurnCounterLabel(), this));
            this.getLevelDescriptionLabel().setText("Rank Trio Score");
            this.getTurnCounterLabel().reset();

           
        }
        else if (difficultyMode.equalsIgnoreCase("flushLevel")) {
                this.setGameLevel(new FlushLevel(this.getTurnCounterLabel(), this));
            this.getLevelDescriptionLabel().setText("Flush Level");
            this.getTurnCounterLabel().reset();

           
        }
        else if (difficultyMode.equalsIgnoreCase("straightLevel")) {
              this.setGameLevel(new StraightLevel(this.getTurnCounterLabel(), this));
            this.getLevelDescriptionLabel().setText("Straight Level");
            this.getTurnCounterLabel().reset();

           
        }
//        else if (difficultyMode.equalsIgnoreCase("comboLevel")) {
//              this.setGameLevel(new ComboLevel(this.getTurnCounterLabel(), this));
//            this.getLevelDescriptionLabel().setText("Combo Level");
//            this.getTurnCounterLabel().reset();
//
//           }
        
        else {
            super.newGame(difficultyMode);
        }
     // clear out the content pane (removes turn counter label and card field)
        BorderLayout bl  = (BorderLayout) this.getContentPane().getLayout();
        this.getContentPane().remove(bl.getLayoutComponent(BorderLayout.CENTER));
        this.getContentPane().add(showCardDeck(), BorderLayout.CENTER);

        // show the window (in case this is the first game)
        this.setVisible(true);
    }



}