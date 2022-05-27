import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.*;


public class ConsoleApp1 extends Application {


    private static LeagueManager1 PLeagueManager = new PremierLeagueManager1(20);
    static Stage window;
    static TableView<FootballClub1> table;
    static TableView<Match1>table2;
    Scene scene1, scene2, scene3;


    /**
     * starts the UI
     * @param primaryStage - the stage used to display the UI
     * @throws Exception - if UI isn't displayed
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        displayMenu();


    }

    /**
     * method created to display a menu, and give users to choose an option to run a e certain method
     * @throws IOException - occurs if it's unable to take in an input.
     * @throws ClassNotFoundException - occurs if the classpath to call a certain class, isn't found
     */

    private void displayMenu() throws IOException, ClassNotFoundException {


        System.out.println("---------------- Premiere League Menu --------------------");
        System.out.println("Enter \"CA\" to create a club and add it to the premier league.");
        System.out.println("Enter \"D\" to delete an existing club from the premiere league");
        System.out.println("Enter \"ST\" to display stats of a club in the premiere league");
        System.out.println("Enter \"T\" to display the Premiere League Table");
        System.out.println("Enter \"M\" to add a played match");
        System.out.println("Enter \"R\" to randomly add matches");
        System.out.println("Enter \"PM\" to display played matches");
        System.out.println("Enter \"S\" to store data into a file");
        System.out.println("Enter \"L\" to load data from a file");
        System.out.println("Enter \"Q\" to quit");

        System.out.println("Select an option: ");
        Scanner input = new Scanner(System.in);
        String option = input.nextLine();

        switch (option) {
            case "CA":
            case "ca":
                addClub();
                break;

            case "D":
            case "d":
                deleteClub();
                break;

            case "ST":
            case "st":
                displayStats();
                break;

            case "T":
            case "t":
                displayTable();
                break;

            case "M":
            case "m":
                addPlayedMatch();
                break;

            case "R":
            case "r":
                PLeagueManager.addRandomMatches();
                displayMenu();
                break;

            case "S":
            case "s":
                saveDataInFile();
                break;

            case "L":
            case "l":
                loadDataFromFile();
                break;

            case "Q":
            case "q":
                System.exit(0);
                break;
            default:
                System.out.println("You have entered an invalid option");
                System.out.println("Please enter an option from (CA, D, ST, T, M, R, S, L and Q) only...");
                displayMenu();
        }
    }


    private void addClub() throws IOException, ClassNotFoundException {
        PLeagueManager.addClub();
        displayMenu();

    }

    private void deleteClub() throws IOException, ClassNotFoundException {
        PLeagueManager.deleteClub();
        displayMenu();

    }

    private void displayStats() throws IOException, ClassNotFoundException {
        PLeagueManager.displayStats();
        displayMenu();

    }

    /**
     * creating a method to display club details in a table
     * @throws IOException - occurs if the file in which the details are stored, is not found
     * @throws ClassNotFoundException - occurs if the classpath to call a certain class, isn't found
     */

