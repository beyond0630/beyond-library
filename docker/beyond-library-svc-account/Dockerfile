FROM openjdk:11

RUN groupadd --gid 1000 beyond && \
    useradd --uid 1000 --gid 1000 beyond && \
        echo 'Asia/Shanghai' > /etc/timezone && \
    ln -snf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

ENV JAVA_OPTS="-server -Xms128m -Xmx512m" \
    JAVA_ARGS="" \
    SPRING_PROFILES_ACTIVE=default


ADD beyond-library-svc-account/target/beyond-library-svc-account-*.jar /beyond-library-svc-account.jar

ADD docker/beyond-library-svc-account/entrypoint.sh /

RUN chmod +x /entrypoint.sh

USER 1000

EXPOSE 9080

ENTRYPOINT ["./entrypoint.sh"]
