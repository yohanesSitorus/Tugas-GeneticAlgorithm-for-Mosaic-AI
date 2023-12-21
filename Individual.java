import java.util.Random;

public class Individual implements Comparable<Individual>{
    public int[] kromosom;
    public int fitness;
    public Random indRand;
    public double parentProbability;
    //random generated
    public Individual(Random r,int size){
        this.indRand=r;
        this.fitness=0;
        this.kromosom=new int[25];
        for(int i=0;i<size;i++){
            this.kromosom[i]=this.indRand.nextInt(0, 2);
        }
    }
    //premade
    public Individual(Random r,int[] kromosom){
        this.indRand=r;
        this.fitness=0;
        this.kromosom=kromosom;
    }

    public void mutate(){
        int index =this.indRand.nextInt(kromosom.length-1);
        if(kromosom[index]==1) kromosom[index]=0;
        else kromosom[index]=1;
    }
    public void hitungFitnessKromosom(int[][] mosaic){
        int[][] kromosomRep=new int[5][5];
        int counterKrom=0;
        int tempFitness=0;
        int temp;
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
                    /*
                     * 123
                     * 456
                     * 789
                     */
                    if(i-1>=0){
                        //1
                        if(j-1>=0 && kromosomRep[i-1][j-1]==1){
                            temp++;
                        }
                        //2
                        if(kromosomRep[i-1][j]==1) temp++;
                        //3
                        if(j+1<5 && kromosomRep[i-1][j+1]==1){
                            temp++;
                        }
                    }
                    //4
                    if(j-1>=0 && kromosomRep[i][j-1]==1)temp++;
                    //5
                    if(kromosomRep[i][j]==1)temp++;
                    //6
                    if(j+1<5 && kromosomRep[i][j+1]==1)temp++;    
                    
                    if(i+1<5){
                        //7
                        if(j-1>=0 && kromosomRep[i+1][j-1]==1){
                            temp++;
                        }
                        //8
                        if(kromosomRep[i+1][j]==1) temp++;
                        //9
                        if(j+1<5 && kromosomRep[i+1][j+1]==1){
                            temp++;
                        }
                    }
                    //kalau < angka mosaic  +1 poin, tiap lebih -2 poin  
                    if(mosaic[i][j]>=temp) tempFitness+=temp;
                    else tempFitness+= mosaic[i][j]-2*(temp-mosaic[i][j]);
                }
                
            }
        }
        this.fitness=tempFitness;

    }
    public Individual doCrossover(Individual other) {	//two points
        // Individual child1 = new Individual(this.indRand,25);
        // Individual child2 = new Individual(this.indRand,  25);
        int[]child1=new int[25];
        int[]child2=new int[25];
        int rd1=0;
        int rd2=1;
        do{
            rd1=this.indRand.nextInt(20)+3;
            rd2=this.indRand.nextInt(20)+3;
        }while(Math.abs(rd1-rd2)<=2);
        int pos1 = Math.min(rd1,rd2);
        int pos2 = Math.max(rd1,rd2);
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
        int choose = this.indRand.nextInt(2);
        if (choose==0) return new Individual(indRand, child1);
        else return new Individual(indRand, child2);
    }
    @Override
    public int compareTo(Individual ind2){
        if(this.fitness>ind2.fitness) return -1;
        else if(this.fitness<ind2.fitness) return 1;
        else return 0;
    }
    @Override
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
