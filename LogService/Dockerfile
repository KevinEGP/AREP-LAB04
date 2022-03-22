FROM openjdk:8

WORKDIR /app/bin

ENV PORT 35000

COPY /target/classes /app/bin/classes
COPY /target/dependency /app/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","co.edu.eci.App"]