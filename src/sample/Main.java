package sample;

import Tree.Node;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class Main extends Application implements EventHandler {

    Button button;
    private Node rootNode;
    private String lettersOnBoard;
    private ObservableList<Solution> list;
    private GridPane gridPane;
    private TextField textField;
    private TableView<Solution> table = new TableView();
    private TableColumn wordColumn = new TableColumn("Słowo");
    private TableColumn numberColumn = new TableColumn("Długość");

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Słowotok");

        button = new Button("Analizuj");

        button.setMinWidth(100);
        button.setMaxWidth(100);


        gridPane = new GridPane();
        gridPane.setPadding(new Insets(15));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER_LEFT);

        Scene myScene = new Scene(gridPane, 400, 500);

        textField = new TextField();
        gridPane.add(textField, 1, 0);

        gridPane.add(button, 2, 0);
        button.setOnAction(this);

        rootNode = new Node();
        rootNode.fillFromFile();

        list = FXCollections.observableArrayList();

        gridPane.add(table, 1, 3);

        primaryStage.setScene(myScene);
        table.getColumns().addAll(wordColumn, numberColumn);
        wordColumn.setMinWidth(table.getWidth() / 2);
        wordColumn.setMaxWidth(table.getWidth() / 2);

        numberColumn.setMinWidth(table.getWidth() / 2);
        numberColumn.setMaxWidth(table.getWidth() / 2);

        primaryStage.show();
    }

    private void go() {

        lettersOnBoard = textField.getCharacters().toString();

        SolutionThread thread = new SolutionThread(textField.getCharacters().toString(), rootNode);

        try {
            list = thread.call();

        } catch (Exception e) {
            e.printStackTrace();
        }

        wordColumn.setMinWidth(150);
        wordColumn.setCellValueFactory(
                new PropertyValueFactory<Solution, String>("word"));

        numberColumn.setMinWidth(75);
        numberColumn.setCellValueFactory(
                new PropertyValueFactory<Solution, String>("length"));

        table.setItems(list);
    }

    @Override
    public void handle(Event event) {
        go();
    }
}
