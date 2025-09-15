class School:
    def __init__(self):
        self.students = {}
        self.__added = []

    def add_student(self, name: str, grade: int) -> None:
        if name in self.roster():
            self.__added.append(False)
        else:
            self.students[grade] = self.students.get(grade, []) + [name]
            self.__added.append(True)

    def roster(self) -> list[str]:
        return [name for g in sorted(self.students.keys()) for name in self.grade(g)]

    def grade(self, grade_number: int) -> list[str]:
        return sorted(self.students.get(grade_number, []))

    def added(self) -> list[bool]:
        return self.__added
