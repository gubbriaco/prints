package esercizio4_6;

import java.util.concurrent.Semaphore;

/**
 * @author gubbriaco
 */
public class AAB {

    private static Semaphore mutexA=new Semaphore(2),
                             mutexB=new Semaphore(0),
                             mutex=new Semaphore(1);
    private static int cnt=0;

    /**
     *
     * @param strings
     */
    public static void main(String...strings){

        while(true){

            new A().start();
            new B().start();

            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }

    }


    private static class A extends Thread{
        @Override public void run(){
            try{
                mutexA.acquire();
                //utilizzando il semaforo mutex garantiamo la stampa della sequenza "AA"
                mutex.acquire();
                System.out.print("A");
                cnt++;
                if(cnt==2) //fine stampa
                    mutexB.release();
                mutex.release();

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private static class B extends Thread{
        @Override public void run(){
            try{
                mutexB.acquire();
                System.out.println("B");
                cnt=0;
                //rilasciamo due permessi per A perche' dovra' stampare due volte
                mutexA.release(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
