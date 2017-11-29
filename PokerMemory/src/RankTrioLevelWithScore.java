import javax.swing.JFrame;

public class RankTrioLevelWithScore extends RankTrioLevel
{
	private long scoreLabel = 0;
	private final int LEVEL_INCREMENT = 100;
	private final int PENALTY = 5;
	
	protected RankTrioLevelWithScore(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) 
	{
		super(validTurnTime, mainFrame);

	}
	
	public void reset() 
	{
		scoreLabel = 0;
	}
	
	public void increment() 
	{
		scoreLabel += LEVEL_INCREMENT;
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
					this.increment();
				}
				else
				{
					// The cards do not match, so start the timer to turn them down
					this.getTurnDownTimer().start();
					this.decrement();
				}
			}
			return true;
		}
		return false;
	}

}
