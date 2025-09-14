def convert(input_grid: list[str]) -> str:
    if len(input_grid) % 4: raise ValueError("Number of input lines is not a multiple of four")
    if any(len(row) % 3 for row in input_grid): raise ValueError("Number of input columns is not a multiple of three")
    
    NUMBERS = [
        [" _ ",
         "| |",
         "|_|"],
        ["   ",
         "  |",
         "  |"],
        [" _ ",
         " _|",
         "|_ "],
        [" _ ",
         " _|",
         " _|"],
        ["   ",
         "|_|",
         "  |"],
        [" _ ",
         "|_ ",
         " _|"],
        [" _ ",
         "|_ ",
         "|_|"],
        [" _ ",
         "  |",
         "  |"],
        [" _ ",
         "|_|",
         "|_|"],
        [" _ ",
         "|_|",
         " _|"]
    ]

    result = ""
    for i in range(0, len(input_grid), 4):
        chunk_row = input_grid[i:i+4]
        for j in range(0, len(chunk_row[0]), 3):
            if (chunk := [row[j:j+3] for row in chunk_row[:-1]]) in NUMBERS:
                result += str(NUMBERS.index(chunk))
            else:
                result += "?"
        result += ","
    return result[:-1]
            
