/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 *
 * @author Timax
 */
public class Zawodnik {
    
    int id;
    String imie;
    String nazwisko;
    String plec;
    int wiek;
    String zespol;
    String numerRejPojazdu;

    public Zawodnik(int id, String imie, String nazwisko, String plec, int wiek, String zespol, String numerRejPojazdu) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.plec = plec;
        this.wiek = wiek;
        this.zespol = zespol;
        this.numerRejPojazdu = numerRejPojazdu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public int getWiek() {
        return wiek;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public String getZespol() {
        return zespol;
    }

    public void setZespol(String zespol) {
        this.zespol = zespol;
    }

    public String getNumerRejPojazdu() {
        return numerRejPojazdu;
    }

    public void setNumerRejPojazdu(String numerRejPojazdu) {
        this.numerRejPojazdu = numerRejPojazdu;
    }
    
    
}

