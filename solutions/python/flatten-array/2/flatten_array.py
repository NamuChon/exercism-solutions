from collections.abc import Iterable
from typing import Any

def flatten(iterable: Iterable[Any]) -> list[Any]:
    result = []
    for element in iterable:
        if isinstance(element, Iterable) and not isinstance(element, str):
            result.extend(flatten(element))
        elif element is not None:
            result.append(element)
    return result
