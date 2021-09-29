package cn.chihsien.fun.chihsien_lib.GC;

/**
 * @describe
 * @auther chihsien
 */
public class StrongReferenceDemo {

    public static void main(String[] args) {
        Object o1 = new Object();//强引用
        Object o2 = o1;//o2引用赋值
        o1 = null; //置空
        System.gc();
        System.out.println(o2);//不会被垃圾回收
    }
}
