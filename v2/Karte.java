package v2;
      enum Farbe{ 
            Kreuz("♣"), Pik("♠"), Herz("♥"), Karo("♦");
            private final String farbsymbol;
    
            Farbe(String farbsymbol) {
                  this.farbsymbol = farbsymbol;}
    
            public String getFarbSymbol() {
                  return farbsymbol;
            }
    
            @Override
            public String toString() {
                  return farbsymbol;}
      }

      //Enumeration der Werte
      enum Wert{
            ZWEI(2,"2"), DREI(3,"3"), VIER(4,"4"), FUENF(5,"5"), SECHS(6,"6"), SIEBEN(7,"7"), ACHT(8,"8"), 
            NEUN(9,"9"), ZEHN(10,"10"), BUBE(10,"J"), DAME(10,"Q"), KOENIG(10,"K"), ASS(11,"A");

            private final int zahlwert;
            private final String symbol;
    
            Wert(int zahlwert, String symbol) {
                  this.zahlwert = zahlwert;
                  this.symbol = symbol;}
    
            public int getZahlwert() {
                  return zahlwert;}
    
            public String getSymbol() {
                  return symbol;}
    
            @Override
            public String toString() {
                   return symbol;}
      }


public class Karte {
      private final Farbe farbe;
      private final Wert wert;
    
      public Karte(Farbe farbe, Wert wert) {
            this.farbe = farbe;
            this.wert = wert;}
    
      public Farbe getFarbe() {
            return farbe;}
    
      public Wert getWert() {
            return wert;}
   
      public int getZahlwert() {
            return wert.getZahlwert();}
   
      public boolean istAss() {
            return wert == Wert.ASS;}
    
      public boolean istBildKarte() {
            return wert == Wert.BUBE || wert == Wert.DAME || wert == Wert.KOENIG;}
    
      public boolean ist10erWert() {
            return getZahlwert() == 10;}
    
      @Override
      public String toString() {
            return wert.getSymbol() + farbe.getFarbSymbol();}
    
      public String toFullString() {
            return wert.name() + " of " + farbe.name() + " (" + getWert() + ")";}
    
    
      @Override
      public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            
            Karte karte = (Karte) obj;
            return farbe == karte.farbe && wert == karte.wert;}
    
      @Override
      public int hashCode() {
            return farbe.hashCode() * 31 + wert.hashCode();}
   
      public boolean hatGleichenZahlwert(Karte other) {
            return this.getZahlwert() == other.getZahlwert();}
      
      public boolean hatGleichenWert(Karte other) {
            return this.wert == other.wert;}
}