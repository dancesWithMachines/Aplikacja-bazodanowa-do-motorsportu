/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Timax
 */
public class BazaMaster {

    private String login;
    private String haslo;
    private Connection polaczenie;
    public List<Tabela> dane;
    public ArrayList<Pojazd> danePojazd;
    public ArrayList<Zawodnik> daneZawodnik;
    public ArrayList<Czas> czasZawodnika;
    public BazaMaster(String login, String haslo) {
        this.login = login;
        this.haslo = haslo;
    }
    
    private void establishConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        polaczenie=
                DriverManager.getConnection(
                    "jdbc:sqlserver://localhost\\MSSQLServer;databaseName=pojazdyIKierowcy",
                    login,haslo 
                );
    }
    
    private void closeConnection () throws SQLException{
        polaczenie.close();
    }
    
    public void pobierzTabele() throws SQLException, ClassNotFoundException {
        establishConnection();
        dane = new ArrayList();
        PreparedStatement ps = polaczenie.prepareStatement("{call dbo.wypiszWyniki}");
        ResultSet rezultat = ps.executeQuery();
        while(rezultat.next()) {
            dane.add(new Tabela(
                    rezultat.getInt("id"),
                    rezultat.getString("imie"),
                    rezultat.getString("nazwisko"),
                    rezultat.getString("marka"),
                    rezultat.getString("model"),
                    rezultat.getTime("najlepszyCzas"),
                    rezultat.getInt("pojemnoscWCm3"),
                    rezultat.getString("rodzajDoladowania")
                ));
        }
        closeConnection();
    }
    
    public void pobierzDanePojazdu(String numerRejestracyjny) throws SQLException, ClassNotFoundException {
        establishConnection();
        danePojazd = new ArrayList();
        CallableStatement cl = polaczenie.prepareCall("{call dbo.pobierzDanePojazdu(?)}");
        cl.setString(1, numerRejestracyjny);
        ResultSet rezultat = cl.executeQuery();
        while(rezultat.next()) {
            danePojazd.add(new Pojazd(
                    rezultat.getString("numerRejPojazdu"),
                    rezultat.getString("marka"),
                    rezultat.getString("model"),
                    rezultat.getInt("pojemnoscWCm3"),
                    rezultat.getString("rodzajDoladowania"),
                    rezultat.getInt("mocWKm"),
                    rezultat.getString("kodSilnika")
                ));
        }
        closeConnection();
    }
    
    public void pobierzDaneZawodnika(String numerRejestracyjny) throws SQLException, ClassNotFoundException {
        establishConnection();
        daneZawodnik = new ArrayList();
        CallableStatement cl = polaczenie.prepareCall("{call dbo.pobierzDaneZawodnika(?)}");
        cl.setString(1, numerRejestracyjny);
        ResultSet rezultat = cl.executeQuery();
        while(rezultat.next()) {
            daneZawodnik.add(new Zawodnik(
                    rezultat.getInt("id"),
                    rezultat.getString("Imie"),
                    rezultat.getString("Nazwisko"),
                    rezultat.getString("plec"),
                    rezultat.getInt("wiek"),
                    rezultat.getString("zespol"),
                    rezultat.getString("numerRejPojazdu")
                ));
        }
        closeConnection();
    }
 
     public void pobierzCzas(String numerRejestracyjny) throws SQLException, ClassNotFoundException {
        establishConnection();
        czasZawodnika = new ArrayList();
        CallableStatement cl = polaczenie.prepareCall("{call dbo.pobierzCzas(?)}");
        cl.setString(1, numerRejestracyjny);
        ResultSet rezultat = cl.executeQuery();
        while(rezultat.next()) {
            czasZawodnika.add(new Czas(
                    rezultat.getInt("idZawodnika"),
                    rezultat.getTime("najlepszyCzas")
                ));
        }
        closeConnection();
    }
    
    public void modyfikujPojazdIZawodnika (String numerRejPojazdu, String marka, String model, String pojemnoscWCm3, String rodzajDoladowania, String mocWKm, String kodSilnika, String imie, String nazwisko, String plec, String wiek, String zespol) throws SQLException, ClassNotFoundException{
            establishConnection();
            CallableStatement procedura=
                    polaczenie.prepareCall("{call dbo.modyfikujPojazdIZawodnika(?,?,?,?,?,?,?,?,?,?,?,?)}");
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
    
    public void modyfikujCzas (String numerRejPojazdu, String czas) throws SQLException, ClassNotFoundException{
            establishConnection();
            CallableStatement procedura=
                    polaczenie.prepareCall("{call dbo.modyfikujCzas(?,?)}");
            procedura.setString(1, numerRejPojazdu);
            procedura.setTime(2,(Time) Time.valueOf(czas));
            procedura.execute();
            polaczenie.close();
    }
    
    public void usunDane (String numerRejPojazdu) throws SQLException, ClassNotFoundException{
            establishConnection();
            CallableStatement procedura=
                    polaczenie.prepareCall("{call dbo.usunDane(?)}");
            procedura.setString(1, numerRejPojazdu);
            procedura.execute();
            polaczenie.close();
    }
}
