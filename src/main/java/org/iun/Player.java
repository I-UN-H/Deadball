package org.iun;

public class Player {
    String name;
    private String lName;
    private String handedness;
    private int age;
    private String trait1;
    private String trait2;
    private int bt;
    private int obt;

    public Player() {
        String var1 = "";
        this.lName = "";
        int var2 = (int)(Math.random() * (double)9.0F + (double)1.0F);
        int var3 = (int)(Math.random() * (double)9.0F + (double)1.0F);
        String[] var4 = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        String[] var5 = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        var1 = var4[var2];
        this.lName = var5[var3];
        this.name = var1 + " " + this.lName;
        int var6 = (int)(Math.random() * (double)10.0F + (double)1.0F);
        if (var6 <= 6) {
            this.handedness = "R";
        } else if (var6 > 6 && var6 <= 9) {
            this.handedness = "L";
        } else {
            this.handedness = "S";
        }

        this.age = (int)(Math.random() * (double)6.0F + (double)19.0F);
        int var7 = (int)(Math.random() * (double)10.0F + (double)1.0F) + (int)(Math.random() * (double)10.0F + (double)1.0F);
        if (var7 == 2) {
            this.trait1 = "P--";
            this.trait2 = this.trait2Fun(this.trait1);
            if (this.trait1 == this.trait2) {
                this.trait2 = "None";
            } else if (this.trait2 == "P+") {
                this.trait1 = "P-";
                this.trait2 = "None";
            } else if (this.trait2 == "P++") {
                this.trait1 = "None";
                this.trait2 = "None";
            } else if (this.trait2 == "P-") {
                this.trait2 = "None";
            }
        } else if (var7 == 3) {
            this.trait1 = "P-";
            this.trait2 = this.trait2Fun(this.trait1);
            if (this.trait1 == this.trait2) {
                this.trait2 = "None";
            } else if (this.trait2 == "P++") {
                this.trait1 = "P+";
                this.trait2 = "None";
            } else if (this.trait2 == "P+") {
                this.trait1 = "None";
                this.trait2 = "None";
            } else if (this.trait2 == "P--") {
                this.trait1 = "P--";
                this.trait2 = "None";
            }
        } else if (var7 == 4) {
            this.trait1 = "S-";
            this.trait2 = this.trait2Fun(this.trait1);
            if (this.trait1 == this.trait2) {
                this.trait2 = "None";
            } else if (this.trait2 == "S+") {
                this.trait1 = "None";
                this.trait2 = "None";
            }
        } else if (var7 == 5) {
            this.trait1 = "C-";
            this.trait2 = this.trait2Fun(this.trait1);
            if (this.trait1 == this.trait2) {
                this.trait2 = "None";
            } else if (this.trait2 == "C+") {
                this.trait1 = "None";
                this.trait2 = "None";
            }
        } else if (var7 == 6) {
            this.trait1 = "D-";
            this.trait2 = this.trait2Fun(this.trait1);
            if (this.trait1 == this.trait2) {
                this.trait2 = "None";
            } else if (this.trait2 == "D+") {
                this.trait1 = "None";
                this.trait2 = "None";
            }
        } else if (var7 == 15) {
            this.trait1 = "D+";
            this.trait2 = this.trait2Fun(this.trait1);
            if (this.trait1 == this.trait2) {
                this.trait2 = "None";
            } else if (this.trait2 == "D-") {
                this.trait1 = "None";
                this.trait2 = "None";
            }
        } else if (var7 == 16) {
            this.trait1 = "P+";
            this.trait2 = this.trait2Fun(this.trait1);
            if (this.trait1 == this.trait2) {
                this.trait2 = "None";
            } else if (this.trait2 == "P++") {
                this.trait1 = "P++";
                this.trait2 = "None";
            } else if (this.trait2 == "P-") {
                this.trait1 = "None";
                this.trait2 = "None";
            } else if (this.trait2 == "P--") {
                this.trait1 = "P--";
                this.trait2 = "None";
            }
        } else if (var7 == 17) {
            this.trait1 = "C+";
            this.trait2 = this.trait2Fun(this.trait1);
            if (this.trait1 == this.trait2) {
                this.trait2 = "None";
            } else if (this.trait2 == "C-") {
                this.trait1 = "None";
                this.trait2 = "None";
            }
        } else if (var7 == 18) {
            this.trait1 = "S+";
            this.trait2 = this.trait2Fun(this.trait1);
            if (this.trait1 == this.trait2) {
                this.trait2 = "None";
            } else if (this.trait2 == "S-") {
                this.trait1 = "None";
                this.trait2 = "None";
            }
        } else if (var7 == 19) {
            this.trait1 = "T+";
        } else if (var7 == 20) {
            this.trait1 = "P++";
            this.trait2 = this.trait2Fun(this.trait1);
            if (this.trait1 == this.trait2) {
                this.trait2 = "None";
            } else if (this.trait2 == "P-") {
                this.trait1 = "P+";
                this.trait2 = "None";
            } else if (this.trait2 == "P--") {
                this.trait1 = "None";
                this.trait2 = "None";
            } else if (this.trait2 == "P+") {
                this.trait2 = "None";
            }
        } else {
            this.trait1 = "None";
            this.trait2 = "None";
        }

        this.bt = (int)(Math.random() * (double)10.0F + (double)1.0F + (double)((int)(Math.random() * (double)10.0F + (double)1.0F)) + (double)15.0F);
        this.obt = this.bt + (int)(Math.random() * (double)4.0F + (double)1.0F + (double)((int)(Math.random() * (double)4.0F + (double)1.0F)));
    }

    private String trait2Fun(String var1) {
        System.out.println("Pre-redundancy Check for Trait1: " + var1);
        int var2 = (int)(Math.random() * (double)10.0F + (double)1.0F) + (int)(Math.random() * (double)10.0F + (double)1.0F);
        if (var2 == 2) {
            this.trait2 = "P--";
        } else if (var2 == 3) {
            this.trait2 = "P-";
        } else if (var2 == 4) {
            this.trait2 = "S-";
        } else if (var2 == 5) {
            this.trait2 = "C-";
        } else if (var2 == 6) {
            this.trait2 = "D-";
        } else if (var2 == 15) {
            this.trait2 = "D+";
        } else if (var2 == 16) {
            this.trait2 = "P+";
        } else if (var2 == 17) {
            this.trait2 = "C+";
        } else if (var2 == 18) {
            this.trait2 = "S+";
        } else if (var2 == 19) {
            this.trait2 = "T+";
        } else if (var2 == 20) {
            this.trait2 = "P++";
        } else {
            this.trait2 = "None";
        }

        System.out.println("Pre-redundancy Check for Trait2: " + this.trait2);
        return this.trait2;
    }

    public Player(String var1) {
        this.name = var1;
    }

    public int getBT() {
        return this.bt;
    }

    public int getOBT() {
        return this.obt;
    }

    public String getTrait1() {
        return this.trait1;
    }

    public String getTrait2() {
        return this.trait2;
    }

    public String getName() {
        return this.name;
    }

    public String getPlayerData() {
        return "Name: " + this.name + " Handedness: " + this.handedness + " BT/OBT: " + this.bt + "/" + this.obt;
    }

    public String toString() {
        return "Name: " + this.name + "\nAge: " + this.age + "\nHandedness: " + this.handedness + "\nTraits: " + this.trait1 + ", " + this.trait2 + "\nBT/OBT: " + this.bt + "/" + this.obt;
    }
}