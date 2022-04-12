package esercizio4_6;

import java.util.concurrent.Semaphore;

/**
 * @author gubbriaco
 */
public class AA_B {

    private static int cntA=1,cnt=0;
    private static Semaphore mutexA=new Semaphore(cntA),
                             mutex=new Semaphore(1),
                             mutexB=new Semaphore(0);

    /**
     *
     * @param strings
     */
    public static void main(String...strings){

        while(true) {
            new A().start();
            new B().start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static class A extends Thread{
        @Override public void run(){
            try{
                mutexA.acquire();

                mutex.acquire();
                System.out.print("A");
                cnt++;
                if(cnt==cntA) {
                    mutexB.release();
                    cnt=0;
                    cntA++;
                }
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
                mutexA.release(cntA);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
