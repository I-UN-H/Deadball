package org.iun;

import java.io.PrintStream;

public class Bases {
    private Player runner;
    private String name;

    public Bases(String var1, Player var2) {
        this.name = var1;
        this.runner = var2;
    }

    public void updateRunner(Player var1) {
        if (var1.getName() != "") {
            PrintStream var10000 = System.out;
            String var10001 = var1.getName();
            var10000.println(var10001 + " makes it to " + this.name);
        }

        this.runner = var1;
    }

    public void emptyPrev(Bases var1, Player var2) {
        this.updateRunner(var1.getRunner());
        var1.updateRunner(var2);
    }

    public boolean onBase() {
        return this.runner.getName() != "";
    }

    public Player getRunner() {
        return this.runner;
    }

    public String getRunnerName() {
        return this.runner.getName();
    }

    public String getBaseName() {
        return this.name;
    }
}