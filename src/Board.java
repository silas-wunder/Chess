import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("unused")
public class Board<E> {
	/**
	 * Current turn of the games
	 */
	private int turn;

	/**
	 * HashMap containing Pieces with Positions
	 */
	private Piece[][] pieces = new Piece[8][8];

	/**
	 * Final value containing width dimension of board
	 */
	private static final int WIDTH = 8;

	/**
	 * Final value containing height dimension of board
	 */
	private static final int HEIGHT = 8;

	/**
	 * Constructor: Adds pieces to HashMap and initializes turn
	 */
	Board() {
		this.turn = 1;
	}

	/**
	 * Removes piece at Position
	 * 
	 * @param pos Position that piece is at
	 */
	void remove(Position pos) {
		pieces[pos.getX()][pos.getY()] = new DefaultPiece(pos.getX(), pos.getY());
	}

	/**
	 * Returns current turn, decides whose move it is
	 * 
	 * @return Current turn of game
	 */
	int getTurn() {
		return this.turn;
	}

	/**
	 * Returns the Piece at given Position
	 * 
	 * @param p Position of piece
	 * @return Piece at Position p
	 */
	Piece get(Position p) {
		return this.pieces[p.getX()][p.getY()];
	}

	/**
	 * Returns if the piece at a given location is white
	 * 
	 * @param p Position to check
	 * @return whether or not the piece is white
	 */
	boolean hasWhite(Position p) {
		if (this.pieces[p.getX()][p.getY()] instanceof DefaultPiece)
			return false;
		return this.pieces[p.getX()][p.getY()].isWhite();
	}

	/**
	 * Returns if the piece at a given location is black
	 * 
	 * @param p Position to check
	 * @return whether or not the piece is black
	 */
	boolean hasBlack(Position p) {
		if (this.pieces[p.getX()][p.getY()] instanceof DefaultPiece)
			return false;
		return this.pieces[p.getX()][p.getY()].isBlack();
	}

	/**
	 * Checks if given Position contains a Piece
	 * 
	 * @param p Position to check
	 * @return Boolean value portraying if there is a Piece at Position p
	 */
	boolean isEmpty(Position p) {
		return (this.isValid(p) && this.pieces[p.getX()][p.getY()] instanceof DefaultPiece);
	}

	/**
	 * Checks that given Position is in board
	 * 
	 * @param p Position to check
	 * @return Boolean value, true if Position is valid
	 */
	boolean isValid(Position p) {
		return ((p.getX() >= 0 && p.getX() < WIDTH) && (p.getY() >= 0 && p.getY() < HEIGHT));
	}

	/**
	 * @return HashMap containing all Pieces and Positions in board
	 */
	Piece[][] getAllPieces() {
		return this.pieces;
	}

	/**
	 * Adds Piece p to Position pos in pieces 2D Array
	 * 
	 * @param p   Piece to add
	 * @param pos Position to add p to
	 */
	void add(Piece p, Position pos) {
		this.pieces[pos.getX()][pos.getY()] = p;
		// p.getPos().setX(pos.getX());
		// p.getPos().setY(pos.getY());
	}

	/**
	 * Moves a Piece to a Position
	 * 
	 * @param p   Piece to move
	 * @param pos Position to move p to
	 */
	void move(Piece p, Position pos) {
		if (this.isEmpty(pos)) {
			this.pieces[p.getPos().getX()][p.getPos().getY()] = null;
			this.pieces[pos.getX()][pos.getY()] = p;
			p.getPos().setX(pos.getX());
			p.getPos().setY(pos.getY());
		} else {
			this.pieces[p.getPos().getX()][p.getPos().getY()] = null;
			this.pieces[pos.getX()][pos.getY()] = p;
			p.getPos().setX(pos.getX());
			p.getPos().setY(pos.getY());
		}
	}

	/**
	 * Returns the type of the Piece at Position pos
	 * 
	 * @param pos Position to check
	 * @return Type of Piece at pos
	 */
	@SuppressWarnings("unchecked")
	E getType(Position pos) {
		return (E) this.pieces[pos.getX()][pos.getY()];
	}

	/**
	 * Increments turn
	 */
	public void incTurn() {
		int pawnCount;
		Piece temp;
		if (whiteTurn()) {
			for (int y = 6; y > -1; y--) {
				for (int x = 0; x < 8; x++) {
					temp = this.get(new Position(x, y));
					if(temp instanceof WhitePawn && (((WhitePawn)temp).canLeftPassant() || ((WhitePawn)temp).canRightPassant())){
						((WhitePawn)temp).canLeftPassant(false);
						((WhitePawn)temp).canRightPassant(false);
					}
				}
			}
		}else {
			for (int y = 1; y < 8; y++) {
				for (int x = 0; x < 8; x++) {
					temp = this.get(new Position(x, y));
					if(temp instanceof BlackPawn && (((BlackPawn)temp).canLeftPassant() || ((BlackPawn)temp).canRightPassant())){
						((BlackPawn)temp).canLeftPassant(false);
						((BlackPawn)temp).canRightPassant(false);
					}
				}
			}
		}

		this.turn++;
	}

	/**
	 * @return True if it's white's turn, false otherwise
	 */
	public boolean whiteTurn() {
		return this.turn % 2 == 1;
	}

	/**
	 * @return True if it's black's turn, false otherwise
	 */
	public boolean blackTurn() {
		return !whiteTurn();
	}
}