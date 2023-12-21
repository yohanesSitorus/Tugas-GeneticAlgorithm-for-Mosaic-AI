import java.util.Random;
import java.util.ArrayList;

public class Population {
    public ArrayList<Individual> populasi;
    private int maxPopulationSize;
    private int populationSize=0;
    public double elitismPct;
    private Random rn;
    private int[][] mosaic;
    int sumRank=0;

    public Population(Random rn, int maxPopulationSize, double elitismPct, int[][] mosaic) {
		this.rn = rn;
        this.maxPopulationSize = maxPopulationSize;
        this.populasi = new ArrayList<Individual>();
        this.elitismPct = elitismPct;
        this.mosaic = mosaic ;
        for (int i=1;i<=this.maxPopulationSize;i++) this.sumRank = this.sumRank + i;
    }
    public void randomPopulation() {
        for (int i=0;i<this.maxPopulationSize;i++) {
            this.addIndividual(new Individual(this.rn, 25));
        }
    }
    public boolean addIndividual(Individual newIdv) {
        if (this.populationSize>=this.maxPopulationSize) return false;
        this.populasi.add(newIdv);
        this.populationSize++;
        return true;
    }
    public boolean isFilled() {
        return this.maxPopulationSize==this.populationSize;
    }
    public void computeAllFitnesses() {
        for (int i=0;i<this.populationSize;i++) {
            ((Individual)this.populasi.get(i)).hitungFitnessKromosom(this.mosaic);
        }
        this.populasi.sort((idv1,idv2) -> idv1.compareTo(idv2));
    }
    public Population getNewPopulationWElit() {
        Population newPop = new Population(this.rn,this.maxPopulationSize,this.elitismPct,this.mosaic);
        int n = (int)(this.elitismPct * this.maxPopulationSize);
        for (int i=0;i<n;i++) {
            boolean res = newPop.addIndividual(this.populasi.get(i));
        }
        return newPop;
    }
    public Individual getBestIdv() {
        return this.populasi.get(0);
    }

    public Individual[] selectParent() {    //rank selection
        Individual[] parents = new Individual[2];
        this.populasi.sort((idv1,idv2) -> idv1.compareTo(idv2));
        for (int i=0;i<this.populasi.size();i++) {
            ((Individual)this.populasi.get(i)).parentProbability = (1.0*(this.populasi.size()-i))/this.sumRank; //Question : artinya dalam 1 populasi tiap individu memeliki parentProbability yg sama?
        }

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
}
