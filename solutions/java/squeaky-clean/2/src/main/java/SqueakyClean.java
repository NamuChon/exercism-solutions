class SqueakyClean {
    static String clean(String identifier) {
        StringBuilder sb = new StringBuilder();
        char[] arr = identifier
            .replace(" ", "_")
            .replace("4", "a")
            .replace("3", "e")
            .replace("0", "o")
            .replace("1", "l")
            .replace("7", "t")
            .toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '-' && i != arr.length - 1) {
                char next = arr[i + 1];
                sb.append(Character.toUpperCase(next));
                i++;
            } else {
                sb.append(arr[i]);
            }
        }
        return sb.toString().replaceAll("[^a-zA-Z0-9\\s_]", "");
    }
}
