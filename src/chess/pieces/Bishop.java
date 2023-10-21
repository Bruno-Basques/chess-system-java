package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import chessboard.Board;

public class Bishop extends ChessPiece {

	public Bishop(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "B";
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
