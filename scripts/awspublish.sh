aws s3 cp ../build/libs/astroforecast-lambda-1.0-SNAPSHOT-all.jar s3://glennbech-lambda-deployments/
aws lambda update-function-code --function-name astronomyforecast --s3-bucket glennbech-lambda-deployments --s3-key astroforecast-lambda-1.0-SNAPSHOT-all.jar
