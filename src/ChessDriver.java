import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings({"rawtypes", "unused"})
public class ChessDriver {
	/**
	 * Main board in Chess.
	 */
	private Board board;
	/**
	 * Width of GUI.
	 */
	private int width;
	/**
	 * Height of GUI.
	 */
	private int height;
	/**
	 * Value used in Standard Draw when placing things on the board, 0.062 = 0 position on board.
	 */
	private final double xValue = 0.062;
	/**
	 * Value used in Standard Draw when placing things on the board, 0.062 = 0 position on board.
	 */
	private final double yValue = 0.062;
	/**
	 * Scale of pictures in Standard Draw.
	 */
	private final double scale = 0.062+0.03;
	/**
	 * Value to increment xValue and yValue when placing things on board, xValue + inc = 1 position on board.
	 */
	private final double inc = 0.124;
	/**
	 * boolean value containing if chess is currently being run, useful in while loop listening for clicks.
	 */
	private boolean running = false;
	/**
	 * boolean value containing whether or not there is a piece selected, used for moving pieces and highlighting.
	 */
	private boolean isSelected = false;
	/**
	 * Current x value of currentPiece
	 */
	private int currentX = 0;
	/**
	 * y value of currentPiece
	 */
	private int currentY = 0;
	/**
	 * color of current piece
	 */
	private Color currentC = null;
	/**
	 * All colors of tiles in the board, initialized later
	 */
	private Color[][] colors = new Color[8][8];
	/**
	 * boolean value containing if user has clicked
	 */
	private boolean clicked = false;
	/**
	 * Position of click
	 */
	private Position posPiece;
	/**
	 * Position of clicked piece, very important when moving
	 */
	private Piece currentPiece;
	public static void main(String[] args) {
		ChessDriver driver = new ChessDriver();
		driver.createBoard(800, 800);
		StdDraw.setPenRadius(0.01);
		driver.createTiles();
		driver.addPieces();
		driver.listen();

	}
	/**
	 *
	 * @param w Width of GUI
	 * @param h Height of GUI
	 */
	public void createBoard(int w, int h){
		this.board = new Board();
		this.width = w;
		this.height = h;
		StdDraw.setCanvasSize(w, h);
		this.running = true;
	}

