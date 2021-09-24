package com.java8.Methods;

public class Methods2 {

    /**
     * @Description 默认方法语法格式如下：
     * @Author shangdehua
     * @Date 2021/9/24 16:47
     */
    public interface Vehicle {
        default void print() {
            System.out.println("我是一辆车!");
        }
        // 静态方法

        /**
         * @Description Java 8 的另一个特性是接口可以声明（并且可以提供实现）静态方法。例如：
         * @Author shangdehua
         * @Date 2021/9/24 17:00
         */
        static void blowHorn() {
            System.out.println("按喇叭!!!");
        }
    }

    public interface FourWheeler {
        default void print() {
            System.out.println("我是一辆四轮车!");
        }
    }

    //一个接口有默认方法，考虑这样的情况，一个类实现了多个接口，且这些接口有相同的默认方法，以下实例说明了这种情况的解决方法：
    /**
     * @author: shangdehua
     * @Date: 2021/9/24 16:59
     * @Description: 第一个解决方案是创建自己的默认方法，来覆盖重写接口的默认方法
     */
    /*public class Car implements Vehicle, FourWheeler {
        default void print(){
            System.out.println("我是一辆四轮汽车!");
        }
    }*/

    /**
     * @author: shangdehua
     * @Date: 2021/9/24 16:59
     * @Description: 第二种解决方案可以使用 super 来调用指定接口的默认方法
     */
    public class Car implements Vehicle, FourWheeler {
        @Override
        public void print() {
            Vehicle.super.print();
        }
    }

    /**
     * @author: shangdehua
     * @Date: 2021/9/24 17:03
     * @Description: 默认方法实例
     */ 
    public static class Main {
        public static void main(String args[]) {
            Vehicle vehicle = new Car();
            vehicle.print();
        }

        interface Vehicle {
            default void print() {
                System.out.println("我是一辆车!");
            }

            static void blowHorn() {
                System.out.println("按喇叭!!!");
            }
        }

        interface FourWheeler {
            default void print() {
                System.out.println("我是一辆四轮车!");
            }
        }

        static class Car implements Vehicle, FourWheeler {
            @Override
            public void print() {
                Vehicle.super.print();
                FourWheeler.super.print();
                Vehicle.blowHorn();
                System.out.println("我是一辆四轮汽车!");
            }
        }
    }
}
