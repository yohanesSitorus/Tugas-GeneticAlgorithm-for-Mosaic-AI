import java.util.Random ;

public class Individual  implements Comparable<Individual>{
    public Mosaic chromosome;
    public int fitness;
    public Random MyRand;
    public double parentProbability;
    public int size ;

    public Individual(Random MyRand, int size) {
        this.MyRand = MyRand;
        // System.out.println("MyRand: " + this.MyRand) ;
        this.size = size ;
        this.fitness = 0;
        this.parentProbability = 0;
        this.fillChromosome() ;
    }

    public Individual(Random MyRand, int chromosome) {
        this.MyRand = MyRand;
        this.chromosome = chromosome;
        this.fitness = 0;
        this.parentProbability = 0;
    }

    public void fillChromosome() {
        this.chromosome = new Mosaic(this.size) ;
        int randRow = this.MyRand.nextInt(this.size-1) ;
        int randCol = this.MyRand.nextInt(this.size-1) ;
        for(int i = 0 ; i < this.chromosome.length ; i++) {
            for(int j = 0 ; j < this.chromosome.length ; j++)
                if(i == randRow && j == randCol) {
                    this.chromosome.grid[i][j] = 1 ;
                }else{
                    this.chromosome.grid[i][j] = 0 ;
                }
        }
    }

    public int setFitness(Mosaic mosaic) {
        Mosaic mosaic = mosaic ;
        int poin ;
        for (int i=0;i<mosaic.size;i++) {
            for(int j=0;j<mosaic.size;j++) {
                poin = 0 ;
                if(mosaic.grid[i][j] != -1) { //memeriksa apakah sebuah cell berisi nomor(!=-1) atau tidak (-1)
                    if(i==0 && j==0) { //bagian pojok kiri atas mosaic
                        poin = this.chromosome[i][j] + this.chromosome[i][j+1] + this.chromosome[i+1][j+1] + this.chromosome[i+1][j] ;
                    }else if(i==0 && j==mosaic.size-1)  { //bagian pojok kanan atas mosaic
                        poin = this.chromosome[i][j] + this.chromosome[i+1][j] + this.chromosome[i-1][j+1] + this.chromosome[i][j-1] ;
                    }else if(i==mosaic.size-1 && j==0) { //bagian pojok kiri bawah mosaic
                        poin = this.chromosome[i][j] + this.chromosome[i][j+1] + this.chromosome[i-1][j+1] + this.chromosome[i-1][j] ;
                    }else if(i == mosaic.size-1 && j == mosaic.size-1) { //bagian pojok kanan bawah mosaic
                        poin = this.chromosome[i][j] + this.chromosome[i-1][j] + this.chromosome[i-1][j-1] + this.chromosome[i][j-1] ;
                    }else if(i == 0) { //bagian pinggir atas tpi bukan pojok 
                        poin = this.chromosome[i][j] + this.chromosome[i][j+1] + this.chromosome[i+1][j+1] + this.chromosome[i+1][j] + this.chromosome[i-1][j-1] + this.chromosome[i][j-1] ;
                    }else if(j == 0) { //bagian pinggir kiri tpi bukan pojok
                        poin = this.chromosome[i][j] + this.chromosome[i-1][j] + this.chromosome[i-1][j+1] + this.chromosome[i][j+1] + this.chromosome[i+1][j+1] + this.chromosome[i+1][j] ;
                    }else if(i == mosaic.size-1) { //pagian pinggir bawah tpi bukan pojok
                        poin = this.chromosome[i][j] + this.chromosome[i][j-1] + this.chromosome[i-1][j-1] + this.chromosome[i-1][j] + this.chromosome[i-1][j+1] + this.chromosome[i][j+1] ;
                    }else if(j == mosaic.size-1) { //bagian pinggir kanan tpi bukan pojok 
                        poin = this.chromosome[i][j] + this.chromosome[i-1][j] + this.chromosome[i-1][j-1] + this.chromosome[i][j-1] + this.chromosome[i+1][j-1] + this.chromosome[i+1][j] ;
                    }else{
                        poin = this.chromosome[i][j] + this.chromosome[i][j+1] + this.chromosome[i+1][j+1] + this.chromosome[i+1][j] + this.chromosome[i+1][j-1] + this.chromosome[i][j-1] + this.chromosome[i-1][j-1] + this.chromosome[i+1][j] + this.chromosome[i-1][j+1];
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

    public Individual doCrossover(Individual other) {	//two points
        
    }

}
