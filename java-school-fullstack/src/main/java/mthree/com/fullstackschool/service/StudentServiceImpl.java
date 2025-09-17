package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.dao.StudentDao;
import mthree.com.fullstackschool.model.Course;
import mthree.com.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceInterface {

    //YOUR CODE STARTS HERE
    StudentDao sdao;
    CourseDao cdao;

    public StudentServiceImpl(StudentDao sdao) {
        this.sdao = sdao;
    }


    //YOUR CODE ENDS HERE

    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE

        return sdao.getAllStudents();

        //YOUR CODE ENDS HERE
    }

    public Student getStudentById(int id) {
        //YOUR CODE STARTS HERE

        try{
            return sdao.findStudentById(id);
        }catch(DataAccessException e){
            Student s = new Student();
            s.setStudentFirstName("Course Not Found");
            s.setStudentLastName("Course Not Found");
            return s;
        }

        //YOUR CODE ENDS HERE
    }

    public Student addNewStudent(Student student) {
        //YOUR CODE STARTS HERE
        boolean empty = false;

        if (student.getStudentFirstName().trim().isEmpty()){
            student.setStudentFirstName("First Name blank, student NOT added");
            empty = true;
        }

        if (student.getStudentLastName().trim().isEmpty()){
            student.setStudentLastName("Last Name blank, student NOT added");
            empty = true;
        }

        if (empty){
            return student;
        }

        return sdao.createNewStudent(student);

        //YOUR CODE ENDS HERE
    }

    public Student updateStudentData(int id, Student student) {
        //YOUR CODE STARTS HERE

        if (id != student.getStudentId()) {
            student.setStudentFirstName("IDs do not match, student not updated");
            student.setStudentLastName("IDs do not match, student not updated");

            return student;
        }

        sdao.updateStudent(student);

        return student;


        //YOUR CODE ENDS HERE
    }

    public void deleteStudentById(int id) {
        //YOUR CODE STARTS HERE

        sdao.deleteStudent(id);

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        if (sdao.findStudentById(studentId).getStudentFirstName().equals("Student Not Found")){
            System.out.println("Student not found");
        }else if (cdao.findCourseById(courseId).getCourseName().equals("Course Not Found")) {
            System.out.println("Course not found");
        }else {
            sdao.deleteStudentFromCourse(studentId, courseId);
            System.out.println("Student: " + studentId + " deleted from course: " + courseId);
        }

        //YOUR CODE ENDS HERE
    }

    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        try {
            if (sdao.findStudentById(studentId).getStudentFirstName().equals("Student Not Found")) {
                System.out.println("Student not found");
            } else if (cdao.findCourseById(courseId).getCourseName().equals("Course Not Found")) {
                System.out.println("Course not found");
            } else {
                sdao.addStudentToCourse(studentId, courseId);
                System.out.println("Student: " + studentId + " added from course: " + courseId);
            }
        }catch (Exception e){
            System.out.println("Student: " + studentId + " already enrolled in course: " + courseId);
        }
        //YOUR CODE ENDS HERE
    }
}
