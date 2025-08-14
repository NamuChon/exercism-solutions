def square_root(number: int) -> int:
    start, end = 0, number//2 + 1
    while start <= end:
        mid = (start+end) // 2
        squared = mid*mid
        if squared == number: return mid
        if squared < number: start = mid + 1
        else: end = mid - 1
    return end
