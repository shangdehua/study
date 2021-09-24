package com.java8.Methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Methods {

    private static final String divisionBig = "=============================================";
    private static final String divisionSmall = "---------------------";

    public static void main(String[] args) {
        MethodsTest1();
        System.out.println(divisionBig);
        MethodsTest2();
    }

    /**
     * @Description  4 个方法作为例子来区分 Java 中 4 种不同方法的引用
     * @Author shangdehua
     * @Date 2021/9/24 15:23
     */
    private static void MethodsTest1(){
        // 构造器引用：它的语法是Class::new，或者更一般的Class< T >::new实例如下：
        final Car car = Car.create( Car::new );
        final List< Car > cars = Arrays.asList( car );

        // 静态方法引用：它的语法是Class::static_method，实例如下：
        cars.forEach( Car::collide );

        // 特定类的任意对象的方法引用：它的语法是Class::method实例如下：
        cars.forEach( Car::repair );

        // 特定对象的方法引用：它的语法是instance::method实例如下：
        final Car police = Car.create( Car::new );
        cars.forEach( police::follow );
    }

    /**
     * @Description 方法引用实例
     * @Author shangdehua
     * @Date 2021/9/24 15:51
     */
    private static void MethodsTest2(){
        List<String> names = new ArrayList();
        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");
        names.forEach(System.out::println);
    }

    @FunctionalInterface
    public interface Supplier<T> {
        T get();
    }
    static class Car {
        //Supplier是jdk1.8的接口，这里和lamda一起使用了
        public static Car create(final Supplier<Car> supplier) {
            return supplier.get();
        }

        public static void collide(final Car car) {
            System.out.println("Collided " + car.toString());
        }

        public void follow(final Car another) {
            System.out.println("Following the " + another.toString());
        }

        public void repair() {
            System.out.println("Repaired " + this.toString());
        }
    }
}
