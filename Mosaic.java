import java.util.Scanner ;

public class Mosaic {

    public int size ;
    public int [][] grid ;

    public Mosaic(int size) {
        this.size = size ;
        grid = new int [size][size] ;
    }

    public void setGrid(int row, int col, int value) {
        this.grid[row][col] = value ;
    }

}
