import java.util.Random; // import Random number generator
// inisialisasi kelas Individual1
public class Individual implements Comparable<Individual>{
    public int[] kromosom; // array integer berisi kromosom
    public int fitness; // variabel integer untuk fitness
    public Random indRand; // variabel dengan tipe Random number untuk indRand
    public double parentProbability; // variabel dengan tipe Double untuk parentProbability
    //Constructor Individual dengan meminta variabel r dan size
    public Individual(Random r,int size){
        this.indRand=r; // inisialisasi r sebagai indRand
        this.fitness=0; // set fitness awal ke 0
        this.kromosom=new int[25]; // inisialisasi kromosom dengan tipe integer sebesar 25
        // looping untuk mengisi kromosom dengan indRand
        for(int i=0;i<size;i++){
            this.kromosom[i]=this.indRand.nextInt(0, 2);
        }
    }
    //Constructor Individual dengan meminta variabel r dan array kromosom
    public Individual(Random r,int[] kromosom){
        this.indRand=r;
        this.fitness=0;
        this.kromosom=kromosom;
    }
    //Method untuk mutasi
    public void mutate(){
        int index =this.indRand.nextInt(kromosom.length-1);
        if(kromosom[index]==1) kromosom[index]=0;
        else kromosom[index]=1;
    }
    //Method menghitunnng Fitness untuk kromosom dengan masukan array 2 dimensi mosaic
    public void hitungFitnessKromosom(int[][] mosaic){
        int[][] kromosomRep=new int[5][5]; // inisialisasi kromosom dengan ukuran 5x5
        int counterKrom=0; // variabel penghitung kromosom
        int tempFitness=0; // variabel penyimpan Fitness sementara
        int temp; // variabel temp untuk menyimpan banyak angka
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                kromosomRep[i][j]=this.kromosom[counterKrom];
                counterKrom++;
            }
        }

        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(mosaic[i][j]!=-1){
                    temp=0;
                    //penggambaran posisi pengecekan
                    /*
                     * 123
                     * 456
                     * 789
                     */
                    if(i-1>=0){
                        //1 (Mengecek posisi kiri atas)
                        if(j-1>=0 && kromosomRep[i-1][j-1]==1){
                            temp++;
                        }
                        //2 (Mengecek posisi tengah atas)
                        if(kromosomRep[i-1][j]==1) temp++;
                        //3 (Mengecek posisi kanan atas)
                        if(j+1<5 && kromosomRep[i-1][j+1]==1){
                            temp++;
                        }
                    }
                    //4 (Mengecek posisi tengah kiri)
                    if(j-1>=0 && kromosomRep[i][j-1]==1)temp++;
                    //5 (Mengecek posisi tengah tengah)
                    if(kromosomRep[i][j]==1)temp++;
                    //6 (Mengecek posisi tengah kanan)
                    if(j+1<5 && kromosomRep[i][j+1]==1)temp++;    
                    
                    if(i+1<5){
                        //7 (Mengecek posisi bawah kiri)
                        if(j-1>=0 && kromosomRep[i+1][j-1]==1){
                            temp++;
                        }
                        //8 (Mengecek posisi bawah tengah)
                        if(kromosomRep[i+1][j]==1) temp++;
                        //9 (Mengecek posisi bawah kanan)
                        if(j+1<5 && kromosomRep[i+1][j+1]==1){
                            temp++;
                        }
                    }
                    //kalau < angka pada mosaic  +1 poin, tiap lebih -2 poin  
                    if(mosaic[i][j]>=temp) tempFitness+=temp;
                    else tempFitness+= mosaic[i][j]-2*(temp-mosaic[i][j]);
                }
                
            }
        }
        this.fitness=tempFitness;

    }
    // Method untuk Crossover, two point crossover
    public Individual doCrossover(Individual other) {	
        //inisialisasi
        int[]child1=new int[25];
        int[]child2=new int[25];
        int rd1=0;
        int rd2=1;
        //cari crossover point 1&2
        do{
            rd1=this.indRand.nextInt(20)+3;
            rd2=this.indRand.nextInt(20)+3;
        }while(Math.abs(rd1-rd2)<=2);
        int pos1 = Math.min(rd1,rd2);
        int pos2 = Math.max(rd1,rd2);
        //proses crossover
        for (int i=0;i<=pos1;i++) {
            child1[i]=this.kromosom[i];
            child2[i]=other.kromosom[i];
        }
        for (int i=pos1+1;i<=pos2;i++) {
            child1[i]=other.kromosom[i];
            child2[i]=this.kromosom[i];
        }
        for (int i=pos2+1;i<Integer.SIZE;i++) {
            child1[i]=this.kromosom[i];
            child2[i]=other.kromosom[i];
        }
        //pilih salah satu anak
        int choose = this.indRand.nextInt(2);
        if (choose==0) return new Individual(indRand, child1);
        else return new Individual(indRand, child2);
    }
    @Override
    // Method untuk membandingkan fitness
    public int compareTo(Individual ind2){
        if(this.fitness>ind2.fitness) return -1;
        else if(this.fitness<ind2.fitness) return 1;
        else return 0;
    }
    @Override
    //Method untuk merubah jadi string
    public String toString(){
        String s="";
        int counter=0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                s+=this.kromosom[counter];
                counter++;
            }
            s+="\n";
        }
        s+="\nFitness= "+this.fitness;
        return s;
    }
}
