def find(search_list: list[int], value: int) -> int:
    start, end = 0, len(search_list)
    
    while start != end:
        middle = (start+end)//2
        middle_item = search_list[middle]
        if value == middle_item: return middle
        if value > middle_item: start = middle + 1
        else: end = middle
    
    raise ValueError("value not in array")
