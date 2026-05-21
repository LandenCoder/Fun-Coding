package main.java.runners;

import main.java.strings.Input;

public class RunCode {

    Input input = new Input();
    Introducer introducer = new Introducer();

    String toRun;

    public RunCode() {
    }

    public void runCode() {
        toRun = introducer.getWhatToRun();
        System.out.println(toRun);
    }
}
