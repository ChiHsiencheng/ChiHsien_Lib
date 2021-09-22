package cn.chihsien.fun.chihsien_lib.Lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable {
    public synchronized void sendSms() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\tsendSms");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\tsendEmail");
    }


    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoked get()");
            set();
        } catch (Exception e) {
            e.printStackTrace(); //处理异常的代码
        } finally {
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoked set()");
        } catch (Exception e) {
            e.printStackTrace(); //处理异常的代码
        } finally {
            lock.unlock();
        }
    }
}

/***
 * @describe
 *  Description:
 *  *  可重入锁(也叫做递归锁)
 *  *  指的是同一先生外层函数获得锁后,内层敌对函数任然能获取该锁的代码
 *  *  在同一线程外外层方法获取锁的时候,在进入内层方法会自动获取锁
 *  *
 *  *  也就是说,线程可以进入任何一个它已经标记的锁所同步的代码块
 * @return
 * @author ChiHsien<br>
 * @version
 */
public class ReenterLockDemo {

    /**
     * t1 sendSms
     * t1 sendEmail
     * t2 sendSms
     * t2 sendEmail
     *
     * @param args
     */
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();
        new Thread(() -> {
            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();


        try {TimeUnit.SECONDS.sleep(2);}catch (InterruptedException e){ e.printStackTrace();}

        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println("--------------------");

        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");
        t3.start();
        t4.start();

    }


}
