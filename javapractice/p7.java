class p7{

    static long startTime = 0;

    public static void main(String args[]){
        ThreadEX11_1 th1 = new ThreadEX11_1();
        ThreadEX11_2 th2 = new ThreadEX11_2();

        th1.start();
        th2.start();

        startTime = System.currentTimeMillis();

        try{
            th1.join();
            th2.join();
        }catch(InterruptedException e){}

        System.out.print("소요시간:"+(System.currentTimeMillis()- p7.startTime));
    }
}
class ThreadEX11_1 extends Thread {
    public void run(){
        for(int i=0; i< 300; i++){
            System.out.print(new String("_"));
        }
    }
}

class ThreadEX11_2 extends Thread{
    public void run(){
        for(int i=0; i< 300; i++){
            System.out.print(new String("|"));
        }
    }
}