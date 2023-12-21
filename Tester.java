import java.util.Scanner;
import java.util.Random;
public class Tester {
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        Random seeder=new Random();
        long seed= seeder.nextLong();
        System.out.println("seed: "+seed);
        Random seededRandom= new Random(seed);
        int mosaic[][]=new int[5][5];
        System.out.println("input puzzle:");
        int maxPoint=0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                mosaic[i][j]=sc.nextInt();
                if(mosaic[i][j]>=0) maxPoint+=mosaic[i][j];
            }
        }
        System.out.println("maxpoint: "+maxPoint);
        System.out.println("input total generation:");
        int totalGeneration=sc.nextInt();
        System.out.println("input max population:");
        int populasiMax=sc.nextInt();
        System.out.println("input mutation rate:");
        double mutateRate=sc.nextDouble();
        System.out.println("input crossover rate:");
        double crossoverRate = sc.nextDouble();
        System.out.println("input elitism:");
        double elitismPct=sc.nextDouble();
        sc.close();
        GeneticAlg ga=new GeneticAlg(seededRandom,totalGeneration,populasiMax,elitismPct,crossoverRate,mutateRate,mosaic,maxPoint);
        Individual res=ga.run();
        System.out.println(res.toString());
    }
}
