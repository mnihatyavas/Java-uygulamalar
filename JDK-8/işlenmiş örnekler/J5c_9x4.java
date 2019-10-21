// J5c_9x4.java: FollowerRangeModel (Takip�iErimiModeli) alt-�rne�i.

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class J5c_9x4 extends J5c_9x3 implements ChangeListener {
    J5c_9x3 kaynakModeli; // J5c_9x3/ConverterRangeModel/DE�i�tiriciErimiModeli: Ger�ek model...

    public J5c_9x4 (J5c_9x3 kaynakModeli) {
        this.kaynakModeli = kaynakModeli;
        kaynakModeli.addChangeListener (this);
    } // J5c_9x4(..) kurucusu sonu...

    public void stateChanged (ChangeEvent olay) {ate�Stat�s�De�i�ti();}

    public int getMaximum() {
        int modelAzamisi = kaynakModeli.getMaximum();
        double ile�arp = kaynakModeli.getMultiplier() / this.getMultiplier();
        return (int)(modelAzamisi * ile�arp);
    } // getMaximum() metodu sonu...

    public void setMaximum (int yeniAzami) {
        kaynakModeli.setMaximum ( 
                (int)(yeniAzami * (this.getMultiplier() / kaynakModeli.getMultiplier())));
    } // setMaximum(..) metodu sonu...

    public int getValue() {return (int)getDoubleValue();}
    public void setValue (int yeniDe�er) {setDoubleValue ((double)yeniDe�er);}
    public double getDoubleValue() {return kaynakModeli.getDoubleValue() * kaynakModeli.getMultiplier() / this.getMultiplier();}
    public void setDoubleValue (double yeniDe�er) {kaynakModeli.setDoubleValue (yeniDe�er * this.getMultiplier() / kaynakModeli.getMultiplier());}
    public int getExtent() {return super.getExtent();}
    public void setExtent (int yeniUzan�m) {super.setExtent (yeniUzan�m);}

    public void setRangeProperties (
            int de�er,
            int uzan�m,
            int enK���k,
            int enB�y�k,
            boolean ayarl�yor) {
        double ile�arp = this.getMultiplier() / kaynakModeli.getMultiplier();
        kaynakModeli.setRangeProperties (de�er * ile�arp, uzan�m, enK���k, (int)(enB�y�k * ile�arp), ayarl�yor);
    } // setRangeProperties(..) metodu sonu...
} // J5c_9x4 s�n�f� sonu...