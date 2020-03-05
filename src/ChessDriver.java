import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings({ "rawtypes", "unused" })
public class ChessDriver {
	/**
	 * Main board in Chess.
	 */
	private Board board;
	/**
	 * invisible board used for checkmate testing
	 */
	private Board testBoard;
	/**
	 * Value used in Standard Draw when placing things on the board, 0.062 = 0
	 * position on board.
	 */
	private final double xValue = 0.062;
	/**
	 * Value used in Standard Draw when placing things on the board, 0.062 = 0
	 * position on board.
	 */
	private final double yValue = 0.062;
	/**
	 * Scale of pictures in Standard Draw.
	 */
	private final double scale = 0.062 + 0.03;
	/**
	 * Value to increment xValue and yValue when placing things on board, xValue +
	 * inc = 1 position on board.
	 */
	private final double inc = 0.124;
	/**
	 * boolean value containing if chess is currently being run, useful in while
	 * loop listening for clicks.
	 */
	private boolean running = false;
	/**
	 * boolean value containing whether or not there is a piece selected, used for
	 * moving pieces and highlighting.
	 */
	private boolean isSelected = false;
	/**
	 * All colors of tiles in the board, initialized later
	 */
	private Color[][] colors = new Color[8][8];
	/**
	 * Position of click
	 */
	private Position posPiece;
	/**
	 * Position of clicked piece, very important when moving
	 */
	private Piece currentPiece;
	/**
	 * X position of the king in check
	 */
	private int xCheck = -1;
	/**
	 * Y position of the king in check
	 */
	private int yCheck = -1;

	// TODO: Check visual bugs, castling through/out of check, stalemate handling
	// doesn't work (checkmate most likely also has bugs), add win notification to
	// UI, add restart option to UI, visuals and coding style should be tweaked for
	// beauty
	public static void main(String[] args) {
		// creates driver object
		ChessDriver driver = new ChessDriver();
		// creates the visual board, all visuals are scaled when this changes
		driver.createBoard(800, 800);
		StdDraw.setPenRadius(0.01);
		// draws all tiles initially
		driver.createTiles();
		// adds all pieces initially
		driver.addPieces();
		// runs the game
		driver.listen();

	}

	/**
	 * Creates board w x h in size
	 * 
	 * @param w Width of GUI
	 * @param h Height of GUI
	 */
	public void createBoard(int w, int h) {
		this.board = new Board();
		this.testBoard = new Board();
		StdDraw.setCanvasSize(w, h);
		this.running = true;
	}

