// J5b_1.java: CelsiusConverterGUI (SantigradÇeviriciGUI) örneði.

public class J5b_1 extends javax.swing.JFrame {
    private javax.swing.JTextField metinSatýrý;
    private javax.swing.JLabel santigradEtiketi;
    private javax.swing.JLabel fahrenhaytEtiketi;
    private javax.swing.JButton deðiþtirButonu;

    public J5b_1() {komponentleriYarat();} // Kurucu...

    private void komponentleriYarat() {
        metinSatýrý = new javax.swing.JTextField();
        santigradEtiketi = new javax.swing.JLabel();
        fahrenhaytEtiketi = new javax.swing.JLabel();
        deðiþtirButonu = new javax.swing.JButton();

        setDefaultCloseOperation (javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle ("Santigrad'dan-Fahrenhayt'a Çevirici");

        santigradEtiketi.setText ("Santigrad derece");
        fahrenhaytEtiketi.setText ("Fahrenhayt derece");

        deðiþtirButonu.setText ("Çevir");
        deðiþtirButonu.addActionListener (new java.awt.event.ActionListener() {
            public void actionPerformed (java.awt.event.ActionEvent olay) {
                int fahrenhaytDerece = (int)((Double.parseDouble (metinSatýrý.getText())) * 1.8 + 32);
                fahrenhaytEtiketi.setText (fahrenhaytDerece + " Fahrenheit derece");
        }}); // deð.. ifadesi sonu...

        javax.swing.GroupLayout yerleþim = new javax.swing.GroupLayout (getContentPane());
        getContentPane().setLayout (yerleþim);
        yerleþim.setHorizontalGroup (
            yerleþim.createParallelGroup (javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup (yerleþim.createSequentialGroup()
                .addContainerGap()
                .addGroup (yerleþim.createParallelGroup (javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup (yerleþim.createSequentialGroup()
                        .addComponent (metinSatýrý, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap (javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent (santigradEtiketi))
                    .addGroup (yerleþim.createSequentialGroup()
                        .addComponent (deðiþtirButonu)
                        .addPreferredGap (javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent (fahrenhaytEtiketi)))
                .addContainerGap (27, Short.MAX_VALUE))
        ); // yer.. ifadesi sonu...

        yerleþim.linkSize (javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {deðiþtirButonu, metinSatýrý});

        yerleþim.setVerticalGroup (
            yerleþim.createParallelGroup (javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(yerleþim.createSequentialGroup()
                .addContainerGap()
                .addGroup (yerleþim.createParallelGroup (javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent (metinSatýrý, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent (santigradEtiketi))
                .addPreferredGap (javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup (yerleþim.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent (deðiþtirButonu)
                    .addComponent (fahrenhaytEtiketi))
                .addContainerGap (21, Short.MAX_VALUE))
        ); // yer.. ifadesi sonu...

        pack();
    } // komponentleriYarat() metodu sonu...

    public static void main (String args[]) {
        java.awt.EventQueue.invokeLater (new Runnable() {
            public void run() {new J5b_1().setVisible (true);}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5b_1 sýnýfý sonu...