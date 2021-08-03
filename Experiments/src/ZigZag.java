import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class ZigZag {

    public static void main(String[] args){
        String inputStr = "PAYPALISHIRING";
        int rows = 5;
        int cols = getColumnSize(inputStr, rows);
        /*char[][] grid  = constructGrid(inputStr,rows,cols);
        printGrid(grid,rows,cols);
        System.out.println(" ");
        printZigZag(grid,rows,cols);*/
        Map<String, Character> sparseMap = constructMap(inputStr, rows);
        printGrid(sparseMap, rows);

    }

    private static void printGrid( Map<String,Character> grid, int rows) {

        String[] s = ".".split("-");
        OptionalInt cols = grid.keySet().stream().map(key -> key.split("-")).mapToInt(x -> Integer.parseInt(x[1])).max();
        System.out.println("Cols size = " + cols);
        for(int r=0;r<rows;r++){
            for(int c=0; c<= cols.getAsInt(); c++){
                Character value = grid.get(r+"-"+c);
                if(null != value) {
                    System.out.print(value + " ");
                }
            }
            //System.out.println(" ");
        }
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

    private static Map<String,Character> constructMap( String inputStr, int rows ) {
        Map<String,Character> grid = new HashMap<>();
        int i=0;
        int j=rows-1;
        int k=0;
        boolean skip;
        int c =0;
        int len = inputStr.length();
        while(len > 0){
            skip = c != (rows -1)*k;
            if(!skip){
                k++;
            }
            for(int r=0;r<rows;r++){
                if(i == inputStr.length()){
                    continue;
                }
                if(!skip || r==j){
                    String key = r+"-"+c;
                    grid.put(key, inputStr.charAt(i++));
                    len--;
                }
            }
            j--;
            if(j <0 ){
                j = rows-2;
            }
            c++;
        }
        return grid;
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
