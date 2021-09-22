package cn.chihsien.fun.chihsien_lib.JUC.JMM;

public class VolattileDemo {
    public static void main(String[] args) {
        JMM_Demo.MyNumber myNumber = new JMM_Demo.MyNumber();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "---------come in");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myNumber.addTo1024();
            System.out.println(Thread.currentThread().getName() + "\t update number,new number value:" + myNumber.number);
        }, "AAA").start();
        while (myNumber.number == 10) {

        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over");

    }
}
