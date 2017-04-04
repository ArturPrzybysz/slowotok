package Board;

import Enumaretion.Result;
import Tree.Node;

import java.util.*;

/**
 * Created by Artur on 2017-01-31.
 */
public class Board {
    private char[][] board;
    private Node root;

    private ArrayList<String> foundWords = new ArrayList<>();

    public Board(char[][] letters, Node givenRoot) {
        board = letters;
        root = givenRoot;
    }

    public ArrayList<String> getFoundWords() {
        deleteDuplicates();
        return foundWords;
    }

    private void deleteDuplicates() {
        HashSet<String> hs = new HashSet<>();
        hs.addAll(foundWords);
        foundWords.clear();
        foundWords.addAll(hs);
    }

    public void searchAll() {
        Stack<Coord> usedLetters;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                usedLetters = new Stack<>();
                search2(usedLetters, i, j);
            }
        }
    }

    public void search2(Stack<Coord> presentCombination, int x, int y) {
        presentCombination.push(new Coord(x, y));
        char[] word = new char[presentCombination.size()];

        for (int i = 0; i < presentCombination.size(); i++) {
            word[i] = board[presentCombination.get(i).getI()][presentCombination.get(i).getJ()];
        }
        String wordString = new String(word);

        Result result = root.ifContains(wordString);

        if (result == Result.negative) {
            return;
        } else if (result == Result.positive) {
            foundWords.add(wordString);
        }

        Stack<Coord> availableFields = new Stack<>();
        Coord coord;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && i == j)) {
                    coord = new Coord(x + i, y + j);
                    if (coord.isWithinBorders()) {
                        if (!ifInStack(coord, presentCombination)) {
                            availableFields.push(coord);
                        }
                    }
                }
            }
        }

        Coord c;
        while (!availableFields.isEmpty()) {
            c = availableFields.pop();
            search2(presentCombination, c.getI(), c.getJ());
            presentCombination.pop();
        }
    }


    private boolean ifInStack(Coord coord, Stack<Coord> stack) {
        for (int i = 0; i < stack.size(); i++) {
            if (stack.get(i).getI() == coord.getI() && stack.get(i).getJ() == coord.getJ()) { //czy działa to equals?

                return true;
            }
        }
        return false;
    }
}