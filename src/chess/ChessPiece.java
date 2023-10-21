package chess;

import chessboard.Board;
import chessboard.Piece;
import chessboard.Position;

public abstract class ChessPiece extends Piece{

	private Color color;
	private int moveCount;
	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	public void increaseMoveCount() {
		moveCount ++;
	}
	
	public void decreaseMoveCount() {
		moveCount --;
	}
	
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p.getColor() != color;
	}	
	
	protected boolean[][] possibleMovesPerDirection(boolean[][] mat, String direction) {
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
}
