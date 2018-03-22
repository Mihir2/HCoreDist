# Fast Hierarchical Clustering

Distributed Hierarchical Clustering with Importance Sampling

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Hadoop & Java

### Installing

A step by step series of examples that tell you have to get a development env running

```
src/main/java/HCdist/

-->HC.java
-->HCMapper.java
-->HCReducer.java
-->attributeMean.java
```

## Running the tests

Arguments required for the main JAVA file:

```
HC <inputAttrPath> <inputDataPath> <outputPath> <NumOfInstances> <NumOfPartitions>
```

Arguments for running jar:

hadoop jar <nameOfJar> <classPath> <inputAttrPath> <inputDataPath> <outputPath> <NumOfInstances> <NumOfPartitions>

```
hadoop jar HC-Distributed.jar HC-Distributed.HC /attribute.arff /instances.arff /output 20 2
```

## Deployment

//TODO


## Authors

Professor Haimonti Dutta <br/>
Akshat Sehgal <br/>
Mihir Chauhan <br/>
