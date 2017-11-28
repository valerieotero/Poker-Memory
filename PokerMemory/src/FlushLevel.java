import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class FlushLevel extends RankTrioLevel {

	protected FlushLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(validTurnTime, mainFrame);
		this.getTurnsTakenCounter().setDifficultyModeLabel("flush");
		this.setCardsPerRow(1);
		this.setRowsPerGrid(1);
		this.setCardsToTurnUp(5);
		this.setTotalUniqueCards(this.getRowsPerGrid() * this.getCardsPerRow());
	}
	
	@Override
	protected void makeDeck() {
	
	//back card
	ImageIcon backIcon = this.getCardIcons()[this.getTotalCardsPerDeck()];
	
	

	
}
}	


