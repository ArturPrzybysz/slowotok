package sample;

import Tree.Node;
import javafx.collections.ObservableList;

import java.util.concurrent.Callable;

/**
 * Created by Artur on 2017-02-22.
 */
public class SolutionThread implements Callable<ObservableList<Solution>> {

    Solution solution;
    Node node;
    String letters;

    public SolutionThread(String letters, Node node) {
        solution = new Solution();
        this.node = node;
        this.letters = letters;
    }

    @Override
    public ObservableList<Solution> call() throws Exception {
        ObservableList<Solution> list = solution.getSolutions(letters, node);
        return list;
    }

//    //??Thread thread;
//    Solution solution;
//    Node node;
//    String letters;
//    private ObservableList<Solution> list;
//
//
//    public SolutionThread(String lettersGiven, Node rootNode) {
//
//        System.out.println("Stworzenie wątku");
//
//        solution = new Solution();
//        node = rootNode;
//        letters = lettersGiven;
//    }
//
//    @Override
//    public void run() {
//        //super.run();
//        System.out.println("Start wątku");
//
//        list = solution.getSolutions(letters, node);
//        System.out.println("Koniec wątku");
//
//
//    }
//
//    public ObservableList getListOfSolutions(){
//        System.out.println("Oddanie odpowiedzi");
//
//        return list;
//    }


}
