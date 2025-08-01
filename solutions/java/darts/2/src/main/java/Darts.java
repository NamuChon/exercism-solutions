class Darts {
    int score(double xOfDart, double yOfDart) {
        double distance = Math.sqrt(xOfDart * xOfDart + yOfDart * yOfDart);
        return distance <= 1 ? 10 : distance <= 5 ? 5 : distance <= 10 ? 1 : 0;
    }
}