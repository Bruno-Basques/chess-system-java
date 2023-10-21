package chess.pieces;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;
import chessboard.Board;
import chessboard.Position;

public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
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

		// Special move en passant
		int rowThatAllowsEnPassant = getColor() == Color.WHITE ? 3 : 4;

		for (int i = 0; i < 2; i++) {
			int columnOfPossibleMoviment = i == 0 ? -1 : 1;
			int rowOfPossibleMoviment = getColor() == Color.WHITE ? -1 : 1;

			if (position.getRow() == rowThatAllowsEnPassant) {
				Position left = new Position(position.getRow(), position.getColumn() + columnOfPossibleMoviment);
				if (getBoard().positionExists(left, false) && isThereOpponentPiece(left)
						&& getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() + rowOfPossibleMoviment][left.getColumn()] = true;
				}
			}
		}

		return mat;
	}

}
