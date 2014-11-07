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
 *The code is published in a public repository. 
 *https://github.com/Rodriwp/GameOfLife
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
                    //System.out.println("The cell have this value: "+i+","+j+": "+countLiveNeighbours);//For debugging only 
					
					//FIXME: Check these conditions and assign new values.
					
					
                   if(board[i][j]){
                	//Any live cell with fewer than two live neighbours dies, as if caused by under-population.
                	//Any live cell with more than three live neighbours dies, as if by overcrowding.
                	   if (countLiveNeighbours<2||countLiveNeighbours>3){
                		   newBoard[i][j]= false;
                	//Any live cell with two or three live neighbours lives on to the next generation.
                	   }else{
                		   newBoard[i][j]= true;
                	   }
   					
                	   
                   }else{
                	 if(countLiveNeighbours == 3){//Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
                		 newBoard[i][j] = true;
                	 }else{
                		 newBoard[i][j] = false; 
                	 }
                   }
			
					
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
	/*
	 * This function checks a matrix of dimension 3x3 center in the point given.
	 * It will go trough all the cells and count the numbers of cells alive.
	 * 
	 * In case you are in the border you can subtract rows and cols of this matrix
	 * by changin minrow, mincol,maxrow,maxcol by 0.
	 * By default you have to use 1 
	 */
    public static short checkingLoop(boolean [][] board,int row,int col,int minrow, int mincol,int maxrow, int maxcol){
    	short alive = 0;
		for(int i = row-minrow; i<= row+maxrow;i++){
			for(int j = col-mincol; j<= col+maxcol;j++){
				if(i== row && j==col){//Check if is the cell that we are evaluating
					continue;
				}
			    if(board[i][j] == true)	{//Check if the cell is alive 
				     alive++;//Sum an alive cell to the counter 
			    }
			}
		}
		return alive;
    }//End of chekingLoop() function 
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
		
		if(row != 0 && row != (nrows-1) && col!= 0 && col != (ncols-1) ){ //Check if the cell to evaluate is not in the border
			
				alive = checkingLoop(board,row,col,1,1,1,1);//Loop for cells that are not in the border
				
		}else if(!(row == 0 && col== 0) && !(row == (nrows-1) && col== 0)&&
				!(row == 0 && col== (ncols-1)) &&!(row == (nrows-1) &&col== (ncols-1)) ){//check that the cell is not in the corner 
			
				/*Code for the cells that are in the boarder but not in the corner 
				 * 
				 * We just avoid evaluating the row or col that is out of the board in each case 
				 * by changing the matrix 3x3 and extracting some row or col 
				 * */
                 
				if(row == 0){
					alive = checkingLoop(board,row,col,0,1,1,1);

				}else if(row == nrows-1){
					alive = checkingLoop(board,row,col,1,1,0,1);
				}else if(col == 0){
					alive = checkingLoop(board,row,col,1,0,1,1);
				}else {
					alive = checkingLoop(board,row,col,1,1,1,0);
				}
				
		}else{
				/*Code for the cells that are in the corner
				 * 
				 * We just avoid evaluating the row or col that is out of the board in each case 
				 * by changing the matrix 3x3 we evaluate just the L
				 * using the function checkingLoop
				 * 
				 * */
                 
				if(row == 0 && col == 0){
					alive = checkingLoop(board,row,col,0,0,1,1);

				}else if(row == nrows-1 && col == 0){
					alive = checkingLoop(board,row,col,1,0,0,1);
				}else if(row  == 0 && col == ncols-1){
					alive = checkingLoop(board,row,col,0,1,1,0);
				}else {
					alive = checkingLoop(board,row,col,1,1,0,0);
				}
			}
			 
		return alive;
	}//End of countLiveNeighbours()


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

