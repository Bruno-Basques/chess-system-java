package chess;

import chessboard.Position;

public class ChessPosition {

	private char column;
	private int row;
	public ChessPosition(char column, int row) {
		chessPositionValidation(column, row);
		this.column = column;
		this.row = row;
	}
	
	private void chessPositionValidation(char columns, int rows) {
		if(columns < 'a' || columns > 'h' || rows < 1 || rows > 8) {
			throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8.");
		}
	}

	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
	
	protected Position toPosition() {
		return new Position((8 - row), (column - 'a'));
	}
	
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)('a' - position.getColumn()), (8 - position.getRow()));
	}
	
	@Override
	public String toString() {
		return "" + column + row;
	}
}
