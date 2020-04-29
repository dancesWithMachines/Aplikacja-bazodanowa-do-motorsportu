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
public class Czas {
    int idZawodnika;
    Time najlepszyCzas;

    public int getIdZawodnika() {
        return idZawodnika;
    }

    public void setIdZawodnika(int idZawodnika) {
        this.idZawodnika = idZawodnika;
    }

    public Time getNajlepszyCzas() {
        return najlepszyCzas;
    }

    public void setNajlepszyCzas(Time najlepszyCzas) {
        this.najlepszyCzas = najlepszyCzas;
    }

    public Czas(int idZawodnika, Time najlepszyCzas) {
        this.idZawodnika = idZawodnika;
        this.najlepszyCzas = najlepszyCzas;
    }
}
