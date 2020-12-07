package aws.lambda.domain.user;

import static lombok.AccessLevel.PROTECTED;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Setter
@SuperBuilder(toBuilder = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PROTECTED)
public abstract class User {

  String userId;
  UserType userType;
  String name;
  String email;

}
