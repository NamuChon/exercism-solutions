class Darts {
    int score(double xOfDart, double yOfDart) {
        int result;
        double distance = Math.sqrt(xOfDart * xOfDart + yOfDart * yOfDart);
        if (distance <= 1) result = 10;
        else if (distance <= 5) result = 5;
        else if (distance <= 10) result = 1;
        else result = 0;
        return result;
    }
}