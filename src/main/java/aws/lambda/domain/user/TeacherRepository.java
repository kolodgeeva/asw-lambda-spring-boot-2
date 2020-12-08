package aws.lambda.domain.user;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher, String> {

    List<Teacher> findByUserType(UserType userType);

    List<Teacher> findByUserTypeAndUserIdStartsWith(UserType userType, String userId);

    List<Teacher> findByUserTypeAndNameStartsWith(UserType userType, String name);

}
