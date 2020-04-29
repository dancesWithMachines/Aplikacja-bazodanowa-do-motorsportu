/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Timax
 */
public class BazaRegular {
    private Connection polaczenie;
    private BazaLogging bazaLogging;
    private String login = "regularUser";
    private String haslo = "regular";

    private void establishConnection() throws ClassNotFoundException, SQLException {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            polaczenie=
                    DriverManager.getConnection(
                            "jdbc:sqlserver://localhost\\MSSQLServer;databaseName=pojazdyIKierowcy",
                            "regularUser","regular"  
                    );
    }
    
    public void dodajPojazdIZawodnika (String numerRejPojazdu, String marka, String model, String pojemnoscWCm3, String rodzajDoladowania, String mocWKm, String kodSilnika, String imie, String nazwisko, String plec, String wiek, String zespol) throws SQLException, ClassNotFoundException{
            establishConnection();
            CallableStatement procedura=
                    polaczenie.prepareCall("{call dbo.dodajPojazdIZawodnika(?,?,?,?,?,?,?,?,?,?,?,?)}");
            procedura.setString(1, numerRejPojazdu);
            procedura.setString(2,marka);
            procedura.setString(3,model);
            procedura.setInt(4,(int)Integer.parseInt(pojemnoscWCm3));
            procedura.setString(5,rodzajDoladowania);
            procedura.setInt(6,(int)Integer.parseInt(mocWKm));
            procedura.setString(7,kodSilnika);
            procedura.setString(8, imie);
            procedura.setString(9, nazwisko);
            procedura.setString(10,plec);
            procedura.setInt(11,(int)Integer.parseInt(wiek));
            procedura.setString(12,zespol);
            procedura.execute();
            polaczenie.close();
    }
}
