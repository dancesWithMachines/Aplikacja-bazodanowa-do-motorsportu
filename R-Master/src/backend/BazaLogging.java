/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Timax
 */
public class BazaLogging {
    
    private Connection polaczenie;

    public void establishConnection(String login, String haslo) throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        polaczenie=
                DriverManager.getConnection(
                    "jdbc:sqlserver://localhost\\MSSQLServer;databaseName=pojazdyIKierowcy",
                    login,haslo 
                );
    }
    
    public void closeConnection () throws SQLException{
        polaczenie.close();
    }
}
