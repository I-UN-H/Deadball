package org.iun;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    static int score1=0;
    static int score2=0;

    static int teamscore=0;

    static int battingNumb;

    static int outs=0;
    static double inning=1;

    static Player p = new Player("");

    static Bases B1 = new Bases("1st",p);
    static Bases B2 = new Bases("2nd",p);
    static Bases B3 = new Bases("3rd",p);

    static Scanner sr = new Scanner(System.in);

    static Boolean loaded = false;
    public static ArrayList<Bases> diamond = new ArrayList<>();

    public static void main(String[] args){

        diamond.add(B1);
        diamond.add(B2);
        diamond.add(B3);

        int input=0;

        @SuppressWarnings("unused")
        Player batter=p;

        int batterAway=0;
        int batterHome=0;

        @SuppressWarnings("unused")
        int roll=0;

        Player p0 = new Player();
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();
        Player p4 = new Player();
        Player p5 = new Player();
        Player p6 = new Player();
        Player p7 = new Player();
        Player p8 = new Player();

        ArrayList<Player> lineup1 = new ArrayList<>();
        lineup1.add(p0);
        lineup1.add(p1);
        lineup1.add(p2);
        lineup1.add(p3);
        lineup1.add(p4);
        lineup1.add(p5);
        lineup1.add(p6);
        lineup1.add(p7);
        lineup1.add(p8);

        ArrayList<Player> fielding1 = new ArrayList<>();
        fielding1.add(p0);
        fielding1.add(p1);
        fielding1.add(p2);
        fielding1.add(p3);
        fielding1.add(p4);
        fielding1.add(p5);
        fielding1.add(p6);
        fielding1.add(p7);
        fielding1.add(p8);

        Player p10 = new Player();
        Player p11 = new Player();
        Player p12 = new Player();
        Player p13 = new Player();
        Player p14 = new Player();
        Player p15 = new Player();
        Player p16 = new Player();
        Player p17 = new Player();
        Player p18 = new Player();

        ArrayList<Player> lineup2 = new ArrayList<>();
        lineup2.add(p10);
        lineup2.add(p11);
        lineup2.add(p12);
        lineup2.add(p13);
        lineup2.add(p14);
        lineup2.add(p15);
        lineup2.add(p16);
        lineup2.add(p17);
        lineup2.add(p18);

        ArrayList<Player> fielding2 = new ArrayList<>();
        fielding2.add(p10); //P
        fielding2.add(p11); //C
        fielding2.add(p12); //1B
        fielding2.add(p13); //2B
        fielding2.add(p14); //3B
        fielding2.add(p15); //SS
        fielding2.add(p16); //LF
        fielding2.add(p17); //CF
        fielding2.add(p18); //RF

        ArrayList<Player> fieldingTeam = fielding2;
        ArrayList<Player> battingTeam=lineup1;

        while(input!=-1)
        {
            System.out.println("0. "+ p0.name);
            System.out.println("1. "+ p1.name);
            System.out.println("2. "+ p2.name);
            System.out.println("3. "+ p3.name);
            System.out.println("4. "+ p4.name);
            System.out.println("5. "+ p5.name);
            System.out.println("6. "+ p6.name);
            System.out.println("7. "+ p7.name);
            System.out.println("8. "+ p8.name);
            System.out.println("Type the number of a player to check their stats, or type -1 to start the match.");
            input=sr.nextInt();
            if(input!=-1)
                System.out.println(lineup1.get(input));
        }
        System.out.println("\nPlay ball");

        while(inning<=9.5)
        {
            outs=0;
            B1.updateRunner(p);
            B2.updateRunner(p);
            B3.updateRunner(p);
            if(inning%1==.5) {
                teamscore=score2;
                battingNumb=batterHome;
                fieldingTeam=fielding1;
                battingTeam=lineup2;
                System.out.println("Bottom of the "+(int)inning);
            }
            else if(inning%1==0) {
                teamscore=score1;
                fieldingTeam=fielding2;
                battingTeam=lineup1;
                battingNumb=batterAway;
                System.out.println("Top of the "+(int)inning);
            }
            playing(battingTeam,fieldingTeam);
            System.out.println("Changing sides");
            if(inning%1==.5) {
                score2=teamscore;
                batterHome=battingNumb;
            }
            else if(inning%1==0) {
                batterAway=battingNumb;
                score1=teamscore;
            }
            inning+=.5;
        }
    }

    public static void score(Bases base) {
        teamscore++;
        System.out.println(base.getRunnerName()+" makes it home!");
        base.updateRunner(p);
    }

    public static void out(Bases base) {
        int baseNumb=0;
        for(int x=0;x<2;x++) {
            if(diamond.get(x).getBaseName().equals(base.getBaseName()))
                baseNumb=x;
        }
        if(!loaded)
            System.out.println(base.getRunnerName()+" is out at "+diamond.get(baseNumb+1).getBaseName());
        else if(base.getBaseName().equals("1st"))
            System.out.println(base.getRunnerName()+" is out attempting to return to 1st.");
        base.updateRunner(p);
        outs++;
    }

    public static void defTrait(Player fielder, Player batter,boolean single,boolean PE) {
        int roll=(int)(Math.random()*12+1);
        if(fielder.getTrait1().equals("D+") ||fielder.getTrait2().equals("D+")) {//need to work on selecting defense
            System.out.println(fielder.getName()+" has trait D+, +1 to roll");
            roll++;
        }
        else if(fielder.getTrait1().equals("D-") || fielder.getTrait2().equals("D-")) {
            System.out.println(fielder.getName()+" has trait D-, -1 to roll");
            roll--;
        }
        System.out.println("["+roll+"]");
        if(PE) {
            System.out.println("Possible error heading to "+fielder.getName());
            if(roll<3)
                single(batter);
            else {
                System.out.println(batter+" out!");
                outs++;
            }
        }
        else if(single)
            singleDef(roll,batter);
        else
            doubleDef(roll,batter);
    }

    public static void single(Player batter)
    {
        if(B1.onBase() && B2.onBase() && B3.onBase()){//bases loaded
            score(B3);
            B3.updateRunner(B2.getRunner());
            B2.updateRunner(B1.getRunner());
        }
        else if(B1.onBase() && B2.onBase() && !B3.onBase()){//1st and 2nd
            B3.updateRunner(B2.getRunner());
            B2.updateRunner(B1.getRunner());
        }
        else if(B1.onBase() && !B2.onBase() && B3.onBase()){//1st and 3rd
            score(B3);
            B2.updateRunner(B1.getRunner());
        }
        else if(!B1.onBase() && B2.onBase() && B3.onBase()){//2nd and 3rd
            score(B3);
            B2.updateRunner(p);
        }
        else if(B1.onBase() && !B2.onBase() && !B3.onBase())//1st
            B2.updateRunner(B1.getRunner());
        else if(!B1.onBase() && B2.onBase() && !B3.onBase())//2nd
            B3.emptyPrev(B2,p);
        else if(!B1.onBase() && !B2.onBase() && B3.onBase())//3rd
            score(B3);
        B1.updateRunner(batter);
    }

    public static void singleAdv(Player batter){
        if(B3.onBase())
            score(B3);
        if(B2.onBase())
            score(B2);
        if(B1.onBase())
            B3.emptyPrev(B1, p);
        B1.updateRunner(batter);
    }

    public static void singleDef(int roll,Player batter){
        if(roll<=2) {
            System.out.println("Error! Batter safe. Runners advance 2.");
            singleAdv(batter);
        }
        else if(roll<=11) {
            System.out.println("No change!");
            single(batter);
        }
        else {
            System.out.println("Batter out, Runners hold.");
            outs++;
        }
    }

    public static void doubleHit(Player batter) {
        if(B1.onBase() && B2.onBase() && B3.onBase()) {//bases loaded
            score(B3);
            score(B2);
            B3.emptyPrev(B1,p);
        }
        else if(B1.onBase() && B2.onBase() && !B3.onBase()) {//1st and 2nd
            score(B2);
            B3.emptyPrev(B1,p);
        }
        else if(B1.onBase() && !B2.onBase() && B3.onBase()) {//1st and 3rd
            score(B3);
            B3.emptyPrev(B1,p);
        }
        else if(!B1.onBase() && B2.onBase() && B3.onBase()){//2nd and 3rd
            score(B3);
            score(B2);
        }
        else if(B1.onBase() && !B2.onBase() && !B3.onBase())//1st
            B3.emptyPrev(B1,p);
        else if(!B1.onBase() && B2.onBase() && !B3.onBase())//2nd
            score(B2);
        else if(B1.onBase() && !B2.onBase() && B3.onBase())//3rd
            score(B3);
        B2.updateRunner(batter);
    }

    public static void doubleAdv(Player batter) {
        if(B3.onBase())
            score(B3);
        if(B2.onBase())
            score(B2);
        if(B1.onBase())
            score(B1);
        B2.updateRunner(batter);
    }

    public static void doubleDef(int roll,Player batter) {
        if(roll<=2) {
            System.out.println("Error! Batter safe. Runners advance 1.");
            doubleAdv(batter);
        }
        else if(roll<=9) {
            System.out.println("No change!");
            doubleHit(batter);
        }
        else if(roll==10||roll==11) {
            System.out.println("Double turns into single! Runners adv. 2");
            singleAdv(batter);
        }
        else {//hit goes down a level and/or hit turns to out
            System.out.println("Batter out, Runners hold.");
            outs++;
        }
    }

    public static void homer(Player batter) {
        System.out.println(batter.getName()+" has hit the ball and its going, going, going and its gone!!!!!! \n");
        if(B3.onBase())
            score(B3);
        if(B2.onBase())
            score(B2);
        if(B1.onBase())
            score(B1);
        teamscore++;
        System.out.println(batter.getName()+" makes it home!");
    }

    public static void playing(ArrayList<Player>lineup,ArrayList<Player>fielding) {
        while(outs<3) {
            System.out.println(lineup.get(battingNumb).getPlayerData());
            Player batter=lineup.get(battingNumb);
            int roll=(int)(Math.random()*100+1);
            System.out.println(roll);
            if(roll==1 || roll==99) {
                System.out.println("Oddity!");
                roll=(int)(Math.random()*10+1)+(int)(Math.random()*10+1);
                if(roll==2)
                    System.out.println("2");
                if(roll==3)
                    System.out.println("3");
                if(roll==4)
                    System.out.println("4");
                if(roll==5)
                    System.out.println("5");
                if(roll==6)
                    System.out.println("6");
                if(roll==7)
                    System.out.println("7");
                if(roll==8)
                    System.out.println("8");
                if(roll==9)
                    System.out.println("9");
                if(roll==10)
                    System.out.println("10");
                if(roll==11)
                    System.out.println("11");
                if(roll==12)
                    System.out.println("12");
                if(roll==13)
                    System.out.println("13");
                if(roll==14)
                    System.out.println("14");
                if(roll==15)
                    System.out.println("15");
                if(roll==16)
                    System.out.println("16");
                if(roll==17)
                    System.out.println("17");
                if(roll==18)
                    System.out.println("18");
                if(roll==19)
                    System.out.println("19");
                if(roll==20)
                    System.out.println("20");
            }
            else if(roll>=2&&roll<=lineup.get(battingNumb).getBT()||roll<1){
                boolean crit=false;
                int roll2=(int)(Math.random()*20+1);//hit table roll
                if(batter.getTrait1().equals("P++")||batter.getTrait2().equals("P++"))
                    roll+=2;
                else if(batter.getTrait1().equals("P+")||batter.getTrait2().equals("P+"))
                    roll++;
                else if(batter.getTrait1().equals("P-")||batter.getTrait2().equals("P-"))
                    roll--;
                else if(batter.getTrait1().equals("P--")||batter.getTrait2().equals("P--"))
                    roll-=2;
                if((roll>=2 && roll<=5)||roll<1){
                    System.out.println("Critical Hit");
                    crit=true;
                }
                else
                    System.out.println("Hit!");
                System.out.println("["+roll2+"]");
                if(crit) {//TODO get crits working and make sure regular hits still work
                    if(roll2>=1&&roll2<=14) {
                        System.out.println("Single into double!");
                        if(B3.onBase())
                            score(B3);
                        if(B2.onBase())
                            score(B2);
                        if(B1.onBase())
                            B3.emptyPrev(B1,p);
                        B2.updateRunner(batter);
                    }
                    else if(roll2>=15&&roll2<18) {
                        System.out.println("Double into Triple!");
                        if(B3.onBase())
                            score(B3);
                        if(B2.onBase())
                            score(B2);
                        if(B1.onBase())
                            score(B1);
                        B3.updateRunner(batter);
                    }
                }
                if(!crit){
                    if(roll2<=2||(roll2>=7&&roll2<=9)) {//regular clear single
                        System.out.println("Single");
                        single(batter);
                    }
                    else if(roll2==3) {//apply 1st base defense
                        System.out.println("Possible single to 1st base!");
                        defTrait(fielding.get(roll2-1),batter,true,false);
                    }
                    else if(roll2==4) {//apply 2nd base defense
                        System.out.println("Possible single to 2nd base!");
                        defTrait(fielding.get(roll2-1),batter,true,false);
                    }
                    else if(roll2==5) {//apply 3rd base defense
                        System.out.println("Possible single to 3rd base!");
                        defTrait(fielding.get(roll2-1),batter,true,false);
                    }
                    else if(roll2==6) {
                        System.out.println("Possible single to Short Stop!");
                        defTrait(fielding.get(roll2-1),batter,true,false);
                    }
                    else if(roll2<=14) //single, runners adv. 2
                        singleAdv(batter);
                    else if(roll2==15) { //double
                        System.out.println("Possible double to Left Field!");
                        defTrait(fielding.get(roll2-9),batter,false,false);
                    }
                    else if(roll2==16)
                    {
                        System.out.println("Possible double to Center Field!");
                        defTrait(fielding.get(roll2-9),batter,false,false);
                    }
                    else if(roll2==17)
                    {
                        System.out.println("Possible double to Right Field!");
                        defTrait(fielding.get(roll2-9),batter,false,false);
                    }
                    else if(roll2==18)
                    {
                        System.out.println("Hits a double, runners adv. 3");
                        doubleAdv(batter);
                    }
                }
                if(roll2>=19)
                    homer(batter);
            }
            else if(roll>=(lineup.get(battingNumb).getBT())+1 && roll<=(lineup.get(battingNumb).getOBT()))
            {
                System.out.println("Walk!");
                if(B1.onBase())
                    B2.emptyPrev(B1,p);
                else if(B1.onBase() && B2.onBase()&&!B3.onBase()) {
                    B3.emptyPrev(B2,p);
                    B2.emptyPrev(B1,p);
                }
                else if(B1.onBase()&&B2.onBase()&&B3.onBase()){
                    score(B3);
                    B3.emptyPrev(B2,p);
                    B2.emptyPrev(B1,p);
                }
                B1.updateRunner(lineup.get(battingNumb));
            }
            else if(roll>=(lineup.get(battingNumb).getOBT())+1 && roll<=(lineup.get(battingNumb).getOBT()+5)) {//TODO implement PE in a way that yknow... works :)
                System.out.println("Possible Error");
                int fielder=roll%10;
                if(fielder==0||fielder==1)
                    fielder=6;
                else if(fielder==2)
                    fielder=4;
                defTrait(fielding.get(fielder-1),batter,true,true);
            }
            else {
                int fielder=roll%10;
                boolean struck=false;
                if(fielder<=2&&(!Objects.equals(fielding.get(0).getTrait1(), "GB+") || !Objects.equals(fielding.get(0).getTrait2(), "GB+"))) {
                    System.out.println("Strikeout!");
                    struck=true;
                    outs++;
                }
                else if(fielder==3)
                    System.out.println("Heading to 1st Base! "+fielding.get(fielder-1).getName()+" is fielding!");
                else if(fielder==4)
                    System.out.println("Heading to 2nd Base! "+fielding.get(fielder-1).getName()+" is fielding!");
                else if(fielder==5)
                    System.out.println("Heading to 3rd Base! "+fielding.get(fielder-1).getName()+" is fielding!");
                else if(fielder==6)
                    System.out.println("Heading to SS! "+fielding.get(fielder-1).getName()+" is fielding!");
                else if(fielder==7)
                    System.out.println("Heading to LF! "+fielding.get(fielder-1).getName()+" is fielding!");
                else if(fielder==8)
                    System.out.println("Heading to CF! "+fielding.get(fielder-1).getName()+" is fielding!");
                else if(fielder==9)
                    System.out.println("Heading to RF! "+fielding.get(fielder-1).getName()+" is fielding!");
                if(!struck) {
                    if(roll>=(lineup.get(battingNumb).getOBT())+6 && roll<=49){
                        System.out.println(fielding.get(fielder-1).getName()+" catches the ball! "+batter.getName()+" is out!");
                        outs++;
                        if(fielder<=4||fielder>=7){
                            if(B3.onBase())
                                score(B3);
                            if(B2.onBase())
                                B3.emptyPrev(B2,p);
                        }
                        if(fielder<=6&&B1.onBase())
                            B2.emptyPrev(B1, p);
                    }
                    else if(roll>=50 && roll<=69){
                        if(fielder<=4||fielder>=7) {
                            if(B3.onBase())
                                score(B3);
                            if(B2.onBase())
                                B3.emptyPrev(B2,p);
                        }
                        if(fielder<=6&&B1.onBase()) {
                            System.out.println("Fielder's choice!");
                            if(B2.onBase())
                                System.out.println(fielding.get(fielder-1).getName()+" catches the ball! "+batter.getName()+" is out!");
                            else {
                                out(B1);
                                B1.updateRunner(batter);
                            }
                        }
                        else {
                            System.out.println(fielding.get(fielder-1).getName()+" catches the ball! "+batter.getName()+" is out!");
                            outs++;
                        }
                    }
                    else if(roll>=70 && roll<=98){
                        System.out.println(fielding.get(fielder-1).getName()+" catches the ball! "+batter.getName()+" is out!");
                        if(fielder<=6&&B1.onBase()&&!loaded) {
                            System.out.println(fielding.get(fielder-1).getName()+" throws the ball to  "+fielding.get(2).getName());
                            out(B1);
                            System.out.println("Double Play!");
                        }
                        outs++;
                    }
                    else
                    {
                        int outcount=0;
                        if(fielder<=6) {
                            if(B2.onBase()) {
                                out(B2);
                                outcount++;
                            }
                            else if(B1.onBase()) {
                                out(B1);
                                outcount++;
                            }
                        }
                        System.out.println(fielding.get(2).getName()+" catches the ball! "+batter.getName()+" is out at 1st!");
                        if(outcount==1) {
                            System.out.print("Double Play!");
                            if(B2.onBase()||B3.onBase())
                                System.out.println(" Runners hold.");
                            else
                                System.out.println();
                        }
                        outs++;
                    }
                }
            }
            if(battingNumb==8)
                battingNumb=-1;
            battingNumb++;

            System.out.println();
            if(B1.onBase() && B2.onBase() && B3.onBase()) //TODO Summary
                System.out.println("Bases loaded!");
            if(B1.onBase())
                System.out.println(B1.getRunnerName()+" on 1st");
            if(B2.onBase())
                System.out.println(B2.getRunnerName()+" on 2nd");
            if(B3.onBase())
                System.out.println(B3.getRunnerName()+" on 3rd");
            System.out.println("Out Count: "+ outs);
            if(inning%1==0)
                score1=teamscore;
            else
                score2=teamscore;
            System.out.println("Score: "+score1+"-"+score2);
            sr.nextLine();
            @SuppressWarnings("unused")
            String gaming=sr.nextLine();
        }
    }
}

