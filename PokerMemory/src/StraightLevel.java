

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
				

				if(((straight[0] == 10 && straight[1] == 11 && straight[2] == 12 && straight[3] == 13 && straight[4] == 20) 
						|| (straight[0] == 2 && straight[1] == 3 && straight[2] == 4 && straight[3] == 5 && straight[4] == 20))
						&&!((card.getSuit().equals(otherCard1.getSuit())) && 
								(card.getSuit().equals(otherCard2.getSuit())) &&
								(card.getSuit().equals(otherCard3.getSuit())) &&
								(card.getSuit().equals(otherCard4.getSuit()))))
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
		
		public void bubbleSort(ArrayList<Card> numArray) {

		    int n = numArray.size();
		    Card temp;

		    for (int i = 0; i < n; i++) {
		        for (int j = 1; j < (n - i); j++) {

		            if (this.getRankValue(numArray.get(j - 1)) > this.getRankValue(numArray.get(j))) {
		                temp = numArray.get(j - 1);
		                numArray.set(j - 1, numArray.get(j));
		                numArray.set(j, temp);
		                //numArray[j - 1] = numArray[j];
		                //numArray[j] = temp;
		            }

		        }
		    }
		}
		public void checkDuplicates(ArrayList<Card> array)
		{
			this.bubbleSort(array);
			long previousValue = this.getRankValue(array.get(0));
			for(int i = 1; i < array.size(); i++)
			{
				if(this.getRankValue(array.get(i)) == previousValue)
				{
					array.remove(i);
					i--;
				}
				else
				{
					previousValue = this.getRankValue(array.get(i));
				}
			}
		}
		
//		
		@Override
		protected boolean  isGameOver(){
			ArrayList<Card>  aToFive = new ArrayList<Card>();
			ArrayList<Card>  twoToSix = new ArrayList<Card>();
			ArrayList<Card>  threeToSeven = new ArrayList<Card>();
			ArrayList<Card>  fourToEight = new ArrayList<Card>();
			ArrayList<Card>  fiveToNine = new ArrayList<Card>();
			ArrayList<Card>  sixToTen = new ArrayList<Card>();
			ArrayList<Card>  sevenToJ = new ArrayList<Card>();
			ArrayList<Card>  eightToQ = new ArrayList<Card>();
			ArrayList<Card>  nineToK = new ArrayList<Card>();
			ArrayList<Card>  aToK = new ArrayList<Card>();
			
			

			for(int j = 0;j < this.getGrid().size();j++) //Makes the arrays of the suits of Face Down Cards
			{
				if(this.getRankValue(this.getGrid().get(j)) <= 5 || this.getRankValue(this.getGrid().get(j)) == 20 && this.getGrid().get(j).isFaceUp() == false)
				{
				aToFive.add(this.getGrid().get(j));
				}
				if(this.getRankValue(this.getGrid().get(j)) >= 2 && this.getRankValue(this.getGrid().get(j)) <= 6 && this.getGrid().get(j).isFaceUp() == false)
				{
					twoToSix.add(this.getGrid().get(j));
				}
				if(this.getRankValue(this.getGrid().get(j)) >= 3 && this.getRankValue(this.getGrid().get(j)) <= 7 && this.getGrid().get(j).isFaceUp() == false)
				{
					threeToSeven.add(this.getGrid().get(j));
				}
				if(this.getRankValue(this.getGrid().get(j)) >= 4 && this.getRankValue(this.getGrid().get(j)) <= 8 && this.getGrid().get(j).isFaceUp() == false)
				{
					fourToEight.add(this.getGrid().get(j));
				}
				if(this.getRankValue(this.getGrid().get(j)) >= 5 && this.getRankValue(this.getGrid().get(j)) <= 9 && this.getGrid().get(j).isFaceUp() == false)
				{
					fiveToNine.add(this.getGrid().get(j));
				}
				if(this.getRankValue(this.getGrid().get(j)) >= 6 && this.getRankValue(this.getGrid().get(j)) <= 10 && this.getGrid().get(j).isFaceUp() == false)
				{
					sixToTen.add(this.getGrid().get(j));
				}
				if(this.getRankValue(this.getGrid().get(j)) >= 7 && this.getRankValue(this.getGrid().get(j)) <= 11 && this.getGrid().get(j).isFaceUp() == false)
				{
					sevenToJ.add(this.getGrid().get(j));
				}
				if(this.getRankValue(this.getGrid().get(j)) >= 8 && this.getRankValue(this.getGrid().get(j)) <= 12 && this.getGrid().get(j).isFaceUp() == false)
				{
					eightToQ.add(this.getGrid().get(j));
				}
				if(this.getRankValue(this.getGrid().get(j)) >= 9 && this.getRankValue(this.getGrid().get(j)) <= 13 && this.getGrid().get(j).isFaceUp() == false)
				{
					nineToK.add(this.getGrid().get(j));
				}
				if((this.getRankValue(this.getGrid().get(j)) >= 10 && this.getRankValue(this.getGrid().get(j)) <= 13) || this.getRankValue(this.getGrid().get(j)) == 20)
				{
					aToK.add(this.getGrid().get(j));
				}
			}
			
			this.checkDuplicates(aToK);
			this.checkDuplicates(nineToK);
			this.checkDuplicates(eightToQ);
			this.checkDuplicates(sevenToJ);
			this.checkDuplicates(sixToTen);
			this.checkDuplicates(fiveToNine);
			this.checkDuplicates(fourToEight);
			this.checkDuplicates(threeToSeven);
			this.checkDuplicates(twoToSix);
			this.checkDuplicates(aToFive);
			
			for (int i =0; i< this.getGrid().size();i++)
				if(!this.getGrid().get(i).isFaceUp() && (aToFive.size() >= 5 || twoToSix.size() >= 5 || threeToSeven.size() >= 5 
				|| fourToEight.size() >= 5 || fiveToNine.size() >= 5 || sixToTen.size() >= 5  || sevenToJ.size() >= 5 || eightToQ.size() >= 5 
				|| nineToK.size() <= 5) || aToK.size() <= 5) //The new condition states that there a need for at least one array of suits of the
				{return false;}
			
			this.scoreLabel = 0;
			return true;
		}

}
