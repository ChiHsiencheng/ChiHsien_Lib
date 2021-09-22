package cn.chihsien.fun.chihsien_lib.JUC.JMM;

/**
 * @describe 1.在高并发下使用单例模式：在所需要单例的对象前面加入volatile关键字
 *           2.使用DCL进行双层检测 在加锁前后分别都进行判断
 *
 * @auther chihsien
 */
public class SingletonDemo {

    private static volatile SingletonDemo instance=null;
    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 构造方法");
    }

    /**
     * 双重检测机制 DCL 在加锁前后分别进行判断
     * 但是DCL有个致命缺陷：指令重排导致不一定线程安全
     * 加入volatile可以禁止指令重排
     * @return
     */
    public static SingletonDemo getInstance(){
        if(instance==null){
            synchronized (SingletonDemo.class){
                if(instance==null){
                    instance=new SingletonDemo();
                }
            }
        }
        return instance;
    }
/***
 * @describe
 * @param: args
 * @return void
 * @author ChiHsien<br>
 * @version
 */
    public static void main(String[] args) {
        for (int i = 1; i <=10; i++) {
            new Thread(() ->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
