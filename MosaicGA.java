
import java.util.Random;
import java.util.ArrayList;    

public class MosaicGA {
    Random MyRand;
    public int maxPopulationSize;
    public double elitismPct;
    public double crossoverRate;
    public double mutationRate;
    public int totalGeneration;
    public Mosaic mosaic ; 

    public MosaicGA(Random MyRand, int maxPopulationSize, double elitismPct, 
    double crossoverRate, double mutationRate, int totalGeneration, Mosaic mosaic) {
        this.MyRand = MyRand;
        this.maxPopulationSize = maxPopulationSize;
        this.elitismPct = elitismPct;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.totalGeneration = totalGeneration;
        this.mosaic = mosaic ;
    }
}