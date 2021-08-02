public class ZigZag {

    public static void main(String[] args){
        String inputStr = "PAYPALISHIRING";
        int rows = 5;
        int cols = getColumnSize(inputStr, rows);
        char[][] grid  = constructGrid(inputStr,rows,cols);
        printGrid(grid,rows,cols);
        System.out.println(" ");
        printZigZag(grid,rows,cols);

    }

    private static void printGrid( char[][] grid, int rows, int cols ) {
        for(int r=0;r<rows;r++){
            for(int c=0; c< cols; c++){
                System.out.print(grid[r][c] + " ");
            }
            System.out.println(" ");
        }
    }

    private static void printZigZag( char[][] grid, int rows, int cols ) {
        for(int r=0;r<rows;r++){
            for(int c=0; c< cols; c++){
                if(grid[r][c] != '0'){
                    System.out.print(grid[r][c] + " ");
                }
            }
            System.out.println(" ");
        }
    }

    private static char[][] constructGrid( String inputStr, int rows, int cols ) {
        char[][] grid = new char[rows][cols];
        int i=0;
        int j=rows-1;
        int k=0;
        boolean skip;
        for(int c=0; c<cols; c++){
            skip = c != (rows -1)*k;
            if(!skip){
                k++;
            }
            for(int r=0;r<rows;r++){
                if(i == inputStr.length()){
                    grid[r][c] = '0';
                    continue;
                }
                if(!skip || r==j){
                    grid[r][c] = inputStr.charAt(i++);
                }else{
                    grid[r][c] = '0';
                }
            }
            j--;
            if(j <0 ){
                j = rows-2;
            }
        }
        return grid;
    }

    private static int getColumnSize( String inputStr, int rows ) {

        if(rows == 1)
            return inputStr.length();

        int len =  inputStr.length();
        int blockLen = rows + (rows-2);
        int midLen = rows -1;
        int remainder = len%blockLen;

        int cols = (len/blockLen) * midLen;
        if(remainder > 0){
            cols = cols + 1;
        }
        if(remainder > rows){
            cols = cols + remainder%rows;
        }

        return cols;
    }

}
