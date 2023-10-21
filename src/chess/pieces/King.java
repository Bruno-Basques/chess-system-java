package chess.pieces;

import java.util.ArrayList;
import java.util.List;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;
import chessboard.Board;
import chessboard.Position;

public class King extends ChessPiece {

	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);

		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}

	private boolean testPositionsRookCastling(String side) {
		Position positionRook;
		int distanceBetweenKingAndRook = 0;
		int auxMultiplier = 1;

		switch (side) {
		case "King side":
			distanceBetweenKingAndRook = 3;
			break;
		case "Queen side":
			distanceBetweenKingAndRook = -4;
			auxMultiplier = -1;
			break;
		}

		positionRook = new Position(position.getRow(), position.getColumn() + distanceBetweenKingAndRook);
		if (!testRookCastling(positionRook)) {
			return false;
		}

		for (int i = 1; i < (distanceBetweenKingAndRook * auxMultiplier); i++) {
			Position p = new Position(position.getRow(), position.getColumn() + (i * auxMultiplier));
			if(getBoard().piece(p) != null) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p, false) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p, false) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(p, false) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p, false) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p, false) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(p, false) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p, false) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p, false) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Special move castling
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {

			// King side rook
			mat[position.getRow()][position.getColumn() + 2] = testPositionsRookCastling("King side");

			// King side rook
			mat[position.getRow()][position.getColumn() - 2] = testPositionsRookCastling("Queen side");
		}

		return mat;
	}
}
