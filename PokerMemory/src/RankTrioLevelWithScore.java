import javax.swing.JFrame;

public class RankTrioLevelWithScore extends RankTrioLevel
{
	private long scoreLabel = 0;
	private final int LEVEL_INCREMENT = 100;
	private final int PENALTY = 5;
	private final int TRIO = 3;
	
	protected RankTrioLevelWithScore(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) 
	{
		super(validTurnTime, mainFrame);
		scoreLabel = 0;
	}
	
	public void reset() 
	{
		scoreLabel = 0;
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

}
