package application;

import chess.ChessPiece;

public class UI {
	public static void printBoard(ChessPiece[][] pieces) {
		
		for(int i = 0; i<pieces.length; i++) {
			System.out.print((8 - i + " "));
			
			for(int j = 0; j<pieces.length; j++) {
					printPiece(pieces, i, j);
				}
			System.out.println();
			}
		System.out.println("  a b c d e f g h");
	}
	
	private static void printPiece(ChessPiece[][] pieces, int row, int column) {
		if(pieces[row][column] != null) {
			System.out.print(pieces[row][column]);			
		}
		else {
			if(row % 2 == 0 && column % 2 == 0 ||
					row % 2 != 0 && column % 2 != 0)
			{
				System.out.print("~");
			}
			else
			{
				System.out.print("-");
			}			
		}
		
		System.out.print(" ");
	}
}
