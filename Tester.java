import java.util.Scanner; //import scanner
import java.util.Random; // import untuk random numbert generator 
// inisialisasi kelas Tester
public class Tester {
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in); // inisialisasi Scanner sebagai sc
        Random seeder=new Random(); // inisialisasi Random number untuk variabel seeder
        long seed= seeder.nextLong(); //memasukkan seeder dalam variabel seed berformat long
        System.out.println("seed: "+seed); // mengoutput seed
        Random seededRandom= new Random(seed); // inisialisasi Random number dengan nilai seed
        int mosaic[][]=new int[5][5];//inisialisasi array 2 dimensi untuk mosaic dengan ukuran 5x5
        System.out.println("input puzzle:"); // mengoutput perintah untuk input puzzle
        int maxPoint=0; // menginisialisasi variabel maxPoint dengan value 0 
        //looping untuk meminta input isi mosaic dan menambahkan maxPoint seiring berjalannya loop
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                mosaic[i][j]=sc.nextInt();
                if(mosaic[i][j]>=0) maxPoint+=mosaic[i][j];
            }
        }
        System.out.println("maxpoint: "+maxPoint); // mengoutput maksimum poin yang dapat diperoleh
        System.out.println("input total generation:"); 
        int totalGeneration=sc.nextInt(); // menginput total generasi yang diinginkan
        System.out.println("input max population:");
        int populasiMax=sc.nextInt(); // menginput maximal populasi yang diinginkan
        System.out.println("input mutation rate:");
        double mutateRate=sc.nextDouble(); // menginput kemungkinan mutasi dalam double
        System.out.println("input crossover rate:");
        double crossoverRate = sc.nextDouble(); // menginput kemungkinan crossover dalam double
        System.out.println("input elitism:");
        double elitismPct=sc.nextDouble(); // menginput elitisme dalam double
        sc.close(); // menutup scanner
        // menginisialisasi dan memasukan semua variabel dalam GeneticAlg dengan nama ga
        GeneticAlg ga=new GeneticAlg(seededRandom,totalGeneration,populasiMax,elitismPct,crossoverRate,mutateRate,mosaic,maxPoint);
        Individual res=ga.run(); // menginisialisasi Individual berisi ga bernama res
        System.out.println(res.toString()); // mengoutput hasil res yang telah dikonversi menjadi string
    }
}
