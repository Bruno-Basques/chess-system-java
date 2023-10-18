package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import chessboard.Board;
import chessboard.Position;

public class King extends ChessPiece{

	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "K";
	}
	
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	private boolean[][] possibleMovesPerDirection(boolean[][] mat, String direction) {
		Position p = new Position(0, 0);

		switch (direction) {
		case "Top":
			p.setValues(position.getRow() - 1, position.getColumn());
			break;
		case "Bottom":
			p.setValues(position.getRow() + 1, position.getColumn());
			break;
		case "Left":
			p.setValues(position.getRow(), position.getColumn() - 1);
			break;
		case "Right":
			p.setValues(position.getRow(), position.getColumn() + 1);
			break;
		case "Diagonal TopRight":
			p.setValues(position.getRow() - 1, position.getColumn() + 1);
			break;
		case "Diagonal TopLeft":
			p.setValues(position.getRow() - 1, position.getColumn() - 1);
			break;
		case "Diagonal BottomRight":
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			break;
		case "Diagonal BottomLeft":
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			break;
		}
		
		if(getBoard().positionExists(p, false) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
				
		mat = possibleMovesPerDirection(mat, "Top");

		mat = possibleMovesPerDirection(mat, "Left");

		mat = possibleMovesPerDirection(mat, "Right");

		mat = possibleMovesPerDirection(mat, "Bottom");
		
		mat = possibleMovesPerDirection(mat, "Diagonal TopRight");

		mat = possibleMovesPerDirection(mat, "Diagonal TopLeft");

		mat = possibleMovesPerDirection(mat, "Diagonal BottomRight");

		mat = possibleMovesPerDirection(mat, "Diagonal BottomLeft");
		
		return mat;
	}
}
