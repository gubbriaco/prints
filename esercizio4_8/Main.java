package esercizio4_8;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Esistono N Thread ognuno identificato da un numero che va da 0 a N-1. I Thread devono essere eseguiti in maniera
 * ordinata. Prima il Thread 0, poi il Thread 1, poi il Thread 2, ...
 * <p>Implementare una soluzione medianti l'ausilio dei semafori</p>
 *
 * @author gubbriaco
 */
public class Main {

    private static int N=10;
    private static Semaphore[] sems=new Semaphore[N];
    private static Thread[] threads=new Thread[N];

    /**
     *
     * @param strings
     * @throws InterruptedException
     */
    public static void main(String...strings) {

        for(int i=0;i<N;++i)
            sems[i]=new Semaphore(i==0?1:0);

        for(int i=0;i<N;++i) {
            threads[i]=new Seq(i);
            threads[i].start();
        }



    }


    private static class Seq extends Thread{

        private int id;

        public Seq(int id){
            this.id=id;
        }

        @Override public void run(){
            try{
                sems[id].acquire();
                System.out.println(this.getName());
                TimeUnit.SECONDS.sleep(1);
                if(id+1==sems.length)
                    return;
                else
                    sems[id+1].release();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

}
