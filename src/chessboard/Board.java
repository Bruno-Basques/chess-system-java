package chessboard;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		boardValidation(rows, columns);	
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	private void boardValidation(int rows, int columns) {
		if(rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getColumns() {
		return columns;
	}
	
	public boolean positionExists(int row, int column) {
		return row >= 0 && 
				row < rows && 
				column >= 0 && 
				column < columns;
	}
	
	public Piece piece(int row, int column)
	{
		if(!positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];
	}
	
	public void placePiece(Piece piece, Position position) {
		if(thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position: " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
		
	public boolean thereIsAPiece(Position position) {
		if(!positionExists(position.getRow(), position.getColumn())) {
			throw new BoardException("Position not on the board");
		}
		return piece(position.getRow(), position.getColumn()) != null;
	}
}
