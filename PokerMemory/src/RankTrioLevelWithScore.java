import java.util.ArrayList;

import javax.swing.JFrame;

public class RankTrioLevelWithScore extends RankTrioLevel
{
	private long scoreLabel = 0;
	private final int LEVEL_INCREMENT = 100;
	private final int PENALTY = 5;
	private final int TRIO = 3;
	private int counterHands;
	
	protected RankTrioLevelWithScore(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) 
	{
		super(validTurnTime, mainFrame);
		scoreLabel = 0;
		counterHands = 0;
	}
	
	public void reset() 
	{
		scoreLabel = 0;
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
	
	public void rankIncrement(Card theCard) 
	{
		scoreLabel += LEVEL_INCREMENT;
		
		if(theCard.getRank().equals("t")) 
		{
			scoreLabel += TRIO * 10;
		}
		else if(theCard.getRank().equals("j")) 
		{
			scoreLabel += TRIO * 11;
		}
		else if(theCard.getRank().equals("q")) 
		{
			scoreLabel += TRIO * 12;
		}
		else if(theCard.getRank().equals("k")) 
		{
			scoreLabel += TRIO * 13;
		}
		else if(theCard.getRank().equals("a")) 
		{
			scoreLabel += TRIO * 20;
		}
		else 
		{
			scoreLabel += TRIO * Long.parseLong(theCard.getRank());
		}
	}
	
	public void decrement() 
	{
		scoreLabel -= PENALTY;
	}
	
	public long getScoreLabel() 
	{
		return scoreLabel;
	}
	
	@Override
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
				if((card.getRank().equals(otherCard1.getRank())) && (card.getRank().equals(otherCard2.getRank()))) {
					// Three cards match, so remove them from the list (they will remain face up)
					this.getTurnedCardsBuffer().clear();
					this.rankIncrement(card);
					this.getMainFrame().setScore(this.getScoreLabel());
					this.counterHands++;
				}
				else
				{
					// The cards do not match, so start the timer to turn them down
					this.getTurnDownTimer().start();
					this.decrement();
					this.getMainFrame().setScore(this.getScoreLabel());
				}
			}
			return true;
		}
		return false;
	}
	@Override
	protected boolean  isGameOver(){
		ArrayList<Card>  ace = new ArrayList<Card>();
		ArrayList<Card>  two = new ArrayList<Card>();
		ArrayList<Card>  three = new ArrayList<Card>();
		ArrayList<Card>  four = new ArrayList<Card>();
		ArrayList<Card>  five = new ArrayList<Card>();
		ArrayList<Card>  six = new ArrayList<Card>();
		ArrayList<Card>  seven = new ArrayList<Card>();
		ArrayList<Card>  eight = new ArrayList<Card>();
		ArrayList<Card>  nine = new ArrayList<Card>();
		ArrayList<Card>  ten = new ArrayList<Card>();
		ArrayList<Card>  jj = new ArrayList<Card>();
		ArrayList<Card>  q = new ArrayList<Card>();
		ArrayList<Card>  k = new ArrayList<Card>();


		for(int j = 0;j < this.getGrid().size();j++) //Makes the arrays of the suits of Face Down Cards
		{
			if(this.getRankValue(this.getGrid().get(j)) == 20 && this.getGrid().get(j).isFaceUp() == false ) 
			{
				ace.add(this.getGrid().get(j));
			}
			else if(this.getRankValue(this.getGrid().get(j)) == 2 && this.getGrid().get(j).isFaceUp() == false) 
			{
				two.add(this.getGrid().get(j));
			}
			else if(this.getRankValue(this.getGrid().get(j)) == 3 && this.getGrid().get(j).isFaceUp() == false) 
			{
				three.add(this.getGrid().get(j));
			}
			else if(this.getRankValue(this.getGrid().get(j)) == 4 && this.getGrid().get(j).isFaceUp() == false) 
			{
				four.add(this.getGrid().get(j));
			}
			else if(this.getRankValue(this.getGrid().get(j)) == 5 && this.getGrid().get(j).isFaceUp() == false) 
			{
				five.add(this.getGrid().get(j));
			}
			else if(this.getRankValue(this.getGrid().get(j)) == 6 && this.getGrid().get(j).isFaceUp() == false) 
			{
				six.add(this.getGrid().get(j));
			}
			else if(this.getRankValue(this.getGrid().get(j)) == 7 &&  this.getGrid().get(j).isFaceUp() == false ) 
			{
				seven.add(this.getGrid().get(j));
			}
			else if(this.getRankValue(this.getGrid().get(j)) == 8  && this.getGrid().get(j).isFaceUp() == false) 
			{
				eight.add(this.getGrid().get(j));
			}
			else if(this.getRankValue(this.getGrid().get(j)) == 9  && this.getGrid().get(j).isFaceUp() == false) 
			{
				nine.add(this.getGrid().get(j));
			}
			else if(this.getRankValue(this.getGrid().get(j)) == 10  && this.getGrid().get(j).isFaceUp() == false) 
			{
				ten.add(this.getGrid().get(j));
			}
			else if(this.getRankValue(this.getGrid().get(j)) == 11  && this.getGrid().get(j).isFaceUp() == false) 
			{
				jj.add(this.getGrid().get(j));
			}
			else if(this.getRankValue(this.getGrid().get(j)) == 12  && this.getGrid().get(j).isFaceUp() == false) 
			{
				q.add(this.getGrid().get(j));
			}
			else if(this.getRankValue(this.getGrid().get(j)) == 13  && this.getGrid().get(j).isFaceUp() == false) 
			{
				k.add(this.getGrid().get(j));
			}
		}
		
		for (int i =0; i< this.getGrid().size();i++)
			if(!this.getGrid().get(i).isFaceUp()  
			&& (ace.size() >= 3 || two.size() >= 3 || three.size() >= 3 || four.size() >= 3|| five.size() >= 3
			|| six.size() >= 3 || seven.size() >= 3 || eight.size() >= 3 || nine.size() >= 3|| ten.size() >= 3
			|| jj.size() >= 3 || q.size() >= 3|| k.size() >= 3)) //The new condition states that there a need for at least one array of suits of the
			{return false;}
		

		return true;
	}

}
