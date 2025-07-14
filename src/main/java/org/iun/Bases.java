package org.iun;

import java.io.PrintStream;
import java.util.Objects;

public class Bases {
    private Player runner;
    private final String name;

    public Bases(String name, Player runner) {
        this.name = name;
        this.runner = runner;
    }

    public void updateRunner(Player runner) {
        if (!Objects.equals(runner.getName(), ""))
            System.out.println(runner.getName() + " makes it to " + this.name);
        this.runner = runner;
    }

    public void emptyPrev(Bases base, Player empty) {
        this.updateRunner(base.getRunner());
        base.updateRunner(empty);
    }

    public boolean onBase(boolean summary) {
        if(summary&&!Objects.equals(runner.getName(), ""))
            System.out.println(runner.getName()+" is on "+this.name);
        return !Objects.equals(this.runner.getName(), "");
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