// J8c_2x_de.java: LabelsBundle_de (Almanya'nýnEtiketBohçasý) alt-örneði.

import java.util.ListResourceBundle;

public class J8c_2x_de extends ListResourceBundle {
    private String[][] içerikler = {
        {"anh1", new String ("Ich liebe dich!")},
        {"anh2", new String ("Wie heist du?")},
        {"anh3", new String ("Der die das, eins zwei drei...")},
    }; // içerikler dizisi sonu...

    public String[][] getContents() {return içerikler;}
} // J8c_2x_de sýnýfý sonu...