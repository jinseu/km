package sample;

class Worker implements Runnable {

    private int count;

    public Worker(int i){
        this.count = i;
    }

    @Override
    public void run() {
       synchronized (this){
           while(this.count <= 10){
               System.out.println(Thread.currentThread().getName() + " " + this.count);
               this.count ++;
               this.notify();
               try{
                   this.wait();
               } catch(InterruptedException e){
                   e.printStackTrace();
               }
           }
           this.notify();
       }
        System.out.println(Thread.currentThread().getName() + " exit");
    }
}
public class ConSeq {
    public static void main(String []args){
        Worker w = new Worker(1);
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);

        t1.start();
        t2.start();
    }
}
