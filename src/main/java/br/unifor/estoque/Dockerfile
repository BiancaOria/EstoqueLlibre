# Usar uma imagem do OpenJDK
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o JAR do Spring Boot para o contêiner
COPY target/estoque-api-0.0.1-SNAPSHOT.jar /app/estoque-api.jar

# Expõe a porta da aplicação
EXPOSE 8080

# Comando para rodar a aplicação Spring Boot
CMD ["java", "-jar", "estoque-api.jar"]
