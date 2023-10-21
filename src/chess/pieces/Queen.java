package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import chessboard.Board;

public class Queen extends ChessPiece {

	public Queen(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "Q";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		mat = possibleMovesPerDirection(mat, "Top");

		mat = possibleMovesPerDirection(mat, "Left");

		mat = possibleMovesPerDirection(mat, "Right");

		mat = possibleMovesPerDirection(mat, "Bottom");
		
		mat = possibleMovesPerDirection(mat, "DiagonalTopLeft");

		mat = possibleMovesPerDirection(mat, "DiagonalTopRight");

		mat = possibleMovesPerDirection(mat, "DiagonalBottomLeft");

		mat = possibleMovesPerDirection(mat, "DiagonalBottomRight");

		return mat;
	}

}
