FROM tomcat:9.0-jre8
RUN apt-get update && apt-get install -y mysql-server
COPY mysql/init.sql /docker-entrypoint-initdb.d/init.sql
ENV MYSQL_ROOT_PASSWORD=hatem
RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY target/demo_listEmployee-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
EXPOSE 3306
CMD service mysql start && catalina.sh run
