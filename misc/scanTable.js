var dynamodb = new AWS.DynamoDB({
  region: 'eu-central-1',
  endpoint: "http://localhost:8000"
});
var tableName = "Users";

var params = {
  TableName: tableName,
  Select: "ALL_ATTRIBUTES"
};


function doScan(response) {
  if (response.error) ppJson(response.error); // an error occurred
  else {
    ppJson(response.data); // successful response

    // More data.  Keep calling scan.
    if ('LastEvaluatedKey' in response.data) {
      response.request.params.ExclusiveStartKey = response.data.LastEvaluatedKey;
      dynamodb.scan(response.request.params)
      .on('complete', doScan)
      .send();
    }
  }
}
console.log("Starting a Scan of the table");
dynamodb.scan(params)
.on('complete', doScan)
.send();