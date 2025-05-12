import java.util.Scanner; //import scanner
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random; // import untuk random numbert generator 
// inisialisasi kelas Tester
public class Tester {
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in); // inisialisasi Scanner sebagai sc
        Random seeder=new Random(); // inisialisasi Random number untuk variabel seeder
        int loop=sc.nextInt();//jumlah case pada input

        try {
            Scanner si = new Scanner(new File("input.txt"));
            try{
                FileWriter output = new FileWriter("output.txt");
                for (int ct=1;ct<=loop;ct++) {
                    int maxPoint=0; // menginisialisasi variabel maxPoint dengan value 0 
                    long seed= seeder.nextLong(); //memasukkan seeder dalam variabel seed berformat long
                    // long seed = sc.nextLong(); //untuk input manual seed
                    
                    Random seededRandom= new Random(seed); // inisialisasi Random number dengan nilai seed
                    int n=0;    //menyimpan lebar/panjang sisi mosaic
                    int totalGeneration=0,populasiMax=0; //simpan generasi sebelum berhenti dan populasi genetic alg
                    int mosaic[][]=null;//simpan mosaic
                    double crossoverRate=0.0, mutateRate=0.0, elitismPct=0.0; //variable crossover,mutate,elitism untuk genetic alg
                    
                    n = si.nextInt() ;//panjang sisi mosaic
                    mosaic =new int[n][n];//inisialisasi array 2 dimensi untuk mosaic dengan ukuran nxn
                    // menerima input mosaic
                    for(int i = 0 ; i < n ; i++) {
                        for(int j = 0 ; j < n ; j++) {
                            mosaic[i][j]= si.nextInt() ;
                            // System.out.print(mosaic[i][j]);
                            if(mosaic[i][j]>=0) maxPoint+= mosaic[i][j];
                        }
                    }
                    //menerima parameter genetic algorithm
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
                    
                    output.write("seed: "+seed+"\n"); // mengoutput seed
                    //print params
                    output.write("total generasi: "+totalGeneration+"\n");
                    output.write("populasi max: "+populasiMax+"\n");
                    output.write("mutasi: "+mutateRate+"\n");
                    output.write("elitisme: "+elitismPct+"\n");
                    //print hasil
                    output.write("generasi stop: "+ga.generationStop+"\n");//generasi saat program berhenti
                    output.write(res.toString()+"\n\n"); // mengoutput hasil res yang telah dikonversi menjadi string
                }
                output.close();
            }catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) { e.printStackTrace();}
    }
}
