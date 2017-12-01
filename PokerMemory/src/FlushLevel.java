import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class FlushLevel extends RankTrioLevel{
	private long score = 0;
	protected FlushLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(validTurnTime, mainFrame);
		this.getTurnsTakenCounter().setDifficultyModeLabel("Flush Level");
		this.setCardsToTurnUp(5);
		this.setCardsPerRow(10);
		this.setRowsPerGrid(5);

	}
	@Override
	protected void makeDeck() {
		// In Trio level the grid consists of distinct cards, no repetitions

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
		score = score + 700 + this.getRankValue(card1) + this.getRankValue(card2) +
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

				
				if((card.getRank().equals(otherCard1.getRank())) && 
						(card.getRank().equals(otherCard2.getRank())) &&
						(card.getRank().equals(otherCard3.getRank())) &&
						(card.getRank().equals(otherCard4.getRank())))
				{
				// Three cards match, so remove them from the list (they will remain face up)
					this.scoreIncrement( card, otherCard1, otherCard2, otherCard3, otherCard4);
					super.getMainFrame().setScore(this.score);
					this.getTurnedCardsBuffer().clear();
				}
				else
				{
					this.score -= 5;
					// The cards do not match, so start the timer to turn them down
					this.getMainFrame().setScore(this.score);
					this.getTurnDownTimer().start();

					
				}
			}
			return true;
		}
return false;
	}
}





