FROM openjdk:8-jdk-alpine as builder
WORKDIR application
ADD ./target/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM openjdk:8-jdk-alpine
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./

# 安装curl
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.ustc.edu.cn/g' /etc/apk/repositories
RUN apk add --update curl && rm -rf /var/cache/apk/*
ENTRYPOINT exec java $JAVA_OPTS $JAVA_DEBUG_OPTS org.springframework.boot.loader.JarLauncher
EXPOSE 8883