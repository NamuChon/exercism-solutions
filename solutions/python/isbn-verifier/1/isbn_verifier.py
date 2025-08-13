def is_valid(isbn: str) -> bool:
    isbn = isbn.replace("-", "")
    try:
        return len(isbn) == 10 and sum((10 if i == 9 and s == "X" else int(s)) * (10 - i) for i, s in enumerate(isbn)) % 11 == 0
    except ValueError:
        return False