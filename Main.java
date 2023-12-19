import java.util.Scanner;
import java.util.Random ;
import java.io.File;
import java.io.FileNotFoundException;

public class MosaicTester{
    public static void main(String[] args){
        Mosaic mosaic = new Mosaic(gridSize) ;
        try {
            sc = new Scanner(new File("input.txt"));
            int gridSize = sc.nextInt() ;
            for(int i ; i < gridSize ; i++) {
                for(int j ; j < gridSize ; j++) {
                    mosaic.setGrid(i, j, sc.nextInt()) ;
                }
            }
        } catch (FileNotFoundException e) { e.printStackTrace();}

        try {
            sc = new Scanner(new File("param.txt"));
            totalGeneration = sc.nextInt();
            maxPopulationSize = sc.nextInt();
            crossoverRate = sc.nextDouble();
            mutationRate = sc.nextDouble();
            elitismPct = sc.nextDouble();
        } catch (FileNotFoundException e) { e.printStackTrace();}

        MosaicGA ga = new MosaicGA(gen,totalGeneration, maxPopulationSize, crossoverRate,
                                        mutationRate, elitismPct, mosaic);
        
    }

}