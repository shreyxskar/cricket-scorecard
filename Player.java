public class Player{

    public String name;
    public int fours, sixes, runs, balls;
    public int crease;

    public Player(){
        name = "";
        fours = 0;
        sixes = 0;
        runs = 0;
        balls = 0;
        //Crease => 1- Out, 2 - Not-out, 3 - Did not bat
        crease = 3;
    }

    public void display_stats(){

        float strike_rate;

        if(balls > 0)
            strike_rate = ((float)runs/(float)balls)*100;
        else
            strike_rate = 0.0f;

        String sr = String.format("%.2f", strike_rate);

        if(this.crease == 2)
            System.out.println(name+"*\t"+runs+"("+balls+")\t"+fours+"\t"+sixes + "\t" + sr);
        else
            System.out.println(name+"\t"+runs+"("+balls+")\t"+fours+"\t"+sixes + "\t" + sr);
    }

    public void add_score(int r){
        this.runs += r;
        this.balls += 1;
        
        if(r == 4) this.fours += 1;

        else if(r == 6) this.sixes += 1;
    }

    public void set_crease(int cre){
        this.crease = cre;
    }
}