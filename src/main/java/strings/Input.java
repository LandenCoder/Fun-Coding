package main.java.strings;

import java.util.Scanner;

public class Input {
    public Input() {
    }

    /**
     * @return whatever the user types in to the terminal
     */
    private String getRawInput() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextLine()) {
        }
        String input = scanner.nextLine();
        // scanner.close(); this shut the scanner down for the rest of the program,
        // which is bad if the user types an unusable input
        return input;
    }

    /**
     * gets the reply someone gave to a question you asked in a terminal
     * 
     * @return the reply in a string, with no spaces and all lower case
     */
    public String getReply() {
        String in = getRawInput();
        in = removeSpaces(in);
        in = in.toLowerCase();
        return in;
    }

    /**
     * removes all spaces from a string
     * 
     * @return the spaceless string
     */
    private String removeSpaces(String input) {
        String[] split = input.split(" ");
        return String.join("", split);
    }

    /**
     * gets either a yes or a no answer, or a null is neither
     * 
     * @return "yes", "no", or null
     *         will return "yes" or "no" even if the input onlly starts with y or n
     */
    public String getYesNo() {
        String input = getRawInput();
        input.toLowerCase();
        input = removeSpaces(input);
        if (input.startsWith("y")) {
            return "yes";
        } else if (input.startsWith("n")) {
            return "no";
        }
        return null;
    }
}