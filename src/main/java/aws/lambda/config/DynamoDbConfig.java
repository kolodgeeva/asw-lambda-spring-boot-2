package aws.lambda.config;

import static com.amazonaws.regions.Regions.EU_CENTRAL_1;
import static org.springframework.util.ObjectUtils.isEmpty;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "aws.lambda.domain")
public class DynamoDbConfig {

  @Value("${amazon.dynamodb.endpoint}")
  private String amazonDynamoDBEndpoint;

  @Value("${amazon.aws.accesskey}")
  private String amazonAWSAccessKey;

  @Value("${amazon.aws.secretkey}")
  private String amazonAWSSecretKey;

  @Bean
  public AmazonDynamoDB amazonDynamoDB() {
    AmazonDynamoDBClientBuilder amazonDynamoDBBuilder
        = AmazonDynamoDBClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(amazonAWSCredentials()));

    if (!isEmpty(amazonDynamoDBEndpoint)) {
      amazonDynamoDBBuilder.setEndpointConfiguration(
          new EndpointConfiguration(amazonDynamoDBEndpoint, EU_CENTRAL_1.getName()));
    }

    return amazonDynamoDBBuilder.build();
  }

  @Bean
  public AWSCredentials amazonAWSCredentials() {
    return new BasicAWSCredentials(
        amazonAWSAccessKey, amazonAWSSecretKey);
  }

}
