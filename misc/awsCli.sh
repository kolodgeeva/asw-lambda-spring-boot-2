#Run docker dynamodb-local
docker run -p 8000:8000 amazon/dynamodb-local -jar DynamoDBLocal.jar -inMemory -sharedDb

#List tables
aws dynamodb list-tables --endpoint-url http://localhost:8000

#Create table
aws dynamodb create-table --table-name items \
--attribute-definitions AttributeName=itemId,AttributeType=S \
--key-schema AttributeName=itemId,KeyType=HASH \
--billing-mode PAY_PER_REQUEST \
--endpoint-url http://localhost:8000

#Create table with GSI
aws dynamodb create-table \
--table-name Users \
--attribute-definitions AttributeName=userId,AttributeType=S AttributeName=userType,AttributeType=S AttributeName=name,AttributeType=S \
--key-schema AttributeName=userId,KeyType=HASH \
--global-secondary-indexes \
"[
            {
                \"IndexName\": \"UserIdGSI\",
                \"KeySchema\": [
                    {\"AttributeName\": \"userType\",\"KeyType\":\"HASH\"},
                    {\"AttributeName\": \"userId\",\"KeyType\":\"RANGE\"}
                ],
                \"Projection\": {
                    \"ProjectionType\": \"INCLUDE\",
                    \"NonKeyAttributes\": [\"name\", \"email\", \"subject\", \"form\"]
                }
            },
            {
                \"IndexName\": \"UserNameGSI\",
                \"KeySchema\": [
                    {\"AttributeName\": \"userType\",\"KeyType\":\"HASH\"},
                    {\"AttributeName\": \"name\",\"KeyType\":\"RANGE\"}
                ],
                \"Projection\": {
                    \"ProjectionType\": \"INCLUDE\",
                    \"NonKeyAttributes\": [\"userId\", \"email\", \"subject\", \"form\"]
                }
            }
        ]" \
--billing-mode PAY_PER_REQUEST \
--endpoint-url http://localhost:8000

#Describe table
aws dynamodb describe-table --table-name Users --endpoint-url http://localhost:8000

#Scan table
aws dynamodb scan \
    --table-name Users \
    --endpoint-url http://localhost:8000
