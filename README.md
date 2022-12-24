# ApiTestRPE

Tecnologia: Java + RestAssured + Cucumber + Maven

Scripts Execução: Na classe TestRunner tem os scripts(tags) que podem ser executados. Caminho /ApiTest/src/test/java/tests/TestRunner.java

Na pasta features(/ApiTest/src/test/resources/features) encontra-se as features com os cenários de teste.

No arquivo pom.xml tem as dependencias necessárias para que os scripts sejam executados.

Após a execução é gerado um relatório automatico acessado via web. URL é informada no console. Também é gerado um relatório html localizado na pasta report do projeto
