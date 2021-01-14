class FooBar {
    private int n;
    private int current;

    public FooBar(int n) {
        this.n = 2*n;
        this.current = 0;

    }

    public void foo(Runnable printFoo) throws InterruptedException {
        synchronized(this) {
            for (;;) {
                if (this.current == this.n) {
                    this.notify();
                    break ;
                }
                if (this.current % 2 == 0) {
                    printFoo.run();
                    this.current += 1;
                }
                this.notify();
                try{
                    this.wait();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        synchronized(this) {
            for (;;) {
                if (this.current == this.n) {
                    this.notify();
                    break ;
                }
                if (this.current % 2 == 1) {
                    printBar.run();
                    this.current += 1;
                }
                this.notify();
                try{
                    this.wait();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}