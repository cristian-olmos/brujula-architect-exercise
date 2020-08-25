FROM tomcat:latest
ADD target/searcher-api.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
