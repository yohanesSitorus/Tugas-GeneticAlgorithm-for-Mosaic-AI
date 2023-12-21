import java.util.Random; // import Random number generator
import java.util.ArrayList; // mengimport fungsi ArrayList

// inisialisasi kelas Population
public class Population {
    public ArrayList<Individual> populasi; // inisialisasi ArrayList dengan tipe Individual bernama populasi
    private int maxPopulationSize; // inisialisasi maxPopulationSize dalam integer
    private int populationSize=0;// inisialisasi populationSize dengan integer bernilai 0
    public double elitismPct; // inisialisasi elitisme dengan tipe double
    private Random rn; // inisialisasi Random number dengan nama rn
    private int[][] mosaic; // inisialisasi array 2 dimensi untuk mosaic
    int sumRank=0; // inisialisasi sumRank dengan nilai 0
    private int size;

    //Constructor Population dengan meminta variabel rn, maxPopulationSize, Elitisme, dan array mosaic
    public Population(Random rn, int maxPopulationSize, double elitismPct, int[][] mosaic,int size) {
		this.rn = rn;
        this.maxPopulationSize = maxPopulationSize;
        this.populasi = new ArrayList<Individual>();
        this.elitismPct = elitismPct;
        this.mosaic = mosaic ;
        this.size=size;
    }
    // method untuk menggenerasi random population pada tahap awal (generasi pertama) sebesar 25, karena ukuran mosaic 5x5
    public void randomPopulation() {
        for (int i=0;i<this.maxPopulationSize;i++) {
            this.addIndividual(new Individual(this.rn, this.size));
        }
    }
    // method untuk menambah Individual baru ke dalam populasi
    public boolean addIndividual(Individual newIdv) {
        if (this.populationSize>=this.maxPopulationSize) return false;
        this.populasi.add(newIdv);
        this.populationSize++;
        return true;
    }
    // method untuk mengecek apakah ukuran populasi sekarang sudah sama dengan maximal population size
    public boolean isFilled() {
        return this.maxPopulationSize==this.populationSize;
    }
    // method untuk menghitung semua Fitness kromosom pada suatu populasi
    public void computeAllFitnesses() {
        for (int i=0;i<this.populationSize;i++) {
            ((Individual)this.populasi.get(i)).hitungFitnessKromosom(this.mosaic);
        }
        // mensorting semua populasi
        this.populasi.sort((idv1,idv2) -> idv1.compareTo(idv2));
        totalFitness();
    }
    // method untuk mendapatkan populasi baru dari eltisime
    public Population getNewPopulationWElit() {
        Population newPop = new Population(this.rn,this.maxPopulationSize,this.elitismPct,this.mosaic,this.size);
        int n = (int)(this.elitismPct * this.maxPopulationSize);
        for (int i=0;i<n;i++) {
            newPop.addIndividual(this.populasi.get(i));
        }
        return newPop;
    }
    // method untuk mengambil individual terbaik
    public Individual getBestIdv() {
        return this.populasi.get(0);
    }
    // method untuk memilih Pareng dengan metode Rank selection
    public Individual[] selectParent() {    
        Individual[] parents = new Individual[2];
        this.populasi.sort((idv1,idv2) -> idv1.compareTo(idv2));
        for (int i=0;i<this.populasi.size();i++) {
            ((Individual)this.populasi.get(i)).parentProbability = (1.0*(this.populasi.size()-i))/this.sumRank; //menghitung probabilitasi Parent 
        }
        // looping untuk mencari parent
        for(int n = 0;n<2;n++) {
            double prob =  this.rn.nextDouble();
            double temp=0;
            for(int i = 0;i < this.populasi.size(); i++) {
                if(this.populasi.get(i).parentProbability+temp >= prob) {
                    parents[n] = this.populasi.get(i);
                }
                temp+=this.populasi.get(i).parentProbability;
            }
        }

        return parents;
    }

    public void totalFitness(){
        // looping untuk menghitung total fitness dalam populasi
        for (int i=1;i<=this.maxPopulationSize;i++) this.sumRank = this.sumRank + i;
    }
}
