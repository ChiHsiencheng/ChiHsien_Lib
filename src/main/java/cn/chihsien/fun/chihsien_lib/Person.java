package cn.chihsien.fun.chihsien_lib;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @describe
 * @auther chihsien
 */
@Data
@NoArgsConstructor
@Getter
@Setter
public class Person {

    private Integer id;
    private String personName;

    public Person(String personName) {
        this.personName = personName;
    }
}
