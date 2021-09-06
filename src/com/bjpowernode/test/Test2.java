package com.bjpowernode.test;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.domain.Student;
import com.bjpowernode.util.SqlSessionUtil;
import com.bjpowernode.vo.StudentAndClassroomVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 作者：阿义
 */

public class Test2 {
    public static void main(String[] args) {

        StudentDao studentDao = SqlSessionUtil.getSession().getMapper(StudentDao.class);

        // 1.测试：parameterType 使用简单数据类型 String
        /*Student s = studentDao.select1("A001");
        System.out.println(s);*/

        // 2.测试：parameterType 使用简单数据类型 int
        // 查询出所有年龄为23岁的学员详细信息
        /*List<Student> sList = studentDao.select2(23);
        for (Student s : sList) {
            System.out.println(s);
        }*/

        // 3.测试：parameterType
        // 需求：查询出姓名为zs  年龄为23岁的学员信息
        // 绝对不可以同时为sql语句传递多个参数
        /*List<Student> sList = studentDao.select3("zs",23);
        for (Student s : sList) {
            System.out.println(s);
        }*/
        // 如果需要为sql语句传递多个参数,我们应该将这多个参数封装到一个domain对象中，或者是打包到一个map集合中

        // 4.测试：parameterType 使用domain为参数 Student s
        // 需求：查询出姓名为zs  年龄为23岁的学员信息
        /*Student s = new Student();
        s.setName("zs");
        s.setAge(23);
        List<Student> sList = studentDao.select4(s);
        for (Student student : sList) {
            System.out.println(student);
        }*/

        // 5.测试：parameterType，使用map为参数
        // 需求：查询出姓名为zs  年龄为23岁的学员信息
        /*Map<String,Object> map = new HashMap<>();
        map.put("name","zs");
        map.put("age",23);
        List<Student> sList = studentDao.select5(map);
        for (Student s : sList) {
            System.out.println(s);
        }*/

        /*
            总结：
                在实际开发项目中，使用domain引用类型，或者是使用map集合都可以
                为sql语句同时传递多个参数

            一般情况下，我们使用domain就可以了
            当domain不符合需求的情况下，使用map集合传值。
        */


        // 6.测试：like模糊查询 使用#{}
        /*List<Student> sList = studentDao.select6("z");
        for (Student s : sList) {
            System.out.println(s);
        }*/

        // 7.测试：resultType 返回String类型
        // 需求：查询出编号为A001的学员的姓名
        /*String name = studentDao.select7("A001");
        System.out.println(name);*/

        // 8.测试：resultType 返回String类型集合
        // 需求：查询出所有学生的姓名
        /*List<String> sList = studentDao.select8();
        for (String s : sList) {
            System.out.println(s);
        }*/

        // 9.测试 resultType 返回int类型
        // 需求：查询出表中一共有多少条信息
        /*int count = studentDao.select9();
        System.out.println(count);*/

        /*
            对于sql语句查询结果，什么时候使用map？
                对于查询的结果，很多情况，使用domain封装不了，所以我们会想到使用map来保存结果
            例如：
                需求:根据姓名分组，查询出来每一个姓名对应的数量
                叫zs的有多少人。
                叫ls的有多少人。
                    select
                        name,count(*)
                    from tbl_student
                    group by name
                对于以上查询结果不能使用domain，因为domain有name属性，但是没有count属性
             使用返回map一定可以保存查询得到的结果。

        */

        // 10.测试：resultType 返回map类型
        /*List<Map<String, Object>> mapList =  studentDao.select10();
        for (Map<String, Object> map : mapList) {
            Set<String> set = map.keySet();
            for (String key : set) {
                System.out.println("key:" + key);
                System.out.println("value:" + map.get(key));
            }
            System.out.println("--------------------");
        }*/

        // 11.测试：resultType 当数据库表字段名称和domain类属性名称不一致时的处理
        /*
            方式一：起别名
                select id,fullname as name from tbl_student
        */

        /*List<Student> sList = studentDao.select11();
        for (Student s : sList) {
            System.out.println(s);
        }*/


        // 12.测试：resultType 当数据库表字段名称和domain类属性名称不一致时的处理
        // 方式二：使用resultMap
        /*List<Student> sList = studentDao.select12();
        for (Student s : sList) {
            System.out.println(s);
        }*/

        // 13.测试：sql片段
        /*Student s = studentDao.select13("A001");
        System.out.println(s);*/

        // 14.测试：多表联查，查询出学生姓名和班级名称
        /*List<Map<String, Object>> mapList = studentDao.select14();
        for (Map<String, Object> map : mapList) {
            Set<String> set = map.keySet();
            for (String key : set) {
                System.out.println("key：" + key);
                System.out.println("value：" + map.get(key));
            }
            System.out.println("--------------------");
        }*/

        // 15.测试：多表联查，查询出学生和班级的所有信息，加VO（Value Object）值对象
        /*
            在实际项目开发中，如果需要为前端展现数据，使用一个domain类型不足以表现出来这些数据
            这时我们可以考虑使用两种技术来实现
            分别为：
                使用map以及vo

            vo指的是创建出来一个类，这个类中的属性是完全由我们自己定义，属性会保存所有需要展现的信息
            vo：
                student
                classroom
        */
        /*List<StudentAndClassroomVO> voList = studentDao.select15();
        for (StudentAndClassroomVO vo : voList) {
            System.out.println(vo);
        }*/

        // 16.测试：多表联查，查询出带有字母z的学生和班级所有信息
        List<StudentAndClassroomVO> voList = studentDao.select16("z");
        for (StudentAndClassroomVO vo : voList) {
            System.out.println(vo);
        }
    }

}
