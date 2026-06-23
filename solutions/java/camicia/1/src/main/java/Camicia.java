import java.util.*;
import java.util.stream.*;

public class Camicia {
    
    static CamiciaResult simulateGame(List<String> playerA, List<String> playerB) {
        
        List<Integer> playerADeck = normalizeDeck(playerA);
        List<Integer> playerBDeck = normalizeDeck(playerB);
        List<List<Integer>> decks = List.of(playerADeck, playerBDeck);
        List<Integer> pile = new LinkedList<>();

        record DeckRecord(List<List<Integer>> decks, List<Integer> pile, int turn, int penalty) {}
        Set<DeckRecord> deckHistory = new HashSet<>();
        
        int turn = 0, penalty = 0, cards = 0, tricks = 0;
        while (true) {
            List<Integer> currentPlayerDeck = decks.get(turn);
            if (currentPlayerDeck.isEmpty()) {
                return new CamiciaResult("finished", cards, tricks + 1);
            }
            
            DeckRecord deckRecord = new DeckRecord(List.of(
                List.copyOf(decks.get(0)),
                List.copyOf(decks.get(1))),
                List.copyOf(pile),
                turn,
                penalty);
            
            if (deckHistory.contains(deckRecord)) {
                return new CamiciaResult("loop", cards, tricks);
            }
            deckHistory.add(deckRecord);
            
            int top = currentPlayerDeck.removeFirst();
            pile.add(top);
            cards++;
            if (top != 0) {
                penalty = top;
                turn = switchTurn(turn);
                continue;
            }
            if (penalty == 0) {
                turn = switchTurn(turn);
                continue;
            }
            penalty--;
            if (penalty == 0) {
                turn = trick(turn, decks, pile);
                tricks++;
                if (currentPlayerDeck.isEmpty()) {
                    return new CamiciaResult("finished", cards, tricks);
                }
            }
        }
    }

    private static List<Integer> normalizeDeck(List<String> deck) {
        return deck.stream()
                   .map(s -> switch (s) {
                                case "J" -> 1;
                                case "Q" -> 2;
                                case "K" -> 3;
                                case "A" -> 4;
                                default -> 0;
                   })
                   .collect(Collectors.toCollection(LinkedList::new));
    }

    private static int switchTurn(int turn) {
        return (turn + 1) % 2;
    }

    private static int trick(int turn, List<List<Integer>> decks, List<Integer> pile) {
        turn = switchTurn(turn);
        decks.get(turn).addAll(pile);
        pile.clear();
        return turn;
    }
}
