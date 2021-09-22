package cn.chihsien.fun.chihsien_lib;

import lombok.Data;

/**
 * @describe
 *  1.基本类型（byte,short,int,long,double,float,char,boolean）为传值；
 *  2.对象类型（Object，数组，容器）为传引用；
 *  3.String、Integer、Double等immutable类型因为类的变量设为final属性，无法被修改，只能重新赋值或生成对象。
 *  当Integer作为方法参数传递时，对其赋值会导致原有的引用被指向了方法内的栈地址，
 *  失去原有的的地址指向，所以对赋值后的Integer做任何操作都不会影响原有值。
 * @auther chihsien
 */
public class TestTransferValue {
    public void changeValue1(int age){
        age = 30;
    }

    public void changeValue2(Person person){
        person.setPersonName("xxx");
    }
    public void changeValue3(String str){
        str = "xxx";
    }

    public static void main(String[] args) {
        TestTransferValue test = new TestTransferValue();


        int age =20;
        //基本类型传复印件 传的是值 在main方法的作用域里
        test.changeValue1(age);//将Main里的age复制一份给了外面changeValue2方法
        System.out.println(age);//此处打印的还是main里的age

        Person person = new Person("abc");
        //将name为abc的person对象的引用地址(指针)传给changeValue2 changeValue2再将其name改为xxx 但是引用地址没变 只是改了那个地址的参数
        test.changeValue2(person);
        //此时指向的还是main的abc的引用地址 只是引用地址的值被改为了xxx
        System.out.println(person.getPersonName());

        String str = "abc";
        test.changeValue3(str);
        //string存在于常量池
        System.out.println(str);


    }
}
