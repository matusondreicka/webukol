# webukol ... Java Homework – Best NBA players tweet stream

The project is deployed on https://webukol.herokuapp.com/

To run webukol locally you need to clone from github, build and run the SpringBoot application:

```
git clone https://github.com/matusondreicka/webukol.git
cd webukol/
./gradlew build
java -jar build/libs/webukol-0.0.1-SNAPSHOT.jar
```

Then the app is running on http://localhost:8080/ The fronted in AngularJS takes data from endpoint http://localhost:8080/players implementented in Java.

