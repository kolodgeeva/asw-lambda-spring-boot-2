package aws.lambda.domain.user;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, String> {

  List<Student> findByUserType(UserType userType);

  List<Student> findByUserTypeAndUserIdStartsWith(UserType userType, String userId);

  List<Student> findByUserTypeAndNameStartsWith(UserType userType, String name);

}
