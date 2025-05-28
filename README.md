# Blackjack
Java - VS-Code Blackjack

# Blackjack Programm - Vollständige Ausarbeitung

## 1. Projektübersicht

### Ziele
- Vollständiges Blackjack-Spiel mit allen Standard-Aktionen
- Intelligente Ass-Behandlung (11 oder 1)
- Spielerverwaltung mit MongoDB
- Zufällige Geldverteilung bei leerem Konto
- Erweiterbar von Konsole zu GUI

### Entwicklungsphasen
1. **Phase 1**: Konsolen-Version mit Grundfunktionalität
2. **Phase 2**: MongoDB-Integration für Spielerdaten
3. **Phase 3**: GUI-Implementation mit JavaFX/Swing

## 2. Systemarchitektur

### 2.1 Klassenübersicht

#### Kern-Klassen
- **Card**: Repräsentiert eine Spielkarte
- **Deck**: Kartenstapel mit Misch- und Ziehfunktionen
- **Hand**: Sammlung von Karten (Spieler/Dealer)
- **Player**: Spielerinformationen und -statistiken
- **Game**: Spiellogik und Rundenverwaltung
- **GameController**: Koordiniert Spielablauf
- **DatabaseManager**: MongoDB-Verbindung und -Operationen

#### Utility-Klassen
- **MoneyGenerator**: Zufällige Geldverteilung
- **InputValidator**: Eingabevalidierung
- **GameStatistics**: Statistikberechnungen

### 2.2 Klassendiagramm (UML-Beschreibung)

```
Card
├── suit: Suit (enum)
├── rank: Rank (enum)
├── getValue(): int
└── toString(): String

Deck
├── cards: List<Card>
├── shuffle(): void
├── dealCard(): Card
├── isEmpty(): boolean
└── reset(): void

Hand
├── cards: List<Card>
├── addCard(Card): void
├── getValue(): int
├── getAceCount(): int
├── isBusted(): boolean
├── isBlackjack(): boolean
├── canSplit(): boolean
└── clear(): void

Player
├── id: String
├── name: String
├── password: String
├── currentMoney: double
├── gamesPlayed: int
├── maxMoney: double
├── maxWin: double
├── Konstruktoren
├── Getter/Setter
└── updateStatistics(): void

Game
├── player: Player
├── playerHand: Hand
├── dealerHand: Hand
├── deck: Deck
├── currentBet: double
├── gameState: GameState (enum)
├── startNewGame(): void
├── hit(): void
├── stand(): void
├── doubleDown(): void
├── split(): Hand
├── dealerPlay(): void
└── determineWinner(): GameResult

GameController
├── game: Game
├── dbManager: DatabaseManager
├── scanner: Scanner
├── startApplication(): void
├── mainMenu(): void
├── playGame(): void
├── handlePlayerAction(): void
└── displayGameState(): void

DatabaseManager
├── mongoClient: MongoClient
├── database: MongoDatabase
├── collection: MongoCollection
├── connect(): void
├── savePlayer(Player): void
├── loadPlayer(String): Player
├── playerExists(String): boolean
└── disconnect(): void

MoneyGenerator
├── MONEY_TABLE: int[]
├── random: Random
└── generateRandomMoney(): int
```

## 3. Detaillierte Klassenspezifikationen

### 3.1 Enumerationen

```java
enum Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES
}

enum Rank {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), 
    EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10), ACE(11);
    
    private final int value;
}

enum GameState {
    BETTING, PLAYING, DEALER_TURN, GAME_OVER
}

enum GameResult {
    PLAYER_WIN, DEALER_WIN, PUSH, PLAYER_BLACKJACK, PLAYER_BUST
}
```

### 3.2 Card-Klasse
```java
public class Card {
    private final Suit suit;
    private final Rank rank;
    
    // Konstruktor
    public Card(Suit suit, Rank rank)
    
    // Getter
    public Suit getSuit()
    public Rank getRank()
    public int getValue()
    
    // Utility
    public boolean isAce()
    public String toString()
}
```

### 3.3 Hand-Klasse (Kern der Ass-Logik)
```java
public class Hand {
    private List<Card> cards;
    
    public void addCard(Card card)
    
    public int getValue() {
        int total = 0;
        int aces = 0;
        
        // Alle Kartenwerte addieren
        for (Card card : cards) {
            total += card.getValue();
            if (card.isAce()) {
                aces++;
            }
        }
        
        // Ass-Behandlung: Wenn Summe > 21 und Asse vorhanden
        while (total > 21 && aces > 0) {
            total -= 10; // Ass von 11 auf 1 reduzieren
            aces--;
        }
        
        return total;
    }
    
    public boolean isBusted()
    public boolean isBlackjack()
    public boolean canSplit()
    public int getAceCount()
    public List<Card> getCards()
    public void clear()
}
```

