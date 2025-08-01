class Bob {

    String hey(String input) {
        if (input.matches("[^a-z]*[A-Z]+[^a-z]*\\?\\s*")) return "Calm down, I know what I'm doing!";
        if (input.matches("(?s).*\\?\\s*")) return "Sure.";
        if (input.matches("[^a-z]*[A-Z]+[^a-z]*")) return "Whoa, chill out!";
        if (input.matches("\\s*")) return "Fine. Be that way!";
        return "Whatever.";
    }
}