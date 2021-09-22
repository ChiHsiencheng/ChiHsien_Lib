package cn.chihsien.fun.chihsien_lib.JUC.CAS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @describe
 * @auther chihsien
 */
@Data
@ToString
@AllArgsConstructor
class User{
    String userName;
    Integer age;
}

public class AtomicReferenceDemo{
    public static void main(String[] args) {
        User z2 = new User("z2", 22);
        User z3 = new User("z3", 22);

        AtomicReference<Object> atomicReference = new AtomicReference<>();
        atomicReference.set(z2);

        System.out.println(atomicReference.compareAndSet(z2, z3)+"\t"+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z2, z3)+"\t"+atomicReference.get().toString());
    }

}