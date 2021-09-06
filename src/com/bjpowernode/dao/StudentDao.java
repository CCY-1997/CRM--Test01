package com.bjpowernode.dao;

import com.bjpowernode.domain.Student;
import com.bjpowernode.vo.StudentAndClassroomVO;

import java.util.List;
import java.util.Map;

/**
 * 作者：阿义
 */
public interface StudentDao {

    public Student getById(String id);

    public void save(Student s);

    List<Student> getAll();

    Student select1(String a001);

    List<Student> select2(int i);

    List<Student> select3(String zs, int i);

    List<Student> select4(Student s);

    List<Student> select5(Map<String, Object> map);

    List<Student> select6(String z);

    String select7(String a001);

    List<String> select8();

    int select9();

    List<Map<String, Object>> select10();

    List<Student> select11();

    List<Student> select12();

    Student select13(String a001);

    List<Map<String, Object>> select14();

    List<StudentAndClassroomVO> select15();

    List<StudentAndClassroomVO> select16(String z);
}
