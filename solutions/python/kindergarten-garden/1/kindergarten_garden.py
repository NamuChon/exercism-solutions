
DEFAULT_STUDENTS = ["Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"]

PLANT_NAMES = {"G": "Grass", "C": "Clover", "R": "Radishes", "V": "Violets"}


class Garden:
    def __init__(self, diagram: str, students: list[str] = DEFAULT_STUDENTS):
        self.__plants = {}
        row1, row2 = diagram.split("\n")
        for i, student in enumerate(sorted(students)):
            plant_i = i*2
            self.__plants[student] = list(map(PLANT_NAMES.get, row1[plant_i:plant_i+2] + row2[plant_i:plant_i+2]))

    def plants(self, student: str):
        return self.__plants[student]
