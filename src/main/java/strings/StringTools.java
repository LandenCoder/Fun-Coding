package main.java.strings;

public class StringTools {
    public StringTools() {
    }

    /**
     * removes all spaces from a string
     * 
     * @return the spaceless string
     */
    public String removeSpaces(String input) {
        String[] split = input.split(" ");
        return String.join("", split);
    }

    /** @return the first letter of each of the string in a string array */
    public String firstLetterOfEach(String[] input) {
        StringBuilder output = new StringBuilder();
        for (String word : input) {
            if (!word.isEmpty()) {
                output.append(word.charAt(0));
            }
        }
        return output.toString();
    }
}
