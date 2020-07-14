import java.util.*;

public class Scorecard {

    Scanner x = new Scanner(System.in);

    Player team1[] = new Player[11];
    int num_players, overs, score, wickets, ball, ov;
    String name;  

    public Scorecard(String name, int num_players, int overs){

        this.name = name;
        this.num_players = num_players;
        this.overs = overs;

        for(int i = 0; i < num_players; i++)
            this.team1[i] = new Player();

        this.score = 0;
        this.wickets = 0; 
        this.ball = 0;
        this.ov = 0;
    }    

    public void batting_lineup(){

        System.out.print("Batting Order for Team \'" + this.name + "\': ");
        for(int i = 0; i < num_players; i++){
            team1[i] = new Player();
            team1[i].name = x.next();
        }
    }

    public void innings(){         

        int p1 = 0, p2 = 1, strike = 0;

        team1[p1].set_crease(2);
        team1[p2].set_crease(2);

        while(ov < overs){

            ball = 0;
            System.out.println("Over "+(ov+1));

            while(ball < 6){

                String b = x.next();

                if(b.equals("W")){ 

                    wickets += 1;

                    team1[strike].balls += 1;
                    team1[strike].set_crease(1);

                    if(strike == p1){   
                        p1 = (p1<p2?p2:p1) + 1;
                        strike = p1;
                    }

                    else{
                        p2 = (p2<p1?p1:p2) + 1; 
                        strike = p2;
                    }

                    if(p1 >= num_players || p2 >= num_players)
                        break;

                    team1[p1].set_crease(2);
                    team1[p2].set_crease(2);

                    ball++;
                }

                else if(b.equals("Wd"))
                    score = score + 1;
                
                else{

                    try{
                        int run = Integer.parseInt(b);
                        team1[strike].add_score(run);
                        score += run;
                        if(run%2 == 1) strike = strike==p1?p2:p1;
                        ball++;
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }

            ov++;
            strike = strike==p1?p2:p1;

            System.out.println("Player\tRuns(Balls)\t4s\t6s\tS/R");
            for(int j = 0; j < num_players; j++)
                team1[j].display_stats();
            
            System.out.println("Total : "+score+"/"+wickets);  

            if(p1 >= num_players || p2 >= num_players)
                break;
        }  

        System.out.println("Innings Over!");        
    }    

    public void innings(int target){ 

        int p1 = 0, p2 = 1, strike = 0;

        team1[p1].set_crease(2);
        team1[p2].set_crease(2);

        while(ov < overs){

            ball = 0;
            System.out.println("Over "+(ov+1));

            while(ball < 6){

                if(score > target) break;

                String b = x.next();  

                if(b.equals("W")){   

                    wickets += 1;
                    team1[strike].balls += 1;
                    team1[strike].set_crease(1);

                    if(strike == p1){
                         p1 = (p1<p2?p2:p1) + 1; 
                         strike = p1;
                    }
                    else{ 
                        p2 = (p2<p1?p1:p2) + 1; 
                        strike = p2;
                    }

                    if(p1 >= num_players || p2 >= num_players)
                        break;

                    team1[p1].set_crease(2);
                    team1[p2].set_crease(2);

                    ball++;
                }

                else if(b.equals("Wd"))
                    score = score + 1;                

                else{

                    try{
                        int run = Integer.parseInt(b);
                        team1[strike].add_score(run);
                        score += run;

                        if(run%2 == 1) strike = strike==p1?p2:p1;

                        ball++;
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }

            ov++;
            strike = strike==p1?p2:p1;

            System.out.println("Player\tRuns(Balls)\t4s\t6s\tS/R");
            for(int j = 0; j < num_players; j++)
                team1[j].display_stats();

            System.out.println("Total : "+score+"/"+wickets);  
                      
            if(p1 >= num_players || p2 >= num_players)
                break;
        }  

        System.out.println("Innings Over!");      
    }   

    public void display_score(){

        if(this.ball == 6){
            System.out.println(this.name+":\t"+this.score+"/"+this.wickets + " in " + (this.ov+1) + " overs");
        }

        else{
            System.out.println(this.name+":\t"+this.score+"/"+this.wickets + " in " + this.ov + "." + this.ball + " overs");
        }        
    }
}