// J9d_3.java: AttributedText (�zelliklendirilenMetin) �rne�i.

import java.applet.Applet;

import java.awt.Frame;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.font.TextAttribute;

import java.util.Hashtable;

public class J9d_3 extends Applet {
    private static String dizge;

    public void init() {repaint();}
    public void paint (Graphics g) {
        Font yaz�Fonu = new Font ("arial", Font.BOLD, 40);

        // arial, bold, 40 ve tesad�fi yaz� rengi �zellikli...
        Hashtable<TextAttribute, Object> k�ymal�TabloListesi = new Hashtable<TextAttribute, Object>();
        k�ymal�TabloListesi.put (TextAttribute.FOREGROUND, new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) ) );
        yaz�Fonu = yaz�Fonu.deriveFont (k�ymal�TabloListesi);
        g.setFont (yaz�Fonu);
        g.drawString (dizge, 45, 40);

        // �nceki �zelliklere ilaveten, yeni yaz� rengi ve kerning netlik �zelli�i...
        k�ymal�TabloListesi.put (TextAttribute.FOREGROUND, new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) ) );
        k�ymal�TabloListesi.put (TextAttribute.KERNING, TextAttribute.KERNING_ON);
        yaz�Fonu = yaz�Fonu.deriveFont (k�ymal�TabloListesi);
        g.setFont (yaz�Fonu);
        g.drawString (dizge, 45, 90);

        // �nceki �zelliklere ilaveten, yeni renk ve alt�izgi �zelli�i...
        k�ymal�TabloListesi.put (TextAttribute.FOREGROUND, new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) ) );
        k�ymal�TabloListesi.put (TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        yaz�Fonu = yaz�Fonu.deriveFont (k�ymal�TabloListesi);
        g.setFont (yaz�Fonu); 
        g.drawString (dizge, 45, 140);

        // �nceki �zelliklere ilaveten, yeni renk ve �st�izgi �zelli�i...
        k�ymal�TabloListesi.put (TextAttribute.FOREGROUND, new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) ) );
        k�ymal�TabloListesi.put (TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
        yaz�Fonu = yaz�Fonu.deriveFont (k�ymal�TabloListesi);
        g.setFont (yaz�Fonu); 
        g.drawString (dizge, 45, 190);

        // �nceki �zelliklere ilaveten, sadece mavi renk �zelli�i...
        k�ymal�TabloListesi.put (TextAttribute.FOREGROUND, Color.BLUE);
        yaz�Fonu = yaz�Fonu.deriveFont (k�ymal�TabloListesi);
        g.setFont (yaz�Fonu);
        g.drawString (dizge, 45, 240);

        // �nceki �zellikleri kullanmadan, yeni yaz� fonu/ebat�/yat�kl��� de�i�ikli�i, ve siyah renk �zelli�i...
        yaz�Fonu = new Font ("times new roman", Font.ITALIC, 12);
        k�ymal�TabloListesi.put (TextAttribute.FOREGROUND, Color.black);
        //yaz�Fonu = yaz�Fonu.deriveFont (k�ymal�TabloListesi); // �nceki �zellikler al�nm�yor...
        g.setFont (yaz�Fonu);
        g.drawString ("Not: Yaz� fonu renk de�i�ikli�i i�in �er�eve ebat�yla oynay�n...", 45, 270);
    } // paint(..) haz�r metodu sonu...

    public static void main (String[] args) {
        dizge = args.length > 0? args[0] : "�OOK DALGALI!";

        Frame �er�eve = new Frame ("�zelliklendirilmi� Metin Numunesi");
        �er�eve.addWindowListener (new WindowAdapter() {public void windowClosing (WindowEvent olay) {System.exit (0);}});
        �er�eve.add ("Center",  new J9d_3()); // Parametresiz varsay�l� kuruculu s�n�f nesnesi yarat�l�r...
        �er�eve.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Tesad�fi a��k renkler...
        �er�eve.setSize (new Dimension (400, 320));
        �er�eve.setLocation (500, 50);
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...
} // J9d_3 s�n�f� sonu...