import java.util.ArrayList;

public class WhiteQueen extends Piece{

	public WhiteQueen(int x, int y) {
		super(x, y);
	}

	/**
	 * Returns the current possible moves for this piece
	 * @return ArrayList<Position> the ArrayList of all possible positions
	 * @param b the board of the piece
	 */
	public ArrayList<Position> getPossibleMoves(Board b) {
		ArrayList<Position> positions = new ArrayList<Position>();
		Position p = new Position(this.getPos().getX() + 1, this.getPos().getY() + 1);
		int max = 1;
		
		//Positions to the diagonal up - right
		do{
			p.setX(p.getX() + 1);
			p.setY(p.getY() + 1);
			max ++;
		}while(b.isEmpty(p) && b.isValid(p) && !b.hasWhite(p));
		for(int i = 0; i <= max; i ++)
			positions.add(new Position(this.getPos().getX() + i, this.getPos().getY() + i));
		
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;
		
		//Positions to the diagonal down - left
		do{
			p.setX(p.getX() - 1);
			p.setY(p.getY() - 1);
			max ++;
		}while(b.isEmpty(p) && b.isValid(p) && !b.hasWhite(p));
		for(int i = max; i >= 0; i --)
			positions.add(new Position(this.getPos().getX() - i, this.getPos().getY() - i));
		
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;
		
		//Positions to the diagonal up - left
		do{
			p.setX(p.getX() + 1);
			p.setY(p.getY() - 1);
			max += 1;
		}while(b.isEmpty(p) && b.isValid(p) && !b.hasWhite(p));
		for(int i = 0; i < max; i ++)
			positions.add(new Position(this.getPos().getX() + i, this.getPos().getY() - i));
		
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;
		
		//Positions to the diagonal down - right
		do{
			p.setX(p.getX() - 1);
			p.setY(p.getY() + 1);
			max ++;
		}while(b.isEmpty(p) && b.isValid(p) && !b.hasWhite(p));
		for(int i = 0; i < max; i ++)
			positions.add(new Position(this.getPos().getX() - i, this.getPos().getY() + i));
		
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;
		
		//Forward Positions
		do{
			p.setY(p.getY() + 1);
			max++;
		}
		while(b.isEmpty(p) && b.isValid(p) && !b.hasWhite(p));
		for(int i = 0; i < max; i++){ //Add possible positions FORWARD
			positions.add(new Position(this.getPos().getX(), this.getPos().getY() + i));
		}
		
		//Positions to the Right
		
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;
		
		do{
			p.setX(p.getX() + 1);
			max++;
		}
		while(b.isEmpty(p) && b.isValid(p) && !b.hasWhite(p));//Positions to the right
		for(int i = 0; i < max; i++){ //Add possible positions RIGHT-WARD
			positions.add(new Position(this.getPos().getX() + i, this.getPos().getY()));
		}
		
		//Positions to the Left
		
		max = 1;
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		
		do{
			p.setX(p.getX() - 1);
			max++;
		}while(b.isEmpty(p) && b.isValid(p) && !b.hasWhite(p));//Positions to the left
		for(int i = max; i >= 0; i--){ //Add possible positions LEFT-WARD
			positions.add(new Position(this.getPos().getX() - i, this.getPos().getY()));
		}
		
		//Positions Behind
		
		max = 1;
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		
		do{
			p.setY(p.getY() - 1);
			max++;
		}
		while(b.isEmpty(p) && b.isValid(p) && !b.hasWhite(p));//Positions down
		for(int i = max; i >= 0; i--){ //Add possible positions DOWN-WARD
			positions.add(new Position(this.getPos().getX(), this.getPos().getY() - i));
		}
		return positions;
	}

	@Override
	public boolean isWhite() {
		return true;
	}

	@Override
	public boolean isBlack() {
		return false;
	}
	
	public String toString(){ return "WhiteQueen.png"; }

}
