package sample;

import Board.Board;
import Tree.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Artur on 2017-02-02.
 */
public class Solution {
    public Solution(String s) {
        word = s;
        length = s.length();
    }

    public Solution() {
        super();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
    //@Todo List of Coords to show the solutions path?

    public ObservableList<Solution> getSolutions(String groupOfLetters, Node rootNode) {

        ObservableList<Solution> list = FXCollections.observableArrayList();

        rootNode = new Node();

        try {
            rootNode.fillFromFile();
        } catch (IOException e) {
            System.out.println("Jest bardzo źle!");
            e.printStackTrace();
        }

        groupOfLetters = groupOfLetters.toLowerCase();
        char[] tab = groupOfLetters.toCharArray();

        char[][] letters = arraysTo2D(tab);

        Board board = new Board(letters, rootNode);
        board.searchAll();

        ArrayList<String> found = board.getFoundWords();

        for (String s : found) {
            list.add(new Solution(s));
        }

        return list;
    }

    //private

    private char[][] arraysTo2D(char[] tab) {
        char[][] board = new char[4][4];

        for (int i = 0; i < 4; i++) {
            board[i][0] = tab[i];
            board[i][1] = tab[i + 4];
            board[i][2] = tab[i + 8];
            board[i][3] = tab[i + 12];
        }
        return board;
    }
    private String word;
    private Integer length;
}
