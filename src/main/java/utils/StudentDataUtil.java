package utils;

import model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDataUtil {
    public static List<Student> getStudents() {
        //create an empty list
        List<Student> students = new ArrayList<>();
        //add sample data
        students.add(new Student("Mary", "Manson", "maryman@gmail.com"));
        students.add(new Student("Dolf", "Larson", "dolph@gmail.com"));
        students.add(new Student("John", "Johnson", "johnn@gmail.com"));
        //return the list
        return students;
    }
}
