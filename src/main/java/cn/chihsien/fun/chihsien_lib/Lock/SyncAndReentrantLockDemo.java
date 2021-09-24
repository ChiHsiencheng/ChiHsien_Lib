package cn.chihsien.fun.chihsien_lib.Lock;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  synchronized和lock的区别
 *  1.原始构成
 *    synchronized是关键字属于JVM层面
 *      monitorenter（底层是通过monitor对象来完成，其实wait/notify等方法也依赖于monitor对象在同步块或方法中才能够调wait/notify等方法）
 *      monitorexit
 *    lock是具体的类，是api层面的锁
 *  2.使用方法
 *    synchronized不需要用户取手动释放锁，当synchronized代码执行完成后 系统会自动让线程释放对锁的占用
 *    ReentrantLock则需要用户去手动释放锁 若没有主动去释放 可能会出现死锁
 *    需要lock unlock 配合try/finally语句块来完成
 *  3.等待是否可中断
 *    synchronized不可中断 除非抛出异常或者正常运行完成
 *    ReentrantLock可中断
 *      1.设置超时方法tryLock(long timeout,TimeUnit unit)
 *      2.lockInterruptibly()放代码块中 调用interrupt()方法可以中断
 *  4.加锁是否公平
 *     synchronized非公平锁
 *     ReentrantLock两者皆可 ，默认是非公平锁
 *  5.锁绑定多个条件Condition
 *      synchronized没有
 *      ReentrantLock用来实现分组唤醒需要唤醒的线程们，可以精确唤醒，
 *                   而不是像synchronized要么随机唤醒一个线程，要么唤醒全部线程
 *  案例：多线程之间按顺序调用 实现 A->B->C三个线程启动 需求如下
 *       AA打印5次 BB打印10次 CC打印15次
 *       紧接着
 *       AA打印5次 BB打印10次 CC打印15次
 *       ........
 *       来10轮
 */
class ShareResource{
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    private Condition c4 = lock.newCondition();

    public void print5(){
        lock.lock();
        try{
            //判断
        while (number != 1){
            c1.await();
        }
        //干活
            for (int i = 1; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
         //通知
        number = 2;//修改标志位
        c2.signal();//通知2号线程
        }catch(Exception e){
            e.printStackTrace(); //处理异常的代码
        }finally {
         lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try{
            //判断
            while (number != 2){
                c2.await();
            }
            //干活
            for (int i = 1; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            number = 3;//修改标志位
            c3.signal();//通知3号线程
        }catch(Exception e){
            e.printStackTrace(); //处理异常的代码
        }finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try{
            //判断
            while (number != 3){
                c3.await();
            }
            //干活
            for (int i = 1; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            number = 1;//修改标志位
            c1.signal();//通知1号线程
        }catch(Exception e){
            e.printStackTrace(); //处理异常的代码
        }finally {
            lock.unlock();
        }
    }

}
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
           new Thread(()->{
               for (int i = 1; i <= 10; i++) {
                   shareResource.print5();
               }
              },"A").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                shareResource.print10();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                shareResource.print15();
            }
        },"C").start();
    }
}
