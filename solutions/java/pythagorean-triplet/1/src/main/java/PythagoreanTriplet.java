import java.util.*;
class PythagoreanTriplet {

    private final int a, b, c;
    
    PythagoreanTriplet(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    static TripletListBuilder makeTripletsList() {
        return new TripletListBuilder();
    }

    @Override
    public boolean equals(Object o) {
        PythagoreanTriplet other = (PythagoreanTriplet) o;
        return a == other.a && b == other.b && c == other.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    static class TripletListBuilder {

        int sum = -1, maxFactor = -1;

        TripletListBuilder thatSumTo(int sum) {
            this.sum = sum;
            return this;
        }

        TripletListBuilder withFactorsLessThanOrEqualTo(int maxFactor) {
            this.maxFactor = maxFactor;
            return this;
        }

        List<PythagoreanTriplet> build() {
            List<PythagoreanTriplet> result = new LinkedList<>();
            if (sum == -1) return List.of();
            if (maxFactor == -1) maxFactor = sum - 3;
            for (int a = 3; a <= maxFactor - 2; a++) {
                for (int b = a + 1; b <= Integer.min(maxFactor - 1, sum / 2 - 1); b++) {
                    int c = sum - a - b;
                    if (c <= maxFactor && a*a + b*b == c*c) result.add(new PythagoreanTriplet(a, b, c));
                }
            }
            return result;
        }

    }

}