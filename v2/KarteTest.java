package v2;

public class KarteTest {
      
      public static void main(String[] args) {
        System.out.println("=== BLACKJACK KARTEN TEST ===\n");
        
        // Test 1: Verschiedene Karten erstellen
        testKartenErstellung();
        
        // Test 2: Ass-Funktionalität
        testAssFunktionalitaet();
        
        // Test 3: Bildkarten
        testBildkarten();
        
        // Test 4: 10er-Werte
        test10erWerte();
        
        // Test 5: Split-Funktionalität
        testSplitFunktionalitaet();
        
        // Test 6: Blackjack-Kombinationen
        testBlackjackKombinationen();
        
        // Test 7: Alle Kartenwerte
        testAlleKartenwerte();
        
        // Test 8: Equals und HashCode
        testEqualsUndHashCode();
        
        System.out.println("\n=== ALLE TESTS ABGESCHLOSSEN ===");
    }
    
    private static void testKartenErstellung() {
        System.out.println("--- Test 1: Karten-Erstellung ---");
        
        Karte assHerz = new Karte(Farbe.Herz, Wert.ASS);
        Karte koenigPik = new Karte(Farbe.Pik, Wert.KOENIG);
        Karte zehnKaro = new Karte(Farbe.Karo, Wert.ZEHN);
        Karte zweiKreuz = new Karte(Farbe.Kreuz, Wert.ZWEI);
        
        System.out.println("Ass Herz: " + assHerz + " (Wert: " + assHerz.getZahlwert() + ")");
        System.out.println("König Pik: " + koenigPik + " (Wert: " + koenigPik.getZahlwert() + ")");
        System.out.println("Zehn Karo: " + zehnKaro + " (Wert: " + zehnKaro.getZahlwert() + ")");
        System.out.println("Zwei Kreuz: " + zweiKreuz + " (Wert: " + zweiKreuz.getZahlwert() + ")");
        System.out.println();
    }
    
    private static void testAssFunktionalitaet() {
        System.out.println("--- Test 2: Ass-Funktionalität ---");
        
        Karte assHerz = new Karte(Farbe.Herz, Wert.ASS);
        Karte assPik = new Karte(Farbe.Pik, Wert.ASS);
        Karte assKaro = new Karte(Farbe.Karo, Wert.ASS);
        Karte assKreuz = new Karte(Farbe.Kreuz, Wert.ASS);
        Karte koenigHerz = new Karte(Farbe.Herz, Wert.KOENIG);
        
        System.out.println("Ist " + assHerz + " ein Ass? " + assHerz.istAss());
        System.out.println("Ist " + assPik + " ein Ass? " + assPik.istAss());
        System.out.println("Ist " + assKaro + " ein Ass? " + assKaro.istAss());
        System.out.println("Ist " + assKreuz + " ein Ass? " + assKreuz.istAss());
        System.out.println("Ist " + koenigHerz + " ein Ass? " + koenigHerz.istAss());
        
        System.out.println("Ass-Wert: " + assHerz.getZahlwert() + " (Initial - Hand-Klasse regelt 1er-Logik)");
        System.out.println();
    }
    
    private static void testBildkarten() {
        System.out.println("--- Test 3: Bildkarten ---");
        
        Karte bubeHerz = new Karte(Farbe.Herz, Wert.BUBE);
        Karte dameKaro = new Karte(Farbe.Karo, Wert.DAME);
        Karte koenigPik = new Karte(Farbe.Pik, Wert.KOENIG);
        Karte zehnKreuz = new Karte(Farbe.Kreuz, Wert.ZEHN);
        Karte assHerz = new Karte(Farbe.Herz, Wert.ASS);
        
        System.out.println("Ist " + bubeHerz + " eine Bildkarte? " + bubeHerz.istBildKarte());
        System.out.println("Ist " + dameKaro + " eine Bildkarte? " + dameKaro.istBildKarte());
        System.out.println("Ist " + koenigPik + " eine Bildkarte? " + koenigPik.istBildKarte());
        System.out.println("Ist " + zehnKreuz + " eine Bildkarte? " + zehnKreuz.istBildKarte());
        System.out.println("Ist " + assHerz + " eine Bildkarte? " + assHerz.istBildKarte());
        System.out.println();
    }
    
