// J4a_4x.java: WeatherData (HavaVerileri) alt-�rne�i.

import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;

public class J4a_4x extends JPanel {
    private JPanel progressPanel = null;
    private JProgressBar progressBar = null;
    private static J4a_4x weatherDataPanel = null;

    // weather data panel is a singleton
    public static J4a_4x havaVerileriPaneliniAl (boolean displayProgress) {
        if (weatherDataPanel == null) {
            weatherDataPanel = new J4a_4x (displayProgress);
        } else {
            if (!displayProgress) {
                weatherDataPanel.removeProgressPanel();
            }
        }
        return weatherDataPanel;
    } // havaVerileriPaneliniAl(..) metodu sonu...

    private  J4a_4x (boolean displayProgress) {
        setBackground(Color.GREEN);
        setLayout (new BorderLayout());

        JLabel lbl1 = new JLabel("D�nya-�apl� Hava S�cakl��� Verileri");
        lbl1.setFont(new Font("Serif", Font.PLAIN, 18));
        lbl1.setBounds (0,0, 300,20);
        add(lbl1); //BorderLayout.PAGE_START/.LINE_START

        JLabel lbl2 = new JLabel("50'den fazla d�nya �ehrindeki hava s�cakl��� verileri");
        lbl2.setBounds (0,30, 300,20);
        add(lbl2);

        JLabel lbl3 = new JLabel("[Celcius Derece = 5/9 * (F - 32)]");
        lbl3.setBounds (0,60, 300,20);
        add(lbl3);

        JLabel lbl4 = new JLabel("[Not: Yakla��k C i�in F'den 30 d�� ve yar�s�n� al.]");
        lbl4.setBounds (0,90, 300,20);
        add(lbl4);

        if (displayProgress) {
            progressPanel = new JPanel();
            progressPanel.setBackground(Color.WHITE);
            progressPanel.setLayout(new BorderLayout(20, 20));

            String lblText = "<html>Stuck in the mud? Make progress with...<br /><font color=red><em>JDK Documentation</em></font><br/></html>";
            lbl1 = new JLabel(lblText);
            progressPanel.add(lbl1, BorderLayout.NORTH);

            progressBar = new JProgressBar(0, 100);
            progressBar.setValue(0);
            progressBar.setStringPainted(true);
            progressPanel.add(progressBar, BorderLayout.SOUTH);

            add(progressPanel, BorderLayout.LINE_END);
        }
        
        String[] columnNames = {"�ehir", "F-S�cakl�k"};
        JTable table = new JTable(verileriAl(), columnNames);

        table.setPreferredScrollableViewportSize(new Dimension(500, 170));
        table.setFillsViewportHeight(true);
        table.setBackground(Color.ORANGE);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.CYAN);

        //Add the scroll pane to this panel.
        add(scrollPane, BorderLayout.PAGE_END);
    } // J4a_4x(..) kurucuusu sonu...

    private void removeProgressPanel() {
        if (progressPanel != null) {
            remove(progressPanel);
            progressPanel = null;
            progressBar = null;
        }
    }

    public void updateProgress(int overallPercent) {
        if (progressBar != null) {
            progressBar.setValue(overallPercent);
        }
    }

    private Object[][]  verileriAl() {
        // Get current classloader

        Object[][] veriler = {
            {"London", "55"},
            {"New York", "70"},
            {"Los Angeles", "80"},
            {"New Delhi", "95"},
            {"Tokyo", "60"},
            {"Seoul", "55"},
            {"Shanghai", "60"},
            {"Milan", "64"},
            {"Paris", "66"},
            {"Buenos Aires", "70"},
            {"Washington DC", "80"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Ottowa", "44"},
            {"Sacramento", "100"},
            }; // veriler dizini sonu...
        return veriler;
    } // verileriAl() metodu sonu...
} // J4a_4x s�n�f� sonu...