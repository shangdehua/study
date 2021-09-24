package com.java8.Interface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Interface {

    private static final String divisionBig = "=============================================";
    private static final String divisionSmall = "---------------------";

    public static void main(String[] args) {
        InterfaceTest1();
        System.out.println(divisionBig);
        InterfaceTest2();
    }

    /**
     * @Description 使用Lambda表达式来表示该接口的一个实现(注 ： JAVA 8 之前一般是用匿名类实现的)
     * @Author shangdehua
     * @Date 2021/9/24 16:06
     */
    private static void InterfaceTest1() {
        GreetingService greetService1 = message -> System.out.println("Hello " + message);
        greetService1.sayMessage("Functional Interface");
    }

    @FunctionalInterface
    interface GreetingService {
        void sayMessage(String message);
    }

    /**
     * @Description 函数式接口实例
     * Predicate <T> 接口是一个函数式接口，它接受一个输入参数 T，返回一个布尔值结果。
     * 该接口包含多种默认方法来将Predicate组合成其他复杂的逻辑（比如：与，或，非）。
     * 该接口用于测试对象是 true 或 false。
     * 我们可以通过以下实例（Java8Tester.java）来了解函数式接口 Predicate <T> 的使用：
     * @Author shangdehua
     * @Date 2021/9/24 16:20
     */
    private static void InterfaceTest2() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // Predicate<Integer> predicate = n -> true
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // n 如果存在则 test 方法返回 true

        System.out.println("输出所有数据:");
        // 传递参数 n
        eval(list, n -> true);

        // Predicate<Integer> predicate1 = n -> n%2 == 0
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // 如果 n%2 为 0 test 方法返回 true

        System.out.println("输出所有偶数:");
        eval(list, n -> n % 2 == 0);

        // Predicate<Integer> predicate2 = n -> n > 3
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // 如果 n 大于 3 test 方法返回 true

        System.out.println("输出大于 3 的所有数字:");
        eval(list, n -> n > 3);
    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            boolean test = predicate.test(n);
            if (predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }

}
