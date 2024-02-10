package basic.function;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Card {
    public enum Rank { DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE }
    public enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }

    private final Rank rank;
    private final Suit suit;
    private Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank rank() { return rank; }
    public Suit suit() { return suit; }
    public String toString() { return rank + " of " + suit; }

    private static Map<Suit, Map<Rank, Card>> table = new EnumMap<>(Suit.class); // 생성자에 키 타입 토큰

    static {
        for(Suit suit : Suit.values()) {
            Map<Rank, Card> suitTable = new EnumMap<>(Rank.class); // 생성자에 Map 키 타입 토큰
            for(Rank rank : Rank.values()) {
                suitTable.put(rank, new Card(rank, suit));
            }
            table.put(suit, suitTable);
        }
    }

    public static Card valueOf(Rank rank, Suit suit) {
        return table.get(suit).get(rank);
    }

    private static final List<Card> protoDeck = new ArrayList<>();

    static {
        for(Suit suit : Suit.values()) {
            for(Rank rank : Rank.values()) {
                protoDeck.add(valueOf(rank, suit)); // EnumMap 에서 가져와서 초기화
                //protoDeck.add(new Card(rank, suit));
            }
        }
    }

    public static ArrayList<Card> newDeck() {
        return new ArrayList<>(protoDeck); // Return copy of prototype deck
    }

}
public class EnumExample1 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int human = Integer.parseInt(br.readLine()); // 사람 수 : 4
        int cardsPerHuman = Integer.parseInt(br.readLine()); // 사람 당 카드 수 : 5

        List<Card> deck = Card.newDeck();
        Collections.shuffle(deck); // 섞기

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= human; i++) {
            sb.append(deal(deck, cardsPerHuman)).append("\n");
        }

        System.out.println(sb);
    }

    // 카드를 나눠 줌
    public static List<Card> deal(List<Card> deck, int n) { // n : cardsPerHuman
        int deckSize = deck.size(); // 순차적으로 나눠줘서 카드 size 줄어듦
        List<Card> handView = deck.subList(deckSize - n, deckSize); // ( from:inclusive, to:exclusive )
        List<Card> hand = new ArrayList<>(handView);
        handView.clear();
        return hand;
    }
}
