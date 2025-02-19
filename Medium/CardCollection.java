import java.util.*;

class CardCollection {
    private Map<String, List<String>> cards;

    public CardCollection() {
        cards = new HashMap<>();
    }

    public void addCard(String symbol, String cardName) {
        cards.computeIfAbsent(symbol, k -> new ArrayList<>()).add(cardName);
    }

    public List<String> getCardsBySymbol(String symbol) {
        return cards.getOrDefault(symbol, new ArrayList<>());
    }

    public void displayAllCards() {
        if (cards.isEmpty()) {
            System.out.println("No cards in the collection.");
        } else {
            for (Map.Entry<String, List<String>> entry : cards.entrySet()) {
                System.out.println("Symbol: " + entry.getKey() + " -> Cards: " + entry.getValue());
            }
        }
    }
}

public class CardCollectionSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static CardCollection collection = new CardCollection();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nCard Collection System");
            System.out.println("1. Add Card");
            System.out.println("2. Search Cards by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCard();
                    break;
                case 2:
                    searchCards();
                    break;
                case 3:
                    collection.displayAllCards();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addCard() {
        System.out.print("Enter Card Symbol: ");
        String symbol = scanner.nextLine();
        System.out.print("Enter Card Name: ");
        String name = scanner.nextLine();
        collection.addCard(symbol, name);
        System.out.println("Card added successfully!");
    }

    private static void searchCards() {
        System.out.print("Enter Symbol to search: ");
        String symbol = scanner.nextLine();
        List<String> foundCards = collection.getCardsBySymbol(symbol);
        if (foundCards.isEmpty()) {
            System.out.println("No cards found with this symbol.");
        } else {
            System.out.println("Cards with symbol " + symbol + ": " + foundCards);
        }
    }
}
