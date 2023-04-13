# base image setup
FROM openjdk:11-jre

# 로컬 -> 컨테이너로 파일 복사
COPY build/libs/dev-test.war app.war

# execute command
#ARG PROFILE
#ENV profiles=$PROFILE
#RUN echo "$profiles"
#ENTRYPOINT ["java", "-Dspring.profiles.active=$PROFILE", "-jar", "app.war"]
#CMD java -jar -Dspring.profiles.active=real app.war
#CMD java -jar app.war
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=real", "app.war"]
