package aws.lambda.controller;

import static lombok.AccessLevel.PRIVATE;

import aws.lambda.domain.user.Teacher;
import aws.lambda.service.TeacherService;
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
@RequestMapping(path = "/teachers")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TeacherController {

  TeacherService teacherService;

  @GetMapping
  public List<Teacher> getTeachers(
      @QueryParam("userId") String userId,
      @QueryParam("name") String name) {
    return teacherService.getTeachers(userId, name);
  }

  @GetMapping(path = "/{name}")
  public Teacher getTeacher(@PathVariable String name) {
    return teacherService.getTeacher(name);
  }

  @PostMapping
  public Teacher createTeacher(@RequestBody Teacher teacher) {
    return teacherService.createTeacher(teacher);
  }

  @PutMapping("/{userId}")
  public Teacher updateTeacher(@RequestBody Teacher teacher, @PathVariable String userId) {
    return teacherService.updateTeacher(teacher, userId);
  }

  @DeleteMapping("/{userId}")
  public void deleteTeacher(@PathVariable String userId) {
    teacherService.deleteTeacher(userId);
  }

}
