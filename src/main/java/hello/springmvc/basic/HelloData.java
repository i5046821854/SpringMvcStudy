package hello.springmvc.basic;


import lombok.Data;

@Data  //getter, setter, tostring 등이 포함된 annotation
public class HelloData {

    String username;
    int age;
}
