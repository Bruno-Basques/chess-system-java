package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import chessboard.Board;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "R";
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
