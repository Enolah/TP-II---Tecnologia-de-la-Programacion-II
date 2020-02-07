package tp.p1.logic;


import tp.p1.utils.MyStringUtils;

public class GamePrinter {
  
  Game game;
  int numRows; 
  int numCols;
  String [][] board;
  final String space = " ";
  
  public GamePrinter (Game game, int cols, int rows) {
    this.game = game;
    this.numRows = rows;
    this.numCols = cols;  
  }
  
  private void encodeGame() {
	//Codifica el game como una matriz numRowsxnumCols de Strings

    for(int i=0; i<numRows; i++) {
        for (int j=0; j<numCols; j++) {
        	game.board.toString(i, j);
    	}
    }
  }
  public String toString() {
  //  encodeGame();

    int cellSize = 7;
    int marginSize = 2;
    String vDelimiter = "|";
    String hDelimiter = "-";
    
    String rowDelimiter = MyStringUtils.repeat(hDelimiter, (numCols * (cellSize + 1)) - 1);
    String margin = MyStringUtils.repeat(space, marginSize);
    String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
    
    StringBuilder str = new StringBuilder();
    
    str.append(lineDelimiter);
    
    for(int i=0; i<numRows; i++) {
        str.append(margin).append(vDelimiter);
        for (int j=0; j<numCols; j++) {
          str.append( MyStringUtils.centre(game.board.toString(i, j), cellSize)).append(vDelimiter);
        }
        str.append(lineDelimiter);
    }
    return str.toString();
  }
  

}
