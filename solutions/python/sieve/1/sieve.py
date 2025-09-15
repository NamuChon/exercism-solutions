def primes(limit: int) -> list[int]:
    marked = [False for _ in range(limit+1)]
    result = []
    for i in range(2, limit+1):
        if not marked[i]:
            result.append(i)
            marked[::i] = [True] * len(marked[::i])
    return result
            
