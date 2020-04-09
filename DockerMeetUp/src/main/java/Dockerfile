FROM openjdk:7
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
ADD HelloWorld.java .
RUN javac HelloWorld.java
CMD ["java", "HelloWorld"]