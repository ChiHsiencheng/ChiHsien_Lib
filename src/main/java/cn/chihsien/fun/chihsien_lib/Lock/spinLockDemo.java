package cn.chihsien.fun.chihsien_lib.Lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @describe
 * @auther chihsien
 */
public class spinLockDemo {

    //原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void mylock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in  Lock QAQ");
        //如果是null 则表示是第一个来的线程
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void myUnlock() {
        Thread thread = Thread.currentThread();
        //如果进来的线程是自己 则置为null 解锁
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t come in Unlock QAQ");
    }

    public static void main(String[] args) {

        spinLockDemo spinLockDemo = new spinLockDemo();
        new Thread(() -> {
            spinLockDemo.mylock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        }, "AA").start();
        try {TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e){ e.printStackTrace();}

           new Thread(()->{
               spinLockDemo.mylock();
               spinLockDemo.myUnlock();
              },"BB").start();

    }
}
