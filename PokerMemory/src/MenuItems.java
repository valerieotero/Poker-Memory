import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Scrollbar;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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

        if(difficultyMode.equalsIgnoreCase("easy")) {
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
   
//    
//    public class ScrollPane
//    {
//    	
//    }
  
    public void showInstructions()
    {
    	//Scrollbar redSlider = new Scrollbar(Scrollbar.VERTICAL, 0, 1, 0, 255);
    	 //add(redSlider);
    	//In a container that uses a BorderLayout:
    	//JTextArea textArea = new JTextArea(5, 30);
    	
    	
        dprintln("MemoryGame.showInstructions()");
        
        
//    	JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//    	
//    	textArea.setText(all);
//    	textArea.setLineWrap(true);
//    	
//    	setPreferredSize(new Dimension(450, 110));
//    	
//    	add(scrollPane, BorderLayout.CENTER);
//        
        
        
        final String HOWTOPLAYTEXT = "How To Play\r\n"
                + "\r\n"
               
                + "EQUAL PAIR LEVEL\r\n"
                + "The game consists of 8 pairs of cards. At the start of the game every card is face down. The object is to find all the pairs and turn them face up.\r\n"
                + "Click on two cards to turn them face up. If the cards are the same, then you have discovered a pair. he pair will remain turned up. \r\n"
                + " If the cards are different, they will flip back over autmatically after a short delay.\r\n"
                + " Continue flipping cards until you have discovered all of the pairs.The game is won when all cards are face up.\r\n"
                + "\r\n"
               
                + "SAME RANK TRIO LEVEL\r\n"
                + "The game consists of a grid of distinct cards. At the start of the game every card is face down. The object is to find all the trios of cards, \r\n"                      
                + "with the same rank and turn them face up. Click on three cards to turn them face up. If the cards have the same rank, then you have discovered a trio. \r\n"
                + "The trio will remain turned up. If the cards are different, they will flip back over autmatically after a short delay.\r\n"              
                + "\r\n"
               
                + "FLUSH LEVEL\r\n"
                + "The game consists of a grid of distinct cards. At the start of the game every card is face down. \r\n"
                + "The object is to find five cards with the same suit and turn them face up. \r\n"               
                + "This would be a Flush Hand. Click on five cards to turn them face up. If the cards have the same suit, then you have discovered a flush. \r\n"
                + "The flush hand will remain turned up. If the cards are different, they will flip back over autmatically after a short delay.\r\n"
                + "Continue flipping cards until you have discovered all of the suits. The game is won when all cards are face up.\r\n" 
                + "\r\n"
                
                + "STRAIGHT LEVEL\r\n"
                + "The game consists of a grid of distinct cards. At the start of the game every card is face down.\r\n"
                + "The object is to find five cards that can be rearranged in a sequence of ascending order and turn them face up.\r\n "
                + "This would be a Straight Hand. Click on five cards to turn them face up. \r\n\""
                + "If the cards can be rearranged in a sequence of ascending order, then you have discovered a straight. The straight hand will remain turned up \r\n"
                + "If the cards are different, they will flip back over autmatically after a short delay.\r\n" 
                + " Continue flipping cards until you have discovered all of the straights. The game is won when all cards are face up\r\n"
                + "\r\n"
               
                + "COMBO LEVEL\r\n"
                + "The game consists of a grid of distinct cards. At the start of the game every card is face down.\r\n"
                + "The object is to find five cards that contain either FLUSH, STRAIGHT OR an ALL REDS and turn them face up!\r\n"
                + "The new level(All Reds) consists of finding five cards that have the same color, in this case: Red. Click on five cards to turn them face up\r\n"
                + "You can either accept the current hand and be evaluaed by the three options or pass \r\n\""
        		+ "The chosen hand will remain turned up. If the cards are different, they will flip back over autmatically, after a short delay.\r\n" 
        		+ "Continue flipping cards until you have discovered all of the possible options.The game is won when all cards are face up.\r\n";
        		
        
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