    private static void test10erWerte() {
        System.out.println("--- Test 4: 10er-Werte ---");
        
        Karte zehnHerz = new Karte(Farbe.Herz, Wert.ZEHN);
        Karte bubeKaro = new Karte(Farbe.Karo, Wert.BUBE);
        Karte damePik = new Karte(Farbe.Pik, Wert.DAME);
        Karte koenigKreuz = new Karte(Farbe.Kreuz, Wert.KOENIG);
        Karte neunHerz = new Karte(Farbe.Herz, Wert.NEUN);
        Karte assKaro = new Karte(Farbe.Karo, Wert.ASS);
        
        System.out.println("Hat " + zehnHerz + " 10er-Wert? " + zehnHerz.ist10erWert());
        System.out.println("Hat " + bubeKaro + " 10er-Wert? " + bubeKaro.ist10erWert());
        System.out.println("Hat " + damePik + " 10er-Wert? " + damePik.ist10erWert());
        System.out.println("Hat " + koenigKreuz + " 10er-Wert? " + koenigKreuz.ist10erWert());
        System.out.println("Hat " + neunHerz + " 10er-Wert? " + neunHerz.ist10erWert());
        System.out.println("Hat " + assKaro + " 10er-Wert? " + assKaro.ist10erWert());
        System.out.println();
    }
    
    private static void testSplitFunktionalitaet() {
        System.out.println("--- Test 5: Split-Funktionalität ---");
        
        // Gleiche Werte (verschiedene Farben)
        Karte koenigHerz = new Karte(Farbe.Herz, Wert.KOENIG);
        Karte koenigPik = new Karte(Farbe.Pik, Wert.KOENIG);
        Karte dameKaro = new Karte(Farbe.Karo, Wert.DAME);
        Karte zehnKreuz = new Karte(Farbe.Kreuz, Wert.ZEHN);
        
        // Exakt gleiche Karten
        Karte assHerz1 = new Karte(Farbe.Herz, Wert.ASS);
        Karte assHerz2 = new Karte(Farbe.Herz, Wert.ASS);
        Karte assPik = new Karte(Farbe.Pik, Wert.ASS);
        
        System.out.println("=== Wert-Vergleiche (für verschiedene Split-Regeln) ===");
        System.out.println(koenigHerz + " hat gleichen Zahlwert wie " + koenigPik + "? " + 
                          koenigHerz.hatGleichenZahlwert(koenigPik));
        System.out.println(koenigHerz + " hat gleichen Zahlwert wie " + dameKaro + "? " + 
                          koenigHerz.hatGleichenZahlwert(dameKaro));
        System.out.println(koenigHerz + " hat gleichen Zahlwert wie " + zehnKreuz + "? " + 
                          koenigHerz.hatGleichenZahlwert(zehnKreuz));
        
        System.out.println("\n=== Rang-Vergleiche (für strenge Split-Regeln) ===");
        System.out.println(koenigHerz + " hat gleichen Wert wie " + koenigPik + "? " + 
                          koenigHerz.hatGleichenWert(koenigPik));
        System.out.println(koenigHerz + " hat gleichen Wert wie " + dameKaro + "? " + 
                          koenigHerz.hatGleichenWert(dameKaro));
        System.out.println(assHerz1 + " hat gleichen Wert wie " + assPik + "? " + 
                          assHerz1.hatGleichenWert(assPik));
        System.out.println();
    }
    
