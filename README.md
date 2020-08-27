# Searcher-api

## API rest para gestión de hoteles, habitaciones y servicios

## Requisitos técnicos
 - JDK11
 - Docker
 - Maven

##Aplicación

La arquitectuta de este proyecto esta más orientada a un enfoque DDD por su claridad a la hora de entender código y dominio y como se separan la lógica de negocio de la infrastructura de la aplicación. También se ha optado por un patrón CQRS por ser una aplicación donde claramente se ven potenciadas las queries frente a los command, aunque la idea en estos patrones es separar también los repositorios y sincronizarlos mediante eventos. Sería una interesante mejora a realizar pero lo he omitido por falta de tiempo.

Para la capa de persistencia se ha elegido mybatis porque considero que se adapta mejor a las posibles relaciones, paginaciones y búsquedas. Bases de datos según profile:
 - Local: H2 en memoria
 - Prod: PostgreSQL 

Para gestión de dependencias y tareas se emplea Maven.

El API es un api rest de spring boot securizada mediante JWT y se expone tambien swagger

Se han implementado también algunos test unitarios a modo de ejemplo de la capa de dominio y test de integración de la capa web. Además se incluye un plugin de maven para test mutantes.

El proyecto también incluye:
  - Jenkinsfile: Pipeline declarativo donde se definen stages para checkout, compile, build y sonar.
  - Sonar: Software para calidad de código.
  - Jacoco: plugin para cobertura de código.
  - Dockerfile con las instrucciones para crear una imagen de docker con la aplicación.

###Configuracion

Se pueden localizar las propiedades de la aplicación en el directorio resources. Existe un fichero común a todos los entornos y un fichero por entorno con las propiedades especificas del mismo.

Las propiedades para los mensajes internacionalizados se encuentran alojadas en resources/i18n/messages_[lang].properties

###Construcción

La construcción y gestión de dependencias se realiza mediante Maven

```bash
mvn clean install
``` 

y generara un archivo searcher-api.war que desplegaremos posteriormente en tomcat
 
###Despliegue

####Configuración entorno

La aplicación esta preparada para correr sobre dos entornos: local y prod. En ambos casos se debe definiir la variable de entorno LOGS_PATH con la ruta donde se quieran almacenar los logs.

Adicionalmente en el profile de prod se necesita disponer de una BBDD postgreSQL cuyas propiedades se definen en el fichero application-prod.yaml. 

###Ejecución

La aplicación se puede ejecutar mediante línea de comandos o a través de un contenedor de docker. Cuenta con unos scripts SQL para generar una batería de pruebas automáticamente en cada ejecución.

####Linea de comandos

Ejecutar el comando 

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
``` 

Y comprobar el correcto funcionamiento en la siguiente dirección:

 - http://localhost:8080/searcher-api/actuator/health

####Docker

En primer lugar es necesario construir la imagen para poder desplegar nuestra aplicación en un contenedor de docker. Para ello, ubicado en el directorio raiz, ejecuta:

```
docker build -t searcher-api .
```

Este comando generará una imagen empleando el fichero Dockerfile existente en la raiz del proyecto. Para comprobar que se ha generado la imagen correctamente puedes ejecutar:

```
docker images
```
 
Una vez generada la imagen puedes ejecutarla en un contenedor mediante el comando:

```
 docker run  -e "SPRING_PROFILES_ACTIVE=local" -p 8080:8080 -t searcher-api
```

Para comprobar que efectivamente nuestra aplicación está levantada en el contenedor docker, accede a la url: ```http request http://localhost:8080/kerberos-api/[current-version]/actuator/health

Y comprobar el correcto funcionamiento en la siguiente dirección:

 - http://localhost:8080/searcher-api/actuator/health
 
###Swagger

Se expone swagger para interacción con el API mediante una interfaz gráfica.

 - http://localhost:8080/searcher-api/swagger-ui.html
 
*Es necesaria autenticación mediante Bearer. Para obtener el token se debe atacar al endpoint 

 - http://localhost:8080/searcher-api/v1/auth/authenticate
 
Pasando en el body como form-data
 - username: test
 - password: 12345
 
Y se obtendrá el bearer en los headers de la respuesta.

###Postman

Se expone también una colección de postman con los diferentes endpoints del API

 - https://www.getpostman.com/collections/4f0e7671371fdaee5a0f

###Health

La aplicación expone un endpoint para consultar la salud del sistema:
 - http://localhost:8080/searcher-api/actuator/health
 
Adicionalmente Spring cuenta con una serie de actuators que quedan como mejora. Un interesante artículo sería el siguiente:
 - https://www.baeldung.com/spring-boot-actuators
