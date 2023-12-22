import java.util.Scanner;
import java.util.Random ;
import java.io.File;
import java.io.FileNotFoundException;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
   		int loop = sc.nextInt();
   		// System.out.println("Target: 13692887");
   		Random init = new Random();
    	for (int ct=1;ct<=loop;ct++) {
    		long seed = init.nextLong();
			System.out.println(seed);
			Random gen = new Random(seed);
	    	int mosaicSize=0, totalGeneration=0, maxPopulationSize=0;
	    	double crossoverRate=0.0, mutationRate=0.0, elitismPct=0.0;
            Mosaic mosaic = null;
            try {
                sc = new Scanner(new File("input.txt"));
                mosaicSize = sc.nextInt() ;
                mosaic = new Mosaic(mosaicSize) ;
                for(int i = 0 ; i < mosaicSize ; i++) {
                    for(int j = 0 ; j < mosaicSize ; j++) {
                        mosaic.setGrid(i, j, sc.nextInt()) ;
                    }
                }
                mosaic.printGrid() ;
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
            Individual res = ga.run();
            System.out.printf("%2d: Acc = %.3f (%d) Seed: %d\n",ct, res.fitness,res.fitness,seed);
        }
       
    }

}

