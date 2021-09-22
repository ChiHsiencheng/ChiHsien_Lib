package cn.chihsien.fun.chihsien_lib.JUC.JMM;

/**
 * @describe JMM
 * @auther chihsien
 */
public class JMM_Demo {

    @SuppressWarnings("ALL")
    static
    class MyNumber {
        //volatile 对其他线程进行通知
        volatile int number = 10;

        public void addTo1024() {
            this.number = 1024;
        }
    }

}

