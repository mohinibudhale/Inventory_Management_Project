
package edu.iit.sat.itmd4515.mbudhale.config;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@DataSourceDefinition(
        name = "java:app/jdbc/itmd4515DS",
        className="com.mysql.cj.jdbc.MysqlDataSource",
        serverName="localhost",
        portNumber=3306,
        databaseName="sakila",
        user="itmd4515",
        password="itmd4515",
        properties = {
            "zeroDateTimeBehavior=CONVERT_TO_NULL",
            "useSSL=false"
        }
)
public class itmd4515DatabaseConfig {
    
}
