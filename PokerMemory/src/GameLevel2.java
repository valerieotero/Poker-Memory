import javax.swing.JFrame;

public class GameLevel2 extends GameLevel
{
	private TurnScoreLabel turnScoreLabel;
	
	protected GameLevel2(TurnsTakenCounterLabel counterLabel, TurnScoreLabel turnScoreLabel, int cardsToGuess, JFrame mainFrame) {
		super(counterLabel, cardsToGuess, mainFrame);
		this.turnScoreLabel = turnScoreLabel; turnScoreLabel.reset();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getMode() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected void makeDeck() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected boolean turnUp(Card card) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	protected boolean isGameOver() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public TurnScoreLabel getTurnScoreLabel() { return turnScoreLabel;}
	
	public void setTurnScoreLabel(TurnScoreLabel turnScoreLabel){
				this.turnScoreLabel = turnScoreLabel.get;
			}
	
	

}
