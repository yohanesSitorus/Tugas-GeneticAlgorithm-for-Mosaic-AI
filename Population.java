import java.util.Random;
import java.util.ArrayList;
import java.util.*;

public class Population {
    Random MyRand;
    public ArrayList<Individual> population;
    private int maxPopulationSize;
    private int populationSize=0;
    public double elitismPct;
    Mosaic mosaic ;
    int size ;
    int sumRank=0;

    public Population(Random MyRand, int maxPopulationSize, double elitismPct, Mosaic mosaic, int size) {
		this.MyRand = MyRand;
        this.population = new ArrayList<Individual>();
        this.maxPopulationSize = maxPopulationSize;
        this.elitismPct = elitismPct;
        this.mosaic = mosaic ;
        this.size = size ;
        for (int i=1;i<=this.maxPopulationSize;i++) this.sumRank = this.sumRank + i;

    }

    public void randomPopulation() {
        for (int i=0;i<this.maxPopulationSize;i++) {
            this.addIndividual(new Individual(this.MyRand, this.mosaic, this.size));
        }
    }

    public boolean addIndividual(Individual newIdv) {
        if (this.populationSize>=this.maxPopulationSize) return false;
        this.population.add(newIdv);
        this.populationSize++;
        return true;
    }

    public void computeAllFitnesses() {
        for (int i=0;i<this.populationSize;i++) {
            ((Individual)this.population.get(i)).setFitness();
        }
        this.population.sort((idv1,idv2) -> idv1.compareTo(idv2));
    }

    public Population getNewPopulationWElit() {
        Population newPop = new Population(this.MyRand, this.populationSize, this.elitismPct, this.mosaic, this.size);
        int n = (int)(this.elitismPct * this.maxPopulationSize);
        //System.out.println(n);
        //System.out.println(this.population.size());
        for (int i=0;i<n;i++) {
        	//System.out.println(i);
            boolean res = newPop.addIndividual(this.population.get(i));
        }
        return newPop;
    }

    public boolean isFilled() {
        return this.maxPopulationSize==this.populationSize;
    }

    public Individual[] selectParent() {    //rank selection
        Individual[] parents = new Individual[2];
        this.population.sort((idv1,idv2) -> idv1.compareTo(idv2)); //Question : knp dikerjakan 2x ? di method computeAllFitnesses udh dilakukan
        //int top = this.population.size()+1; //Question: knp topnya harus +1 ?
        for (int i=0;i<this.population.size();i++) {
            ((Individual)this.population.get(i)).parentProbability = (1.0*(this.population.size()-i))/this.sumRank; //Question : artinya dalam 1 populasi tiap individu memeliki parentProbability yg sama?
        }

        for(int n = 0;n<2;n++) {
            double prob =  this.MyRand.nextDouble();
            for(int i = 0;i < this.population.size(); i++) {
                if(this.population.get(i).parentProbability >= prob) {
                    parents[n] = this.population.get(i);
                }
            }
        }

        return parents;
    }

    public Individual getBestIdv() {
        //int top = this.population.size()+1;
        return this.population.get(0);
    }

	@Override
	public String toString() {
		String res = new String();
		for (int i =0;i<this.population.size();i++) {
			res = res + new String(this.population.get(i)+"\n");
		}
		return res;
	}
}

/*
 * Testing : 
 * coba print population. Apakah urutannya dari yg fitness tertinggi paling depan atau sebaliknya
 */
