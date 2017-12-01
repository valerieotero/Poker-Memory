
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class ComboLevel extends FlushLevel{
	
	// Flush LEVEL: The goal is to find, on each turn, three cards with the same rank
	private long currentScore=0;
	protected ComboLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(validTurnTime, mainFrame);
		this.getTurnsTakenCounter().setDifficultyModeLabel("Flush Level");	
		this.setCardsToTurnUp(5);
		this.setCardsPerRow(10);
		this.setRowsPerGrid(5);
		this.getMainFrame().setScore(this.getLevelScore());
	}	

	@Override
	protected boolean turnUp(Card card) {
		// the card may be turned
		if(this.getTurnedCardsBuffer().size() < getCardsToTurnUp()) 
		{
			super.turnUp(card);
			// add the card to the list
			this.getTurnedCardsBuffer().add(card);
			if(this.getTurnedCardsBuffer().size() == getCardsToTurnUp())
			{
				// We are uncovering the last card in this turn
				// Record the player's turn
				this.getTurnsTakenCounter().increment();
				// get the other card (which was already turned up)
				Card otherCard1 = (Card) this.getTurnedCardsBuffer().get(0);
				Card otherCard2 = (Card) this.getTurnedCardsBuffer().get(1);
				Card otherCard3 = (Card) this.getTurnedCardsBuffer().get(2);
				Card otherCard4 = (Card) this.getTurnedCardsBuffer().get(3);
				if((card.getSuit().equals(otherCard1.getSuit())) && (card.getSuit().equals(otherCard2.getSuit())) && 
						(card.getSuit().equals(otherCard3.getSuit())) && (card.getSuit().equals(otherCard4.getSuit()))) {
					// Three cards match, so remove them from the list (they will remain face up)
					currentScore += 700 + ConvertValue(card)+ConvertValue(otherCard1)+ConvertValue(otherCard2)+ConvertValue(otherCard3)+ConvertValue(otherCard4);
					
					this.getMainFrame().setScore(this.getLevelScore());
					this.getTurnedCardsBuffer().clear();
				}
				else
				{
					currentScore -= 5;
					this.getMainFrame().setScore(this.getLevelScore());
					// The cards do not match, so start the timer to turn them down
					this.getTurnDownTimer().start();
				}
			}
			return true;
		}
		return false;
	}
	public long getLevelScore(){
		return currentScore;
	}
	
//	@Override
//	//Important for newGame method in MemoryGame and Memory Frame
//	public String getMode() {
//		// TODO Auto-generated method stub
//		return "flush";
//	}
//	
	
	@Override
	protected boolean isGameOver()
	{		
		for (int i =0; i< this.getGrid().size();i++)
			if(!this.getGrid().get(i).isFaceUp()) return false;

		return true;
	}
}
