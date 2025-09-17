package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.dao.StudentDao;
import mthree.com.fullstackschool.dao.TeacherDao;
import mthree.com.fullstackschool.model.Student;
import mthree.com.fullstackschool.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherServiceInterface {

    //YOUR CODE STARTS HERE

    TeacherDao tdao;

    public TeacherServiceImpl(TeacherDao tdao) {
        this.tdao = tdao;
    }
    //YOUR CODE ENDS HERE

    public List<Teacher> getAllTeachers() {
        //YOUR CODE STARTS HERE

        return tdao.getAllTeachers();

        //YOUR CODE ENDS HERE
    }

    public Teacher getTeacherById(int id) {
        //YOUR CODE STARTS HERE

        try{
            return tdao.findTeacherById(id);
        }catch(DataAccessException e){
            Teacher t = new Teacher();
            t.setTeacherFName("Teacher Not Found");
            t.setTeacherLName("Teacher Not Found");
            return t;
        }

        //YOUR CODE ENDS HERE
    }

    public Teacher addNewTeacher(Teacher teacher) {
        //YOUR CODE STARTS HERE
        boolean empty = false;

        if (teacher.getTeacherFName().trim().isEmpty()){
            teacher.setTeacherFName("First Name blank, teacher NOT added");
            empty = true;
        }

        if (teacher.getTeacherLName().trim().isEmpty()){
            teacher.setTeacherLName("Last Name blank, teacher NOT added");
            empty = true;
        }

        if (empty){
            return teacher;
        }

        return tdao.createNewTeacher(teacher);
        //YOUR CODE ENDS HERE
    }

    public Teacher updateTeacherData(int id, Teacher teacher) {
        //YOUR CODE STARTS HERE

        if (id != teacher.getTeacherId()) {
            teacher.setTeacherFName("IDs do not match, teacher not updated");
            teacher.setTeacherLName("IDs do not match, teacher not updated");

            return teacher;
        }

        tdao.updateTeacher(teacher);

        return teacher;

        //YOUR CODE ENDS HERE
    }

    public void deleteTeacherById(int id) {
        //YOUR CODE STARTS HERE

        tdao.deleteTeacher(id);

        //YOUR CODE ENDS HERE
    }
}
