# 直接使用 OpenJDK 17 作为运行时环境
FROM openjdk:17-jdk-slim

WORKDIR /app

# 直接从本地复制打好的 JAR 文件到容器中
COPY VIS-0.0.1-SNAPSHOT.jar app.jar

# 设置启动命令
#ENTRYPOINT ["java", "-jar",  "app.jar","--spring.profiles.active=prod"]

# Run the web service on container startup.
CMD ["java","-jar","app.jar","--spring.profiles.active=prod"]