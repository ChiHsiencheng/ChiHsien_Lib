package cn.chihsien.fun.chihsien_lib.OOM;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @describe
 * @auther chihsien
 * VM参数:
 *      -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 * java8之后用元空间Metaspace来替代永久代
 * Metaspace是方法区在HotSpot中的实现 它与持久代最大的区别在于:Metaspace并不在虚拟机内存中 而是使用本地内存
 * 也就是说 在java8中 classe Metadata(the virtual machines internal presentation of java class)
 *  被存储在叫做Metaspace的native memory
 *  Metaspace中存放了以下信息:
 *  虚拟机加载的类信息
 *  常量池
 *  静态变量
 *  即时编译后的代码
 *  模拟Metaspace空间溢出 我们不断往Metaspace里生成类
 */
public class MetaspaceOOMDemo {
    static class OOMTest{

    }
    public static void main(String[] args) {
        int i = 0;//模拟多少次之后发生该错误
        try{
            while (true){
                i++;
                //spring cglib的动态字节码技术
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });
                enhancer.create();
            }
        }catch(Throwable e){
            System.out.println("在第"+i+"次之后产生了异常");
            e.printStackTrace();
        }
    }

}
