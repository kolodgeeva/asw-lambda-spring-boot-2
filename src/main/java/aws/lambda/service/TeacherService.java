package aws.lambda.service;

import static aws.lambda.domain.user.UserType.TEACHER;
import static lombok.AccessLevel.PRIVATE;

import aws.lambda.domain.user.Teacher;
import aws.lambda.domain.user.TeacherRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TeacherService {

  TeacherRepository teacherRepository;

  public List<Teacher> getTeachers() {
    return teacherRepository.findByUserType(TEACHER);
  }

  public Teacher getTeacher(String userId) {
    return teacherRepository.findById(userId).orElse(null);
  }

  public Teacher createTeacher(Teacher teacher) {
    return teacherRepository.save(teacher);
  }

  public Teacher updateTeacher(Teacher teacher, String userId) {
    Teacher dbTeacher = teacherRepository.findById(userId)
        .orElseThrow(() -> new IllegalStateException(
            String.format("Item with id %s doesn't exist", userId)));

    Teacher updatedTeacher = dbTeacher.toBuilder()
        .name(teacher.getName())
        .email(teacher.getEmail())
        .subject(teacher.getSubject())
        .build();

    return teacherRepository.save(updatedTeacher);
  }

  public void deleteTeacher(String userId) {
    Teacher teacher = teacherRepository.findById(userId)
        .orElseThrow(() -> new IllegalStateException(
            String.format("Item with id %s doesn't exist", userId)));
    teacherRepository.delete(teacher);
  }

}
