class SqueakyClean {
    static String clean(String identifier) {
        StringBuilder sb = new StringBuilder();
        String[] arr = identifier
            .replace(" ", "_")
            .replace("4", "a")
            .replace("3", "e")
            .replace("0", "o")
            .replace("1", "l")
            .replace("7", "t")
            .split("");
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i].equals("-"))
            {
                sb.append(arr[i + 1].toUpperCase());
                i++;
            }
            else
            {
                sb.append(arr[i]);
            }
        }
        return sb.toString().replaceAll("[^a-zA-Z0-9\\s_]", "");
    }
}
