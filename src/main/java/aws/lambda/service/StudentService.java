package aws.lambda.service;

import static aws.lambda.domain.user.UserType.STUDENT;
import static lombok.AccessLevel.PRIVATE;

import aws.lambda.domain.user.Student;
import aws.lambda.domain.user.StudentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class StudentService {

  StudentRepository studentRepository;

  public List<Student> getStudents() {
    return studentRepository.findByUserType(STUDENT);
  }

  public Student getStudent(String userId) {
    return studentRepository.findById(userId).orElse(null);
  }

  public Student createStudent(Student student) {
    return studentRepository.save(student);
  }

  public Student updateStudent(Student student, String userId) {
    Student dbStudent = studentRepository.findById(userId)
        .orElseThrow(() -> new IllegalStateException(
            String.format("Item with id %s doesn't exist", userId)));

    Student updatedStudent = dbStudent.toBuilder()
        .name(student.getName())
        .email(student.getEmail())
        .form(student.getForm())
        .build();

    return studentRepository.save(updatedStudent);
  }

  public void deleteStudent(String userId) {
    Student student = studentRepository.findById(userId)
        .orElseThrow(() -> new IllegalStateException(
            String.format("Item with id %s doesn't exist", userId)));
    studentRepository.delete(student);
  }

}
