import java.util.ArrayList;
import java.util.Random;

public class GeneticAlg {
    private Random rn;
    public int maxPopulationSize;
    public double elitismPct;
    public double crossoverRate;
    public double mutationRate;
    public int totalGeneration;
    public int[][] mosaic;
    public int maxPoint;

    public GeneticAlg(Random rn, int totalGeneration, int maxPopulationSize, double elitismPct,double crossoverRate, double mutationRate,int[][] mosaic,int maxPoint) {
        this.rn = rn;
        this.totalGeneration = totalGeneration;
        this.maxPopulationSize = maxPopulationSize;
        this.elitismPct = elitismPct;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.mosaic=mosaic;
        this.maxPoint=maxPoint;
    }
    public Individual run() {
        int generation = 1;
        Population currentPop = new Population(this.rn,this.maxPopulationSize,this.elitismPct,this.mosaic);
        currentPop.randomPopulation();
        currentPop.computeAllFitnesses();
        //System.out.println(currentPop);
        while (generation>=this.totalGeneration) {
        	//System.out.println("Gen : "+generation+" Best: "+currentPop.getBestIdv().fitness);
            Population newPop = currentPop.getNewPopulationWElit();
            //System.out.println(newPop);
            while (newPop.isFilled()==false) {
            	//System.out.println("fill");
                Individual[] parents = currentPop.selectParent();
                //System.out.println(parents[0]);
                //System.out.println(parents[1]);
                if (this.rn.nextDouble()<this.crossoverRate) {
                	//System.out.println("crossed");
                    Individual child = parents[0].doCrossover(parents[1]);
                    if (this.rn.nextDouble()<this.mutationRate) {
                        //System.out.println("mutate");
                        child.mutate();
                    }
                    newPop.addIndividual(child);
                }
                //else System.out.println("not crossed");
            }
            generation++;
            currentPop = newPop;
            currentPop.computeAllFitnesses();
            if(currentPop.getBestIdv().fitness==maxPoint)break;
            //System.out.println(currentPop);
            //report pop
        }
        return currentPop.getBestIdv();
    }
}


