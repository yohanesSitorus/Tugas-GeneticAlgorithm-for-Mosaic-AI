import java.util.Random ;

public class Individual  implements Comparable<Individual>{
    public Mosaic chromosome;
    public int fitness;
    public Random MyRand;
    public double parentProbability;
    public int size ;
    Mosaic mosaic ;

    public Individual(Random MyRand, Mosaic mosaic, int size) {
        this.MyRand = MyRand;
        // System.out.println("MyRand: " + this.MyRand) ;
        this.mosaic = mosaic ;
        this.size = size ;
        this.fitness = 0;
        this.parentProbability = 0;
        this.fillChromosome() ;
    }

    public void fillChromosome() {
        this.chromosome = new Mosaic(this.size) ;
        int randRow = this.MyRand.nextInt(this.size-1) ;
        int randCol = this.MyRand.nextInt(this.size-1) ;
        for(int i = 0 ; i < this.chromosome.size ; i++) {
            for(int j = 0 ; j < this.chromosome.size ; j++)
                if(i == randRow && j == randCol) {
                    this.chromosome.grid[i][j] = 1 ;
                }else{
                    this.chromosome.grid[i][j] = 0 ;
                }
        }
    }

    public int setFitness() {
        int poin ;
        for (int i=0;i<mosaic.size;i++) {
            for(int j=0;j<mosaic.size;j++) {
                poin = 0 ;
                if(mosaic.grid[i][j] != -1) { //memeriksa apakah sebuah cell berisi nomor(!=-1) atau tidak (-1)
                    if(i==0 && j==0) { //bagian pojok kiri atas mosaic
                        poin = this.chromosome.grid[i][j] + this.chromosome.grid[i][j+1] + this.chromosome.grid[i+1][j+1] + this.chromosome.grid[i+1][j] ;
                    }else if(i==0 && j==mosaic.size-1)  { //bagian pojok kanan atas mosaic
                        poin = this.chromosome.grid[i][j] + this.chromosome.grid[i+1][j] + this.chromosome.grid[i-1][j+1] + this.chromosome.grid[i][j-1] ;
                    }else if(i==mosaic.size-1 && j==0) { //bagian pojok kiri bawah mosaic
                        poin = this.chromosome.grid[i][j] + this.chromosome.grid[i][j+1] + this.chromosome.grid[i-1][j+1] + this.chromosome.grid[i-1][j] ;
                    }else if(i == mosaic.size-1 && j == mosaic.size-1) { //bagian pojok kanan bawah mosaic
                        poin = this.chromosome.grid[i][j] + this.chromosome.grid[i-1][j] + this.chromosome.grid[i-1][j-1] + this.chromosome.grid[i][j-1] ;
                    }else if(i == 0) { //bagian pinggir atas tpi bukan pojok 
                        poin = this.chromosome.grid[i][j] + this.chromosome.grid[i][j+1] + this.chromosome.grid[i+1][j+1] + this.chromosome.grid[i+1][j] + this.chromosome.grid[i+1][j-1] + this.chromosome.grid[i][j-1] ;
                    }else if(j == 0) { //bagian pinggir kiri tpi bukan pojok
                        poin = this.chromosome.grid[i][j] + this.chromosome.grid[i-1][j] + this.chromosome.grid[i-1][j+1] + this.chromosome.grid[i][j+1] + this.chromosome.grid[i+1][j+1] + this.chromosome.grid[i+1][j] ;
                    }else if(i == mosaic.size-1) { //pagian pinggir bawah tpi bukan pojok
                        poin = this.chromosome.grid[i][j] + this.chromosome.grid[i][j-1] + this.chromosome.grid[i-1][j-1] + this.chromosome.grid[i-1][j] + this.chromosome.grid[i-1][j+1] + this.chromosome.grid[i][j+1] ;
                    }else if(j == mosaic.size-1) { //bagian pinggir kanan tpi bukan pojok 
                        poin = this.chromosome.grid[i][j] + this.chromosome.grid[i-1][j] + this.chromosome.grid[i-1][j-1] + this.chromosome.grid[i][j-1] + this.chromosome.grid[i+1][j-1] + this.chromosome.grid[i+1][j] ;
                    }else{
                        poin = this.chromosome.grid[i][j] + this.chromosome.grid[i][j+1] + this.chromosome.grid[i+1][j+1] + this.chromosome.grid[i+1][j] + this.chromosome.grid[i+1][j-1] + this.chromosome.grid[i][j-1] + this.chromosome.grid[i-1][j-1] + this.chromosome.grid[i+1][j] + this.chromosome.grid[i-1][j+1];
                    }
                }
                
                //memeriksa apakah box hitam di tetangga suatu cell sdh melebihi nomor dalam cell tsb
                if(poin > mosaic.grid[i][j]) {
                    poin = mosaic.grid[i][j]-poin;
                }
                this.fitness += poin ;
            }
        }
        return this.fitness;
    }

    public void doMutation() {
        int mutatedRow = this.MyRand.nextInt(this.size-1) ;
        int mutatedCol = this.MyRand.nextInt(this.size-1) ;
        
        if(this.chromosome.grid[mutatedRow][mutatedCol] == 1){
            this.chromosome.grid[mutatedRow][mutatedCol] = 0 ;
        }else{
            this.chromosome.grid[mutatedRow][mutatedCol] = 1 ;
        }
        
    }

    public Individual doCrossover(Individual other) {	//two points
        Individual child1 = new Individual(this.MyRand, this.mosaic, this.size);
        Individual child2 = new Individual(this.MyRand, this.mosaic, this.size);

        for(int i = 0 ; i < this.size ; i++){
            for(int j = 0 ; j < this.size ; j++) {
                if(i%2==1){
                    child1.chromosome.grid[i][j] = other.chromosome.grid[i][j] ;
                    child2.chromosome.grid[i][j] = this.chromosome.grid[i][j] ;
                }else{
                    child1.chromosome.grid[i][j] = this.chromosome.grid[i][j] ;
                    child2.chromosome.grid[i][j] = other.chromosome.grid[i][j] ;
                }
                
            }
        }
        int choose = this.MyRand.nextInt(2);
        //System.out.println(choose);
        //System.out.println("-----");
        if (choose==0) return child1;
        else return child2;
        //return child;
    }

    @Override
    public int compareTo(Individual other) {
    	if (this.fitness>other.fitness) return -1;
        else if (this.fitness<other.fitness) return 1;
        else return 0;
    }

    @Override
	public String toString() {
		String res = new String(this.chromosome.grid + " " + this.fitness);
		return res;
	}

    public String printChromosome() {
        String linearChromosome = "" ;
        for(int i = 0 ; i < this.size ; i++){
            for(int j = 0 ; j < this.size ; j++){
                linearChromosome += this.chromosome.grid[i][j] ;
            }
        }
        return linearChromosome ;
    }

}
