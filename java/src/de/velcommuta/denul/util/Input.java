package de.velcommuta.denul.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Utility functions for interactive input from STDIN
 */
public class Input {
    /**
     * Read a single line from STDIN
     * @return The line, or null in case of an error
     */
    @NotNull
    private static String read() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Read until empty line is found
        try {
            return br.readLine();
        } catch (IOException e) {
            return "";
        }
    }


    /**
     * Read input from STDIN and make sure it's a number
     * @return The number that was entered
     */
    private static int readInt() {
        String rep = read();
        try {
            return Integer.parseInt(rep);
        } catch (NumberFormatException e) {
            System.out.print("That's not even a number. Try again: ");
            return readInt();
        }
    }


    /**
     * Read multiple lines from STDIN, terminated by an empty line
     * @param prompt The message that should be displayed as a question
     * @return The input lines
     */
    @NotNull
    public static String readLines(String prompt) {
        System.out.println(prompt + ":");
        System.out.println("(Finish your input with an empty line)");
        // Prepare variables
        StringBuilder input = new StringBuilder();
        String line;
        // Read until empty line is found
        while (!(line = read()).equals("")) {
            if (input.length() != 0) {
                input.append("\n");
            }
            input.append(line);
        }
        // return the result
        return input.toString();
    }


    /**
     * Read a single line from standard input
     * @param prompt The question to display. Will be postfixed with a ": " and displayed as the question
     * @return The input string
     */
    @NotNull
    public static String readLine(String prompt) {
        System.out.print(prompt + ": ");
        String line = read();
        if (line != null) return line;
        return "";
    }


    /**
     * Offer a number of options to the user and ask for a selection
     * @param prompt The prompt to display
     * @param options The options
     * @return The index of the selected option
     */
    public static int readSelection(String prompt, String[] options) {
        System.out.println(prompt);
        for (int i = 1; i <= options.length; i++) {
            System.out.println("  (" + i + ") " + options[i-1]);
        }
        System.out.print("Please select an option: ");
        int selection = readInt();
        while (selection - 1 >= options.length || selection - 1 < 0) {
            System.out.print("That's not an option. Try again: ");
            selection = readInt();
        }
        return selection - 1;
    }


    /**
     * Ask the user a yes/no question.
     * @param prompt The question. Will be postfixed with a "(y/n)"
     * @return True if the user selected y[es], false otherwise
     */
    public static boolean yes(String prompt) {
        System.out.print(prompt + " (y/n) ");
        String reply = read().toLowerCase();
        while (!(reply.equals("y") || reply.equals("n") || reply.equals("yes") || reply.equals("no"))) {
            reply = read().toLowerCase();
        }
        return (reply.equals("y") || reply.equals("yes"));
    }
}