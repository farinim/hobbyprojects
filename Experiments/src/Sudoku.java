public class Sudoku {

    int GRID_SIZE = 9;
    public boolean isNumberInRow(int[][] board, int row, int num){
        for(int col=0; col<GRID_SIZE; col++){
            if(board[row][col] == num){
                return true;
            }
        }
        return false;
    }

    public boolean isNumberInCol(int[][] board, int col, int num){
        for(int row=0; row<GRID_SIZE; row++){
            if(board[row][col] == num){
                return true;
            }
        }
        return false;
    }

    public boolean isNumberInBox(int[][] board, int num, int row,  int col){
        int boxStarRow = row -row%3;
        int boxStartCol = col - col%3;
        for(int i=boxStarRow ; i<boxStarRow +3; i++){
            for (int j=boxStartCol; j< boxStartCol +3; j++)
                if(board[i][j] == num){
                    return true;
                }
        }
        return false;
    }

    public boolean isNumberUsed(int[][] board, int num, int row, int col){
        if(isNumberInRow(board, row, num) ||
                isNumberInCol(board, col, num) ||
                isNumberInBox(board, num, row, col)
        )
        { return true;}
        else
        {return false;}

    }

    public boolean solve(int[][] board){
        for(int row=0; row< GRID_SIZE; row++){
            for(int col=0; col<GRID_SIZE; col++){
                if(board[row][col] == 0){
                    for(int n=1; n<=GRID_SIZE; n++){
                        if(!isNumberUsed(board, n, row, col)){
                            board[row][col] = n;
                            if(!solve(board)){
                                board[row][col] = 0;
                                //return false;
                            }
                        }
                    }
                    if(board[row][col] == 0) {
                        //System.out.println("row/col : ["+ row +"]["+col+"]");
                        return false;
                    }
                }
            }
        }
        return true;
    }


    private void printBoard( int[][] board ) {
        for(int row=0; row <GRID_SIZE; row++){
            for (int col=0; col<GRID_SIZE; col++){
                System.out.print(board[row][col] + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args){
        Sudoku puzzle = new Sudoku();
        int[][] board = {
                {8,0,0,7,1,5,0,0,4},
                {0,0,5,3,0,6,7,0,0},
                {3,0,6,4,0,8,9,0,1},
                {0,6,0,0,5,0,0,4,0},
                {0,0,0,8,0,7,0,0,0},
                {0,5,0,0,4,0,0,9,0},
                {6,0,9,5,0,3,4,0,2},
                {0,0,4,9,0,2,5,0,0},
                {5,0,0,1,6,4,0,0,9}
        };

        if(puzzle.solve(board)){
            puzzle.printBoard(board);
        }else{
            System.out.println("Board is not solvable");

        }
    }

}