### 3.4 Player-Klasse
```java
public class Player {
    private String id;
    private String name;
    private String password;
    private double currentMoney;
    private int gamesPlayed;
    private double maxMoney;
    private double maxWin;
    
    // Konstruktoren
    public Player(String name, String password)
    
    // Geld-Management
    public boolean canBet(double amount)
    public void placeBet(double amount)
    public void winMoney(double amount)
    public void updateStatistics(double winAmount)
    
    // Getter/Setter für alle Felder
}
```

### 3.5 Game-Klasse (Hauptspiellogik)
```java
public class Game {
    private Player player;
    private Hand playerHand;
    private Hand dealerHand;
    private Deck deck;
    private double currentBet;
    private GameState gameState;
    private List<Hand> splitHands; // Für Split-Funktionalität
    
    public void startNewGame()
    public void placeBet(double amount)
    public void dealInitialCards()
    
    // Spieleraktionen
    public void hit()
    public void stand()
    public boolean doubleDown()
    public boolean split()
    
    // Dealer-Logik
    public void dealerPlay()
    
    // Spiel-Ende
    public GameResult determineWinner()
    public void payoutWinnings(GameResult result)
    
    // Utility
    public boolean isGameOver()
    public void resetGame()
}
```

### 3.6 MoneyGenerator-Klasse
```java
public class MoneyGenerator {
    private static final int[] MONEY_TABLE = {
        50, 100, 150, 200, 250, 300, 400, 500, 750, 1000
    };
    private static final Random random = new Random();
    
    public static int generateRandomMoney() {
        return MONEY_TABLE[random.nextInt(MONEY_TABLE.length)];
    }
    
    public static void givePlayerMoney(Player player) {
        if (player.getCurrentMoney() <= 0) {
            int amount = generateRandomMoney();
            player.setCurrentMoney(amount);
            System.out.println("Glückstag! Sie erhalten " + amount + "€ zum Spielen!");
        }
    }
}
```

### 3.7 DatabaseManager-Klasse
```java
public class DatabaseManager {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> playerCollection;
    
    public void connect()
    public void disconnect()
    
    public void savePlayer(Player player)
    public Player loadPlayer(String name)
    public boolean playerExists(String name)
    public boolean validateLogin(String name, String password)
    
    // Utility-Methoden für Document-Konvertierung
    private Document playerToDocument(Player player)
    private Player documentToPlayer(Document doc)
}
```

## 4. Spielregeln und Logik

### 4.1 Blackjack Regeln
- Ziel: 21 erreichen oder näher als Dealer kommen
- Ass = 11 oder 1 (automatische Anpassung)
- Bildkarten = 10
- Blackjack = Ass + 10er-Karte (21 mit 2 Karten)

### 4.2 Spieleraktionen

#### Hit
- Weitere Karte ziehen
- Möglich solange Hand < 21

#### Stand
- Keine weiteren Karten
- Dealer ist am Zug

#### Double Down
- Einsatz verdoppeln
- Genau eine weitere Karte
- Nur mit ersten 2 Karten möglich

#### Split
- Nur bei gleichen Kartenwerten
- Einsatz verdoppeln
- Zwei separate Hände spielen

### 4.3 Dealer-Regeln
- Muss ziehen bei 16 oder weniger
- Muss stehen bei 17 oder mehr
- Soft 17 (Ass + 6): Dealer zieht

### 4.4 Ass-Behandlung (Algorithmus)
```
Für jede Hand:
1. Summiere alle Kartenwerte (Ass = 11)
2. Zähle Asse
3. Während (Summe > 21 UND Asse > 0):
   - Summe = Summe - 10
   - Asse = Asse - 1
4. Rückgabe: finale Summe
```

## 5. Datenbank-Schema (MongoDB)

### 5.1 Player Collection
```json
{
  "_id": ObjectId,
  "name": String,
  "password": String (gehashed),
  "currentMoney": Number,
  "gamesPlayed": Number,
  "maxMoney": Number,
  "maxWin": Number,
  "createdAt": Date,
  "lastLogin": Date
}
```

### 5.2 Indizes
```javascript
// Eindeutiger Index auf Name
db.players.createIndex({ "name": 1 }, { unique: true })

// Index für schnelle Suche
db.players.createIndex({ "name": 1, "password": 1 })
```

## 6. Implementierungsplan

