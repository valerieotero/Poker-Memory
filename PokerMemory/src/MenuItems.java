import java.awt.BorderLayout;
import java.awt.Component;
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
        private Component mainFrame;

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
       
        JMenu helpMenu = menuBar.getMenu(1);
        helpMenu.removeAll();
        menuBar.add(helpMenu);

        ActionListener menuHandler = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dprintln("actionPerformed " + e.getActionCommand());
                try {
                    if(e.getActionCommand().equals("Easy Level")) newGame("easy");
                    else if(e.getActionCommand().equals("Equal Pair Level")) newGame("equalpair");
                    else if(e.getActionCommand().equals("Same Rank Trio Level")) newGame("ranktrio");
                    else if(e.getActionCommand().equals("Flush Level")) newGame("flushlevel");
                    else if(e.getActionCommand().equals("Straight Level")) newGame("StraightLevel");                    
                    else if(e.getActionCommand().equals("Combo Level")) newGame("ComboLevel");                    
                    else if(e.getActionCommand().equals("How To Play")) showInstructions();
                    else if(e.getActionCommand().equals("About")) showAbout();
                    else if(e.getActionCommand().equals("Exit")) System.exit(0);
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
            
        JMenuItem mntmHowToPlay = new JMenuItem("How To Play");
        mntmHowToPlay.addActionListener(menuHandler);
        helpMenu.add(mntmHowToPlay);
       
        JMenuItem mntmAbout = new JMenuItem("About");
        mntmAbout.addActionListener(menuHandler);
        helpMenu.add(mntmAbout);
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

        else if (difficultyMode.equalsIgnoreCase("comboLevel")) {
              this.setGameLevel(new ComboLevel(this.getTurnCounterLabel(), this));
            this.getLevelDescriptionLabel().setText("Combo Level");
            this.getTurnCounterLabel().reset();
        }
        
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
   
  
    public void showInstructions()
    {
        dprintln("MemoryGame.showInstructions()");
        final String HOWTOPLAYTEXT = "How To Play\r\n"
                + "\r\n"
               
                + "EQUAL PAIR Level\r\n"
                + "The game consists of 8 pairs of cards.  At the start of the game,\r\n"
                + "every card is face down.  The object is to find all the pairs and\r\n"
                + "turn them face up.\r\n"
                + "\r\n"
                + "Click on two cards to turn them face up. If the cards are the \r\n"
                + "same, then you have discovered a pair.  The pair will remain\r\n"
                + "turned up.  If the cards are different, they will flip back\r\n"
                + "over automatically after a short delay.  Continue flipping\r\n"
                + "cards until you have discovered all of the pairs.  The game\r\n"
                + "is won when all cards are face up.\r\n"
                + "\r\n"
               
                + "SAME RANK TRIO Level\r\n"
                + "The game consists of a grid of distinct cards.  At the start of the game,\r\n"
                + "every card is face down.  The object is to find all the trios \r\n"
                + "of cards with the same rank and turn them face up.\r\n"
                + "\r\n"
                + "Click on three cards to turn them face up. If the cards have the \r\n"
                + "same rank, then you have discovered a trio.  The trio will remain\r\n"
                + "turned up.  If the cards are different, they will flip back\r\n"
                + "over automatically after a short delay.  Continue flipping\r\n"
                + "cards until you have discovered all of the pairs.  The game\r\n"
                + "is won when all cards are face up.\r\n"
                + "\r\n"
                + "Each time you flip two cards up, the turn counter will\r\n"
                + "increase.  Try to win the game in the fewest number of turns!\r\n"
               
                + "FLUSH LEVEL\r\n"
                + "The game consists of a grid of distinct cards.\r\n"
                + "Find five cards with the same suit. Ranks can be different\r\n"
               
                + "STRAIGHT LEVEL\r\n"
                + "Find five cards that can be rearranged in ascending order in a sequence\r\n "
                + "with 2 or more Suits\r\n"
               
                + "COMBO LEVEL\r\n"
                + "Find five cards that contain either PAIRS, TRIOS, FLUSH, STRAIGHT, OR a FULL HOUSE!\r\n"
                + "You can either accept the current hand/s or pass.";
       
        JOptionPane.showMessageDialog(this.mainFrame, HOWTOPLAYTEXT, "How To Play", JOptionPane.PLAIN_MESSAGE);
    }
    private void showAbout()
    {
        dprintln("MemoryGame.showAbout()");
        final String ABOUTTEXT = "Game Customized at UPRM. Originally written by Mike Leonhard";

        JOptionPane.showMessageDialog(this, ABOUTTEXT
                , "About Memory Game", JOptionPane.PLAIN_MESSAGE);
    }

}