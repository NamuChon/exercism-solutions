import java.util.Objects;

class Rational {

    private final int numerator, denominator;

    Rational(int numerator, int denominator) {
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        if (numerator == 0) {
            this.numerator = 0;
            this.denominator = 1;
        } else {
            int gcd = Math.abs(Integer.min(numerator, denominator));
            while (numerator % gcd != 0 || denominator % gcd != 0) gcd--;
            this.numerator = numerator / gcd;
            this.denominator = denominator / gcd;
        }
    }

    int getNumerator() {
        return numerator;
    }

    int getDenominator() {
        return denominator;
    }

    Rational add(Rational other) {
        return new Rational(numerator * other.denominator + denominator * other.numerator, denominator * other.denominator);
    }

    Rational subtract(Rational other) {
        return new Rational(numerator * other.denominator - denominator * other.numerator, denominator * other.denominator);
    }

    Rational multiply(Rational other) {
        return new Rational(numerator * other.numerator, denominator * other.denominator);
    }

    Rational divide(Rational other) {
        return new Rational(numerator * other.denominator, denominator * other.numerator);
    }

    Rational abs() {
        return new Rational(Math.abs(numerator), Math.abs(denominator));
    }

    Rational pow(int power) {
        return new Rational((int) Math.pow(power >= 0 ? numerator : denominator, Math.abs(power)), (int) Math.pow((power >= 0 ? denominator : numerator), Math.abs(power)));
    }

    double exp(double exponent) {
        return Math.pow(exponent, (double) numerator / denominator);
    }
    
    @Override
    public String toString() {
        return String.format("%d/%d", this.getNumerator(), this.getDenominator());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Rational other) {
            return this.getNumerator() == other.getNumerator()
                    && this.getDenominator() == other.getDenominator();
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getNumerator(), this.getDenominator());
    }
}
