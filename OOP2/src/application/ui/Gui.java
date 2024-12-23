package application.ui;

import application.model.Node;
import application.algorithm.BellmanFord;
import application.algorithm.Flooding;
import application.model.Edge;
import application.model.Graph;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.Stack;

public class Gui {

    private final Graph graph = new Graph();
    private final PrintGraph printGraph = new PrintGraph(graph);
    public GridPane createUI(){

        GridPane gridPane = new GridPane();

        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        return gridPane;
    }

    public void addSearchEdgeUI(GridPane gridPane, String str){
        // Add Name Label
        Label nameLabel = new Label("From Vertex : ");
        gridPane.add(nameLabel, 0,2);
        TextField nameField = new TextField();
        nameField.setPromptText(" Enter From Vertex");
        nameField.setPrefHeight(30);
        gridPane.add(nameField, 1,2);

        Label toLabel = new Label("To Vertex : ");
        gridPane.add(toLabel, 2,2);
        TextField toField = new TextField();
        toField.setPromptText("Enter to Vertex");
        toField.setPrefHeight(30);
        gridPane.add(toField, 3,2);

        Button SearchButton = new Button(str);
        SearchButton.setPrefHeight(30);
        SearchButton.setDefaultButton(true);
        SearchButton.setPrefWidth(100);
        gridPane.add(SearchButton, 0, 3, 4, 1);
        GridPane.setValignment(SearchButton, VPos.CENTER);
        GridPane.setHalignment(SearchButton, HPos.CENTER);

        SearchButton.setOnAction(event -> {
            if(nameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!", "Please enter name");
            }else if(toField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!", "Please enter name");
            }
            else {
                switch (str) {
                    case "Search Edge": {
                        String output = graph.SearchEdge(nameField.getText(), toField.getText());
                        showAlert(Alert.AlertType.INFORMATION, gridPane.getScene().getWindow(), "Addition Successful!", output);
                        break;
                    }
                    case "Delete Edge": {
                        String output = graph.DeleteEdge(nameField.getText(), toField.getText());
                        showAlert(Alert.AlertType.INFORMATION, gridPane.getScene().getWindow(), "Deletion Successful!", output);
                        break;
                    }
                    case "Get Path": {
                        String output = graph.getPath(nameField.getText(), toField.getText());
                        showAlert(Alert.AlertType.INFORMATION, gridPane.getScene().getWindow(), " Path ", output);
                        break;
                    }
                }
                nameField.setText(null);
                toField.setText(null);
            }
            //add node here
        });
    }
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    public void GraphGui(Pane pane){
        printGraph.showGraph(pane);
    }
    public void animateDijkstraGui(Pane pane){

        String[] typeOfNodes = {"Circle","Square","Plus","Cross","Triangle"};
        Label nameLabel = new Label("From Vertex : ");
        nameLabel.setLayoutX(10);
        nameLabel.setLayoutY(40);
        TextField nameField = new TextField();
        nameField.setPromptText("From");
        nameField.setPrefHeight(15);
        nameField.setPrefWidth(60);
        nameField.setLayoutX(85);
        nameField.setLayoutY(37);
        pane.getChildren().add(nameLabel);
        pane.getChildren().add(nameField);

        Label toLabel = new Label("To Vertex : ");
        toLabel.setLayoutX(155);
        toLabel.setLayoutY(40);
        TextField toField = new TextField();
        toField.setPromptText("To");
        toField.setPrefHeight(15);
        toField.setPrefWidth(60);
        toField.setLayoutX(215);
        toField.setLayoutY(37);
        pane.getChildren().addAll(toLabel,toField);

        Button SearchButton = new Button("PATH");
        SearchButton.setPrefHeight(15);
        SearchButton.setDefaultButton(true);
        SearchButton.setPrefWidth(60);
        SearchButton.setLayoutX(395);
        SearchButton.setLayoutY(37);
        pane.getChildren().add(SearchButton);

        final String[] str = new String[1];
        ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList(typeOfNodes));
        EventHandler<ActionEvent> newEvent = event6_ -> {
            str[0] = comboBox.getValue();
            SearchButton.setOnAction(event5 -> printGraph.animatePath(pane,nameField.getText(),toField.getText(),str[0]));
        };

        comboBox.setLayoutX(285);
        comboBox.setLayoutY(37);
        comboBox.setOnAction(newEvent);
        pane.getChildren().add(comboBox);

