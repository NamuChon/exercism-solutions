"""
This exercise stub and the test suite contain several enumerated constants.

Enumerated constants can be done with a NAME assigned to an arbitrary,
but unique value. An integer is traditionally used because it’s memory
efficient.
It is a common practice to export both constants and functions that work with
those constants (ex. the constants in the os, subprocess and re modules).

You can learn more here: https://en.wikipedia.org/wiki/Enumerated_type
"""

from typing import Any

# Possible sublist categories.
# Change the values as you see fit.
SUBLIST = 0
SUPERLIST = 1
EQUAL = 2
UNEQUAL = 3


def sublist(list_one: list[Any], list_two: list[Any]) -> int:
    if list_one == list_two: return EQUAL
    if is_sublist(list_one, list_two): return SUBLIST
    if is_sublist(list_two, list_one): return SUPERLIST
    return UNEQUAL


def is_sublist(list_one: list[Any], list_two: list[Any]) -> bool:
    length = len(list_one)
    return any(list_one == list_two[i:i+length] for i in range(0, len(list_two) - length + 1))
