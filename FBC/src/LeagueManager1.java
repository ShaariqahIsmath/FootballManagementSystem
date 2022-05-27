import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;


interface LeagueManager1 {

    /**
     * declaring methods
     */
    public void addClub();
    public void deleteClub();
    public void displayStats();
    public void displayTable();
    public void addPlayedMatch();
    public String saveDataInFile(String fileName) throws IOException, ClassNotFoundException;
    public EventHandler<ActionEvent> addRandomMatches();
    public void loadDataFromFile(String fileName) throws IOException, ClassNotFoundException;




}
