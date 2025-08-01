class ComplexNumber {

    private final double real, imaginary;

    ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    double getReal() {
        return real;
    }

    double getImaginary() {
        return imaginary;
    }

    double abs() {
        return Math.sqrt(real*real + imaginary*imaginary);
    }

    ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(real + other.real, imaginary + other.imaginary);
    }

    ComplexNumber subtract(ComplexNumber other) {
        return new ComplexNumber(real - other.real, imaginary - other.imaginary);
    }

    ComplexNumber multiply(ComplexNumber other) {
        return new ComplexNumber(real * other.real - imaginary * other.imaginary, imaginary * other.real + real * other.imaginary);
    }

    ComplexNumber divide(ComplexNumber other) {
        double denominator = other.real*other.real + other.imaginary*other.imaginary;
        return new ComplexNumber((real * other.real + imaginary * other.imaginary) / denominator, (imaginary * other.real - real * other.imaginary) / denominator);
    }

    ComplexNumber conjugate() {
        return new ComplexNumber(real, -imaginary);
    }

    ComplexNumber exponentialOf() {
        double multiplicand = Math.exp(real);
        return new ComplexNumber(multiplicand * Math.cos(imaginary), multiplicand * Math.sin(imaginary));
    }
}