
import java.util.Random;

public class MosaicGA {
    Random MyRand;
    public int maxPopulationSize;
    public double elitismPct;
    public double crossoverRate;
    public double mutationRate;
    public int totalGeneration;
    public Mosaic mosaic ; 

    public MosaicGA(Random MyRand, int totalGeneration, int maxPopulationSize, 
    double crossoverRate, double mutationRate, double elitismPct, Mosaic mosaic) {
        this.MyRand = MyRand;
        this.maxPopulationSize = maxPopulationSize;
        this.elitismPct = elitismPct;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.totalGeneration = totalGeneration;
        this.mosaic = mosaic ;
    }

    public Individual run() {
        int generation = 1;
        Population currentPop = new Population(this.MyRand, this.maxPopulationSize, this.elitismPct, this.mosaic, this.mosaic.size);
        currentPop.randomPopulation();
        currentPop.computeAllFitnesses();
        System.out.println("current population: " + currentPop.toString());
        while (terminate(generation)==false) {
        	//System.out.println("Gen : "+generation+" Best: "+currentPop.getBestIdv().fitness);
            Population newPop = currentPop.getNewPopulationWElit();
            //System.out.println(newPop);
            while (newPop.isFilled()==false) {
            	//System.out.println("fill");
                Individual[] parents = currentPop.selectParent();
                //System.out.println(parents[0]);
                //System.out.println(parents[1]);
                if (this.MyRand.nextDouble()<this.crossoverRate) {
                	//System.out.println("crossed");
                    Individual child = parents[0].doCrossover(parents[1]);
                    if (this.MyRand.nextDouble()<this.mutationRate) {
                        //System.out.println("mutate");
                        child.doMutation();
                    }
                    newPop.addIndividual(child);
                }
                //else System.out.println("not crossed");
            }
            generation++;
            currentPop = newPop;
            currentPop.computeAllFitnesses();
            //System.out.println(currentPop);
            //report pop
        }
        return currentPop.getBestIdv();
    }

    public boolean terminate(int generation){
        if (generation >= this.totalGeneration) return true;
        else return false;
        //or by running time
        //or population not changed
    }

}