        printGraph.getGraphPane(pane,false);
    }
    public void animateBellmanFordGui(Pane pane) {
        String[] typeOfNodes = {"Circle", "Square", "Plus", "Cross", "Triangle"};
        Label nameLabel = new Label("From Vertex : ");
        nameLabel.setLayoutX(10);
        nameLabel.setLayoutY(40);
        TextField nameField = new TextField();
        nameField.setPromptText("From");
        nameField.setPrefHeight(15);
        nameField.setPrefWidth(60);
        nameField.setLayoutX(85);
        nameField.setLayoutY(37);
        pane.getChildren().add(nameLabel);
        pane.getChildren().add(nameField);

        Label toLabel = new Label("To Vertex : ");
        toLabel.setLayoutX(155);
        toLabel.setLayoutY(40);
        TextField toField = new TextField();
        toField.setPromptText("To");
        toField.setPrefHeight(15);
        toField.setPrefWidth(60);
        toField.setLayoutX(215);
        toField.setLayoutY(37);
        pane.getChildren().addAll(toLabel, toField);

        Button searchButton = new Button("PATH");
        searchButton.setPrefHeight(15);
        searchButton.setPrefWidth(60);
        searchButton.setLayoutX(395);
        searchButton.setLayoutY(37);
        pane.getChildren().add(searchButton);

        final String[] str = new String[1];
        ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList(typeOfNodes));
        EventHandler<ActionEvent> newEvent = event4 -> {
            str[0] = comboBox.getValue();
            searchButton.setOnAction(event3 -> {
                String from = nameField.getText();
                String to = toField.getText();

                // Validate input
                if (from.isEmpty() || to.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter both From and To vertices!", ButtonType.OK);
                    alert.show();
                    return;
                }

                Node source = graph.getNode(from);
                Node target = graph.getNode(to);

                if (source == null || target == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid vertices!", ButtonType.OK);
                    alert.show();
                    return;
                }

                BellmanFord bellmanFord = new BellmanFord();
                for (Node node : graph.getNodes()) {
                    bellmanFord.addNode(node);
                }
                for (Edge edge : graph.getEdges()) {
                    bellmanFord.addEdge(edge.getSource(), edge.getDestination(), edge.getWeight());
                }

                Stack<String> path = bellmanFord.getNodePath(source, target);
                if (path == null) {
                    // Nếu path trả về null, tức là có chu trình âm
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Graph contains a negative weight cycle!", ButtonType.OK);
                    alert.show();
                    return;
                }

                // Animate path using printGraph
                printGraph.animatePath(pane, from, to, str[0]);
            });
        };

        comboBox.setLayoutX(285);
        comboBox.setLayoutY(37);
        comboBox.setOnAction(newEvent);
        pane.getChildren().add(comboBox);

        printGraph.getGraphPane(pane, false);
    }
    void animateFloodingGui(Pane pane) {
        String[] typeOfNodes = {"Circle", "Square", "Plus", "Cross", "Triangle"};
        Label nameLabel = new Label("From Vertex : ");
        nameLabel.setLayoutX(10);
        nameLabel.setLayoutY(40);
        TextField nameField = new TextField();
        nameField.setPromptText("From");
        nameField.setPrefHeight(15);
        nameField.setPrefWidth(60);
        nameField.setLayoutX(85);
        nameField.setLayoutY(37);
        pane.getChildren().add(nameLabel);
        pane.getChildren().add(nameField);

        Label toLabel = new Label("To Vertex : ");
        toLabel.setLayoutX(155);
        toLabel.setLayoutY(40);
        TextField toField = new TextField();
        toField.setPromptText("To");
        toField.setPrefHeight(15);
        toField.setPrefWidth(60);
        toField.setLayoutX(215);
        toField.setLayoutY(37); 
        pane.getChildren().addAll(toLabel, toField);

        Button searchButton = new Button("Flood");
        searchButton.setPrefHeight(15);
        searchButton.setPrefWidth(60);
        searchButton.setLayoutX(395);
        searchButton.setLayoutY(37);
        pane.getChildren().add(searchButton);

        final String[] str = new String[1];
        ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList(typeOfNodes));
        EventHandler<ActionEvent> newEvent = event1 -> {
            str[0] = comboBox.getValue();
            searchButton.setOnAction(event2 -> {
                String from = nameField.getText();
                String to = toField.getText();

                // Validate input
                if (from.isEmpty() || to.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter both From and To vertices!", ButtonType.OK);
                    alert.show();
                    return;
                }

                Node source = graph.getNode(from);
                Node target = graph.getNode(to);

                if (source == null || target == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid vertices!", ButtonType.OK);
                    alert.show();
                    return;
                }

                Flooding flooding = new Flooding();
                for (Node node : graph.getNodes()) {
                    flooding.addNode(node);
                }
                for (Edge edge : graph.getEdges()) {
                    flooding.addEdge(edge.getSource(), edge.getDestination(), edge.getWeight());
                }

                Stack<String> path = flooding.getNodePath(source, target);
                if (path == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "No path found between the nodes!", ButtonType.OK);
                    alert.show();
                    return;
                }

                // Animate path using printGraph
                printGraph.animatePath(pane, from, to, str[0]);
            });
        };

        comboBox.setLayoutX(285);
        comboBox.setLayoutY(37);
        comboBox.setOnAction(newEvent);
        pane.getChildren().add(comboBox);

        printGraph.getGraphPane(pane, false);
    }
}