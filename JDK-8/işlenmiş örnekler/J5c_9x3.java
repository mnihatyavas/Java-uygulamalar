// J5c_9x3.java: ConverterRangeModel (DeðiþtiriciErimiModeli) alt-örneði.

import javax.swing.BoundedRangeModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;

public class J5c_9x3 implements BoundedRangeModel {
    protected ChangeEvent deðiþiklikOlayý = null;
    protected EventListenerList dinleyiciListesi = new EventListenerList();

    protected int azami = 10000;
    protected int asgari = 0;
    protected int uzaným = 0;
    protected double deðer = 0.0;
    protected double çarpan = 1.0;
    protected boolean ayarlasýnMý = false;

    public J5c_9x3() {}
    public double getMultiplier() {return çarpan;}
    public void setMultiplier (double çarpan) {this.çarpan = çarpan; ateþStatüsüDeðiþti();}
    public int getMaximum() {return azami;}
    public void setMaximum (int yeniAzami) {setRangeProperties (deðer, uzaným, asgari, yeniAzami, ayarlasýnMý);}
    public int getMinimum() {return (int)asgari;}
    public void setMinimum (int yeniAsgari) {}
    public int getValue() {return (int)getDoubleValue();}
    public void setValue (int yeniDeðer) {setDoubleValue ((double)yeniDeðer);}
    public double getDoubleValue() {return deðer;}
    public void setDoubleValue (double yeniDeðer) {setRangeProperties (yeniDeðer, uzaným, asgari, azami, ayarlasýnMý);}
    public int getExtent() {return (int)uzaným;}
    public void setExtent (int yeniUzaným) {}
    public boolean getValueIsAdjusting() {return ayarlasýnMý;}
    public void setValueIsAdjusting (boolean b) {setRangeProperties (deðer, uzaným, asgari, azami, b);}

    public void setRangeProperties (
            int yeniDeðer,
            int yeniUzaným,
            int yeniSagir,
            int yeniAzam,
            boolean yeniAyar) {
            setRangeProperties (
                (double)yeniDeðer,
                yeniUzaným,
                yeniSagir,
                yeniAzam,
                yeniAyar);
    } // setRangeProperties(..1) metodu sonu...

    public void setRangeProperties (
                double yeniDeðer,
                int kullanýlmayanUzaným,
                int kullanýlmayanSagir,
                int yeniAzam,
                boolean yeniAyar) {
        if (yeniAzam <= asgari) yeniAzam = asgari + 1;
        if (Math.round (yeniDeðer) > yeniAzam) yeniDeðer = yeniAzam;
        boolean deðiþtiMi = false;
        if (yeniDeðer != deðer) {deðer = yeniDeðer; deðiþtiMi = true;}
        if (yeniAzam != azami) {azami = yeniAzam; deðiþtiMi = true;}
        if (yeniAyar != ayarlasýnMý) {azami = yeniAzam; ayarlasýnMý = yeniAyar; deðiþtiMi = true;}
        if (deðiþtiMi) ateþStatüsüDeðiþti();
    } // setRangeProperties (..2) metodu sonu...

    public void addChangeListener (ChangeListener dinle) {dinleyiciListesi.add (ChangeListener.class, dinle);}
    public void removeChangeListener (ChangeListener dinle) {dinleyiciListesi.remove (ChangeListener.class, dinle);}

    protected void ateþStatüsüDeðiþti() {
        Object[] dinleyiler = dinleyiciListesi.getListenerList();
        for (int i = dinleyiler.length - 2; i >= 0; i -=2 ) {
            if (dinleyiler[i] == ChangeListener.class) {
                if (deðiþiklikOlayý == null) deðiþiklikOlayý = new ChangeEvent (this);
                ((ChangeListener)dinleyiler[i+1]).stateChanged (deðiþiklikOlayý);
            } // if-din.. kararý sonu
        } // for döngüsü sonu...
    } // ateþStatüsüDeðiþti() metodu sonu...
} // J5c_9x3 sýnýfý sonu...