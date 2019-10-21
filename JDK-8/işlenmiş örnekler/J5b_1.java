// J5b_1.java: CelsiusConverterGUI (Santigrad�eviriciGUI) �rne�i.

public class J5b_1 extends javax.swing.JFrame {
    private javax.swing.JTextField metinSat�r�;
    private javax.swing.JLabel santigradEtiketi;
    private javax.swing.JLabel fahrenhaytEtiketi;
    private javax.swing.JButton de�i�tirButonu;

    public J5b_1() {komponentleriYarat();} // Kurucu...

    private void komponentleriYarat() {
        metinSat�r� = new javax.swing.JTextField();
        santigradEtiketi = new javax.swing.JLabel();
        fahrenhaytEtiketi = new javax.swing.JLabel();
        de�i�tirButonu = new javax.swing.JButton();

        setDefaultCloseOperation (javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle ("Santigrad'dan-Fahrenhayt'a �evirici");

        santigradEtiketi.setText ("Santigrad derece");
        fahrenhaytEtiketi.setText ("Fahrenhayt derece");

        de�i�tirButonu.setText ("�evir");
        de�i�tirButonu.addActionListener (new java.awt.event.ActionListener() {
            public void actionPerformed (java.awt.event.ActionEvent olay) {
                int fahrenhaytDerece = (int)((Double.parseDouble (metinSat�r�.getText())) * 1.8 + 32);
                fahrenhaytEtiketi.setText (fahrenhaytDerece + " Fahrenheit derece");
        }}); // de�.. ifadesi sonu...

        javax.swing.GroupLayout yerle�im = new javax.swing.GroupLayout (getContentPane());
        getContentPane().setLayout (yerle�im);
        yerle�im.setHorizontalGroup (
            yerle�im.createParallelGroup (javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup (yerle�im.createSequentialGroup()
                .addContainerGap()
                .addGroup (yerle�im.createParallelGroup (javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup (yerle�im.createSequentialGroup()
                        .addComponent (metinSat�r�, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap (javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent (santigradEtiketi))
                    .addGroup (yerle�im.createSequentialGroup()
                        .addComponent (de�i�tirButonu)
                        .addPreferredGap (javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent (fahrenhaytEtiketi)))
                .addContainerGap (27, Short.MAX_VALUE))
        ); // yer.. ifadesi sonu...

        yerle�im.linkSize (javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {de�i�tirButonu, metinSat�r�});

        yerle�im.setVerticalGroup (
            yerle�im.createParallelGroup (javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(yerle�im.createSequentialGroup()
                .addContainerGap()
                .addGroup (yerle�im.createParallelGroup (javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent (metinSat�r�, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent (santigradEtiketi))
                .addPreferredGap (javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup (yerle�im.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent (de�i�tirButonu)
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
} // J5b_1 s�n�f� sonu...