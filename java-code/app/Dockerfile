FROM maven:3-jdk-7
RUN rm -f /usr/share/maven/conf/settings.xml
ADD settings.xml /usr/share/maven/conf/settings.xml
ADD ../pom.xml /tmp/build/

ADD ../src /tmp/build/src
        #构建应用
RUN cd /tmp/build && mvn clean package -DskipTests=true \
        #拷贝编译结果到指定目录
        && mv target/*.jar /app.jar \
        #清理编译痕迹
        && cd / && rm -rf /tmp/build

VOLUME /tmp
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]