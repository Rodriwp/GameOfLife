package gol;

import java.io.IOException;

/**
 * This class presents the classical simulation Game of Life using functions. Some functionalities must be complete. 
 * 
 * See FIXME comments to implement the missing functionality.
 * 
 * @see http://en.wikipedia.org/wiki/Conway's_Game_of_Life
 * Teacher:
 * @author chema
 * Students:
 * @author Rodrigo Mompo Redoli 
 * @author Jorge Ramirez Cobo 
 *
 */
public class SimpleGameOfLifeToComplete {

	private static final int NGENERATIONS = 100;
	private static final int SIZE = 20;


	public static void main(String []args) throws IOException, InterruptedException{
		boolean [][]board = new boolean[SIZE][SIZE];
		boolean [][] newBoard = new boolean[SIZE][SIZE];
		int countLiveNeighbours=0;
		int totalAlive = 0;
		for(int i = 0;i<board.length;i++){
			for (int j= 0; j<board[0].length;j++){
				board[i][j]=Math.random()>0.5;
				totalAlive+=(board[i][j]?1:0);
			}
		}
		//board = demoPattern();

		for(int generation=0;generation<NGENERATIONS;generation++){
			//Evolution
			System.out.println("Generation: "+generation+" total alive: "+totalAlive);
			show(board);
			totalAlive = 0;
			newBoard= new boolean[SIZE][SIZE];
			for(int i = 0;i<board.length;i++){
				for (int j= 0; j<board[0].length;j++){
					
					//FIXME: Implement this function.
					countLiveNeighbours=countLiveNeighbours(board,i,j);
					
					//FIXME: Check these conditions and assign new values.
					//Any live cell with fewer than two live neighbours dies, as if caused by under-population.
					//Any live cell with two or three live neighbours lives on to the next generation.
					//Any live cell with more than three live neighbours dies, as if by overcrowding.
					//Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

			
					
					totalAlive+=(newBoard[i][j]?1:0);
				}
			}//Updating board

			//Copy newBoard into board
			for(int i = 0;i<board.length;i++){
				for (int j= 0; j<board[0].length;j++){
					board[i][j] = newBoard[i][j];
				}
			}

			Thread.currentThread().sleep(200);
		}

	}

	/**
	 * Given a board of boolean values where 1 is an alive cell and 0 a died one. The function must return 
	 * the number of alive cells around a cell(row, col).
	 * @param board The board to check.
	 * @param row Row of the cell to check.
	 * @param col Col of the cell to check.
	 * @return The number of alive neigbours around cell (row, col).
	 */
	public static short countLiveNeighbours(boolean [][]board, int row, int col){
		//FIXME: Implement this function. Calculate the number of alive neighbours around the cell(row,col).
		short alive = 0;
		int nrows = board.length;
		int ncols = board[0].length;
		//FIXME: Check if the neighbours are alive or not.
		return alive;
	}


	public static void show(boolean [][]board){
		for(int i = 0;i<board.length;i++){
			for (int j= 0; j<board[0].length;j++){
				if(board[i][j]){
					System.out.print("\t \u2665");
				}else{
					System.out.print("\t \u271D");
				}
			}
			System.out.println();
		}
	}

	public static boolean [][] demoPattern(){
		return new boolean[][]{
				{ true,	 true,	 true,	 true,	 false,	 false,	 true,	 false,	 false,	 false},
				{ false,	 true,	 true,	 false,	 false,	 true,	 true,	 true,	 false,	 false},
				{ true,	 true,	 false,	 true,	 false,	 true,	 false,	 false,	 true,	 true},
				{ false,	 true,	 false,	 false,	 false,	 false,	 false,	 false,	 false,	 false},
				{ false,	 true,	 true,	 true,	 false,	 true,	 true,	 true,	 false,	 false},
				{ true,	 false,	 true,	 true,	 false,	 true,	 true,	 true,	 true,	 true},
				{ true,	 false,	 true,	 false,	 false,	 true,	 true,	 false,	 true,	 false},
				{ true,	 false,	 false,	 true,	 false,	 false,	 false,	 true,	 false,	 false},
				{ true,	 false,	 false,	 true,	 false,	 true,	 true,	 true,	 true,	 false},
				{ false,	 false,	 true,	 true,	 false,	 false,	 false,	 false,	 true,	 true} 
		};
	}

}

