FROM openjdk:alpine

COPY ./docker-entrypoint.sh /

EXPOSE 8080

ENTRYPOINT ["sh", "/docker-entrypoint.sh"]

ADD build/libs/core-1.0.jar /app.jar

HEALTHCHECK --interval=60s --timeout=10s --retries=3  --start-period=30s \
  CMD curl --silent --fail localhost:8080/actuator/health || exit 1
