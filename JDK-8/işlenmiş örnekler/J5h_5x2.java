// J5h_5x2.java: TransferActionListener (AktarmaHareketiDinleyicisi) alt-�rne�i.

import java.awt.KeyboardFocusManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import javax.swing.Action;
 import java.beans.PropertyChangeListener;
 import java.beans.PropertyChangeEvent;

// Cut/Copy/Paste hareketleri takip dinlenir ve odaklanan par�a �zerinde uygun i�lem tamamlan�r...
public class J5h_5x2 implements ActionListener, PropertyChangeListener {
    private JComponent odakl�Par�a = null;

    public J5h_5x2() {
        KeyboardFocusManager y�netici = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        y�netici.addPropertyChangeListener ("permanentFocusOwner", this);
    } // J5h_5x2() kurucusu sonu...

    public void propertyChange (PropertyChangeEvent olay) {
        Object nesne = olay.getNewValue();
        if (nesne instanceof JComponent) odakl�Par�a = (JComponent)nesne;
        else odakl�Par�a = null;
    } // propertyChange(..) haz�r metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        if (odakl�Par�a == null) return;
        String hareket = (String)olay.getActionCommand();
        Action aksiyon = odakl�Par�a.getActionMap().get (hareket);
        if (aksiyon != null) aksiyon.actionPerformed (new ActionEvent (odakl�Par�a, ActionEvent.ACTION_PERFORMED, null));
    } // actionPerformed(..) haz�r metodu sonu...
} // J5h_5x2 s�n�f� sonu...