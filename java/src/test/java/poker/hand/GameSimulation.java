package poker.hand;

import java.util.List;

import static java.util.Arrays.asList;

public class GameSimulation {
    public static void main(String[] args) {
        runGame();
    }

    public static void runGame() {
        String playerA = "Corbin";
        String playerB = "Larissa";

        List<List<String>> runs = asList(
                asList("2H 3D 5S 9C KD", "2D 3H 5C 9S KH"),
                asList("2H 2D 5S 6C JS", "2C 2S 6H 8H AC"),
                asList("2H 2D 5S 6C KS", "2C 2S 6H 8H JC"),
                asList("7H JC KD AH AS", "2D 3H 5C 9S KH"),
                asList("5D 6C 7H JH AS", "2D 4H 4C 9S KH"),
                asList("7H JC KD AH AS", "2D 3H 5C KS KH"),
                asList("7H 8C TD QH QS", "2D 3H 5C KS KH"),
                asList("7H 8C KD KC AS", "2D 3H 5C KS KH"),
                asList("2H 2D 5S 5C KS", "2C 3S 6H 8H JC"),
                asList("2H 4D 5S 5C KS", "2C 6S 6H 8H 8C"),
                asList("6C 6D 5S 5C KS", "6S 6H 7H 7D AC"),
                asList("6S 6H QH QD AC", "6C 6D 5S 5C KS"),
                asList("6S 6H QH QD AC", "6C 6D QS QC KS"),
                asList("2H 2D 2S 5C KS", "2C 3S 6H 8H JC"),
                asList("2H 2S 4D 5C KS", "2C 3S 6H 6D 6C"),
                asList("2H 5D 5S 5C KS", "2C 3S JH JD JC"),
                asList("2H KD KS KH AS", "2C 3S JH JD KC"),
                asList("2H 3D 4S 5C 6S", "2C 3S 6H 8H JC"),
                asList("2H 2S 4D 5C KS", "7C 8S 9H TD JC"),
                asList("2H 3D 4S 5C 6S", "6C 7S 8H 9D TC"),
                asList("6C 7S 8H 9D TC", "2H 3D 4S 5C 6S"),
                asList("6C 7S 8H 9D TC", "6D 7C 8C 9H TS"),
                asList("2H 5H 8H JH KH", "2C 3S 6H 8S JC"),
                asList("2C 3S 6H 8S JC", "2D 5D 8D JD KD"),
                asList("2C 3C 6C 8C JC", "2D 5D 8D JD KD"),
                asList("2C 3C 6C 8C AC", "2D 5D 8D JD KD"),
                asList("2C 3C 6C 8C AC", "2D 3D 6D 8D AD"),
                asList("6C 7S 8H 9D TC", "3H 3S AC AD AH"),
                asList("2H 2S 2D JH JS", "6C 7S 8H 9D TC"),
                asList("2C 2S 2H AH AD", "3D 3S 3C TD TS"),
                asList("5C 5S 5H AH AD", "3D 3S 3C TD TS"),
                asList("2C 3S 8H TH AD", "9D 9S 9C 9H TS"),
                asList("8D TS TC TH TD", "2C 3S 8H TH AD"),
                asList("8D TS TC TH TD", "2C 4S 4H 4C 4D"),
                asList("8D TS TC TH TD", "JC JS JH JD AD"),
                asList("8S 9S TS JS QS", "JC JS JH JD AD"),
                asList("JC JS JH JD AD", "2H 3H 4H 5H 6H"),
                asList("8D 9D TD JD QD", "2H 3H 4H 5H 6H"),
                asList("2H 3H 4H 5H 6H", "8D 9D TD JD QD"),
                asList("2H 3H 4H 5H 6H", "2D 3D 4D 5D 6D"),
                asList("2H 3H 4H 5H 6H", "TD JD QD KD AD"),
                asList("TH JH QH KH AH", "2H 3H 4H 5H 6H"),
                asList("TH JH QH KH AH", "TD JD QD KD AD")
        );

        System.out.println("----------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("-------Welcome to the Poker Game between \"%s\" and \"%s\"%s\n", playerA, playerB, "-------");
        System.out.println("----------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------");

        System.out.println();

        for (int game = 0; game < runs.size(); game++) {
            String firstPlayerCards = runs.get(game).get(0);
            String secondPlayerCards = runs.get(game).get(1);
            PokerGame pokerGame = new PokerGame(playerA, firstPlayerCards, playerB, secondPlayerCards);

            System.out.printf("-------Game %s%s\n", game + 1, "-------");
            System.out.printf("%s has: %s\n", playerA, firstPlayerCards);
            System.out.printf("%s has: %s\n", playerB, secondPlayerCards);
            System.out.printf("Result: %s\n", pokerGame.play());
            System.out.println("---------------------");
            System.out.println();
        }
    }
}
