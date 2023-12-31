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
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	private boolean positionExists(int row, int column) {
		if (row >= 0 && row < rows && column >= 0 && column < columns) {
			return true;
		} else {
			return false;
		}
	}

	public boolean positionExists(Position position, boolean throwException) {
		
		boolean positionExists = positionExists(position.getRow(), position.getColumn());
		if(throwException && !positionExists) {
			throw new BoardException("Position not on the board");
		}
		return positionExists;
	}

	private Piece piece(int row, int column) {
		positionExists(new Position(row, column), true);
		return pieces[row][column];
	}

	public Piece piece(Position position) {
		return piece(position.getRow(), position.getColumn());
	}

	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position: " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}

	public Piece removePiece(Position position) {
		positionExists(position, true);
		if (piece(position) == null) {
			return null;
		}
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}

	public boolean thereIsAPiece(Position position) {
		positionExists(position, true);
		return piece(position.getRow(), position.getColumn()) != null;
	}
}
