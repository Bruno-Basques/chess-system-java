package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import chessboard.Board;
import chessboard.Position;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);
		int rowMovePerColor = getColor() == Color.WHITE ? -1 : +1;

		p.setValues(position.getRow() + rowMovePerColor, position.getColumn());
		if (getBoard().positionExists(p, false) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		if (getMoveCount() == 0 && mat[p.getRow()][p.getColumn()]) {
			p.setValues(position.getRow() + (rowMovePerColor * 2), position.getColumn());
			if (getBoard().positionExists(p, false) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		}

		p.setValues(position.getRow() + rowMovePerColor, position.getColumn() - 1);
		if (getBoard().positionExists(p, false) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + rowMovePerColor, position.getColumn() + 1);
		if (getBoard().positionExists(p, false) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}

}
