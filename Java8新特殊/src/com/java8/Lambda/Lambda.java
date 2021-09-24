package com.java8.Lambda;

public class Lambda {

    private static final String divisionBig = "=============================================";
    private static final String divisionSmall = "---------------------";

    public static void main(String[] args) {
        lambdaTest1();
        System.out.println(divisionBig);
        lambdaTest2();
    }

    /**
     * @Description 基本用法
     * @Author shangdehua
     * @Date 2021/9/24 14:59
     */
    private static void lambdaTest1() {
        Lambda tester = new Lambda();
        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;
        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };
        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));

        // 不用括号
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");

    }

    final static String salutation = "Hello! ";

    /**
     * @Description 变量作用域
     * @Author shangdehua
     * @Date 2021/9/24 15:10
     */
    private static void lambdaTest2() {
        GreetingService greetService1 = message ->
                System.out.println(salutation + message);
        greetService1.sayMessage("Runoob");
        System.out.println(divisionSmall);

        // 直接在 lambda 表达式中访问外层的局部变量
        final int num = 1;
        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
        // 输出结果为 3
        s.convert(2);
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);

    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    public interface Converter<T1, T2> {
        void convert(int i);
    }
}
