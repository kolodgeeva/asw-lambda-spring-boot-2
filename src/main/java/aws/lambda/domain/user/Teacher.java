package aws.lambda.domain.user;

import static aws.lambda.domain.user.UserType.TEACHER;
import static lombok.AccessLevel.PRIVATE;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@With
@SuperBuilder(toBuilder = true)
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@DynamoDBTable(tableName = "Users")
public class Teacher extends User {

  String subject;

  @DynamoDBAttribute
  public String getSubject() {
    return subject;
  }

  @DynamoDBHashKey
  @DynamoDBIndexRangeKey(globalSecondaryIndexName = "UserIdGSI")
  public String getUserId() {
    return userId;
  }

  @DynamoDBAttribute
  @DynamoDBIndexRangeKey(globalSecondaryIndexName = "UserNameGSI")
  public String getName() {
    return name;
  }

  @DynamoDBAttribute
  public String getEmail() {
    return email;
  }

  @DynamoDBAttribute
  @DynamoDBTypeConvertedEnum
  @DynamoDBIndexHashKey(globalSecondaryIndexNames = {"UserIdGSI", "UserNameGSI"})
  public UserType getUserType() {
    return TEACHER;
  }

}
