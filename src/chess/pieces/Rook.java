package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import chessboard.Board;
import chessboard.Position;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "R";
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
		}

		while (p.getRow() >= 0 && p.getRow() < getBoard().getRows() && p.getColumn() >= 0
				&& p.getColumn() < getBoard().getColumns() && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;

			switch (direction) {
			case "Top":
				p.setRow(p.getRow() - 1);
				break;
			case "Bottom":
				p.setRow(p.getRow() + 1);
				break;
			case "Left":
				p.setColumn(p.getColumn() - 1);
				break;
			case "Right":
				p.setColumn(p.getColumn() + 1);
				break;
			}
		}

		if (p.getRow() >= 0 && p.getRow() < getBoard().getRows() && p.getColumn() >= 0
				&& p.getColumn() < getBoard().getColumns() && isThereOpponentPiece(p)) {
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

		return mat;
	}
}
