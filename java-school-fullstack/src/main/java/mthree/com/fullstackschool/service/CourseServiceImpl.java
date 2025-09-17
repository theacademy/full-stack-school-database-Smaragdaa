package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.dao.CourseDaoImpl;
import mthree.com.fullstackschool.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseServiceInterface {

    //YOUR CODE STARTS HERE
    CourseDao cdao;

    public CourseServiceImpl(CourseDao cdao) {
        this.cdao = cdao;
    }

    //YOUR CODE ENDS HERE

    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE

        return cdao.getAllCourses();

        //YOUR CODE ENDS HERE
    }

    public Course getCourseById(int id) {
        //YOUR CODE STARTS HERE
        try{
            return cdao.findCourseById(id);
        }catch(DataAccessException e){
            Course c = new Course();
            c.setCourseName("Course Not Found");
            c.setCourseDesc("Course Not Found");
            return c;
        }

        //YOUR CODE ENDS HERE
    }

    public Course addNewCourse(Course course) {
        //YOUR CODE STARTS HERE
        boolean empty = false;

        if (course.getCourseName().trim().isEmpty()){
            course.setCourseName("Name blank, course NOT added");
            empty = true;
        }

        if (course.getCourseDesc().trim().isEmpty() ){
            course.setCourseDesc("Description blank, course NOT added");
            empty = true;
        }

        if (empty){
            return course;
        }

        return cdao.createNewCourse(course);

        //YOUR CODE ENDS HERE
    }

    public Course updateCourseData(int id, Course course) {
        //YOUR CODE STARTS HERE

        if (id != course.getCourseId()) {
            course.setCourseName("IDs do not match, course not updated");
            course.setCourseDesc("IDs do not match, course not updated");
            return course;
        }

        cdao.updateCourse(course);

        return course;

        //YOUR CODE ENDS HERE
    }

    public void deleteCourseById(int id) {
        //YOUR CODE STARTS HERE

        cdao.deleteCourse(id);
        System.out.println( "Course ID: " + id + " deleted");

        //YOUR CODE ENDS HERE
    }
}
