FROM java:openjdk-8-jdk-alpine
VOLUME /tmp
ADD target/beta-spring-0.0.1-SNAPSHOT.jar app.jar

#设置alpine系统时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/${TZ} /etc/localtime && echo ${TZ} > /etc/timezone

EXPOSE 9090
ENTRYPOINT [ "java", "-jar", "/app.jar" ]