    private static void testBlackjackKombinationen() {
        System.out.println("--- Test 6: Blackjack-Kombinationen ---");
        
        Karte assHerz = new Karte(Farbe.Herz, Wert.ASS);
        Karte koenigHerz = new Karte(Farbe.Herz, Wert.KOENIG);
        Karte dameKaro = new Karte(Farbe.Karo, Wert.DAME);
        Karte bubeKreuz = new Karte(Farbe.Kreuz, Wert.BUBE);
        Karte zehnPik = new Karte(Farbe.Pik, Wert.ZEHN);
        
        System.out.println("=== Blackjack-Kombinationen (Ass + 10er-Wert = 21) ===");
        System.out.println(assHerz + " + " + koenigHerz + " = " + 
                          (assHerz.getZahlwert() + koenigHerz.getZahlwert()) + " → BLACKJACK!");
        System.out.println(assHerz + " + " + dameKaro + " = " + 
                          (assHerz.getZahlwert() + dameKaro.getZahlwert()) + " → BLACKJACK!");
        System.out.println(assHerz + " + " + bubeKreuz + " = " + 
                          (assHerz.getZahlwert() + bubeKreuz.getZahlwert()) + " → BLACKJACK!");
        System.out.println(assHerz + " + " + zehnPik + " = " + 
                          (assHerz.getZahlwert() + zehnPik.getZahlwert()) + " → BLACKJACK!");
        System.out.println();
    }
    
    private static void testAlleKartenwerte() {
        System.out.println("--- Test 7: Alle Kartenwerte ---");
        
        System.out.println("=== Zahlenkarten ===");
        for (Wert wert : new Wert[]{Wert.ZWEI, Wert.DREI, Wert.VIER, Wert.FUENF, 
                                   Wert.SECHS, Wert.SIEBEN, Wert.ACHT, Wert.NEUN, Wert.ZEHN}) {
            Karte karte = new Karte(Farbe.Herz, wert);
            System.out.println(karte + " = " + karte.getZahlwert());
        }
        
        System.out.println("\n=== Bildkarten (alle Wert 10) ===");
        for (Wert wert : new Wert[]{Wert.BUBE, Wert.DAME, Wert.KOENIG}) {
            Karte karte = new Karte(Farbe.Karo, wert);
            System.out.println(karte + " = " + karte.getZahlwert());
        }
        
        System.out.println("\n=== Ass (initial Wert 11) ===");
        Karte ass = new Karte(Farbe.Pik, Wert.ASS);
        System.out.println(ass + " = " + ass.getZahlwert() + " (wird in Hand-Klasse zu 1 wenn nötig)");
        System.out.println();
    }
    
    private static void testEqualsUndHashCode() {
        System.out.println("--- Test 8: Equals und HashCode ---");
        
        Karte assHerz1 = new Karte(Farbe.Herz, Wert.ASS);
        Karte assHerz2 = new Karte(Farbe.Herz, Wert.ASS);
        Karte assPik = new Karte(Farbe.Pik, Wert.ASS);
        Karte koenigHerz = new Karte(Farbe.Herz, Wert.KOENIG);
        
        System.out.println("=== Equals-Tests ===");
        System.out.println(assHerz1 + " equals " + assHerz2 + "? " + assHerz1.equals(assHerz2));
        System.out.println(assHerz1 + " equals " + assPik + "? " + assHerz1.equals(assPik));
        System.out.println(assHerz1 + " equals " + koenigHerz + "? " + assHerz1.equals(koenigHerz));
        
        System.out.println("\n=== HashCode-Tests ===");
        System.out.println(assHerz1 + " HashCode: " + assHerz1.hashCode());
        System.out.println(assHerz2 + " HashCode: " + assHerz2.hashCode());
        System.out.println("Gleiche HashCodes? " + (assHerz1.hashCode() == assHerz2.hashCode()));
        System.out.println();
    }
    
    // Bonus: Methode zum Testen aller Farbkombinationen
    private static void testAlleFarben() {
        System.out.println("--- Bonus: Alle Farben mit Ass ---");
        
        for (Farbe farbe : Farbe.values()) {
            Karte ass = new Karte(farbe, Wert.ASS);
            System.out.println("Ass " + farbe.name() + ": " + ass);
        }
        System.out.println();
    }
    
    // Aufruf für Bonus-Test (optional)
    static {
        // Wird automatisch beim Laden der Klasse ausgeführt
        // Können Sie auskommentieren wenn Sie möchten
        // testAlleFarben();
    }


}
