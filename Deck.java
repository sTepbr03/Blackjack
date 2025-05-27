import java.util.ArrayList;
import java.util.Collections;

class Deck {
    private ArrayList<Karte> karten;
    private String[] farben = {"Herz", "Karo", "Kreuz", "Pik"};
    private String[] werte = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Bube", "Dame", "König", "Ass"};

    public Deck() {
        karten = new ArrayList<>();
        for (String farbe : farben) {
            for (String wert : werte) {
                karten.add(new Karte(farbe, wert));
            }
        }
        mischen();
    }

    public void mischen() {
        Collections.shuffle(karten);
    }

    public Karte zieheKarte() {
        if (karten.isEmpty()) {
            // Neues Deck erstellen, wenn leer
            for (String farbe : farben) {
                for (String wert : werte) {
                    karten.add(new Karte(farbe, wert));
                }
            }
            mischen();
        }
        return karten.remove(0);
    }
}