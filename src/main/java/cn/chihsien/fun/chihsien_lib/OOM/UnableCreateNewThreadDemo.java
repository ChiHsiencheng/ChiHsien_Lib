package cn.chihsien.fun.chihsien_lib.OOM;

/**
 * @describe
 * @auther chihsien
 * 高并发请求服务器的时候 会出现 Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
 * 实际上这是与平台有关
 *  原因:
 *      应用创建了过多的线程 一个应用进程创建的线程 超过系统承载的极限
 *      服务器不允许应用创建过多的线程 linux系统默认允许单个进程可以创建的线程数是1024个
 *  解决:
 *      降低应用创建的线程数量
 *      如果实在是需要那么多的线程数量 修改Linux服务器的配置 扩大默认限制
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for (int i = 1; ; i++) {
            System.out.println("********i="+i);
               new Thread(()->{
                      try{Thread.sleep(Integer.MAX_VALUE );}catch(InterruptedException e){e.printStackTrace();}
                  },""+i).start();
        }
    }
}
