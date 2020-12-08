package aws.lambda.controller;

import static lombok.AccessLevel.PRIVATE;

import aws.lambda.domain.user.Student;
import aws.lambda.service.StudentService;
import java.util.List;
import javax.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
@RequestMapping(path = "/students")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class StudentController {

  StudentService studentService;

  @GetMapping
  public List<Student> getStudents(
      @QueryParam("userId") String userId,
      @QueryParam("name") String name) {
    return studentService.getStudents(userId, name);
  }

  @GetMapping(path = "/{name}")
  public Student getStudent(@PathVariable String name) {
    return studentService.getStudent(name);
  }

  @PostMapping
  public Student createStudent(@RequestBody Student student) {
    return studentService.createStudent(student);
  }

  @PutMapping("/{userId}")
  public Student updateStudent(@RequestBody Student student, @PathVariable String userId) {
    return studentService.updateStudent(student, userId);
  }

  @DeleteMapping("/{userId}")
  public void deleteStudent(@PathVariable String userId) {
    studentService.deleteStudent(userId);
  }

}