	/**
	 * Creates tiles of alternating color throughout the chess board in order to make a checkered playing board
	 */
	public void createTiles(){
		double x = xValue;
		double y = yValue;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(i % 2 == 0){
					if(j % 2 == 0){
						//This should be the darker color
						StdDraw.setPenColor(new Color(95,95,95));
						colors[i][j] = new Color(95,95,95);
					}else{
						//This should be the lighter color
						StdDraw.setPenColor(new Color(255,255,255));
						colors[i][j] = new Color(255,255,255);
					}

				}else{
					if(j % 2 == 0){
						//This should be the lighter color
						StdDraw.setPenColor(new Color(255,255,255));
						colors[i][j] = new Color(255,255,255);
					}else{
						//This should be the darker color
						StdDraw.setPenColor(new Color(95,95,95));
						colors[i][j] = new Color(95,95,95);
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
	public void addPieces(){
		this.board.add(new WhiteRook(0, 0), new Position(0, 0));
		StdDraw.picture(this.xValue, this.yValue, "WhiteRook.png", scale, scale);
		this.board.add(new WhiteKnight(1, 0), new Position(1, 0));
		StdDraw.picture(this.xValue + (inc*1), this.yValue, "WhiteKnight.png", scale, scale);
		this.board.add(new WhiteBishop(2, 0), new Position(2, 0));
		StdDraw.picture(this.xValue + (inc*2), this.yValue, "WhiteBishop.png", scale, scale);
		this.board.add(new WhiteQueen(3, 0), new Position(3, 0));
		StdDraw.picture(this.xValue + (inc*3), this.yValue, "WhiteQueen.png", scale, scale);
		this.board.add(new WhiteKing(4, 0), new Position(4, 0));
		StdDraw.picture(this.xValue + (inc*4), this.yValue, "WhiteKing.png", scale, scale);
		this.board.add(new WhiteBishop(5, 0), new Position(5, 0));
		StdDraw.picture(this.xValue + (inc*5), this.yValue, "WhiteBishop.png", scale, scale);
		this.board.add(new WhiteKnight(6, 0), new Position(6, 0));
		StdDraw.picture(this.xValue + (inc*6), this.yValue, "WhiteKnight.png", scale, scale);
		this.board.add(new WhiteRook(7, 0), new Position(7, 0));
		StdDraw.picture(this.xValue + (inc*7), this.yValue, "WhiteRook.png", scale, scale);
		this.board.add(new BlackRook(0, 7),  new Position(0, 7));
		StdDraw.picture(this.xValue, this.yValue + (inc*7), "BlackRook.png", scale, scale);
		this.board.add(new BlackKnight(1, 7), new Position(1, 7));
		StdDraw.picture(this.xValue + (inc*1), this.yValue + (inc*7), "BlackKnight.png", scale, scale);
		this.board.add(new BlackBishop(2, 7), new Position(2, 7));
		StdDraw.picture(this.xValue + (inc*2), this.yValue + (inc*7), "BlackBishop.png", scale, scale);
		this.board.add(new BlackQueen(3, 7), new Position(3, 7));
		StdDraw.picture(this.xValue + (inc*3), this.yValue + (inc*7), "BlackQueen.png", scale, scale);
		this.board.add(new BlackKing(4, 7), new Position(4, 7));
		StdDraw.picture(this.xValue + (inc*4), this.yValue + (inc*7), "BlackKing.png", scale, scale);
		this.board.add(new BlackBishop(5, 7), new Position(5, 7));
		StdDraw.picture(this.xValue + (inc*5), this.yValue + (inc*7), "BlackBishop.png", scale, scale);
		this.board.add(new BlackKnight(6, 7), new Position(6, 7));
		StdDraw.picture(this.xValue + (inc*6), this.yValue + (inc*7), "BlackKnight.png", scale, scale);
		this.board.add(new BlackRook(7, 7),  new Position(7, 7));
		StdDraw.picture(this.xValue + (inc*7), this.yValue + (inc*7), "BlackRook.png", scale, scale);
		for(int i = 0; i < 8; i++){
			  for(int j = 1; j < 8; j++){
			    if(j == 1){
			      this.board.add(new WhitePawn(i, j), new Position(i, j));
			      StdDraw.picture(this.xValue + (inc*i), this.yValue + (inc*j), "WhitePawn.png", scale, scale);
			    }else if(j == 6){
			      this.board.add(new BlackPawn(i, j), new Position(i, j));
			      StdDraw.picture(this.xValue + (inc*i), this.yValue + (inc*j), "BlackPawn.png", scale, scale);
			    }else if(j >= 2 && j <= 5)
			      this.board.add(new DefaultPiece(i, j), new Position(i, j)); //PLACEHOLDER so NullPointerException will never be thrown
			  }
			}
	}

	/**
	 * Listens for clicks in GUI
	 */
	public void listen(){
		while(this.running){
			if(StdDraw.isMousePressed()){
				click(StdDraw.mouseX(), StdDraw.mouseY());
				StdDraw.pause(120);
			}
			boolean whiteKing = false;
			boolean blackKing = false;
			Piece[][] pieces = this.board.getAllPieces();
			for(int i = 0; i < pieces.length; i++){
				for(int j = 0; j < pieces[i].length; j++){
					if(pieces[i][j] instanceof BlackKing){
						blackKing = true;
					}
					if(pieces[i][j] instanceof WhiteKing){
						whiteKing = true;
					}
				}
			}
			if(whiteKing == false){
				System.out.println("Player 2 wins!");
				this.running = false;
			}
			if(blackKing == false){
				System.out.println("Player 1 wins!");
				this.running = false;
			}
		}
	}

	/**
	 * Click listener
	 * @param x X coordinate clicked in GUI
	 * @param y Y coordinate clicked in GUI
	 */
	public void click(double x, double y){
		double xCoor = ((x - this.xValue)/inc);
		double yCoor = ((y - this.yValue)/inc);
		int xC = 0;
		int yC = 0;
		if((xCoor * 10) % 10 >= 5){
			xC = ((int) xCoor) + 1;
		}else{
			xC = ((int) xCoor);
		}
		if((yCoor * 10) % 10 >= 5){
			yC = ((int) yCoor) + 1;
		}else{
			yC = ((int) yCoor);
		}
		this.posPiece = new Position(xC, yC);
		if(this.isSelected){
			if(this.currentPiece.getPos().equals(this.posPiece)){//If a player clicks on the currently selected piece, it is unselected
				this.isSelected = false;
			    this.createTiles();
			    this.addPictures();
			}else if(currentPiece.isWhite() == this.board.get(posPiece).isWhite() && currentPiece.isBlack() == this.board.get(posPiece).isBlack()){
				selectPiece(posPiece.getX(), posPiece.getY());
				this.createTiles();
				this.addPictures();
				this.currentX = 0;
				this.currentY = 0;
				this.currentC = null;
				isSelected = false;
			}else{
				ArrayList<Position> moves = this.currentPiece.getPossibleMoves(this.board);
				for(int i = 0; i < moves.size(); i++){
					if(this.posPiece.equals(moves.get(i))){
						move(this.currentPiece, this.posPiece);
					}
				}
			}
		}else{
			if(this.board.get(this.posPiece) instanceof DefaultPiece){
				//Cancel click
			}else{
				//Allow selection of a piece only if it's that player's turn
				if(this.board.whiteTurn() == this.board.get(this.posPiece).isWhite() && this.board.blackTurn() == this.board.get(this.posPiece).isBlack()){
					selectPiece(this.posPiece.getX(), this.posPiece.getY());
					assignPiece();
					ArrayList<Position> moves = this.currentPiece.getPossibleMoves(this.board);
				}
			}
		}
	}
	/**
	 *
	 * @param x x Coordinate
	 * @param y y Coordinate
	 */
	public void selectPiece(int x, int y){
		double xCoor = xValue + (inc*x);
		double yCoor = yValue + (inc*y);
			if(this.board.get(new Position(x, y)) instanceof DefaultPiece){
				//No tile is highlighted
			}else{
			    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
			    StdDraw.rectangle(xCoor, yCoor, xValue-0.009, yValue-0.009);
				String p = this.board.get(new Position(x, y)).toString();
				StdDraw.picture(xCoor, yCoor, p, scale, scale);
			}
		}

	/**
	 *
	 * @param p Piece to move
	 * @param pos Position to move to
	 */
	public void move(Piece p, Position pos){
		Object type = this.board.getType(p.getPos());
		if(this.board.isValid(pos)){
			if((p.isWhite() == this.board.hasBlack(pos) && p.isBlack() == this.board.hasWhite(pos)) || (this.board.hasWhite(pos) == this.board.hasBlack(pos))){
				this.board.remove(pos);
				this.board.remove(p.getPos());
				this.board.add(new DefaultPiece(p.getPos().getX(), p.getPos().getY()), p.getPos()); //Adds DefaultPiece to p's position
				p.getPos().setX(pos.getX());
				p.getPos().setY(pos.getY());
				this.board.add((Piece) type, pos);
				if(type instanceof BlackPawn && !((BlackPawn)this.board.getType(pos)).hasMoved()){
					if(6 - pos.getY() == 2){
						Position leftPos = new Position(pos.getX() - 1, pos.getY());
						Position rightPos = new Position(pos.getX() + 1, pos.getY());
						if(this.board.isValid(leftPos)){
							if(this.board.get(leftPos) instanceof WhitePawn){
								((WhitePawn)this.board.get(leftPos)).canPassant(true);
							}
						}
						if(this.board.isValid(rightPos)){
							if(this.board.get(rightPos) instanceof WhitePawn){
								((WhitePawn)this.board.get(rightPos)).canPassant(true);
							}
						}
					}
					((BlackPawn)this.board.getType(pos)).moved();
				}
				if(type instanceof WhitePawn && !((WhitePawn)this.board.getType(pos)).hasMoved()){
					if(pos.getY() - 1 == 2){
						Position leftPos = new Position(pos.getX() - 1, pos.getY());
						Position rightPos = new Position(pos.getX() + 1, pos.getY());
						if(this.board.isValid(leftPos)){
							if(this.board.get(leftPos) instanceof BlackPawn){
								((BlackPawn)this.board.get(leftPos)).canPassant(true);
							}
						}
						if(this.board.isValid(rightPos)){
							if(this.board.get(rightPos) instanceof BlackPawn){
								((BlackPawn)this.board.get(rightPos)).canPassant(true);
							}
						}
					}
					((WhitePawn)this.board.getType(pos)).moved();
				}
			    StdDraw.setPenColor(colors[p.getPos().getX()][p.getPos().getY()]);
			    StdDraw.filledRectangle(xValue + (inc*p.getPos().getX()), yValue + (inc*p.getPos().getY()), xValue, yValue);
			    StdDraw.setPenColor(colors[pos.getX()][pos.getY()]);
			    StdDraw.filledRectangle(xValue + (inc*pos.getX()), yValue + (inc*pos.getY()), xValue, yValue);
			    StdDraw.picture(xValue + (inc*pos.getX()), yValue + (inc*pos.getY()), p.toString(), scale, scale);
			    this.createTiles();
			    this.addPictures();
			    this.isSelected = false;
			    this.currentX = 0;
			    this.currentY = 0;
			    this.currentC = null;
				this.board.incTurn();

				if(type instanceof BlackKing){
					//Castle check and moving rook
					
					if(!((BlackKing)p).hasMoved()){
						if(4 - pos.getX() > 1){
						this.currentPiece = this.board.get(new Position(0, 7));
						move(this.currentPiece, new Position(3, 7));
						this.board.incTurn();
					}else if(4 - pos.getX() < -1){
						this.currentPiece = this.board.get(new Position(7, 7));
						move(this.currentPiece, new Position(5, 7));
						this.board.incTurn();
					}
						((BlackKing)p).moved();
					}
				}
				if(type instanceof WhiteKing){
					//System.out.println(p.getPos().getX() + "   " + pos.getX());
					
					if(!((WhiteKing)p).hasMoved()){
						if(4 - pos.getX() > 1){
						this.currentPiece = this.board.get(new Position(0, 0));
						move(this.currentPiece, new Position(3, 0));
						this.board.incTurn();
					}else if(4 - pos.getX() < -1){
						this.currentPiece = this.board.get(new Position(7, 0));
						move(this.currentPiece, new Position(5, 0));
						this.board.incTurn();
						System.out.println("haha dummy");
					}
						((WhiteKing)p).moved();
					}
				}
				//Turns hasMoved variable to true after being moved
				if(type instanceof BlackRook && !((BlackRook)p).hasMoved())
					((BlackRook)p).moved();
				if(type instanceof WhiteRook && !((WhiteRook)p).hasMoved())
					((WhiteRook)p).moved();
		    }else{

		    }
		}else{
			System.out.println("\u001B[31m" + "Invalid move, please select a new move or piece." + "\u001B[0m");
			this.currentX = 0;
			this.currentY = 0;
			this.currentC = null;
			isSelected = false;
		}
	}

	/**
	 * Assigns currentPiece the value of posPiece (currentPiece is a placeholder of sorts)
	 * Says a piece has been selected
	 * Assigns new color in color array for easier access when redoing rectangles
	 */
	public void assignPiece(){
		this.isSelected = true; //A piece is selected
		this.currentPiece = this.board.get(this.posPiece);
		this.currentC = colors[this.currentPiece.getPos().getX()][this.currentPiece.getPos().getY()];
	}

	/**
	 * Adds pictures for all of the pieces in the board, called when board is refreshed
	 */
	public void addPictures(){
		Piece[][] pieces = this.board.getAllPieces();
		for(int i = 0; i < pieces.length; i++){
			for(int j = 0; j < pieces[i].length; j++){
				StdDraw.picture(this.xValue + (inc * pieces[i][j].getPos().getX()), this.yValue + (inc * pieces[i][j].getPos().getY()), pieces[i][j].toString(), scale, scale);
			}
		}
	}


}
