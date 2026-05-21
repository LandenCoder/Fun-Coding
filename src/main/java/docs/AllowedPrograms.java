package main.java.docs;

public class AllowedPrograms {

    // Have ALL options be completely lowercase with no spaces,
    // and have ALL the runnable programs in this array.
    private static String[] programList = new String[] { "textgame", "sayhi" };
    private static Runnable[] programs = new Runnable[] { new main.java.programs.textGame.TextGame()::runGame,
            new main.java.programs.SayHi()::sayHi };

    public static String[] allowedProgramList() {
        return programList;
    }

    public static Runnable[] allowedProgramsRunnable() {
        return programs;
    }
}