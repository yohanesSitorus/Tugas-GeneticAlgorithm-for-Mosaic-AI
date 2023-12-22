
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

	public void printGrid() {
        String res ;
        for(int i = 0 ; i < this.size ; i++){
            res = "" ;
            for(int j = 0 ; j < this.size ; j++){
                res += this.grid[i][j] + " ";
            }
            System.out.println(res);
        }
	}

}
