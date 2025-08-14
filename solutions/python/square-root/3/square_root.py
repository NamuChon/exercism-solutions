def square_root(number: int) -> int:
    start, end = 0, number
    while start <= end:
        mid = (start+end) // 2
        if mid*mid <= number: start = mid + 1
        else: end = mid - 1
    return end
