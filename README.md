# Books Service API Rest with Spring Boot

Micro Service development in Spring Boot, modeling the response for a REST API Server, backend services.

```text
# Build a JAR
mvn clean package

# Run a JAR
mvn spring-boot:run

# Run in Debug mode.
java -jar -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n your-app.jar
```

**1. Get All Books**
- GET - http://localhost:8080/api/v1/books
- request: none
- response: body JSON
```json
{
    "code": 200,
    "message": "200 OK",
    "data": [
        {
            "id": 1,
            "codebook": "1010001",
            "title": "God my Lord",
            "description": "King of King's my Lord forever",
            "published": true
        },
        {
            "id": 2,
            "codebook": "1010002",
            "title": "Jesus it's pastor",
            "description": "My love for a eternity",
            "published": true
        }
    ]
}
```
**2. Create a Book**
- POST - http://localhost:8080/api/v1/books
- request: body JSON
```json
{
    "codebook": "1010002",
    "description": "My love for a eternity",
    "published": true,
    "title": "Jesus it's pastor"
}
```
- response: body JSON
```json
{
    "code": 200,
    "message": "200 OK",
    "data": {
        "id": 2,
        "codebook": "1010002",
        "title": "Jesus it's pastor",
        "description": "My love for a eternity",
        "published": true
    }
}
```
**3. Update a Book by ID**
- PUT - http://localhost:8080/api/v1/books?id=1
- request: body JSON
```json
{
    "codebook": "1010002",
    "description": "King of King's my Lord forever",
    "published": true,
    "title": "God my Lord"
}
```
response: body JSON
```json
{
    "code": 200,
    "message": "200 OK",
    "data": {
        "id": 1,
        "codebook": "1010002",
        "title": "God my Lord",
        "description": "King of King's my Lord forever",
        "published": true
    }
}
```
**4. Delete Book by ID**
- DELETE - http://localhost:8080/api/v1/books?id=1
- request: none
- response: body JSON
```json
{
    "code": 200,
    "message": "200 OK",
    "data": null
}
```
**5. Find Book by Codebook**
- GET - http://localhost:8080/api/v1/books/codebook?codebook=1010001
- request: Query Params / codebook: string
- response: body JSON
```json
{
    "code": 200,
    "message": "200 OK",
    "data": {
        "id": 2,
        "codebook": "1010001",
        "title": "God my Lord",
        "description": "King of King's my Lord forever",
        "published": true
    }
}
```
**6. Find Book by ID**
- GET - http://localhost:8080/api/v1/books/id?id=2
- request: Query Params / id: long
- response: body JSON
```json
{
    "code": 200,
    "message": "200 OK",
    "data": {
        "id": 2,
        "codebook": "1010002",
        "title": "Jesus it's pastor",
        "description": "My love for a eternity",
        "published": true
    }
}
```

### Dockerfile

```text
FROM openjdk:11-jre-slim-buster
RUN apt-get update && apt-get install curl -y
COPY ./target/backend-springboot-book.jar backend-springboot-book.jar
ENTRYPOINT ["java","-jar","backend-springboot-book.jar"]
```

#### Docker Hub

- Login into Docker Hub account
```text
docker login
```
- Show messages:
```text
Authenticating with existing credentials...
Login with your Docker ID to push and pull images from Docker Hub. If you don't have a Docker ID, head over to https://hub.docker.com to create one.
Username (your-user-show):
Password: <your-password-here>
```
- Show messages:
```text
Authenticating with existing credentials...
Login Succeeded

Logging in with your password grants your terminal complete access to your account.
For better security, log in with a limited-privilege personal access token. Learn more at https://docs.docker.com/go/access-tokens/
```
- Build a JAR
```text
mvn clean package
```
- Build a Images for docker
```text
docker build . -t backend-springboot-book:v1.0.0
```
- Tag a Image for push to docker hub
```text
docker tag d445fafeabcf raulrobinson/backend-springboot-book:v1.0.0
```
- Push image to Docker Hub
```text
docker push raulrobinson/backend-springboot-book:v1.0.0
```