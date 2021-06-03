public class DynamicProgramming {

    //https://en.wikipedia.org/wiki/Dynamic_programming#Egg_dropping_puzzle
    public static int eggDrop(int N, int K){
        int c = 0;

        if(N== 1){
            return K;
        }
        int eggsleft = N;
        int i = K;
        while(i > 0 && eggsleft > 1){
            c++;
            i = i /2;
            //System.out.println("floor i = " +i);
            eggsleft = eggsleft - 1;
        }

        if(eggsleft == 1) {
            c += i - 2;
        }
        System.out.println("minimum drops "+ c);
        return c;
    }
    public static void main(String[] args){
       /* eggDrop(1,5);
        eggDrop(2,5);*/
        eggDrop(2,32);
        eggDrop(4,3);
    }
}
