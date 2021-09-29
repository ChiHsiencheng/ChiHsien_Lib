package cn.chihsien.fun.chihsien_lib.GC;

/**
 * 可以做GCRoots的对象
 *      虚拟机栈(栈帧中的局部变量区,也叫做局部变量表
 *      方法区中的类静态属性引用的对象。
 *      方法区中常量引用的对象
 *      本地方法栈中N( Native方法)引用的对象
 *
 */
public class GCRootDemo {

  private byte[] bytes =  new byte[100*1024*1024];

  public static void m1(){
      GCRootDemo t1 = new GCRootDemo();
      System.gc();
      System.out.println("第一次GC完成");
  }

    public static void main(String[] args) {
        m1();
    }

}
