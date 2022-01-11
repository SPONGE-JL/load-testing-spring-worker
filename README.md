# [Spring worker for load-testing](https://hub.docker.com/r/dev2sponge/load-testing-spring-worker)

Sample Workload SpringBoot Application based Java.

## API list

- **[GET] `/`**: root (return hello-message)
- **[GET] `/load-gen/loop`**: load-testing-point
  - [Param] `count (default: 50)`: Loop cycle count
  - [Param] `range (default: 1000)`: Fibonacci's range
  - [Param] `enableOOM (default: false)`: make out of memory
  - ex. `/load-gen/loop?count=50&range=1000&enableOOM=false`

## How to use on docker image

```bash
# Build Docker Image
docker build -t java-sample-app .

# Run on docker image
docker run -d \
  --name java-sample-app \
  -p 8080:8080 \
  java-sample-app
  
# Check the log from running container
docker logs -f java-sample-app
```

access to ...

- <http://localhost:8080>
- <http://localhost:8080/load-gen/loop>

### Clean up

```bash
# Stop Container
docker stop java-sample-app

# Remove Container
docker rm java-sample-app

# Remove Image
docker rmi java-sample-app
```
