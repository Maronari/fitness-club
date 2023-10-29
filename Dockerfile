FROM eclipse-temurin:20-jdk-alpine  
ARG JAR_FILE=target/fitness_club-0.5.jar 
WORKDIR /opt/app  
COPY ${JAR_FILE} fitness_club.jar 
ENTRYPOINT ["java","-jar","fitness_club.jar"]