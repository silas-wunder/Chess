package src.main.java;
import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class BlackKing extends Piece{
	
	private boolean inCheck;
	private boolean hasMoved;

	public BlackKing(int x, int y) {
		super(x, y);
		hasMoved = false;
		inCheck = false;
	}

	/**
	 * Returns the current possible moves for this piece
	 * @return ArrayList<Position> the ArrayList of all possible positions
	 * @param b the board of the piece
	 */
	public ArrayList<Position> getPossibleMoves(Board b) {
		ArrayList<Position> positions = new ArrayList<Position>();
		Position p = new Position(this.getPos().getX(), this.getPos().getY());
		
		//Checks the position to the right
		
		if(b.isValid(new Position(p.getX() + 1, p.getY())))
			if(!b.hasBlack(new Position(p.getX() + 1, p.getY())))
				positions.add(new Position(p.getX() + 1, p.getY()));
		
		//Checks the position to the left
		
		if(b.isValid(new Position(p.getX() - 1 , p.getY())))
			if(!b.hasBlack(new Position(p.getX() - 1, p.getY())))
				positions.add(new Position(p.getX() - 1, p.getY()));
		
		//Checks the position above
		
		if(b.isValid(new Position(p.getX(), p.getY() + 1)))
			if(!b.hasBlack(new Position(p.getX(), p.getY() + 1)))
				positions.add(new Position(p.getX(), p.getY() + 1));
		
		//Checks the position below
		
		if(b.isValid(new Position(p.getX(), p.getY() - 1)))
			if(!b.hasBlack(new Position(p.getX(), p.getY() - 1)))
				positions.add(new Position(p.getX(), p.getY() - 1));
		
		//Checks the position to the diagonal up - right
		
		if(b.isValid(new Position(p.getX() + 1, p .getY() + 1)))
			if(!b.hasBlack(new Position(p.getX() + 1, p.getY() + 1)))
				positions.add(new Position(p.getX() + 1, p .getY() + 1));
		
		//Checks the position to the diagonal down - right
		
		if(b.isValid(new Position(p.getX() + 1, p .getY() - 1)))
			if(!b.hasBlack(new Position(p.getX() + 1, p.getY() - 1)))
				positions.add(new Position(p.getX() + 1, p .getY() - 1));
		
		//Checks the position to the diagonal up - left
		
		if(b.isValid(new Position(p.getX() - 1, p .getY() + 1)))
			if(!b.hasBlack(new Position(p.getX() - 1, p.getY() + 1)))
				positions.add(new Position(p.getX() - 1, p .getY() + 1));
		
		//Checks the position to the diagonal down - left
		
		if(b.isValid(new Position(p.getX() - 1, p .getY() - 1)))
			if(!b.hasBlack(new Position(p.getX() - 1, p.getY() - 1)))
				positions.add(new Position(p.getX() - 1, p .getY() - 1));

		//Checks conditions for the castle move
		if(!hasMoved){
			//Checks if the spaces to the right are empty
			if(b.isEmpty(new Position(5, 7)) && b.isEmpty(new Position(6, 7))){
				if(b.getType(new Position(7, 7)) instanceof BlackRook){
					if(!((BlackRook)(b.get(new Position(7, 7)))).hasMoved()){
						positions.add(new Position(6, 7));
					}
				}
			}
			//Checks if the spaces to the left are empty
			if(b.isEmpty(new Position(3, 7)) && b.isEmpty(new Position(2, 7)) && b.isEmpty(new Position(1, 7))){
				if(b.getType(new Position(0, 7)) instanceof BlackRook){
					if(!((BlackRook)(b.get(new Position(0, 7)))).hasMoved()){
						positions.add(new Position(2, 7));
					}
				}
			}
		}	
		
		return positions;
	}

	public void moved(){
		hasMoved = true;
	}

	public boolean hasMoved(){
		return hasMoved;
	}

	@Override
	public boolean isWhite() {
		return false;
	}

	@Override
	public boolean isBlack() {
		return true;
	}

	/**
	 * If King is in check, inCheck is set to true
	 */
	public void inCheck(){ this.inCheck = true; }
	
	/**
	 * Once King is out of check, inCheck is set to false
	 */
	public void outOfCheck(){ this.inCheck = false; }

	
	public String toString(){ return "BlackKing.png"; }
	
}
