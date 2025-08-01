class Bob {

    String hey(String input) {
        input = input.trim();
        if (input.isEmpty()) return "Fine. Be that way!";
        if (input.matches("(?=.*[A-Z])(?!.*[a-z]).*\\?")) return "Calm down, I know what I'm doing!";
        if (input.endsWith("?")) return "Sure.";
        if (input.matches("(?=.*[A-Z])(?!.*[a-z]).*")) return "Whoa, chill out!";
        return "Whatever.";
    }
}