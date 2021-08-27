# Martin Robots
Application to determines each sequence of robot positions and reports the final position of the robot.

# Languages
- Java

# Required
- Maven
- Spring Boot

# Run the Server
```sh
# maven
mvn spring-boot:run
```
# REST API

## Check if Robots is fall and the final destination

### Request

`GET api/check/{Max X coordination}/{Max Y coordination}/{Inital X coordination}/{Initial Y coordination}/{Initial Direction}/{Instruction}`

    https://localhost:8000/api/check/5/3/1/1/E/RFRFRFRF

### Response

   {"result":"1 1 N","steps":null}


## Check if Robots is fall and the final destination with detail steps

### Request

`GET api/detailcheck/{Max X coordination}/{Max Y coordination}/{Inital X coordination}/{Initial Y coordination}/{Initial Direction}/{Instruction}`

    https://localhost:8000/api/detailcheck/5/3/1/1/E/RFRFRFRF

### Response

   {"result":"1 1 N","steps":[{"instruction":"R","position":"1 1 E"},{"instruction":"F","position":"2 1 E"},{"instruction":"R","position":"2 1 S"},{"instruction":"F","position":"2 0 S"},{"instruction":"R","position":"2 0 W"},{"instruction":"F","position":"1 0 W"},{"instruction":"R","position":"1 0 N"},{"instruction":"F","position":"1 1 N"}]}


# Demo site

https://eric-martian-robots.herokuapp.com/api/detailcheck/5/3/1/1/E/RFRFRFRF
