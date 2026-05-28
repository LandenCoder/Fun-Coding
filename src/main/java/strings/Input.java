package main.java.strings;

import java.util.Scanner;

public class Input {

    StringTools strings = new StringTools();

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
        in = strings.removeSpaces(in);
        in = in.toLowerCase();
        return in;
    }

    /**
     * gets either a yes or a no answer, or a null is neither
     * 
     * @return "yes", "no", or null
     *         will return "yes" or "no" even if the input onlly starts with y or n
     */
    public String getYesNo() {
        String input = getRawInput();
        input = input.toLowerCase();
        input = strings.removeSpaces(input);
        if (input.startsWith("y")) {
            return "yes";
        } else if (input.startsWith("n")) {
            return "no";
        }
        return null;
    }

    /**
     * @param allowed a string of all the options that are allowed, as one character
     *                for each option
     * @return the input if it as one of the allowed options
     */
    public String getAllowed(String allowed) {
        boolean isDone = false;
        while (!isDone) {
            String input = getRawInput();
            input = input.toLowerCase();
            input = strings.removeSpaces(input);
            for (String option : allowed.split("")) {
                if (input.startsWith(option)) {
                    return option;
                }
            }
            System.out.println("This is not an option. please try again.");
        }
        return null;
    }
}