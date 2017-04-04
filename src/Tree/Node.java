package Tree;

import Enumaretion.Result;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Artur on 2017-01-29.
 */


public class Node {

    private HashMap<Character, Node> next = new HashMap<>();
    private boolean isWord = false;

    public Node() {
        super();
    }



    public void addWord(String wordString) {
        char[] word = wordString.toCharArray();

        Node iterator = this;
        for (int i = 0; i < word.length; i++) {

            if (iterator.next.containsKey(word[i])) {
                iterator = iterator.next.get(word[i]);
            } else {
                Node nastepny = new Node();
                iterator.next.put(word[i], nastepny);
                iterator = iterator.next.get(word[i]);
            }
        }
        iterator.setAsEndOfWord();
    }

    public Result ifContains(String wordString) {

        char[] word = wordString.toCharArray();
        Node iterator = this;
        for (int i = 0; i < word.length; i++) {
            if (iterator.next.containsKey(word[i])) {
                iterator = iterator.next.get(word[i]);
            } else {
                return Result.negative;
            }
        }

        if (iterator.isWord) {
            return Result.positive;
        } else {
            return Result.partly;
        }
    }

    private void setAsEndOfWord() {
        isWord = true;
    }

    public void fillFromFile() throws IOException {
        // Open the file
        // FileInputStream fstream = new FileInputStream("");
        BufferedReader br = new BufferedReader
                (new InputStreamReader
                        (this.getClass().getResourceAsStream("/Files/slowa.txt")));
        String strLine;

//Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            addWord(strLine);
        }

//Close the input stream
        br.close();

    }
}

