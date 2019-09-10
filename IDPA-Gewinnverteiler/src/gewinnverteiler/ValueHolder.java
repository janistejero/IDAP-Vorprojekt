/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gewinnverteiler;

/**
 *
 * @author Janis Tejero
 */
public class ValueHolder {

    // Variablen
    private static ValueHolder instance;

    // erfolg
    private double erfolg;
    private double bilanzerfolg;
    private double zwischenresultat;

    // kapital
    private double aktienkapital;
    private double partizipationskapital;

    // vortrag
    private double neuererfolgvortag;
    private double erfolgvortrag;

    // reserven
    private double reservenzuweisung;
    private double erforderlicheReserve;
    private double aktuell1Reserve;
    private double aktuell2Reserve;
    private double ziel1Reserve;
    private double neu1Reserve;
    private double neu2Reserve;

    // dividende
    private double gewuenschteDividende;
    private double grunddividende;
    private double superdividende;
    private boolean superdividendeAusschuettung;
    private boolean dividendenAusschuettung;

    
    private ValueHolder() {

    }

    public static ValueHolder getInstance() {
        if (ValueHolder.instance == null) {
            ValueHolder.instance = new ValueHolder();
        }
        return ValueHolder.instance;
    }

    public double getErfolg() {
        return erfolg;
    }

    public void setErfolg(double erfolg) {
        this.erfolg = erfolg;
    }

    public double getBilanzerfolg() {
        return bilanzerfolg;
    }

    public void setBilanzerfolg(double bilanzerfolg) {
        this.bilanzerfolg = bilanzerfolg;
    }

    public double getZwischenresultat() {
        return zwischenresultat;
    }

    public void setZwischenresultat(double zwischenresultat) {
        this.zwischenresultat = zwischenresultat;
    }

    public double getAktienkapital() {
        return aktienkapital;
    }

    public void setAktienkapital(double aktienkapital) {
        this.aktienkapital = aktienkapital;
    }

    public double getPartizipationskapital() {
        return partizipationskapital;
    }

    public void setPartizipationskapital(double partizipationskapital) {
        this.partizipationskapital = partizipationskapital;
    }

    public double getNeuererfolgvortag() {
        return neuererfolgvortag;
    }

    public void setNeuererfolgvortag(double neuererfolgvortag) {
        this.neuererfolgvortag = neuererfolgvortag;
    }

    public double getErfolgvortrag() {
        return erfolgvortrag;
    }

    public void setErfolgvortrag(double erfolgvortrag) {
        this.erfolgvortrag = erfolgvortrag;
    }

    public double getReservenzuweisung() {
        return reservenzuweisung;
    }

    public void setReservenzuweisung(double reservenzuweisung) {
        this.reservenzuweisung = reservenzuweisung;
    }

    public double getErforderlicheReserve() {
        return erforderlicheReserve;
    }

    public void setErforderlicheReserve(double erforderlicheReserve) {
        this.erforderlicheReserve = erforderlicheReserve;
    }

    public double getAktuell1Reserve() {
        return aktuell1Reserve;
    }

    public void setAktuell1Reserve(double aktuell1Reserve) {
        this.aktuell1Reserve = aktuell1Reserve;
    }

    public double getAktuell2Reserve() {
        return aktuell2Reserve;
    }

    public void setAktuell2Reserve(double aktuell2Reserve) {
        this.aktuell2Reserve = aktuell2Reserve;
    }

    public double getZiel1Reserve() {
        return ziel1Reserve;
    }

    public void setZiel1Reserve(double ziel1Reserve) {
        this.ziel1Reserve = ziel1Reserve;
    }

    public double getNeu1Reserve() {
        return neu1Reserve;
    }

    public void setNeu1Reserve(double neu1Reserve) {
        this.neu1Reserve = neu1Reserve;
    }

    public double getNeu2Reserve() {
        return neu2Reserve;
    }

    public void setNeu2Reserve(double neu2Reserve) {
        this.neu2Reserve = neu2Reserve;
    }

    public double getGewuenschteDividende() {
        return gewuenschteDividende;
    }

    public void setGewuenschteDividende(double gewuenschteDividende) {
        this.gewuenschteDividende = gewuenschteDividende;
    }

    public double getGrunddividende() {
        return grunddividende;
    }

    public void setGrunddividende(double grunddividende) {
        this.grunddividende = grunddividende;
    }

    public double getSuperdividende() {
        return superdividende;
    }

    public void setSuperdividende(double superdividende) {
        this.superdividende = superdividende;
    }

    public boolean getSuperdividendeAusschuettung() {
        return superdividendeAusschuettung;
    }

    public void setSuperdividendeAusschuettung(boolean superdividendeAusschuettung) {
        this.superdividendeAusschuettung = superdividendeAusschuettung;
    }

    public boolean getDividendenAusschuettung() {
        return dividendenAusschuettung;
    }

    public void setDividendenAusschuettung(boolean dividendenAusschuettung) {
        this.dividendenAusschuettung = dividendenAusschuettung;
    }

}
