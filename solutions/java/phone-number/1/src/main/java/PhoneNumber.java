class PhoneNumber {
    private final String number;
    PhoneNumber(String numberString) {
        if (numberString.matches(".*[a-zA-Z].*")) throw new IllegalArgumentException("letters not permitted");
        if (numberString.matches(".*[,!?;:\"'\\[\\]{}<>].*")) throw new IllegalArgumentException("punctuations not permitted");
        
        numberString = numberString.replaceAll("[^0-9]", "");
        final int length = numberString.length();
        
        if (length < 10) throw new IllegalArgumentException("must not be fewer than 10 digits");
        if (length > 11) throw new IllegalArgumentException("must not be greater than 11 digits");
        
        if (length == 11) {
            if (numberString.charAt(0) == '1') numberString = numberString.substring(1);
            else throw new IllegalArgumentException("11 digits must start with 1");
        }
        
        String exceptionCodeType = null;
        String exceptionCodeNumber = null;
        int codeStart = Character.getNumericValue(numberString.charAt(0));
        if (codeStart < 2) exceptionCodeType = "area";
        else {
            codeStart = Character.getNumericValue(numberString.charAt(3));
            if (codeStart < 2) exceptionCodeType = "exchange";
        }
        exceptionCodeNumber = codeStart == 0 ? "zero" : "one";
        if (exceptionCodeType != null) throw new IllegalArgumentException(exceptionCodeType + " code cannot start with " + exceptionCodeNumber);
        number = numberString;
    }

    String getNumber() {
        return number;
    }

}