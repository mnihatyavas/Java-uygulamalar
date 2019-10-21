// J5c_9x3.java: ConverterRangeModel (De�i�tiriciErimiModeli) alt-�rne�i.

import javax.swing.BoundedRangeModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;

public class J5c_9x3 implements BoundedRangeModel {
    protected ChangeEvent de�i�iklikOlay� = null;
    protected EventListenerList dinleyiciListesi = new EventListenerList();

    protected int azami = 10000;
    protected int asgari = 0;
    protected int uzan�m = 0;
    protected double de�er = 0.0;
    protected double �arpan = 1.0;
    protected boolean ayarlas�nM� = false;

    public J5c_9x3() {}
    public double getMultiplier() {return �arpan;}
    public void setMultiplier (double �arpan) {this.�arpan = �arpan; ate�Stat�s�De�i�ti();}
    public int getMaximum() {return azami;}
    public void setMaximum (int yeniAzami) {setRangeProperties (de�er, uzan�m, asgari, yeniAzami, ayarlas�nM�);}
    public int getMinimum() {return (int)asgari;}
    public void setMinimum (int yeniAsgari) {}
    public int getValue() {return (int)getDoubleValue();}
    public void setValue (int yeniDe�er) {setDoubleValue ((double)yeniDe�er);}
    public double getDoubleValue() {return de�er;}
    public void setDoubleValue (double yeniDe�er) {setRangeProperties (yeniDe�er, uzan�m, asgari, azami, ayarlas�nM�);}
    public int getExtent() {return (int)uzan�m;}
    public void setExtent (int yeniUzan�m) {}
    public boolean getValueIsAdjusting() {return ayarlas�nM�;}
    public void setValueIsAdjusting (boolean b) {setRangeProperties (de�er, uzan�m, asgari, azami, b);}

    public void setRangeProperties (
            int yeniDe�er,
            int yeniUzan�m,
            int yeniSagir,
            int yeniAzam,
            boolean yeniAyar) {
            setRangeProperties (
                (double)yeniDe�er,
                yeniUzan�m,
                yeniSagir,
                yeniAzam,
                yeniAyar);
    } // setRangeProperties(..1) metodu sonu...

    public void setRangeProperties (
                double yeniDe�er,
                int kullan�lmayanUzan�m,
                int kullan�lmayanSagir,
                int yeniAzam,
                boolean yeniAyar) {
        if (yeniAzam <= asgari) yeniAzam = asgari + 1;
        if (Math.round (yeniDe�er) > yeniAzam) yeniDe�er = yeniAzam;
        boolean de�i�tiMi = false;
        if (yeniDe�er != de�er) {de�er = yeniDe�er; de�i�tiMi = true;}
        if (yeniAzam != azami) {azami = yeniAzam; de�i�tiMi = true;}
        if (yeniAyar != ayarlas�nM�) {azami = yeniAzam; ayarlas�nM� = yeniAyar; de�i�tiMi = true;}
        if (de�i�tiMi) ate�Stat�s�De�i�ti();
    } // setRangeProperties (..2) metodu sonu...

    public void addChangeListener (ChangeListener dinle) {dinleyiciListesi.add (ChangeListener.class, dinle);}
    public void removeChangeListener (ChangeListener dinle) {dinleyiciListesi.remove (ChangeListener.class, dinle);}

    protected void ate�Stat�s�De�i�ti() {
        Object[] dinleyiler = dinleyiciListesi.getListenerList();
        for (int i = dinleyiler.length - 2; i >= 0; i -=2 ) {
            if (dinleyiler[i] == ChangeListener.class) {
                if (de�i�iklikOlay� == null) de�i�iklikOlay� = new ChangeEvent (this);
                ((ChangeListener)dinleyiler[i+1]).stateChanged (de�i�iklikOlay�);
            } // if-din.. karar� sonu
        } // for d�ng�s� sonu...
    } // ate�Stat�s�De�i�ti() metodu sonu...
} // J5c_9x3 s�n�f� sonu...