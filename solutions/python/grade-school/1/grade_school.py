class School:
    def __init__(self):
        self.students = [set() for _ in range(12)]
        self.__added = []

    def add_student(self, name: str, grade: int) -> None:
        if name in self.roster():
            self.__added.append(False)
        else:
            self.students[grade-1].add(name)
            self.__added.append(True)

    def roster(self) -> list[str]:
        return [name for s in self.students for name in sorted(s)]

    def grade(self, grade_number: int) -> list[str]:
        return sorted(self.students[grade_number-1])

    def added(self) -> list[bool]:
        return self.__added