	/**
	 * Creates tiles of alternating color throughout the chess board in order to
	 * make a checkered playing board
	 */
	public void createTiles() {
		double x = xValue;
		double y = yValue;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				// If this is the location of a king in check, draw square red
				if (xCheck == j && yCheck == i) {
					StdDraw.setPenColor(new Color(170, 0, 0));
					colors[i][j] = new Color(170, 0, 0);
				} else if (i % 2 == 0) {
					if (j % 2 == 0) {
						// This should be the darker color
						StdDraw.setPenColor(new Color(95, 95, 95));
						colors[i][j] = new Color(95, 95, 95);
					} else {
						// This should be the lighter color
						StdDraw.setPenColor(new Color(255, 255, 255));
						colors[i][j] = new Color(255, 255, 255);
					}

				} else {
					if (j % 2 == 0) {
						// This should be the lighter color
						StdDraw.setPenColor(new Color(255, 255, 255));
						colors[i][j] = new Color(255, 255, 255);
					} else {
						// This should be the darker color
						StdDraw.setPenColor(new Color(95, 95, 95));
						colors[i][j] = new Color(95, 95, 95);
					}
				}
				StdDraw.filledRectangle(x, y, xValue, yValue);
				x += inc;
			}
			y += inc;
			x = xValue;
		}
	}

	/**
	 * Adds all of the chess pieces to the GUI and to the 2D Array in board
	 */
	public void addPieces() {
		this.board.add(new WhiteRook(0, 0), new Position(0, 0));
		StdDraw.picture(this.xValue, this.yValue, "resources/WhiteRook.png", scale, scale);
		this.board.add(new WhiteKnight(1, 0), new Position(1, 0));
		StdDraw.picture(this.xValue + (inc * 1), this.yValue, "resources/WhiteKnight.png", scale, scale);
		this.board.add(new WhiteBishop(2, 0), new Position(2, 0));
		StdDraw.picture(this.xValue + (inc * 2), this.yValue, "resources/WhiteBishop.png", scale, scale);
		this.board.add(new WhiteQueen(3, 0), new Position(3, 0));
		StdDraw.picture(this.xValue + (inc * 3), this.yValue, "resources/WhiteQueen.png", scale, scale);
		this.board.add(new WhiteKing(4, 0), new Position(4, 0));
		StdDraw.picture(this.xValue + (inc * 4), this.yValue, "resources/WhiteKing.png", scale, scale);
		this.board.add(new WhiteBishop(5, 0), new Position(5, 0));
		StdDraw.picture(this.xValue + (inc * 5), this.yValue, "resources/WhiteBishop.png", scale, scale);
		this.board.add(new WhiteKnight(6, 0), new Position(6, 0));
		StdDraw.picture(this.xValue + (inc * 6), this.yValue, "resources/WhiteKnight.png", scale, scale);
		this.board.add(new WhiteRook(7, 0), new Position(7, 0));
		StdDraw.picture(this.xValue + (inc * 7), this.yValue, "resources/WhiteRook.png", scale, scale);
		this.board.add(new BlackRook(0, 7), new Position(0, 7));
		StdDraw.picture(this.xValue, this.yValue + (inc * 7), "resources/BlackRook.png", scale, scale);
		this.board.add(new BlackKnight(1, 7), new Position(1, 7));
		StdDraw.picture(this.xValue + (inc * 1), this.yValue + (inc * 7), "resources/BlackKnight.png", scale, scale);
		this.board.add(new BlackBishop(2, 7), new Position(2, 7));
		StdDraw.picture(this.xValue + (inc * 2), this.yValue + (inc * 7), "resources/BlackBishop.png", scale, scale);
		this.board.add(new BlackQueen(3, 7), new Position(3, 7));
		StdDraw.picture(this.xValue + (inc * 3), this.yValue + (inc * 7), "resources/BlackQueen.png", scale, scale);
		this.board.add(new BlackKing(4, 7), new Position(4, 7));
		StdDraw.picture(this.xValue + (inc * 4), this.yValue + (inc * 7), "resources/BlackKing.png", scale, scale);
		this.board.add(new BlackBishop(5, 7), new Position(5, 7));
		StdDraw.picture(this.xValue + (inc * 5), this.yValue + (inc * 7), "resources/BlackBishop.png", scale, scale);
		this.board.add(new BlackKnight(6, 7), new Position(6, 7));
		StdDraw.picture(this.xValue + (inc * 6), this.yValue + (inc * 7), "resources/BlackKnight.png", scale, scale);
		this.board.add(new BlackRook(7, 7), new Position(7, 7));
		StdDraw.picture(this.xValue + (inc * 7), this.yValue + (inc * 7), "resources/BlackRook.png", scale, scale);
		// Adds the pawns and DefaultPieces to every other location on the board
		for (int i = 0; i < 8; i++) {
			for (int j = 1; j < 8; j++) {
				if (j == 1) {
					this.board.add(new WhitePawn(i, j), new Position(i, j));
					StdDraw.picture(this.xValue + (inc * i), this.yValue + (inc * j), "resources/WhitePawn.png", scale,
							scale);
				} else if (j == 6) {
					this.board.add(new BlackPawn(i, j), new Position(i, j));
					StdDraw.picture(this.xValue + (inc * i), this.yValue + (inc * j), "resources/BlackPawn.png", scale,
							scale);
				} else if (j >= 2 && j <= 5)
					// DefaultPiece is a placeholder piece so that NullPointer is never thrown
					this.board.add(new DefaultPiece(i, j), new Position(i, j));
			}
		}
	}

	/**
	 * Copies the current board to testBoard, used to run potential moves
	 */
	private void copyBoard() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Position temp = new Position(x, y);
				if (this.board.getType(temp) instanceof DefaultPiece)
					this.testBoard.add(new DefaultPiece(x, y), temp);
				else if (this.board.getType(temp) instanceof WhitePawn)
					this.testBoard.add(new WhitePawn(x, y), temp);
				else if (this.board.getType(temp) instanceof BlackPawn)
					this.testBoard.add(new BlackPawn(x, y), temp);
				else if (this.board.getType(temp) instanceof WhiteRook)
					this.testBoard.add(new WhiteRook(x, y), temp);
				else if (this.board.getType(temp) instanceof BlackRook)
					this.testBoard.add(new BlackRook(x, y), temp);
				else if (this.board.getType(temp) instanceof WhiteKnight)
					this.testBoard.add(new WhiteKnight(x, y), temp);
				else if (this.board.getType(temp) instanceof BlackKnight)
					this.testBoard.add(new BlackKnight(x, y), temp);
				else if (this.board.getType(temp) instanceof WhiteBishop)
					this.testBoard.add(new WhiteBishop(x, y), temp);
				else if (this.board.getType(temp) instanceof BlackBishop)
					this.testBoard.add(new BlackBishop(x, y), temp);
				else if (this.board.getType(temp) instanceof WhiteQueen)
					this.testBoard.add(new WhiteQueen(x, y), temp);
				else if (this.board.getType(temp) instanceof BlackQueen)
					this.testBoard.add(new BlackQueen(x, y), temp);
				else if (this.board.getType(temp) instanceof WhiteKing)
					this.testBoard.add(new WhiteKing(x, y), temp);
				else if (this.board.getType(temp) instanceof BlackKing)
					this.testBoard.add(new BlackKing(x, y), temp);
			}
		}
	}

	/**
	 * Listens for clicks in GUI
	 */
	public void listen() {
		while (this.running) {
			if (this.board.whiteTurn()) {
				for (Position p : whiteLocations(this.board)) {
					this.board.get(p).calculatePossibleMoves(this.board);
				}
			} else {
				for (Position p : blackLocations(this.board)) {
					this.board.get(p).calculatePossibleMoves(this.board);
				}
			}
			if (StdDraw.isMousePressed()) {
				click(StdDraw.mouseX(), StdDraw.mouseY());
				// was 120, anything lower doesn't register clicks, not sure what the effect is
				StdDraw.pause(300);
			}
			boolean whiteKing = false;
			boolean blackKing = false;
			Piece[][] pieces = this.board.getAllPieces();
			for (int i = 0; i < pieces.length; i++) {
				for (int j = 0; j < pieces[i].length; j++) {
					// Checks to make sure an instance of both kings exist
					// This should become obselete once checkmate implementation is complete
					if (pieces[i][j] instanceof BlackKing)
						blackKing = true;
					if (pieces[i][j] instanceof WhiteKing)
						whiteKing = true;
				}
			}
			// If either king is missing, the game is over
			// Again, should become obsolete once checkmate implementation is complete
			if (whiteKing == false) {
				System.out.println("Player 2 wins!");
				this.running = false;
			}
			if (blackKing == false) {
				System.out.println("Player 1 wins!");
				this.running = false;
			}
		}
	}

	/**
	 * Click listener
	 * 
	 * @param x X coordinate clicked in GUI
	 * @param y Y coordinate clicked in GUI
	 */
	public void click(double x, double y) {
		double xCoor = ((x - this.xValue) / inc);
		double yCoor = ((y - this.yValue) / inc);
		int xC = 0;
		int yC = 0;
		// Calculates location of click on a 0-7 scale, DO NOT MODIFY
		if ((xCoor * 10) % 10 >= 5)
			xC = ((int) xCoor) + 1;
		else
			xC = ((int) xCoor);

		if ((yCoor * 10) % 10 >= 5)
			yC = ((int) yCoor) + 1;
		else
			yC = ((int) yCoor);

		// Kill the click method if clicked out of bounds to prevent crash
		if (xC == 8 || yC == 8)
			return;

		this.posPiece = new Position(xC, yC);
		if (this.isSelected) {
			// If there is a selected piece, take action
			if (this.currentPiece.getPos().equals(this.posPiece)) {
				// If the player clicks on the currently selected piece, it is unselected
				this.isSelected = false;
				this.createTiles();
				this.addPictures();
			} else if (currentPiece.isWhite() == this.board.get(posPiece).isWhite()
					&& currentPiece.isBlack() == this.board.get(posPiece).isBlack()) {
				// If the player clicks on a piece of the same color, change selected piece to
				// that piece
				this.createTiles();
				this.addPictures();
				selectPiece(posPiece.getX(), posPiece.getY());
				assignPiece();
			} else {
				// If nothing else, check to see if the clicked location is a valid move for the
				// selected piece
				ArrayList<Position> moves = this.currentPiece.getPossibleMoves(this.board);
				for (int i = 0; i < moves.size(); i++) {
					if (this.posPiece.equals(moves.get(i))) {
						// If this is a valid move, make the move
						move(this.currentPiece, this.posPiece, this.board);
					}
				}
			}
		} else {
			// If there is no actively selected piece, select a piece or ignore click
			if (this.board.get(this.posPiece) instanceof DefaultPiece) {
				// Ignore click of empty space
			} else {
				// Allow selection of a piece only if it's that player's turn
				if (this.board.whiteTurn() == this.board.get(this.posPiece).isWhite()
						&& this.board.blackTurn() == this.board.get(this.posPiece).isBlack()) {
					selectPiece(this.posPiece.getX(), this.posPiece.getY());
					assignPiece();
				}
			}
		}
	}

	/**
	 * Highlights the tile at location (x, y)
	 * 
	 * @param x x Coordinate
	 * @param y y Coordinate
	 */
	public void selectPiece(int x, int y) {
		double xCoor = xValue + (inc * x);
		double yCoor = yValue + (inc * y);
		if (this.board.get(new Position(x, y)) instanceof DefaultPiece) {
			// No tile is highlighted
		} else {
			StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
			StdDraw.rectangle(xCoor, yCoor, xValue - 0.009, yValue - 0.009);
			String p = this.board.get(new Position(x, y)).toString();
			StdDraw.picture(xCoor, yCoor, p, scale, scale);
		}
	}

	/**
	 * Moves piece p to position pos, handles en passant and castling, takes in b so
	 * that some actions are not taken on the test board
	 * 
	 * //TODO: Create helper methods for a lot of this stuff
	 * 
	 * @param p   Piece to move
	 * @param pos Position to move to
	 * @param b   Board object taken in
	 */
	public void move(Piece p, Position pos, Board b) {
		// This is the type of piece of p
		Object type = b.getType(p.getPos());
		// This is the type of piece at pos
		Piece temp = b.get(pos);
		// Starting location of p, x direction
		int startX = p.getPos().getX();
		// Starting location of p, y direction
		int startY = p.getPos().getY();

		// If the clicked location is valid, execute move
		if (b.isValid(pos)) {
			// If the piece is being moved to the location of a current piece, take that
			// piece

			// This is an if else with no else, else condition is never hit because of default pieces
			// TODO: Restructure the conditional and remove the else statement
			if ((p.isWhite() == b.hasBlack(pos) && p.isBlack() == b.hasWhite(pos))
					|| (b.hasWhite(pos) == b.hasBlack(pos))) {
				// Remove the piece currently there
				b.remove(pos);
				b.remove(p.getPos());
				// Adds DefaultPiece to p's position
				b.add(new DefaultPiece(p.getPos().getX(), p.getPos().getY()), p.getPos());
				// Move p to pos
				p.getPos().setX(pos.getX());
				p.getPos().setY(pos.getY());
				// Add p to the board in position pos
				b.add((Piece) type, pos);

				// Calculate all possible moves for all pieces
				for (Position tmpPos : blackLocations(b))
					b.get(tmpPos).calculatePossibleMoves(b);
				for (Position tmpPos : whiteLocations(b))
					b.get(tmpPos).calculatePossibleMoves(b);
				// Check to see if either king is in check now
				int check = checkCheck(b);

				if (check == 1 && b.whiteTurn()) {
					// If white has moved and is still in check, this is not a valid move
					if (b.equals(this.board)) {
						// If this is the GUI board, void the move and return pieces
						b.remove(pos);
						b.remove(new Position(startX, startY));
						b.add(temp, pos);
						p.getPos().setX(startX);
						p.getPos().setY(startY);
						b.add((Piece) type, new Position(startX, startY));
						System.out.println("\u001B[31mInvalid move, please select a new move or piece.\u001B[0m");
						this.createTiles();
						this.addPictures();
						selectPiece(p.getPos().getX(), p.getPos().getY());
					}
				} else if (check == 2 && b.blackTurn()) {
					// If black has moved and is still in check, this is not a valid move
					if (b.equals(this.board)) {
						// If this is the GUI board, void the move and return pieces
						b.remove(pos);
						b.remove(new Position(startX, startY));
						b.add(temp, pos);
						p.getPos().setX(startX);
						p.getPos().setY(startY);
						b.add((Piece) type, new Position(startX, startY));
						// TODO: Instead of printing to console, there should be a visual indicator
						System.out.println("\u001B[31mInvalid move, please select a new move or piece.\u001B[0m");
						this.createTiles();
						this.addPictures();
						selectPiece(p.getPos().getX(), p.getPos().getY());
					}
				} else {
					// If the player has not put themselves in check, this is a valid move
					// Checks conditions for white passant
					if (type instanceof BlackPawn && !((BlackPawn) b.getType(p.getPos())).hasMoved()) {
						if (6 - pos.getY() == 2) {
							Position leftPos = new Position(pos.getX() - 1, pos.getY());
							Position rightPos = new Position(pos.getX() + 1, pos.getY());
							if (b.isValid(leftPos)) {
								if (b.get(leftPos) instanceof WhitePawn) {
									((WhitePawn) b.get(leftPos)).canRightPassant(true);
								}
							}
							if (b.isValid(rightPos)) {
								if (b.get(rightPos) instanceof WhitePawn) {
									((WhitePawn) b.get(rightPos)).canLeftPassant(true);
								}
							}
						}
						((BlackPawn) b.getType(pos)).moved();
					}
					// Checks conditions for black passant
					if (type instanceof WhitePawn && !((WhitePawn) b.getType(p.getPos())).hasMoved()) {
						if (pos.getY() - 1 == 2) {
							Position leftPos = new Position(pos.getX() - 1, pos.getY());
							Position rightPos = new Position(pos.getX() + 1, pos.getY());
							if (b.isValid(leftPos)) {
								if (b.get(leftPos) instanceof BlackPawn) {
									((BlackPawn) b.get(leftPos)).canRightPassant(true);
								}
							}
							if (b.isValid(rightPos)) {
								if (b.get(rightPos) instanceof BlackPawn) {
									((BlackPawn) b.get(rightPos)).canLeftPassant(true);
								}
							}
						}
						((WhitePawn) b.getType(pos)).moved();
					}
					if (type instanceof WhitePawn) {
						// Checks to see if white pawn has reached back rank and should be promoted
						// Automatically promotes to queen, as that is the chosen piece 97% of the
						// time
						if (pos.getY() == 7) {
							((WhitePawn) p).kingMe(board);
							// If not promotable, check to see if this pawn can passant left
						} else if (((WhitePawn) p).canLeftPassant()) {
							if (startX - pos.getX() > 0) {
								Position target = new Position(startX - 1, startY);
								b.remove(target);
								b.add(new DefaultPiece(startX - 1, startY), target);
							}
							// Check if this pawn can passant right if nothing else
						} else if (((WhitePawn) p).canRightPassant()) {
							if (startX - pos.getX() < 0) {
								Position target = new Position(startX + 1, startY);
								b.remove(target);
								b.add(new DefaultPiece(startX + 1, startY), target);
							}
						}
					}
					if (type instanceof BlackPawn) {
						// Checks to see if black pawn has reached back rank and should be promoted
						// Automatically promotes to queen, as that is the chosen piece 97% of the
						// time
						if (pos.getY() == 0) {
							((BlackPawn) p).kingMe(board);
							// If not promotable, check to see if this pawn can passant left
						} else if (((BlackPawn) p).canLeftPassant()) {
							if (startX - pos.getX() > 0) {
								Position target = new Position(startX - 1, startY);
								b.remove(target);
								b.add(new DefaultPiece(startX - 1, startY), target);
							}
							// Check if this pawn can passant right if nothing else
						} else if (((BlackPawn) p).canRightPassant()) {
							if (startX - pos.getX() < 0) {
								Position target = new Position(startX + 1, startY);
								b.remove(target);
								b.add(new DefaultPiece(startX + 1, startY), target);
							}
						}

					}
					// Stores if either king is in check
					int tempC = checkCheck(this.board);
					// TODO: The call to blackstale and whitestale are wrong
					if (b.equals(this.board)) {
						if (whiteStale()) {
							if (tempC == 1) {
								this.running = false;
								System.out.println("checkmate, white loses");
							} else {
								this.running = false;
								System.out.println("stalemate");
							}
						} else if (blackStale()) {
							if (tempC == 2) {
								this.running = false;
								System.out.println("checkmate, black loses");
							} else {
								this.running = false;
								System.out.println("stalemate");
							}
						}

					}

					if (b.equals(this.board)) {
						StdDraw.setPenColor(colors[p.getPos().getX()][p.getPos().getY()]);
						StdDraw.filledRectangle(xValue + (inc * p.getPos().getX()), yValue + (inc * p.getPos().getY()),
								xValue, yValue);
						StdDraw.setPenColor(colors[pos.getX()][pos.getY()]);
						StdDraw.filledRectangle(xValue + (inc * pos.getX()), yValue + (inc * pos.getY()), xValue,
								yValue);
						StdDraw.picture(xValue + (inc * pos.getX()), yValue + (inc * pos.getY()), p.toString(), scale,
								scale);
						this.createTiles();
						this.addPictures();
					}
					this.isSelected = false;
					b.incTurn();

					if (type instanceof BlackKing) {
						// Castle check and moving rook

						if (!((BlackKing) p).hasMoved()) {
							if (4 - pos.getX() > 1) {
								this.currentPiece = b.get(new Position(0, 7));
								move(this.currentPiece, new Position(3, 7), b);
								b.incTurn();
							} else if (4 - pos.getX() < -1) {
								this.currentPiece = b.get(new Position(7, 7));
								move(this.currentPiece, new Position(5, 7), b);
								b.incTurn();
							}
							((BlackKing) p).moved();
						}
					}
					if (type instanceof WhiteKing) {
						// System.out.println(p.getPos().getX() + " " + pos.getX());

						if (!((WhiteKing) p).hasMoved()) {
							if (4 - pos.getX() > 1) {
								this.currentPiece = b.get(new Position(0, 0));
								move(this.currentPiece, new Position(3, 0), b);
								b.incTurn();
							} else if (4 - pos.getX() < -1) {
								this.currentPiece = b.get(new Position(7, 0));
								move(this.currentPiece, new Position(5, 0), b);
								b.incTurn();
								System.out.println("haha dummy");
							}
							((WhiteKing) p).moved();
						}
					}
					// Turns hasMoved variable to true after being moved
					if (type instanceof BlackRook && !((BlackRook) p).hasMoved())
						((BlackRook) p).moved();
					if (type instanceof WhiteRook && !((WhiteRook) p).hasMoved())
						((WhiteRook) p).moved();
				}

			}
		} else

		{
			System.out.println("\u001B[31mInvalid move, please select a new move or piece.\u001B[0m");
			isSelected = false;
		}
	}

	/**
	 * Determines which king, if any, is in check
	 * 
	 * returns 0 if neither in check, 1 if white is in check, and 2 if black is in
	 * check
	 * 
	 * @param b the board to run the check on
	 * @return int representing which king is in check
	 */
	public int checkCheck(Board b) {
		ArrayList<Position> blackPositions = blackLocations(b);
		ArrayList<Position> whitePositions = whiteLocations(b);
		WhiteKing wKing = new WhiteKing(-1, -1);
		BlackKing bKing = new BlackKing(-1, -1);

		// Grabs the location of the kings
		for (int i = 0; i < whitePositions.size(); i++)
			if (b.get(whitePositions.get(i)) instanceof WhiteKing)
				wKing = ((WhiteKing) b.get(whitePositions.get(i)));

		for (int i = 0; i < blackPositions.size(); i++)
			if (b.get(blackPositions.get(i)) instanceof BlackKing)
				bKing = ((BlackKing) b.get(blackPositions.get(i)));

		// detects if pieces should not be allowed to move in a way that puts its own
		// king in check
		if (b.whiteTurn()) {
			wKing.setCheck(false);
			// Check to see if any of the black pieces are able to move to the current
			// location of the white king
			for (Position p : blackLocations(b)) {
				for (Position mov : b.get(p).getPossibleMoves(b)) {
					if (mov.equals(wKing.getPos())) {
						wKing.setCheck(true);
						if (b == this.board) {
							xCheck = wKing.getPos().getX();
							yCheck = wKing.getPos().getY();
						}
						return 1;
					}
				}
			}

			bKing.setCheck(false);
			// Check to see if any of the white pieces are able to move to the current
			// location of the black king
			for (Position p : whiteLocations(b)) {
				for (Position mov : b.get(p).getPossibleMoves(b)) {
					if (mov.equals(bKing.getPos())) {
						bKing.setCheck(true);
						if (b == this.board) {
							xCheck = bKing.getPos().getX();
							yCheck = bKing.getPos().getY();
						}
						return 2;
					}
				}
			}

		} else {
			bKing.setCheck(false);
			// Check to see if any of the black pieces are able to move to the current
			// location of the white king
			for (Position p : whiteLocations(b)) {
				for (Position mov : b.get(p).getPossibleMoves(b)) {
					if (mov.equals(bKing.getPos())) {
						bKing.setCheck(true);
						if (b == this.board) {
							xCheck = bKing.getPos().getX();
							yCheck = bKing.getPos().getY();
						}
						return 2;
					}
				}
			}

			wKing.setCheck(false);
			// Check to see if any of the white pieces are able to move to the current
			// location of the black king
			for (Position p : blackLocations(b)) {
				for (Position mov : b.get(p).getPossibleMoves(b)) {
					if (mov.equals(wKing.getPos())) {
						wKing.setCheck(true);
						if (b == this.board) {
							xCheck = wKing.getPos().getX();
							yCheck = wKing.getPos().getY();
						}
						return 1;
					}
				}
			}

		}

		xCheck = -1;
		yCheck = -1;
		return 0;
	}

	/**
	 * Returns all locations of black pieces on the board b
	 * 
	 * @param b the board to check positions on
	 * @return an arraylist of all positions with a black piece
	 */
	public ArrayList<Position> blackLocations(Board b) {
		ArrayList<Position> positions = new ArrayList<Position>();
		int count = 0;
		for (int y = 7; y > -1; y--) {
			for (int x = 0; x < 8; x++) {
				if (b.hasBlack(new Position(x, y))) {
					positions.add(new Position(x, y));
					count++;
				}
				if (count > 15)
					break;
			}
			if (count > 15)
				break;
		}
		return positions;
	}

	/**
	 * Returns all locations of white pieces on the board b
	 * 
	 * @param b the board to check positions on
	 * @return an arraylist of all positions with a white piece
	 */
	public ArrayList<Position> whiteLocations(Board b) {
		ArrayList<Position> positions = new ArrayList<Position>();
		int count = 0;
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (b.hasWhite(new Position(x, y))) {
					positions.add(new Position(x, y));
					count++;
				}
				if (count > 15)
					break;
			}
			if (count > 15)
				break;
		}
		return positions;
	}

	/**
	 * In theorey this checks to see if white is in stalemate, doesn't work
	 * 
	 * TODO: Considering current location of piece a valid move and exiting early
	 * FIXME: Pawn check handling on test board is not correct
	 * 
	 * @return boolean representing whether white is in stalemate
	 */
	private boolean whiteStale() {
		// Copy the board to test board
		copyBoard();
		for (Position p : whiteLocations(this.testBoard))
			this.testBoard.get(p).calculatePossibleMoves(this.testBoard);
		for (Position p : blackLocations(this.testBoard))
			this.testBoard.get(p).calculatePossibleMoves(this.testBoard);
		// Grabs the location of all white pieces
		ArrayList<Position> pieces = whiteLocations(this.testBoard);
		// Loop through every piece and check if any of them put the black
		for (int i = 0; i < pieces.size(); i++) {
			Piece tempP = this.testBoard.get(pieces.get(i));
			Position startP = new Position(tempP.getPos().getX(), tempP.getPos().getY());
			ArrayList<Position> tempMoves = tempP.getPossibleMoves(this.testBoard);
			for (int j = 0; j < tempMoves.size(); j++) {
				if (this.testBoard.isValid(tempMoves.get(j))) {
					move(tempP, tempMoves.get(j), this.testBoard);
					int check = checkCheck(this.testBoard);
					if (check == 0 || check == 2) {
						xCheck = -1;
						yCheck = -1;
						return false;
					}
					move(tempP, startP, this.testBoard);

				}
			}

		}
		return true;

	}

	/**
	 * In theorey this checks to see if black is in stalemate, doesn't work
	 * 
	 * TODO: Considering current location of piece a valid move and exiting early
	 * FIXME: Pawn check handling on test board is not correct
	 * 
	 * @return boolean representing whether white is in stalemate
	 */
	private boolean blackStale() {
		copyBoard();
		for (Position p : blackLocations(this.testBoard))
			this.testBoard.get(p).calculatePossibleMoves(this.testBoard);
		for (Position p : whiteLocations(this.testBoard))
			this.testBoard.get(p).calculatePossibleMoves(this.testBoard);

		ArrayList<Position> pieces = blackLocations(this.testBoard);
		for (int i = 0; i < pieces.size(); i++) {
			Piece tempP = this.testBoard.get(pieces.get(i));
			Position startP = new Position(tempP.getPos().getX(), tempP.getPos().getY());
			ArrayList<Position> tempMoves = tempP.getPossibleMoves(this.testBoard);
			for (int j = 0; j < tempMoves.size(); j++) {
				if (this.testBoard.isValid(tempMoves.get(j))) {
					move(tempP, tempMoves.get(j), this.testBoard);
					int check = checkCheck(this.testBoard);
					if (check == 0 || check == 1) {
						xCheck = -1;
						yCheck = -1;
						return false;
					}
					move(tempP, startP, this.testBoard);
				}
			}
		}
		return true;
	}

	/**
	 * Assigns currentPiece the value of posPiece (currentPiece is a placeholder of
	 * sorts) Says a piece has been selected Assigns new color in color array for
	 * easier access when redoing rectangles
	 */
	public void assignPiece() {
		this.isSelected = true; // A piece is selected
		this.currentPiece = this.board.get(this.posPiece);
	}

	/**
	 * Adds pictures for all of the pieces in the board, called when board is
	 * refreshed
	 */
	public void addPictures() {
		Piece[][] pieces = this.board.getAllPieces();
		for (int i = 0; i < pieces.length; i++) {
			for (int j = 0; j < pieces[i].length; j++) {
				StdDraw.picture(this.xValue + (inc * pieces[i][j].getPos().getX()),
						this.yValue + (inc * pieces[i][j].getPos().getY()), pieces[i][j].toString(), scale, scale);
			}
		}
	}

}
