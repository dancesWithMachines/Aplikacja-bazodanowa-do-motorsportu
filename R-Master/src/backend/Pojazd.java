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
public class Pojazd {
    
    String numerRejPojazdu;
    String marka;
    String model;
    int pojemnoscWCm3;
    String rodzajDoladowania;
    int mocWKm;
    String kodSilnika;

    public Pojazd(String numerRejPojazdu, String marka, String model, int pojemnoscWCm3, String rodzajDoladowania, int mocWKm, String kodSilnika) {
        this.numerRejPojazdu = numerRejPojazdu;
        this.marka = marka;
        this.model = model;
        this.pojemnoscWCm3 = pojemnoscWCm3;
        this.rodzajDoladowania = rodzajDoladowania;
        this.mocWKm = mocWKm;
        this.kodSilnika = kodSilnika;
    }

    public String getNumerRejPojazdu() {
        return numerRejPojazdu;
    }

    public void setNumerRejPojazdu(String numerRejPojazdu) {
        this.numerRejPojazdu = numerRejPojazdu;
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

    public int getMocWKm() {
        return mocWKm;
    }

    public void setMocWKm(int mocWKm) {
        this.mocWKm = mocWKm;
    }

    public String getKodSilnika() {
        return kodSilnika;
    }

    public void setKodSilnika(String kodSilnika) {
        this.kodSilnika = kodSilnika;
    }
    
}
