class Markdown {

    String parse(String markdown) {
        markdown = markdown.replaceAll("__(.+)__", "<strong>$1</strong>").replaceAll("_(.+)_", "<em>$1</em>");
        String[] lines = markdown.split("\n");
        StringBuilder result = new StringBuilder();
        boolean activeList = false;

        for (int i = 0; i < lines.length; i++) {
            String theLine = lines[i];
            if (theLine.startsWith("#")) {
                int count = theLine.replaceAll("^(#*).*", "$1").length();
                theLine = count == 0 || count > 6 ? parseParagraph(theLine) : parseHeader(theLine, count);
            } else if (theLine.startsWith("*")) {
                theLine = parseListItem(theLine);
            } else {
                theLine = parseParagraph(theLine);
            }

            if (theLine.matches("(<li>).*") && !activeList) {
                activeList = true;
                result.append("<ul>");
            } else if (!theLine.matches("(<li>).*") && activeList) {
                activeList = false;
                result.append("</ul>");
            }
            result.append(theLine);
        }

        if (activeList) {
            result.append("</ul>");
        }

        return result.toString();
    }

    private String parseHeader(String markdown, int count) {
        return "<h" + count + ">" + markdown.substring(count + 1) + "</h" + count + ">";
    }

    private String parseListItem(String markdown) {
        return "<li>" + markdown.substring(2) + "</li>";
    }

    private String parseParagraph(String markdown) {
        return "<p>" + markdown + "</p>";
    }
}
