def rebase(input_base: int, digits: list[int], output_base: int) -> list[int]:
    if input_base < 2: raise ValueError("input base must be >= 2")
    if output_base < 2: raise ValueError("output base must be >= 2")
    if not all(0 <= d < input_base for d in digits): raise ValueError("all digits must satisfy 0 <= d < input base")
    
    decimal = sum(d*input_base**i for i, d in enumerate(reversed(digits)))
    
    def convert(num: int) -> list[int]:
        return [] if num == 0 else convert(num//output_base) + [num%output_base]
    
    return convert(decimal) or [0]
