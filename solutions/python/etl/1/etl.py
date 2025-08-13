def transform(legacy_data: dict[int, list[str]]) -> dict[str, int]:
    return {letter.lower(): point for point, letters in legacy_data.items() for letter in letters}
