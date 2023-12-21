import java.util.Scanner; //import scanner
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random; // import untuk random numbert generator 
// inisialisasi kelas Tester
public class Tester {
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in); // inisialisasi Scanner sebagai sc
        Random seeder=new Random(); // inisialisasi Random number untuk variabel seeder
        int loop=sc.nextInt();
        for (int ct=1;ct<=loop;ct++) {
            int maxPoint=0; // menginisialisasi variabel maxPoint dengan value 0 
    		long seed= seeder.nextLong(); //memasukkan seeder dalam variabel seed berformat long
            System.out.println("seed: "+seed); // mengoutput seed
			System.out.println(seed);
			Random seededRandom= new Random(seed); // inisialisasi Random number dengan nilai seed
            int n=0;
            int totalGeneration=0,populasiMax=0;
            int mosaic[][]=null;
	    	double crossoverRate=0.0, mutateRate=0.0, elitismPct=0.0;
            try {
                sc = new Scanner(new File("input.txt"));
                n = sc.nextInt() ;
                mosaic =new int[n][n];//inisialisasi array 2 dimensi untuk mosaic dengan ukuran nxn
                for(int i = 0 ; i < n ; i++) {
                    for(int j = 0 ; j < n ; j++) {
                        mosaic[i][j]= sc.nextInt() ;
                        if(mosaic[i][j]>=0) maxPoint+= mosaic[i][j];
                    }
                }
            } catch (FileNotFoundException e) { e.printStackTrace();}
    
            try {
                sc = new Scanner(new File("param.txt"));
                totalGeneration = sc.nextInt();
                populasiMax = sc.nextInt();
                crossoverRate = sc.nextDouble();
                mutateRate = sc.nextDouble();
                elitismPct = sc.nextDouble();
            } catch (FileNotFoundException e) { e.printStackTrace();}
            // menginisialisasi dan memasukan semua variabel dalam GeneticAlg dengan nama ga
            GeneticAlg ga=new GeneticAlg(seededRandom,totalGeneration,populasiMax,elitismPct,crossoverRate,mutateRate,mosaic,maxPoint,n);
            Individual res=ga.run(); // menginisialisasi Individual berisi ga bernama res
            System.out.println(res.toString()); // mengoutput hasil res yang telah dikonversi menjadi string
        }
    }
}
