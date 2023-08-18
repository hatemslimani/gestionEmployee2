FROM tomcat:9.0-jre8
RUN apt-get update && apt-get install -y mysql-server && \
    mysql -e "CREATE DATABASE gestEmployee"
RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY target/demo_listEmployee-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
EXPOSE 3306
CMD service mysql start && \
    mysql -u root -p -e "USE gestEmployee; CREATE TABLE IF NOT EXISTS Employee (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255) NOT NULL, lastName VARCHAR(255) NOT NULL, birthDate VARCHAR(255) NOT NULL, role VARCHAR(255) NOT NULL, department VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL);" && \
    catalina.sh run
