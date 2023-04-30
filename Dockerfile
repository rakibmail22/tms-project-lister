FROM openjdk:17-jdk-slim
RUN groupadd -g 2000 phapp \
&& useradd -m -u 2001 -g phapp phapp
USER phapp:phapp
ARG DEPENDENCY=build/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.tms.project.ProjectApplication"]