package cn.chihsien.fun.chihsien_lib.Queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @describe
 * @auther chihsien
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
     /*   System.out.println(queue.add("a"));
        System.out.println(queue.add("b"));
        System.out.println(queue.add("c"));
*/

    /*    System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
        System.out.println(queue.offer("x"));*/

       /* try {
            queue.put("a");
            queue.put("a");
            queue.put("a");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


        //System.out.println(queue.element());
        System.out.println("==================");

   /*     try {
            queue.take();
            queue.take();
            queue.take();
            queue.take();//取不到不结束
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        try {
            System.out.println(queue.offer("a", 2L, TimeUnit.SECONDS));
            System.out.println(queue.offer("a", 2L, TimeUnit.SECONDS));
            System.out.println(queue.offer("a", 2L, TimeUnit.SECONDS));
            System.out.println(queue.offer("a", 2L, TimeUnit.SECONDS));//2s后退出
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



     //   System.out.println(queue.remove());
    //        System.out.println(queue.poll());



    }
}
