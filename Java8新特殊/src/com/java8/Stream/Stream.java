package com.java8.Stream;

import java.util.*;
import java.util.stream.Collectors;

public class Stream {

    private static final String divisionBig = "=============================================";
    private static final String divisionSmall = "---------------------";

    public static void main(String[] args) {
        foreachAndfindAndmatch();
        System.out.println(divisionBig);
        filter();
        System.out.println(divisionBig);
        maxAndminAndcount();
        System.out.println(divisionBig);
        mapAndflatMap();
        System.out.println(divisionBig);
        reduce();
        System.out.println(divisionBig);
        toCollect();
        System.out.println(divisionBig);
        countAndaveraging();
        System.out.println(divisionBig);
        partitioningByAndgroupingBy();
        System.out.println(divisionBig);
        joining();
        System.out.println(divisionBig);
        reducing();
        System.out.println(divisionBig);
        sorted();
        System.out.println(divisionBig);
        distinctAndlimitAndskip();

    }

    /**
     * @Description 遍历/匹配（foreach/find/match）
     * @Author shangdehua
     * @Date 2021/9/23 16:25
     */
    private static void foreachAndfindAndmatch() {
        System.out.println(divisionSmall + "遍历/匹配（foreach/find/match）");
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        // 遍历输出符合条件的元素
        list.stream().filter(x -> x > 6).forEach(System.out::println);
        // 匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        // 匹配任意（适用于并行流）
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();
        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x < 6);
        System.out.println("匹配第一个值：" + findFirst.get());
        System.out.println("匹配任意一个值：" + findAny.get());
        System.out.println("是否存在大于6的值：" + anyMatch);
    }

    /**
     * @Description 筛选（filter）
     * @Author shangdehua
     * @Date 2021/9/23 16:27
     */
    public static void filter() {
        System.out.println(divisionSmall + "筛选出Integer集合中大于7的元素，并打印出来");
        List<Integer> list = Arrays.asList(6, 7, 3, 8, 1, 2, 9);
        java.util.stream.Stream<Integer> stream = list.stream();
        stream.filter(x -> x > 7).forEach(System.out::println);
        System.out.println(divisionSmall + "筛选员工中工资高于8000的人，并形成新的集合。");

        List<String> fiterList = Person.getPersonList().stream().filter(x -> x.getSalary() > 8000).map(Person::getName).collect(Collectors.toList());
        System.out.println("高于8000的员工姓名：" + fiterList);
    }

    /**
     * @Description 聚合（max/min/count)
     * @Author shangdehua
     * @Date 2021/9/23 16:37
     */
    public static void maxAndminAndcount() {
        System.out.println(divisionSmall + "获取String集合中最长的元素。");
        List<String> list = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
        Optional<String> max = list.stream().max(Comparator.comparing(String::length));
        System.out.println("最长的字符串：" + max.get());
        Optional<String> min = list.stream().min(Comparator.comparing(String::length));
        System.out.println("最短的字符串：" + min.get());
        System.out.println(divisionSmall + "获取Integer集合中的最大值。");

        List<Integer> integerList = Arrays.asList(7, 6, 9, 4, 11, 6);
        // 自然排序
        Optional<Integer> max1 = integerList.stream().max(Integer::compareTo);
        // 自定义排序
        Optional<Integer> max2 = integerList.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println("自然排序的最大值：" + max1.get());
        System.out.println("自定义排序的最大值：" + max2.get());
        System.out.println(divisionSmall + "获取员工工资最高的人。");

        Optional<Person> max3 = Person.getPersonList().stream().max(Comparator.comparingInt(Person::getSalary));
        System.out.println("员工工资最大值：" + max3.get().getSalary());
        System.out.println(divisionSmall + "计算Integer集合中大于6的元素的个数。");

        long count = Arrays.asList(7, 6, 4, 8, 2, 11, 9).stream().filter(x -> x > 6).count();
        System.out.println("list中大于6的元素个数：" + count);
    }

    /**
     * @Description 映射(map / flatMap)
     * @Author shangdehua
     * @Date 2021/9/23 16:54
     */
    public static void mapAndflatMap() {
        System.out.println(divisionSmall + "英文字符串数组的元素全部改为大写。整数数组每个元素+3。");
        String[] strArr = {"abcd", "bcdd", "defde", "fTr"};
        List<String> strList = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());
        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> intListNew = intList.stream().map(x -> x + 3).collect(Collectors.toList());
        System.out.println("每个元素大写：" + strList);
        System.out.println("每个元素+3：" + intListNew);
        System.out.println(divisionSmall + "将员工的薪资全部增加1000。");

        List<Person> personList = Person.getPersonList();
        // 不改变原来员工集合的方式
        List<Person> personListNew = personList.stream().map(person -> {
            Person personNew = new Person(person.getName(), 0, 0, null, null);
            personNew.setSalary(person.getSalary() + 10000);
            return personNew;
        }).collect(Collectors.toList());
        System.out.println("一次改动前：" + personList.get(0).getName() + "-->" + personList.get(0).getSalary());
        System.out.println("一次改动后：" + personListNew.get(0).getName() + "-->" + personListNew.get(0).getSalary());
        // 改变原来员工集合的方式
        List<Person> personListNew2 = personList.stream().map(person -> {
            person.setSalary(person.getSalary() + 10000);
            return person;
        }).collect(Collectors.toList());
        System.out.println("二次改动前：" + personList.get(0).getName() + "-->" + personListNew.get(0).getSalary());
        System.out.println("二次改动后：" + personListNew2.get(0).getName() + "-->" + personListNew.get(0).getSalary());
        System.out.println(divisionSmall + "将两个字符数组合并成一个新的字符数组。");

        List<String> list = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> listNew = list.stream().flatMap(s -> {
            // 将每个元素转换成一个stream
            String[] split = s.split(",");
            java.util.stream.Stream<String> s2 = Arrays.stream(split);
            return s2;
        }).collect(Collectors.toList());

        System.out.println("处理前的集合：" + list);
        System.out.println("处理后的集合：" + listNew);
    }

    /**
     * @Description 归约(reduce)
     * @Author shangdehua
     * @Date 2021/9/23 17:07
     */
    public static void reduce() {
        System.out.println(divisionSmall + "求Integer集合的元素之和、乘积和最大值。");
        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4);
        // 求和方式1
        Optional<Integer> sum1 = list.stream().reduce((x, y) -> x + y);
        // 求和方式2
        Optional<Integer> sum2 = list.stream().reduce(Integer::sum);
        // 求和方式3
        Integer sum3 = list.stream().reduce(0, Integer::sum);
        // 求乘积
        Optional<Integer> product = list.stream().reduce((x, y) -> x * y);
        // 求最大值方式1
        Optional<Integer> max = list.stream().reduce((x, y) -> x > y ? x : y);
        // 求最大值写法2
        Integer max2 = list.stream().reduce(1, Integer::max);
        System.out.println("list求和：" + sum1.get() + "," + sum2.get() + "," + sum3);
        System.out.println("list求积：" + product.get());
        System.out.println("list求和：" + max.get() + "," + max2);
        System.out.println(divisionSmall + "求所有员工的工资之和和最高工资。");

        List<Person> personList = Person.getPersonList();
        // 求工资之和方式1：
        Optional<Integer> sumSalary = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        // 求工资之和方式2：
        Integer sumSalary2 = personList.stream().reduce(0, (sum, p) -> sum += p.getSalary(),
                (sum4, sum5) -> sum4 + sum5);
        // 求工资之和方式3：
        Integer sumSalary3 = personList.stream().reduce(0, (sum, p) -> sum += p.getSalary(), Integer::sum);

        // 求最高工资方式1：
        Integer maxSalary = personList.stream().reduce(0, (max1, p) -> max1 > p.getSalary() ? max1 : p.getSalary(),
                Integer::max);
        // 求最高工资方式2：
        Integer maxSalary2 = personList.stream().reduce(0, (max5, p) -> max5 > p.getSalary() ? max5 : p.getSalary(),
                (max3, max4) -> max3 > max4 ? max3 : max4);

        System.out.println("工资之和：" + sumSalary.get() + "," + sumSalary2 + "," + sumSalary3);
        System.out.println("最高工资：" + maxSalary + "," + maxSalary2);
    }

    /**
     * @Description 归集(toList / toSet / toMap)
     * @Author shangdehua
     * @Date 2021/9/23 17:14
     */
    public static void toCollect() {
        System.out.println(divisionSmall + "归集(toList/toSet/toMap)");
        List<Integer> list = Arrays.asList(1, 6, 3, 4, 6, 7, 9, 6, 20);
        List<Integer> listNew = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        Set<Integer> set = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toSet());
        List<Person> personList = Person.getPersonList();
        Map<?, Person> map = personList.stream().filter(p -> p.getSalary() > 8000)
                .collect(Collectors.toMap(Person::getName, p -> p));
        System.out.println("toList:" + listNew);
        System.out.println("toSet:" + set);
        System.out.println("toMap:" + map);
    }

    /**
     * @Description 统计(count / averaging)
     * @Author shangdehua
     * @Date 2021/9/23 17:21
     */
    public static void countAndaveraging() {
        System.out.println(divisionSmall + "统计(count/averaging)");
        List<Person> personList = Person.getPersonList();
        // 求总数
        Long count = personList.stream().collect(Collectors.counting());
        // 求平均工资
        Double average = personList.stream().collect(Collectors.averagingDouble(Person::getSalary));
        // 求最高工资
        Optional<Integer> max = personList.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compare));
        // 求工资之和
        Integer sum = personList.stream().collect(Collectors.summingInt(Person::getSalary));
        // 一次性统计所有信息
        DoubleSummaryStatistics collect = personList.stream().collect(Collectors.summarizingDouble(Person::getSalary));
        System.out.println("员工总数：" + count);
        System.out.println("员工平均工资：" + average);
        System.out.println("员工最高工资：" + max.get());
        System.out.println("员工工资总和：" + sum);
        System.out.println("员工工资所有统计：" + collect);
    }

    /**
     * @Description 分组(partitioningBy / groupingBy)
     * @Author shangdehua
     * @Date 2021/9/23 17:30
     */
    public static void partitioningByAndgroupingBy() {
        System.out.println(divisionSmall + "将员工按薪资是否高于8000分为两部分；将员工按性别和地区分组");
        List<Person> personList = Person.getPersonList();
        // 将员工按薪资是否高于8000分组
        Map<Boolean, List<Person>> part = personList.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
        // 将员工按性别分组
        Map<String, List<Person>> group = personList.stream().collect(Collectors.groupingBy(Person::getSex));
        // 将员工先按性别分组，再按地区分组
        Map<String, Map<String, List<Person>>> group2 = personList.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getArea)));
        System.out.println("员工按薪资是否大于8000分组情况：" + part);
        System.out.println("员工按性别分组情况：" + group);
        System.out.println("员工按性别、地区：" + group2);
    }

    /**
     * @Description 接合(joining)
     * @Author shangdehua
     * @Date 2021/9/23 17:33
     */
    public static void joining(){
        System.out.println(divisionSmall+"接合(joining)");
        List<Person> personList = Person.getPersonList();
        String names = personList.stream().map(p -> p.getName()).collect(Collectors.joining(","));
        System.out.println("所有员工的姓名：" + names);
        List<String> list = Arrays.asList("A", "B", "C");
        String string = list.stream().collect(Collectors.joining("-"));
        System.out.println("拼接后的字符串：" + string);
    }

    /**
     * @Description 归约(reducing)
     * @Author shangdehua
     * @Date 2021/9/23 17:35
     */
    public static void reducing(){
        System.out.println(divisionSmall+"归约(reducing)");
        List<Person> personList =Person.getPersonList();
        // 每个员工减去起征点后的薪资之和（这个例子并不严谨，但一时没想到好的例子）
        Integer sum = personList.stream().collect(Collectors.reducing(0, Person::getSalary, (i, j) -> (i + j - 5000)));
        System.out.println("员工扣税薪资总和：" + sum);
        // stream的reduce
        Optional<Integer> sum2 = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        System.out.println("员工薪资总和：" + sum2.get());
    }

    /**
     * @Description 排序(sorted)
     * @Author shangdehua
     * @Date 2021/9/23 17:37
     */
    public static void sorted(){
        System.out.println(divisionSmall+"将员工按工资由高到低（工资一样则按年龄由大到小）排序");
        List<Person> personList = Person.getPersonList();
        // 按工资升序排序（自然排序）
        List<String> newList = personList.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName)
                .collect(Collectors.toList());
        // 按工资倒序排序
        List<String> newList2 = personList.stream().sorted(Comparator.comparing(Person::getSalary).reversed())
                .map(Person::getName).collect(Collectors.toList());
        // 先按工资再按年龄升序排序
        List<String> newList3 = personList.stream()
                .sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getAge)).map(Person::getName)
                .collect(Collectors.toList());
        // 先按工资再按年龄自定义排序（降序）
        List<String> newList4 = personList.stream().sorted((p1, p2) -> {
            if (p1.getSalary() == p2.getSalary()) {
                return p2.getAge() - p1.getAge();
            } else {
                return p2.getSalary() - p1.getSalary();
            }
        }).map(Person::getName).collect(Collectors.toList());
        System.out.println("按工资升序排序：" + newList);
        System.out.println("按工资降序排序：" + newList2);
        System.out.println("先按工资再按年龄升序排序：" + newList3);
        System.out.println("先按工资再按年龄自定义降序排序：" + newList4);
    }

    /**
     * @Description 提取/组合
     * @Author shangdehua
     * @Date 2021/9/23 17:39
     */
    public static void distinctAndlimitAndskip(){
        System.out.println(divisionSmall+"提取/组合");
        String[] arr1 = { "a", "b", "c", "d" };
        String[] arr2 = { "d", "e", "f", "g" };
        java.util.stream.Stream<String> stream1 = java.util.stream.Stream.of(arr1);
        java.util.stream.Stream<String> stream2 = java.util.stream.Stream.of(arr2);
        // concat:合并两个流 distinct：去重
        List<String> newList = java.util.stream.Stream.concat(stream1, stream2).distinct().collect(Collectors.toList());
        // limit：限制从流中获得前n个数据
        List<Integer> collect = java.util.stream.Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
        // skip：跳过前n个数据
        List<Integer> collect2 = java.util.stream.Stream.iterate(1, x -> x + 2).skip(1).limit(5).collect(Collectors.toList());
        System.out.println("流合并：" + newList);
        System.out.println("limit：" + collect);
        System.out.println("skip：" + collect2);
    }


}
