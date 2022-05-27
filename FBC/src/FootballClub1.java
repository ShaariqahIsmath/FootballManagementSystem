import java.io.Serializable;


abstract class SportsClub1 implements Serializable {

    /**
     * initialising variables
     */
    String clubName;
    String location;

    /**
     * implementing getters and setters for respective variables
     * @return values for each variable
     */
    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * converting object to string value
     * @return the converted value
     */
    @Override
    public String toString() {
        return "SportsClub{" +
                "clubName='" + clubName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}



public class FootballClub1 extends SportsClub1 implements Comparable<FootballClub1>, Serializable {


    /**
     * initialising variables
     */

    String clubName;
    int NumberOfMatchesPlayed;
    double points;
    int goalsScored, goalsConceded;
    int wins, draws, defeats;


    /**
     * implementing getters and setters for respective variables
     * @return values for each variable
     */

    public String getClubName(){

        return clubName;
    }

    public int getNumberOfMatchesPlayed(){

        return NumberOfMatchesPlayed;
    }

    public double getPoints (){
        return points;
    }


    public int getGoalsScored(){

        return goalsScored;
    }

    public int getGoalsConceded(){

        return goalsConceded;
    }

    public int getWins(){
        return wins;
    }

    public int getDraws(){
        return draws;
    }

    public int getDefeats(){
        return defeats;
    }

    public void setClubName(String name){
        this.clubName = name;
    }

    public void setNumberOfMatchesPlayed(int matchesPlayed){
        this.NumberOfMatchesPlayed = matchesPlayed;
    }

    public void setPoints(double overallPoints){
        this.points = overallPoints;
    }


    public void setGoalsScored(int score){
        this.goalsScored = score;
    }

    public void setGoalsConceded(int conceded){
        this.goalsConceded = conceded;
    }

    public void setWins(int victories){
        this.wins = victories;
    }

    public void setDraws(int tie){
        this.draws = tie;
    }

    public void setDefeats(int losses){
        this.defeats = losses;
    }

    @Override
    public String toString(){
        return this.getClubName();
    }


    /**
     * compares the score of one club with another, and returns the difference
     * @param club2
     * @return
     */
    @Override
    public int compareTo(FootballClub1 club2) {

        if (this.getPoints() > club2.getPoints() )
            return -1;
        else
        if (this.getPoints() < club2.getPoints())
            return 1;

        else {
            int goalDifference = this.getGoalsScored() - this.getGoalsConceded();
            int goalDifference2 = club2.getGoalsScored()- club2.getGoalsConceded();

            if (goalDifference> goalDifference2)
                return -1;
            else
            if (goalDifference < goalDifference2)
                return 1;
            else return 0;
        }

    }






}
