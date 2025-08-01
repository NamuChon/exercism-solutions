class Proverb {
    private final String cited;
    Proverb(String[] words) {
        if (words.length == 0) {
            cited = "";
        }
        else if (words.length == 1) {
            cited = "And all for the want of a " + words[0] + ".";
        }
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < words.length - 1; i++) {
                sb.append("For want of a " + words[i] + " the " + words[i + 1] + " was lost.\n");
            }
            cited = sb.toString() + "And all for the want of a " + words[0] + ".";
        }
    }

    String recite() {
        return cited;
    }
}
