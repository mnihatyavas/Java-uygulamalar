// J5c_9x4.java: FollowerRangeModel (TakipçiErimiModeli) alt-örneði.

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class J5c_9x4 extends J5c_9x3 implements ChangeListener {
    J5c_9x3 kaynakModeli; // J5c_9x3/ConverterRangeModel/DEðiþtiriciErimiModeli: Gerçek model...

    public J5c_9x4 (J5c_9x3 kaynakModeli) {
        this.kaynakModeli = kaynakModeli;
        kaynakModeli.addChangeListener (this);
    } // J5c_9x4(..) kurucusu sonu...

    public void stateChanged (ChangeEvent olay) {ateþStatüsüDeðiþti();}

    public int getMaximum() {
        int modelAzamisi = kaynakModeli.getMaximum();
        double ileÇarp = kaynakModeli.getMultiplier() / this.getMultiplier();
        return (int)(modelAzamisi * ileÇarp);
    } // getMaximum() metodu sonu...

    public void setMaximum (int yeniAzami) {
        kaynakModeli.setMaximum ( 
                (int)(yeniAzami * (this.getMultiplier() / kaynakModeli.getMultiplier())));
    } // setMaximum(..) metodu sonu...

    public int getValue() {return (int)getDoubleValue();}
    public void setValue (int yeniDeðer) {setDoubleValue ((double)yeniDeðer);}
    public double getDoubleValue() {return kaynakModeli.getDoubleValue() * kaynakModeli.getMultiplier() / this.getMultiplier();}
    public void setDoubleValue (double yeniDeðer) {kaynakModeli.setDoubleValue (yeniDeðer * this.getMultiplier() / kaynakModeli.getMultiplier());}
    public int getExtent() {return super.getExtent();}
    public void setExtent (int yeniUzaným) {super.setExtent (yeniUzaným);}

    public void setRangeProperties (
            int deðer,
            int uzaným,
            int enKüçük,
            int enBüyük,
            boolean ayarlýyor) {
        double ileÇarp = this.getMultiplier() / kaynakModeli.getMultiplier();
        kaynakModeli.setRangeProperties (deðer * ileÇarp, uzaným, enKüçük, (int)(enBüyük * ileÇarp), ayarlýyor);
    } // setRangeProperties(..) metodu sonu...
} // J5c_9x4 sýnýfý sonu...