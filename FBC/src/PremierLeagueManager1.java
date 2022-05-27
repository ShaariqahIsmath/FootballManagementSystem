import javafx.event.ActionEvent;
import javafx.event.EventHandler;


import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class PremierLeagueManager1  implements LeagueManager1  {

    /**
     * initialising variables
     */
    int NumberOfClubs;
    static ArrayList<FootballClub1> P_League;
    static ArrayList<Match1> Matches;
    Scanner input = new Scanner(System.in);

    /**
     * creating a constructor
     * @param NumberOfClubs
     */
    public PremierLeagueManager1(int NumberOfClubs){
        this.NumberOfClubs = NumberOfClubs;
        P_League = new ArrayList<>();
        Matches = new ArrayList<>();

    }


    /**
     * creating a method to addClubs to the League Arraylist
     */
    @Override
    public void addClub() {

        if (P_League.size() == NumberOfClubs){
            System.out.println("Cannot add more clubs");
            return;
        }

        FootballClub1 club = new FootballClub1();
        System.out.println("Insert Club Name: ");
        String name = input.nextLine().toLowerCase();
        club.setClubName(name);



        for (FootballClub1 club1 : P_League) {
            if (club1.getClubName().equals(name)) {
                System.out.println("This club already exists");
                return;

            }
        }

        System.out.println("Insert Location: ");
        String location = input.nextLine();
        club.setLocation(location);
        P_League.add(club);

    }

    /**
     * creating a method to delete a club from the list
     */
    @Override
    public void deleteClub() {

        System.out.println("Insert Club Name: ");
        String name = input.nextLine();
        for (FootballClub1 club : P_League) {
            if (club.getClubName().equals(name)) {
                P_League.remove(club);
                System.out.println(club.getClubName() + " has been removed.");

                return;

            }
        }
        System.out.println("Club doesn't exist!");
        return;

    }

    /**
     * creating a method to display the stats of each club in the League Arraylist
     */
    @Override
    public void displayStats() {

        System.out.println("Insert the Club Name: ");
        String name = input.nextLine();

        for (FootballClub1 club : P_League) {
            if (club.getClubName().equals(name)) {
                System.out.println("Club Name: " + club.getClubName());
                System.out.println("No. of Matches Played: " + club.getNumberOfMatchesPlayed());
                System.out.println("Matches Won: " + club.getWins());
                System.out.println("Matches Drawn: " + club.getDraws());
                System.out.println("Matches Lost: " + club.getDefeats());
                System.out.println("Goals Scored: " + club.getGoalsScored());
                System.out.println("Goals Conceded: " + club.getGoalsConceded());
                System.out.println("Club Points: " + club.getPoints());


                return;



            }
        }

        System.out.println("Club doesn't exist!");
        return;


    }

    /**
     * creating a a method to display the club details in a table, in the console
     */
    @Override
    public void displayTable() {

        Collections.sort(P_League);

        for (FootballClub1 club : P_League){
            System.out.println(" Club Name         : " + club.getClubName() +"\n " +"Location          : "   +club.getLocation()   + "\n" +" Points            : "+ club.getPoints() + "\n "+"Goal Difference   : " + (club.getGoalsScored() - club.getGoalsConceded()) + "\n" + " Goals Scored      : "+ club.getGoalsScored() + "\n" + " Goals Conceded    : "  + club.getGoalsConceded() +  "\n" + " Club Wins         : " + club.getWins());
        }
        return;



    }


    /**
     * creating a method to add a match played by 2 clubs in the League Arraylist
     */
    @Override
    public void addPlayedMatch() {


        System.out.println("Enter the date ");
        String answer;
        System.out.println("Enter the day of the date in the format (dd)");
        String dayInput = input.nextLine();
        System.out.println("Enter the month of the date in the format (MM)");
        String monthInput = input.nextLine();
        System.out.println("Enter the year of the date in the format (yyyy)");
        String yearInput = input.nextLine();



        int y = Integer.parseInt( yearInput );
        int m = Integer.parseInt( monthInput );  // 1-12 for January-December.
        int d = Integer.parseInt( dayInput );

        LocalDate localDate = LocalDate.of( y , m , d );



        System.out.println("Enter Home Team: ");
        answer = input.nextLine();

        FootballClub1 h = null;
        for (FootballClub1 club : P_League) {
            if (club.getClubName().equals(answer)) {
                h = club;
            }
        }

        if (h == null) {
            System.out.println("This club does not exist in the league!");
            return;

        }

        System.out.println("Enter Away Team: ");
        answer = input.nextLine();

        FootballClub1 a = null;
        for (FootballClub1 club : P_League) {
            if (club.getClubName().equals(answer)) {
                a = club;
            }
        }

        if (a == null) {
            System.out.println("This club does not exist in the league!");
            return;
        }


        System.out.println("Enter the goals scored by the Home Team: ");
        answer = input.nextLine();

        int homeTeamGoals = -1;
        try {
            homeTeamGoals = Integer.parseInt(answer);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        if (homeTeamGoals == -1){
            System.out.println("You HAVE to enter the number of goals!");

        }


        System.out.println("Enter the goals scored by the Away Team: ");
        answer = input.nextLine();

        int awayTeamGoals = -1;
        try {
            awayTeamGoals = Integer.parseInt(answer);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (awayTeamGoals == -1){
            System.out.println("You HAVE to enter the number of goals!");
            return;
        }


        Match1 match = new Match1();
        match.setTeam1(h);
        match.setTeam2(a);
        match.setTeam1Score(homeTeamGoals);
        match.setTeam2Score(awayTeamGoals);
        match.setDate(localDate);
        Matches.add(match);

        h.setGoalsScored(h.getGoalsScored() + homeTeamGoals);
        a.setGoalsScored(a.getGoalsScored() + awayTeamGoals);
        h.setGoalsConceded(h.getGoalsConceded() + awayTeamGoals);
        a.setGoalsConceded(a.getGoalsConceded() + homeTeamGoals);
        h.setNumberOfMatchesPlayed(h.getNumberOfMatchesPlayed() + 1);
        a.setNumberOfMatchesPlayed(a.getNumberOfMatchesPlayed() + 1);

        if (homeTeamGoals > awayTeamGoals){

            h.setPoints(h.getPoints() + 3);
            h.setWins(h.getWins() + 1);
            a.setDefeats(a.getDefeats() + 1);
        }

        else if (homeTeamGoals < awayTeamGoals){

            a.setPoints(a.getPoints() + 3);
            a.setWins(a.getWins() + 1);
            h.setDefeats(a.getDefeats() + 1);

        }

        else{
            a.setPoints(a.getPoints() + 1);
            h.setPoints(h.getPoints() + 1);
            a.setDraws(a.getDraws() + 1);
            h.setDraws(h.getDraws() + 1);
        }

        return;

    }

    /**
     * creating a method to generate random matches played between 2 clubs in the League Arraylist
     * @return a match played
     */

    @Override
    public EventHandler<ActionEvent> addRandomMatches() {

        Match1 match = new Match1();
        Random r1, r2, random1, random2;


        LocalDate randomDate = createRandomDate(2021,2023);
        match.setDate(randomDate);


        r1 = new Random();
        int randomTeamA = r1.nextInt(P_League.toArray().length);

        r2 = new Random();
        int randomTeamB = r2.nextInt(P_League.toArray().length);


        if ( randomTeamA != randomTeamB){
            match.setTeam1(P_League.get(randomTeamA));
            System.out.println("TEAM A :" + match.team1);

            match.setTeam2(P_League.get(randomTeamB));
            System.out.println("TEAM B :" + match.team2);

            System.out.println("Match Played  :" + match.team1 + " vs " + match.team2);

        }
        else {
            System.out.println("One team can't have a match with itself!! ");
            return null;

        }


        random1 = new Random();
        int max = 10;
        int min = 0;

        int score1 = random1.nextInt((max - min + 1) + min);
        match.setTeam1Score(score1);

        random2 = new Random();
        int score2 = random2.nextInt((max - min + 1) + min);
        match.setTeam2Score(score2);


        match.getTeam1().setGoalsScored(match.getTeam1Score() + score1);
        System.out.println("TEAM A score :" + match.getTeam1().getPoints());
        match.getTeam2().setGoalsScored(match.getTeam2Score() + score2);
        System.out.println("TEAM B score :" + match.getTeam2().getPoints());

        match.getTeam1().setGoalsConceded(match.getTeam2Score() + score2 );
        match.getTeam2().setGoalsConceded(match.getTeam1Score() + score1);
        match.getTeam1().setNumberOfMatchesPlayed(match.getTeam1().getNumberOfMatchesPlayed() + 1);
        match.getTeam2().setNumberOfMatchesPlayed(match.getTeam2().getNumberOfMatchesPlayed() + 1);

        if (match.getTeam1Score() > match.getTeam2Score()){

            match.getTeam1().setPoints(match.getTeam1().getPoints() + 3);
            match.getTeam1().setWins(match.getTeam1().getWins() + 1);
            match.getTeam2().setDefeats(match.getTeam2().getDefeats() + 1);
            System.out.println("Winner  : " + match.team1);


        }
        else if (match.getTeam1Score() < match.getTeam2Score()){

            match.getTeam2().setPoints(match.getTeam2().getPoints() + 3);
            match.getTeam2().setWins(match.getTeam2().getWins() + 1);
            match.getTeam1().setDefeats(match.getTeam1().getDefeats() + 1);
            System.out.println("TEAM B  score :" + match.getTeam2().getPoints());
            System.out.println("Winner  : " + match.team2);


        }
        else {

            match.getTeam1().setPoints(match.getTeam1().getPoints() + 1);
            match.getTeam2().setPoints(match.getTeam2().getPoints() + 1);
            match.getTeam1().setDraws(match.getTeam1().getDraws() + 1);
            match.getTeam2().setDraws(match.getTeam2().getDraws() + 1);
            System.out.println("Match is a tie");



        }

        Matches.add(match);
        return null;

    }

    public static int createRandomIntBetween(int start, int end){
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static LocalDate createRandomDate(int startYear, int endYear){

        int day = createRandomIntBetween(1,28);
        int month = createRandomIntBetween(1,12);
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day);
    }

    /**
     * saves data into file
     * @param fileName - contains club and match details
     * @return - succcess message
     * @throws IOException - occurs if file is not found
     */

    @Override
    public String saveDataInFile(String fileName) throws IOException {

        FileOutputStream saveFile = null;
        ObjectOutputStream save = null;
        try {
            saveFile = new FileOutputStream(fileName);
            save = new ObjectOutputStream(saveFile);

            save.writeObject(P_League);
            save.writeObject(Matches);

        } catch (Exception e){
            return "Exception occurred!";

        }

        saveFile.close();
        save.close();

        return "Data has been successfully saved!";




    }

    /**
     * loads data from a file
     * @param fileName - contains club and match details
     * @throws IOException - occurs if file is not found
     */


    @Override
    public void loadDataFromFile(String fileName) throws IOException {

        FileInputStream saveFile = null;
        ObjectInputStream save = null;

        try {
            saveFile = new FileInputStream(fileName);
            save = new ObjectInputStream(saveFile);

            try {
                P_League = (ArrayList<FootballClub1>)save.readObject();
                Matches = (ArrayList<Match1>)save.readObject();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException notFoundException){
            System.out.println("File not found!!");
        }
        catch (IOException e){
            System.out.println("IOException occurred");
        }

        saveFile.close();
        save.close();
        System.out.println("Data has been successfully loaded!");


    }





}
