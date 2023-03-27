package service.impl;

import db.Database;
import gender.Gender;
import model.Student;
import service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService {
    private List<Database>databases;
    private List<Student>students;

    @Override
    public String addStudent(Student student) {
        students.add(student);
        return "Студент ийгиликтүү кошулду!";
    }
    @Override
    public Student getStudentById(int id) {
        return students.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }
    @Override
    public List<Student> getAllStudents() {
        return students.stream().collect(Collectors.toList());
    }
    @Override
    public String updateStudentFullName(int id, String fullName) {
        Student student = students.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        if (student != null) {
            student.setFullName(fullName);
            return "Студенттин толук аты-жөнү ийгиликтүү жаңыртылды!";
        }
        return "Студент табылган жок!";
    }

    @Override
    public List<Student> filterByGender(Gender gender){
        return students.stream().filter(s -> s.getGender() == gender).collect(Collectors.toList());
    }

    @Override
    public void deleteStudentById(int id) {
        students.removeIf(s -> s.getId() == id);

    }
}
