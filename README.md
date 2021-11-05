# Load Tester

Sample Workload SpringBoot Application based Java.

- Here is the API List and, you can use `/load-gen/loop` API for that.

| API List              | Description                |
| :-------------------- | :------------------------- |
| `/`                   | home: return hello-message |
| `/load-gen/loop` or `/load-gen/loop?count=30&range=1000&enableOOM=true`      | Genereate Load with Fibonacci Sum  - `Count (default: 50)`: Loop cycle number, `range (default: 1000)`: Fibonacci's range, `enableOOM (default: false)`: make out of memory |

## How to Use on docker image

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

- http://localhost:8080
- http://localhost:8080/load-gen/loop

### Clean up

```bash
# Stop Container
docker stop java-sample-app

# Remove Container
docker rm java-sample-app

# Remove Image
docker rmi java-sample-app
```
