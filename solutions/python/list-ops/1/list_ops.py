def append(list1, list2):
    for element in list2:
        list1.append(element)
    return list1


def concat(lists):
    result = []
    for list in lists:
        append(result, list)
    return result


def filter(function, list):
    return [element for element in list if function(element)]


def length(list):
    return sum(1 for _ in list)


def map(function, list):
    return [function(element) for element in list]


def foldl(function, list, initial):
    for element in list:
        initial = function(initial, element)
    return initial


def foldr(function, list, initial):
    for element in reverse(list):
        initial = function(initial, element)
    return initial


def reverse(list):
    return list[::-1]
