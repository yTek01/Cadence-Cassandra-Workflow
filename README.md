# Cadence-Cassandra-Workflow

### **_Start by making a directory for your cadence workflows_** 

```
mkdir cadence
```
***I assumes you to have***
* *A basic understanding of Docker images and containers.*

* *Docker installed on your local system, if you haven't please see to install it [Docker Installation Instructions](https://docs.docker.com/engine/installation/).*

*Make sure docker is running locally, if you don’t have it already, please get it from the docker webpage. Get the latest version of the Cadence YAML file that contains the necessary images using the command below.** 

```
curl -O https://raw.githubusercontent.com/uber/cadence/master/docker/docker-compose.yml && curl -O https://raw.githubusercontent.com/uber/cadence/master/docker/prometheus_config.yml
```
### **_Pull and start all the necessary images in the YAML file_**
```
docker-compose up
```
### **_When the containers are running, then pull the latest Cadence CLI image using the command below_**
```
docker run --rm ubercadence/cli:master
```

*For your Cadence workflow, you must always register a domain for your Cadence workflow, run the following command once before running any new samples with a separate domain. Here we have created a domain called hello-domain.**

```
docker run --network=host --rm ubercadence/cli:master --do hello-domain domain register
```

### *_Note: If you are getting a connection refused problems with registering a domain, you can go into the docker container and execute the following command to register workflow domain._**
```
docker exec -it cadence_cadence_1 /bin/bash
```

### **_Register your domain_**
```
cadence --address $(hostname -i):7933 --do hello-domain domain register
```

### **_Navigate to cadence/src/main/java/com/cadence/App.java and start the workflow_**

### **_Open another terminal to confrim that the domain has been created_**
```
docker run --network=host --rm ubercadence/cli:master --do hello-domain domain describe
```

*See *[blog.anant.us](https://blog.anant.us/) for full article*