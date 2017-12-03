

import java.util.ArrayList;
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
				

				if((straight[0] == 10 && straight[1] == 11 && straight[2] == 12 && straight[3] == 13 && straight[4] == 20) || (straight[0] == 2 && straight[1] == 3 && straight[2] == 4 && straight[3] == 5 && straight[4] == 20))
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
		
		@Override
		protected boolean  isGameOver(){
			ArrayList<Card>  daimonds = new ArrayList<Card>();
			ArrayList<Card>  hearts = new ArrayList<Card>();
			ArrayList<Card>  spades = new ArrayList<Card>();
			ArrayList<Card>  crest = new ArrayList<Card>();

			for(int j = 0;j < this.getGrid().size();j++) //Makes the arrays of the suits of Face Down Cards
			{
				if(this.getGrid().get(j).getSuit().equals("d") && this.getGrid().get(j).isFaceUp() == false)
				{
					daimonds.add(this.getGrid().get(j));
				}
				else if(this.getGrid().get(j).getSuit().equals("c") && this.getGrid().get(j).isFaceUp() == false)
				{
					crest.add(this.getGrid().get(j));
				}
				else if(this.getGrid().get(j).getSuit().equals("h") && this.getGrid().get(j).isFaceUp() == false)
				{
					hearts.add(this.getGrid().get(j));
				}
				else if(this.getGrid().get(j).getSuit().equals("s") && this.getGrid().get(j).isFaceUp() == false)
				{
					spades.add(this.getGrid().get(j));
				}
			}
			
			for (int i =0; i< this.getGrid().size();i++)
				if(!this.getGrid().get(i).isFaceUp() && (daimonds.size() >= 5 || hearts.size() >= 5 || crest.size() >= 5 || spades.size() >= 5)) //The new condition states that there a need for at least one array of suits of the
				{return false;}
			

			return true;
		}

}
