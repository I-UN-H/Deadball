package org.iun;
import java.util.Objects;

public class Player {
    String name;
    private String handedness;
    private int age;
    private final String[] traits = new String[20];
    private int bt;
    private int obt;


    public Player() {
        int nameRoll1=(int)(Math.random()*(double)9.0F+(double)1.0F);
        int nameRoll2=(int)(Math.random()*(double)9.0F+(double)1.0F);
        String[] firstNames=new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        String[] lastNames=new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        String fName=firstNames[nameRoll1];
        String lName=lastNames[nameRoll2];
        this.name=fName+" "+lName;
        int var6 = (int)(Math.random() * (double)10.0F + (double)1.0F);
        if(var6 <= 6)
            this.handedness="R";
        else if(var6 <= 9)
            this.handedness="L";
        else
            this.handedness="S";
        this.age = (int)(Math.random() * (double)6.0F + (double)19.0F);
        traits[0]=getTrait();
        if(traits[0]!=null)
            traits[1]=getTrait();
        if(traits[0]!=null&&traits[1]!=null){
            if(Objects.equals(traits[0],"P+")&&traits[1].equals("P+"))
                traits[0]="P++";
            else if(Objects.equals(traits[0],"P-")&&traits[1].equals("P-"))
                traits[0]="P--";
            else if((Objects.equals(traits[0],"P++")&& traits[1].equals("P-"))||(Objects.equals(traits[0],"P-")&& traits[1].equals("P++")))
                traits[0]="P+";
            else if((Objects.equals(traits[0],"P--")&& traits[1].equals("P++"))||(Objects.equals(traits[0],"P+")&& traits[1].equals("P--")))
                traits[0]="P-";
            else if(traits[0].substring(0, 1).equals(traits[1].substring(0, 1)))
                traits[0]=null;
            traits[1]=null;
        }
        if(traits[0]==(null))
            traits[0] = "None";

        this.bt = (int)(Math.random() * (double)10.0F + (double)1.0F + (double)((int)(Math.random() * (double)10.0F + (double)1.0F)) + (double)15.0F);
        this.obt = this.bt + (int)(Math.random() * (double)4.0F + (double)1.0F + (double)((int)(Math.random() * (double)4.0F + (double)1.0F)));
    }

    public String getTrait() {
        int traitRoll = (int) (Math.random() * (double) 10.0F + (double) 1.0F) + (int) (Math.random() * (double) 10.0F + (double) 1.0F);
        String[] traitPicks = {"P--", "P-", "S-", "C-", "D-", "D+", "P+", "C+", "S+", "T+", "P++"};
        if (traitRoll <= 6)
            return (traitPicks[traitRoll - 2]);
        else if (traitRoll >= 15)
            return (traitPicks[traitRoll - 10]);
        else
            return null;
    }
    public Player(String var1) {
        this.name = var1;}
    public boolean findTrait(String trait) {
        if(this.consTraitList().contains(trait)){
            switch (trait) {
                case "P++" -> System.out.println("+2 to HT Roll");
                case "P--" -> System.out.println("-2 to HT Roll");
                case "P+" -> System.out.println("+1 to HT Roll");
                case "P-" -> System.out.println("-1to HT Roll");
                case "D+" -> System.out.println("+1 to DEF Roll (-1 to Stolen base roll if Catcher)");
                case "D-" -> System.out.println("-1 to DEF Roll (+1 to Stolen base roll if Catcher)");
                case "T+" -> System.out.println("Can reroll on injury table!");
                case "S-" -> System.out.println("-2 to Stolen base rolls");
            }
        }
        return this.consTraitList().contains(trait);}
    public boolean findTrait(String trait, int use) {
        if(this.consTraitList().contains(trait)){
            if(trait.equals("C+")&&use==1) //Double occurs
                System.out.println("HT of 1-2, doubles!");
            if(trait.equals("C+")&&use==2) //Bunt
                System.out.println("+1 when rolling to bunt.");
            if(trait.equals("C+")&&use==3) //H&R
                System.out.println("+10 to BT/OBT when rolling for Hit and Run");
            if(trait.equals("C-")&&use==1) //stat change
                System.out.println("-3 to BT/OBT when runner on 2nd/3rd");
            if(trait.equals("C-")&&use==2) //Bunt
                System.out.println("-1 when rolling to bunt");
            if(trait.equals("C-")&&use==3)
                System.out.println("No bonus on a Hit and Run");
            if(trait.equals("S+")&&use==1)
                System.out.println("On HT of 1, doubles!");
            if(trait.equals("S+")&&use==2)
                System.out.println("On HT of 2, triples!");
            if(trait.equals("S+")&&use==3)
                System.out.println("+1 when rolling to steal a base");
            if(trait.equals("S+")&&use==4)
                System.out.println("Can attempt to steal home, On an 8, successfully steal home");
        }
        return this.consTraitList().contains(trait);
    }
    public String consTraitList(){
        StringBuilder list= new StringBuilder();
        for (String trait : traits) {
            if (trait != null)
                list.append(trait).append(", ");
        }
        return list.toString();
    }
    public int getBT() {
        return this.bt;}
    public int getOBT() {
        return this.obt;}
    public String getName() {
        return this.name;}
    public String getPlayerData() {
        return "Name: " + this.name + " Handedness: " + this.handedness + " BT/OBT: " + this.bt + "/" + this.obt;}
    public String toString() {
        return "Name: " + this.name + "\nAge: " + this.age + "\nHandedness: " + this.handedness + "\nTraits: " + this.consTraitList() + "\nBT/OBT: " + this.bt + "/" + this.obt;}}