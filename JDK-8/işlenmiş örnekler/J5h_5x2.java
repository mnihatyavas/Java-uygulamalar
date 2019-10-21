// J5h_5x2.java: TransferActionListener (AktarmaHareketiDinleyicisi) alt-örneði.

import java.awt.KeyboardFocusManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import javax.swing.Action;
 import java.beans.PropertyChangeListener;
 import java.beans.PropertyChangeEvent;

// Cut/Copy/Paste hareketleri takip dinlenir ve odaklanan parça üzerinde uygun iþlem tamamlanýr...
public class J5h_5x2 implements ActionListener, PropertyChangeListener {
    private JComponent odaklýParça = null;

    public J5h_5x2() {
        KeyboardFocusManager yönetici = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        yönetici.addPropertyChangeListener ("permanentFocusOwner", this);
    } // J5h_5x2() kurucusu sonu...

    public void propertyChange (PropertyChangeEvent olay) {
        Object nesne = olay.getNewValue();
        if (nesne instanceof JComponent) odaklýParça = (JComponent)nesne;
        else odaklýParça = null;
    } // propertyChange(..) hazýr metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        if (odaklýParça == null) return;
        String hareket = (String)olay.getActionCommand();
        Action aksiyon = odaklýParça.getActionMap().get (hareket);
        if (aksiyon != null) aksiyon.actionPerformed (new ActionEvent (odaklýParça, ActionEvent.ACTION_PERFORMED, null));
    } // actionPerformed(..) hazýr metodu sonu...
} // J5h_5x2 sýnýfý sonu...