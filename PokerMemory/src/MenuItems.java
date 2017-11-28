import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuItems extends MemoryFrame{
	   
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
	                    if(e.getActionCommand().equals("Flush Level")) newGame("flush");
	                } catch (IOException e2) {
	                    e2.printStackTrace(); throw new RuntimeException("IO ERROR");
	                }
	            }
	        };  
	        
	        JMenuItem flushLevelMenuItem = new JMenuItem("Flush Level");
	        flushLevelMenuItem.addActionListener(menuHandler);
	        memoryMenu.add(flushLevelMenuItem);
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

        if(difficultyMode.equalsIgnoreCase("flush")) {
            this.setGameLevel(new FlushLevel(this.getTurnCounterLabel(), this));
            this.getLevelDescriptionLabel().setText("Flush Level");
            this.getTurnCounterLabel().reset();
       
            // clear out the content pane (removes turn counter label and card field)
            BorderLayout bl  = (BorderLayout) this.getContentPane().getLayout();
            this.getContentPane().remove(bl.getLayoutComponent(BorderLayout.CENTER));
            this.getContentPane().add(showCardDeck(), BorderLayout.CENTER);

            // show the window (in case this is the first game)
            this.setVisible(true);
        }
        else {
            super.newGame(difficultyMode);
            
}
}

	private void setGameLevel(FlushLevel flushLevel) {
		// TODO Auto-generated method stub
		
	}
}