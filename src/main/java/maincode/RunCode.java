package main.java.maincode;

import main.java.strings.Input;

public class RunCode {

    Input input = new Input();
    Introducer introducer = new Introducer();
    String[] allowedPrograms = AllowedPrograms.allowedProgramList();
    Runnable[] programs = AllowedPrograms.allowedProgramsRunnable();

    String toRun;

    public RunCode() {
    }

    private void printLines(double num) {
        for (int i = 0; i < num; i++) {
            System.out.println();
        }
    }

    public void runCode() {
        printLines(2);
        toRun = introducer.getWhatToRun(); // Gets the user input of what program to run
        // for each member in the allowed programs, if it matches that program, run it
        for (int i = 0; i < allowedPrograms.length; i++) {
            if (toRun.equals(allowedPrograms[i])) {
                printLines(2);
                System.out.println("Running " + allowedPrograms[i] + "...");
                printLines(1);
                programs[i].run();
            }
        }
        printLines(1);
        System.out.println("Thank you for using the Fun-Coding workspace! See you next time!");
    }
}
