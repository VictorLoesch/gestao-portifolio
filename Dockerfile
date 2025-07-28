# Stage 1: build com Maven
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app

# Copia POM e fontes, depois faz o package
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: runtime com JRE
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copia o WAR gerado no builder
COPY --from=builder /app/target/*.war app.war

# Porta exposta
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "/app/app.war"]