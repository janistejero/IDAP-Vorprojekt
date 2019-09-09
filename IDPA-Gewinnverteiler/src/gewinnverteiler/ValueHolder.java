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

    private static ValueHolder instance;

    
    private double bilanzerfolg;
    private double zwischenresultat;
    private double erforderlicheReserve;
    private double erfolg;
    private double aktienkapital;
    private double partizipationskapital;
    private double erfolgvortrag;
    
    // vortrag
    private double neuererfolgvortag;
    
    // reserven
    private double gesReserven;
    private double reservenzuweisung;
    
    // dividende
    private double gewuenschteDividende;
    private double grunddividende;
    private double superdividende;
    
    private ValueHolder() {

    }

    public static ValueHolder getInstance() {
        if (ValueHolder.instance == null) {
            ValueHolder.instance = new ValueHolder();
        }
        return ValueHolder.instance;
    }
    

}
