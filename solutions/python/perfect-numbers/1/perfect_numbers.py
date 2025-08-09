def classify(number: int) -> str:
    """ A perfect number equals the sum of its positive divisors.

    :param number: int a positive integer
    :return: str the classification of the input integer
    """

    if number < 1: raise ValueError("Classification is only possible for positive integers.")

    aliquot_sum = sum(i for i in range(1, number//2 + 1) if number % i == 0)
    return "perfect" if aliquot_sum == number else "abundant" if aliquot_sum > number else "deficient"