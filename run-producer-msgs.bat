@echo OFF
REM Rodando diretamente:
REM set CP=.;amqp-client-4.0.2.jar;slf4j-api-1.7.21.jar;slf4j-simple-1.7.22.jar
REM java -cp %CP% Send

REM Rodando como JAR file
java -jar Producer.jar