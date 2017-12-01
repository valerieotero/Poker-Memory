

import java.util.Arrays;

import javax.swing.JFrame;

public class StraightLevel extends FlushLevel
{
	long scoreLabel;
	protected StraightLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(validTurnTime, mainFrame);
		scoreLabel = 0;
	
//	
	}
	
		protected boolean turnUp(Card card) {
		// the card may be turned
		if(this.getTurnedCardsBuffer().size() < getCardsToTurnUp()) 
		{
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
				
				long[] straight = {super.getRankValue(card),super.getRankValue(otherCard1),super.getRankValue(otherCard2),super.getRankValue(otherCard3),super.getRankValue(otherCard4)};
				Arrays.sort(straight);
				

				if(straight[0] == 10 && straight[1] == 11 && straight[2] == 12 && straight[3] == 13 && straight[4] == 20)
				{
				// Three cards match, so remove them from the list (they will remain face up)
					this.scoreLabel += 20 * 100 + 1000;
					this.getMainFrame().setScore(this.scoreLabel);
					this.getTurnedCardsBuffer().clear();
				}
				
				else if(straight[0] + 1 == straight[1] && straight[1] + 1 == straight[2] && straight[2] + 1 == straight[3] && straight[3] + 1 == straight[4]) 
				{
					this.scoreLabel += straight[4] * 100 + 1000;
					this.getMainFrame().setScore(this.scoreLabel);
					this.getTurnedCardsBuffer().clear();
				}
				else
				{
					this.scoreLabel = scoreLabel - 5;;
					this.getMainFrame().setScore(this.scoreLabel);
					this.getTurnDownTimer().start();

					
				}
			}
			return true;
		}
return false;
	}

}
