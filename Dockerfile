FROM tomcat:8.5.81-jre8-openjdk
COPY shortener-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/shortener-1.0-SNAPSHOT.war
# or
# COPY target/shortener-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/shortener-1.0-SNAPSHOT.war
CMD ["catalina.sh", "run"]