### Build
User `mvn clean package` to run tests and build application artifact.

### Deploy
After configuring eb-cli and Beanstalk environment (see [sample environment with zero downtime deployment](https://github.com/bartlomiejmakarewicz/revolut-ops/blob/master/revolut-dev.env.yml)), use `eb deploy` to deploy application.
