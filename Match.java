import java.util.*;
public class Match {

    static Scanner xx;
    
    public static void main(String args[])throws InterruptedException{

        try{
            xx = new Scanner(System.in);

            System.out.print("No. of Players in each team: ");
            int players = xx.nextInt();

            System.out.print("No. of Overs to be played by each team: ");
            int overs = xx.nextInt();

            System.out.println("Enter Team Names separated by newline:");
            String team1 = xx.next();
            String team2 = xx.next();
        
            Scorecard a_team = new Scorecard(team1, players, overs);
            a_team.batting_lineup();

            Scorecard b_team = new Scorecard(team2, players, overs);
            b_team.batting_lineup();

            System.out.print("Press F to flip the coin (Winning Team bats first) : ");
            String c = xx.next();

            if(!c.equalsIgnoreCase("f")){ 
                xx.close(); 
                System.out.println("Match Abandoned!"); 
                return;
            }

            int toss = (int) (((int)(Math.random()*100))%2);

            if(toss == 1){
                Scorecard tmpScorecard = a_team;
                a_team = b_team;
                b_team = tmpScorecard;            
            }
        
            System.out.println(a_team.name + " won the toss and will bat first.");   
            Thread.sleep(2000);     

            a_team.innings();        
            b_team.innings(a_team.score);
        
            match_result(a_team, b_team);
        }

        catch(Exception e){
            System.out.println(e);
            System.out.println("Program exited safely.");
        }

        finally{
            xx.close();
        }        
    }

    public static void match_result(Scorecard a, Scorecard b){

        System.out.print("Result >> ");

        if(a.score > b.score) 
            System.out.println(a.name + " defeated " + b.name + " by " + (a.score-b.score) + " runs.");

        else if(a.score < b.score) 
            System.out.println(b.name + " defeated " + a.name + " by " + (b.num_players-b.wickets) + " wickets.");

        else 
            System.out.println("Match Tied.");

        a.display_score();
        b.display_score();
    }
}