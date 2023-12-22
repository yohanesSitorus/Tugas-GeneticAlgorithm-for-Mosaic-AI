import java.util.Random; // Menginisialisasi Random number generator
// inisialisasi kelas GeneticAlg 
public class GeneticAlg {
    private Random rn; // variabel rn berbendutk random number
    public int maxPopulationSize; // varieabel maxPopulationSize dalam integer
    public double elitismPct; // variabel elitisme dalam double
    public double crossoverRate;// variabel kemungkinan crossover dalam double
    public double mutationRate; // variabel kemungkinan mutasi dalam double
    public int totalGeneration; // vairabel total generasi dalam integer
    public int[][] mosaic; // variabel mosaic bertipe array 2 dimensi
    public int maxPoint; // variabel maxPoint bertipe integer
    public int size;//besar panjang atau lebar mosaic

    // Constructor GeneticAlg meminta variabel rn, totalGeneration, maxPopuilationSize, elitisme, crossoverRate, mutationRate, Array mosaic juga maxPoint
    public GeneticAlg(Random rn, int totalGeneration, int maxPopulationSize, double elitismPct,double crossoverRate, double mutationRate,int[][] mosaic,int maxPoint,int n) {
        this.rn = rn;
        this.totalGeneration = totalGeneration;
        this.maxPopulationSize = maxPopulationSize;
        this.elitismPct = elitismPct;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.mosaic=mosaic;
        this.maxPoint=maxPoint;
        this.size=n;
    }
    //Method run untuk menjalankan Genetic Algorithm
    public Individual run() {
        int generation = 1; // generasi start dari generasi pertama
        Population currentPop = new Population(this.rn,this.maxPopulationSize,this.elitismPct,this.mosaic,this.size); // mengambil isi populasi sekarang
        currentPop.randomPopulation(); // mengisi dengan random population karena generasi pertama
        currentPop.computeAllFitnesses(); // menghitung semua fitness populasi
        // looping selama banyak generasi yang diminta belom terlebihi
        while (generation<=this.totalGeneration) {
            Population newPop = currentPop.getNewPopulationWElit(); // mengambil populasi sekarang denga elitisme
            // jika newPop tidak penuh, maka akan memilih parent untuk crossover
            while (newPop.isFilled()==false) {
                Individual[] parents = currentPop.selectParent(); // memilih parent
                // jika rn lebih kecil dari crossover rate maka akan terjadi crossover pada parent
                if (this.rn.nextDouble()<this.crossoverRate) {
                    Individual child = parents[0].doCrossover(parents[1]);
                    // jika rn lebih kecil dari mutationRate maka akan terjadi mutasi pada anak
                    if (this.rn.nextDouble()<this.mutationRate) {
                        child.mutate();
                    }
                    newPop.addIndividual(child); // menambah anak sebagai individual pada newPop
                }
            }
            generation++; // generasi bertammbah
            currentPop = newPop; // currentpop dijadikan newpop
            currentPop.computeAllFitnesses(); // menghitung fitness dari currentpop
            // jika fitness individual terbaik dari currentpop = maxPoint maka akan berhenti
            if(currentPop.getBestIdv().fitness==maxPoint)break;
        }
        System.out.println("generation count: "+(generation-1));//print generation count
        return currentPop.getBestIdv(); // mengembalikan individual terbaik dari currentpop
    }
}


