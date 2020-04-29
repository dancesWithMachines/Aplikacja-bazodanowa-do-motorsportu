/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.Time;

/**
 *
 * @author Timax
 */
public class Tabela {
    
    int id;
    String imie;
    String nazwisko;
    String marka;
    String model;
    Time najlepszyCzas;
    int pojemnoscWCm3;
    String rodzajDoladowania;

    public Tabela(int id, String imie, String nazwisko, String marka, String model, Time najlepszyCzas, int pojemnoscWCm3, String rodzajDoladowania) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.marka = marka;
        this.model = model;
        this.najlepszyCzas = najlepszyCzas;
        this.pojemnoscWCm3 = pojemnoscWCm3;
        this.rodzajDoladowania = rodzajDoladowania;
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

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Time getNajlepszyCzas() {
        return najlepszyCzas;
    }

    public void setNajlepszyCzas(Time najlepszyCzas) {
        this.najlepszyCzas = najlepszyCzas;
    }

    public int getPojemnoscWCm3() {
        return pojemnoscWCm3;
    }

    public void setPojemnoscWCm3(int pojemnoscWCm3) {
        this.pojemnoscWCm3 = pojemnoscWCm3;
    }

    public String getRodzajDoladowania() {
        return rodzajDoladowania;
    }

    public void setRodzajDoladowania(String rodzajDoladowania) {
        this.rodzajDoladowania = rodzajDoladowania;
    }
    
    
}
