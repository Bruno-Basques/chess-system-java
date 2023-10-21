package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import chessboard.Board;
import chessboard.Position;

public class Bishop extends ChessPiece {

	public Bishop(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "B";
	}

	private boolean[][] possibleMovesPerDirection(boolean[][] mat, String direction) {
		Position p = new Position(0, 0);

		switch (direction) {
		case "DiagonalTopLeft":
			p.setValues(position.getRow() - 1, position.getColumn() - 1);
			break;
		case "DiagonalTopRight":
			p.setValues(position.getRow() - 1, position.getColumn() + 1);
			break;
		case "DiagonalBottomLeft":
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			break;
		case "DiagonalBottomRight":
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			break;
		}

		while (getBoard().positionExists(p, false) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;

			switch (direction) {
			case "DiagonalTopLeft":
				p.setValues(p.getRow() - 1, p.getColumn() - 1);
				break;
			case "DiagonalTopRight":
				p.setValues(p.getRow() - 1, p.getColumn() + 1);
				break;
			case "DiagonalBottomLeft":
				p.setValues(p.getRow() + 1, p.getColumn() - 1);
				break;
			case "DiagonalBottomRight":
				p.setValues(p.getRow() + 1, p.getColumn() + 1);
				break;
			}
		}

		if (getBoard().positionExists(p, false) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		return mat;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		mat = possibleMovesPerDirection(mat, "DiagonalTopLeft");

		mat = possibleMovesPerDirection(mat, "DiagonalTopRight");

		mat = possibleMovesPerDirection(mat, "DiagonalBottomLeft");

		mat = possibleMovesPerDirection(mat, "DiagonalBottomRight");

		return mat;
	}

}
