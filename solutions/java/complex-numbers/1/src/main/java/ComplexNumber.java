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
        return new ComplexNumber(real + other.getReal(), imaginary + other.getImaginary());
    }

    ComplexNumber subtract(ComplexNumber other) {
        return new ComplexNumber(real - other.getReal(), imaginary - other.getImaginary());
    }

    ComplexNumber multiply(ComplexNumber other) {
        return new ComplexNumber(real * other.getReal() - imaginary * other.getImaginary(), imaginary * other.getReal() + real * other.getImaginary());
    }

    ComplexNumber divide(ComplexNumber other) {
        double denominator = other.getReal()*other.getReal() + other.getImaginary()*other.getImaginary();
        return new ComplexNumber((real * other.getReal() + imaginary * other.getImaginary()) / denominator, (imaginary * other.getReal() - real * other.getImaginary()) / denominator);
    }

    ComplexNumber conjugate() {
        return new ComplexNumber(real, -imaginary);
    }

    ComplexNumber exponentialOf() {
        double multiplicand = Math.exp(real);
        return new ComplexNumber(multiplicand * Math.cos(imaginary), multiplicand * Math.sin(imaginary));
    }
}