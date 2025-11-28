def rebase(input_base: int, digits: list[int], output_base: int) -> list[int]:
    if input_base < 2: raise ValueError("input base must be >= 2")
    if output_base < 2: raise ValueError("output base must be >= 2")
    if not all(0 <= d < input_base for d in digits): raise ValueError("all digits must satisfy 0 <= d < input base")
    
    decimal = sum(d*input_base**i for i, d in enumerate(reversed(digits)))

    result = []
    while decimal != 0:
        result.append(decimal % output_base)
        decimal //= output_base
    return list(reversed(result)) or [0]