    private void displayTable() throws IOException, ClassNotFoundException {

        /**
         * heading for the first window
         */
        Label heading = new Label("PREMIER LEAGUE TABLE");
        heading.setLayoutX(300);
        heading.setLayoutY(30);
        heading.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 26));
        heading.setTextFill(Color.TOMATO);

        /**
         * creating table for the clubs in the League, displayed in the first window
         */

        TableColumn<FootballClub1, String> nameColumn = new TableColumn<>("Club Name");
        nameColumn.setSortable(false);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));

        TableColumn<FootballClub1, String> locationColumn = new TableColumn<>("Location");
        locationColumn.setSortable(false);
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        TableColumn<FootballClub1, String> matchesColumn = new TableColumn<>("Number of Matches Played");
        matchesColumn.setSortable(false);
        matchesColumn.setCellValueFactory(new PropertyValueFactory<>("NumberOfMatchesPlayed"));

        TableColumn<FootballClub1, Double> pointsColumn = new TableColumn<>("Club Points");
        pointsColumn.setSortable(false);
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));

        TableColumn<FootballClub1, Integer> winsColumn = new TableColumn<>("Club Wins");
        winsColumn.setSortable(false);
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));

        TableColumn<FootballClub1, Integer> goalsScoredColumn = new TableColumn<>("Goals Scored");
        goalsScoredColumn.setSortable(false);
        goalsScoredColumn.setCellValueFactory(new PropertyValueFactory<>("goalsScored"));

        TableColumn<FootballClub1, Integer> goalsConcededColumn = new TableColumn<>(" Goals Conceded");
        goalsConcededColumn.setSortable(false);
        goalsConcededColumn.setCellValueFactory(new PropertyValueFactory<>("goalsConceded"));

        table = new TableView<>();
        table.setItems(clubDeets());
        table.setPrefWidth(800);
        table.setPrefHeight(500);
        table.setLayoutX(50);
        table.setLayoutY(85);

        VBox boxes = new VBox();
        boxes.getChildren().addAll(table);


        /**
         * creating table for the matches played in the League displayed in the second window
         */


        TableColumn<Match1, FootballClub1> teamAColumn = new TableColumn<>("Home Team ");
        teamAColumn.setCellValueFactory(new PropertyValueFactory<>("team1"));

        TableColumn<Match1, FootballClub1> teamBColumn = new TableColumn<>("Away Team");
        teamBColumn.setCellValueFactory(new PropertyValueFactory<>("team2"));

        TableColumn<Match1, Integer> teamAScoreColumn = new TableColumn<>("Home Team Goals");
        teamAScoreColumn.setCellValueFactory(new PropertyValueFactory<>("team1Score"));

        TableColumn<Match1, Integer> teamBScoreColumn = new TableColumn<>("Away Team Goals");
        teamBScoreColumn.setCellValueFactory(new PropertyValueFactory<>("team2Score"));

        TableColumn<Match1, Date> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));


        table2 = new TableView<>();
        table2.setItems(matchDeets());

        table2.getColumns().addAll(teamAColumn, teamBColumn, teamAScoreColumn, teamBScoreColumn, dateColumn);
        table2.setPrefWidth(780);
        table2.setPrefHeight(500);
        table2.setLayoutX(50);
        table2.setLayoutY(100);

        final VBox boxes2 = new VBox();
        boxes2.getChildren().addAll(table2);


        /**
         * buttons for the first window
         */

        Button point = new Button("Sort by Points");
        point.setLayoutX(40);
        point.setLayoutY(610);
        point.setPadding(new Insets(6,4,6,4));
        point.setOnAction(event -> {
            pointsColumn.setSortable(true);
            pointsColumn.setSortType(TableColumn.SortType.DESCENDING);
            table.getSortOrder().add(pointsColumn);
            table.sort();
            pointsColumn.setSortable(false);
        });

        Button score = new Button("Sort by Goals Scored");
        score.setLayoutX(152);
        score.setLayoutY(610);
        score.setPadding(new Insets(6,4,6,4));

        score.setOnAction(event -> {
            goalsScoredColumn.setSortable(true);
            goalsScoredColumn.setSortType(TableColumn.SortType.DESCENDING);
            table.getSortOrder().add(goalsScoredColumn);
            table.sort();
            goalsScoredColumn.setSortable(false);
        });

        Button wins = new Button("Sort by Wins");
        wins.setLayoutX(310);
        wins.setLayoutY(610);
        wins.setPadding(new Insets(6,4,6,4));

        wins.setOnAction(event -> {
            winsColumn.setSortable(true);
            winsColumn.setSortType(TableColumn.SortType.DESCENDING);
            table.getSortOrder().add(winsColumn);
            table.sort();
            winsColumn.setSortable(false);
        });

        /**
         * closes the window returns user back to the console
         */
        Button back = new Button(" Return ");
        back.setLayoutX(790);
        back.setLayoutY(610);
        back.setPadding(new Insets(7,5,7,5));

        back.setOnAction(event -> {
            window.close();
            try {
                displayMenu();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        Button displayMatchTable = new Button(" Match Table ");
        displayMatchTable.setLayoutX(740);
        displayMatchTable.setLayoutY(30);
        displayMatchTable.setPadding(new Insets(7,5,7,5));

        Button randomMatch = new Button("Generate Match");
        randomMatch.setLayoutY(610);
        randomMatch.setLayoutX(655);
        randomMatch.setPadding(new Insets(7,5,7,5));
        randomMatch.setOnAction(event -> {
            addRandomMatches();
        });

        /**
         * heading for second window
         */

        Label heading2 = new Label("     MATCH TABLE     ");
        heading2.setLayoutX(320);
        heading2.setLayoutY(15);
        heading2.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 26));
        heading2.setTextFill(Color.TOMATO);

        /**
         *  buttons for the second window
         */

        Button exit = new Button(" Return ");
        exit.setLayoutX(770);
        exit.setLayoutY(615);
        exit.setPadding(new Insets(7,5,7,5));
        exit.setOnAction(event -> {
            window.close();
            try {
                displayMenu();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });

        Button GoBack = new Button("Previous Window");
        GoBack.setLayoutX(625);
        GoBack.setLayoutY(615);
        GoBack.setPadding(new Insets(7,5,7,5));


        Button search = new Button("Search");
        search.setLayoutY(48);
        search.setLayoutX(215);
        search.setMinSize(75, 40 );

        TextField searchBox = new TextField();
        searchBox.setLayoutY(52);
        searchBox.setLayoutX(60);
        searchBox.setMaxWidth(150);



        FilteredList<Match1> filteredData = new FilteredList<>(matchDeets(), p -> true);

            searchBox.textProperty().addListener((observable, oldValue, inputValue) -> {
                search.setOnAction(event -> {
                filteredData.setPredicate(match1 -> {
                    if (inputValue == null || inputValue.isEmpty()){
                        return true;
                    }

                    if (String.valueOf(match1.getDate()).contains(inputValue)){
                        return true;
                    }
                    return false;
                });
            });
        });


        SortedList<Match1> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table2.comparatorProperty());
        table2.setItems(sortedData);

        /**
         * second window
         */
        AnchorPane layout2 = new AnchorPane();
        layout2.getChildren().addAll(exit, table2, boxes2, search, searchBox, heading2, GoBack);
        scene2 = new Scene(layout2, 900, 700);
        displayMatchTable.setOnAction(event -> window.setScene(scene2));


        /**
         * first window
         */
        AnchorPane layout1 = new AnchorPane();
        layout1.getChildren().addAll(score, wins, boxes, back, table, point, heading, displayMatchTable, randomMatch);
        scene1 = new Scene(layout1, 900, 700);
        window.setScene(scene1);
        GoBack.setOnAction(event -> window.setScene(scene1)); // back to scene 1

        /**
         * display league table
         */

        table.getColumns().addAll(nameColumn, locationColumn, matchesColumn, pointsColumn, winsColumn, goalsScoredColumn, goalsConcededColumn );
        window.setScene(scene1);
        window.setTitle("Premier League Table");
        window.show();
        PLeagueManager.displayTable();



    }

    /**
     * saves necessary club details and helps display the details in a table
     * @return club details
     */

    public static ObservableList<FootballClub1> clubDeets(){
        ObservableList<FootballClub1> cName = FXCollections.observableArrayList();
        cName.setAll(PremierLeagueManager1.P_League);
        return cName;
    }


    private void addPlayedMatch() throws IOException, ClassNotFoundException {
        PLeagueManager.addPlayedMatch();
        displayMenu();

    }

    /**
     * method to display a list of options: to generate a match, display the tables, and return to the console
     */
    private void addRandomMatches(){

        Button randomGenerate = new Button("Generate Random Matches");
        randomGenerate.setLayoutX(145);
        randomGenerate.setLayoutY(150);
        randomGenerate.setOnAction(event -> PLeagueManager.addRandomMatches());

        Button displayTables = new Button("Display League and Match Table");
        displayTables.setLayoutX(130);
        displayTables.setLayoutY(250);
        displayTables.setOnAction(event -> {
            try {
                displayTable();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        Button leave = new Button("Return");
        leave.setLayoutX(210);
        leave.setLayoutY(200);
        leave.setOnAction(event -> {
            window.close();
            try {
                displayMenu();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        AnchorPane layout3 = new AnchorPane();
        layout3.getChildren().addAll(leave, displayTables, randomGenerate);
        scene3 = new Scene(layout3, 490, 490);
        window.setScene(scene3);
        window.show();

    }

    private void saveDataInFile() throws IOException, ClassNotFoundException {
        PLeagueManager.saveDataInFile("PLM Details");
        System.out.println("Data has been successfully saved!!");
        displayMenu();

    }

    private void loadDataFromFile() throws IOException, ClassNotFoundException {
        displayMenu();

    }

    /**
     * saves necessary match details and helps display them in a table
     * @return match details
     */

    public static ObservableList<Match1> matchDeets(){
        ObservableList<Match1> match = FXCollections.observableArrayList();
        match.setAll(PremierLeagueManager1.Matches);
        return match;
    }


    /**
     * main method
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        PLeagueManager.loadDataFromFile("PLM Details");
        launch(args);
    }


}


