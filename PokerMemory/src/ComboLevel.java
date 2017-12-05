
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;

public class ComboLevel extends FlushLevel{
	
	// COMBO LEVEL: The goal is to chose, on each turn, how you want to evaluate your hand 
	
	private long scoreLabel;
	//private int highestCard;
	
	protected ComboLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(validTurnTime, mainFrame);
		this.getTurnsTakenCounter().setDifficultyModeLabel("Flush Level");
		this.setCardsToTurnUp(5);
		this.setCardsPerRow(10);
		this.setRowsPerGrid(5);
		scoreLabel = 0;
	}	

	public long getScoreLabel() 
	{
		return scoreLabel;
	}
	
	@Override
	protected void makeDeck() {
		
		// In Combo level the grid consists of distinct cards, no repetitions
		//back card
		ImageIcon backIcon = this.getCardIcons()[this.getTotalCardsPerDeck()];

		int cardsToAdd[] = new int[getRowsPerGrid() * getCardsPerRow()];
		for(int i = 0; i < (getRowsPerGrid() * getCardsPerRow()); i++)
		{
			cardsToAdd[i] = i;
		}

		// randomize the order of the deck
		this.randomizeIntArray(cardsToAdd);

		// make each card object
		for(int i = 0; i < cardsToAdd.length; i++)
		{
			// number of the card, randomized
			int num = cardsToAdd[i];
			// make the card object and add it to the panel
			String rank = cardNames[num].substring(0, 1);
			String suit = cardNames[num].substring(1, 2);
			this.getGrid().add( new Card(this, this.getCardIcons()[num], backIcon, num, rank, suit));
		}
	}
	public long getRankValue(Card theCard)
	{
		if(theCard.getRank().equals("a"))
		{
			return 20;
		}
		else if (theCard.getRank().equals("k"))
		{
			return 13;
		}
		else if (theCard.getRank().equals("q"))
		{
			return 12;
		}
		else if (theCard.getRank().equals("t"))
		{
			return 10;
		}
		else if (theCard.getRank().equals("j"))
		{
			return 11;
		}
		else
		{
			return Long.parseLong(theCard.getRank());
		}
	}
	
	public void scoreIncrement(Card card1,Card card2, Card card3, Card card4, Card card5) 
	{
		scoreLabel = scoreLabel + 700 + this.getRankValue(card1) + this.getRankValue(card2) +
				this.getRankValue(card3) + this.getRankValue(card4) + this.getRankValue(card5);
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
				
				//shows the 
				Card[] cards = null;
				showDialog (cards);
				
				long[] straight = {super.getRankValue(card),super.getRankValue(otherCard1),super.getRankValue(otherCard2),super.getRankValue(otherCard3),super.getRankValue(otherCard4)};
				Arrays.sort(straight);
				
						if((card.getSuit().equals(otherCard1.getSuit())) && 
						(card.getSuit().equals(otherCard2.getSuit())) &&
						(card.getSuit().equals(otherCard3.getSuit())) &&
						(card.getSuit().equals(otherCard4.getSuit()))) 
							
														
				{
				// Three cards match, so remove them from the list (they will remain face up)
					this.scoreIncrement( card, otherCard1, otherCard2, otherCard3, otherCard4);
					this.getMainFrame().setScore(this.getScoreLabel());
					this.getTurnedCardsBuffer().clear();
				}
					//SraightLevel
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
					
					//All Reds Level
					else if (( card.getSuit().equals("h")||(card.getSuit().equals("d")))
							&& (otherCard1.getSuit().equals("h")||(otherCard1.getSuit().equals("d")))     
							&&(otherCard2.getSuit().equals("h")||(otherCard2.getSuit().equals("d")))
							&& (otherCard3.getSuit().equals("h")||(otherCard3.getSuit().equals("d")))     
							&&(otherCard4.getSuit().equals("h")||(otherCard4.getSuit().equals("d")))) 
					{
						this.scoreLabel += straight[4] * 5 + 555 ;
						this.getMainFrame().setScore(this.scoreLabel);
						this.getTurnedCardsBuffer().clear();
					}
																		
				else
				{
					this.scoreLabel -= 5;
					// The cards do not match, so start the timer to turn them down
					this.getMainFrame().setScore(this.scoreLabel);
					this.getTurnDownTimer().start();
				
				}
					
			} 
			
			return true;
		}
return false;
	}
	
	//	Evaluation box
	public void showDialog (Card[] card) {
	
Object [] possibilities = {"Select one--" , "Flush", "Straight", "All Reds"};
 Icon icon = null;
 Component frame = null; 
 int pass = JOptionPane.showConfirmDialog(null, "Do you want to keep this hand?", "Pass Option", JOptionPane.YES_NO_OPTION);
 if(pass == 0) 
 {
String s = (String)JOptionPane.showInputDialog(frame, "Choose method evaluation\n" +  "For the game:", "Evaluation Method",JOptionPane.PLAIN_MESSAGE, icon, possibilities, "s");
 }


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
	
	public boolean redChecker() 
	{
		ArrayList<Card> redArray = new ArrayList<Card>();
		for(int i = 0; i < this.getGrid().size();i++) 
		{
			if((this.getGrid().get(i).getSuit().equalsIgnoreCase("d") || this.getGrid().get(i).getSuit().equalsIgnoreCase("h")) && this.getGrid().get(i).isFaceUp() == false) 
			{
				redArray.add(this.getGrid().get(i));
			}
		}
		if(redArray.size() >= 5) {return true;}
		else {return false;}
	}
	
	public boolean straight() 
	{
	
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
		if((aToFive.size() >= 5 || twoToSix.size() >= 5 || threeToSeven.size() >= 5 
				|| fourToEight.size() >= 5 || fiveToNine.size() >= 5 || sixToTen.size() >= 5  || sevenToJ.size() >= 5 || eightToQ.size() >= 5 
				|| nineToK.size() <= 5 || aToK.size() <= 5)) 
		{
			return true;
		}
		
		else 
		{
			return false;
		}
	}
	
	@Override
	protected boolean  isGameOver(){

		
		for (int i =0; i< this.getGrid().size();i++) {
			if(!this.getGrid().get(i).isFaceUp() && (super.isGameOver() == false || this.redChecker() == true || this.straight() == true))
			{
				return false;
			}
		}
		this.scoreLabel = 0;
		return true;
	}

	


}

