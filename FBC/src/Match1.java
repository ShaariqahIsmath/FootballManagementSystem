import java.io.Serializable;
import java.time.LocalDate;


public class Match1 implements Serializable {

    /**
     * initializing variables
     */
    FootballClub1 team1;
    FootballClub1 team2;

    int team1Score;
    int team2Score;

    LocalDate date;

    /**
     * implementing getters and setters for respective variables
     * @return values for each variable
     */

    public FootballClub1 getTeam1(){

        return team1;
    }

    public FootballClub1 getTeam2(){

        return team2;
    }

    public int getTeam1Score(){

        return team1Score;
    }

    public int getTeam2Score(){

        return team2Score;
    }

    public LocalDate getDate(){
        return date;
    }


    public void setTeam1(FootballClub1 t1) {

        this.team1 = t1;
    }

    public void setTeam2(FootballClub1 t2) {

        this.team2 = t2;
    }

    public void setTeam1Score(int t1Score) {

        this.team1Score = t1Score;
    }

    public void setTeam2Score(int t2Score) {

        this.team2Score = t2Score;
    }

    public void setDate(LocalDate date){

        this.date = date;
    }

    /**
     * converting object to string value
     * @return the converted value
     */

    @Override
    public String toString(){
        return "TeamA: " + this.getTeam1() + ", TeamB: " +this.getTeam2()+ ", TeamA Score: " + this.getTeam1Score() + ", TeamB Score: " + this.getTeam2Score() ;

    }






}