### Phase 1: Grundstruktur (Woche 1-2)
1. **Tag 1-2**: Basis-Klassen (Card, Deck, Hand)
2. **Tag 3-4**: Ass-Logik implementieren und testen
3. **Tag 5-7**: Player und Game-Klasse
4. **Tag 8-10**: Hit/Stand Funktionalität
5. **Tag 11-14**: Double Down und Split

### Phase 2: Erweiterte Features (Woche 3)
1. **Tag 1-3**: MoneyGenerator und Zufallstabelle
2. **Tag 4-7**: Vollständige Konsolen-UI

### Phase 3: Datenbank (Woche 4)
1. **Tag 1-3**: MongoDB Setup und Connection
2. **Tag 4-5**: DatabaseManager implementieren
3. **Tag 6-7**: Player-Persistierung testen

### Phase 4: Polish (Woche 5)
1. **Tag 1-3**: Error Handling und Validierung
2. **Tag 4-5**: Statistiken und Reporting
3. **Tag 6-7**: Code-Cleanup und Dokumentation

## 7. Technische Anforderungen

### 7.1 Dependencies (Maven/Gradle)
```xml
<!-- MongoDB Driver -->
<dependency>
    <groupId>org.mongodb</groupId>
    <artifactId>mongodb-driver-sync</artifactId>
    <version>4.11.1</version>
</dependency>

<!-- Password Hashing -->
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-crypto</artifactId>
    <version>6.1.5</version>
</dependency>

<!-- JUnit für Tests -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
</dependency>
```

### 7.2 Projektstruktur
```
src/
├── main/
│   └── java/
│       └── blackjack/
│           ├── model/
│           │   ├── Card.java
│           │   ├── Deck.java
│           │   ├── Hand.java
│           │   └── Player.java
│           ├── game/
│           │   ├── Game.java
│           │   └── GameController.java
│           ├── database/
│           │   └── DatabaseManager.java
│           ├── util/
│           │   ├── MoneyGenerator.java
│           │   └── InputValidator.java
│           └── Main.java
└── test/
    └── java/
        └── blackjack/
            ├── model/
            └── game/
```

## 8. Test-Strategie

### 8.1 Unit Tests
- **Hand.getValue()**: Verschiedene Ass-Kombinationen
- **Deck**: Mischen und Karten ziehen
- **Game**: Alle Spieleraktionen
- **MoneyGenerator**: Zufallsverteilung

### 8.2 Integration Tests
- **DatabaseManager**: CRUD-Operationen
- **GameController**: Komplette Spielrunden
- **Player**: Statistik-Updates

### 8.3 Test-Cases für Ass-Logik
```java
@Test
public void testAceHandling() {
    Hand hand = new Hand();
    hand.addCard(new Card(Suit.HEARTS, Rank.ACE));   // 11
    hand.addCard(new Card(Suit.SPADES, Rank.ACE));   // 11
    hand.addCard(new Card(Suit.CLUBS, Rank.NINE));   // 9
    
    // Erwartung: 11 + 1 + 9 = 21 (ein Ass wird zu 1)
    assertEquals(21, hand.getValue());
}
```

## 9. Erweiterungsmöglichkeiten

### Für GUI-Version (Phase 4)
- **JavaFX**: Moderne UI mit FXML
- **Animation**: Kartenbewegungen
- **Sound**: Spielgeräusche
- **Themes**: Verschiedene Tisch-Designs

### Zusätzliche Features
- **Multiplayer**: Mehrere Spieler am Tisch
- **Tournament**: Turnierformat
- **Statistics Dashboard**: Detaillierte Auswertungen
- **Card Counting Training**: Lernmodus

## 10. Starthilfe: Erste Schritte

### Schritt 1: Card und Rank implementieren
```java
// Beginnen Sie mit der einfachsten Klasse
public class Card {
    // Implementieren Sie toString() für einfaches Debugging
}
```

### Schritt 2: Hand-Klasse mit Ass-Logik
```java
// Fokus auf getValue() Methode
// Testen Sie mit verschiedenen Kombinationen
```

### Schritt 3: Einfacher Test
```java
public static void main(String[] args) {
    Hand hand = new Hand();
    hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
    hand.addCard(new Card(Suit.SPADES, Rank.KING));
    System.out.println("Hand value: " + hand.getValue()); // Should be 21
}
```

Diese Ausarbeitung gibt Ihnen eine solide Grundlage für Ihr Blackjack-Projekt. Beginnen Sie mit den Basis-Klassen und arbeiten Sie sich systematisch vor. Viel Erfolg beim Programmieren!
