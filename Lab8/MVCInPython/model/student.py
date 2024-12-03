# model/student.py

class Student:
    def __init__(self, name, reg_no, semester, gpa):
        self._name = name
        self._reg_no = reg_no
        self._semester = semester
        self._gpa = gpa

    # Setters
    def set_name(self, name):
        self._name = name

    def set_reg_no(self, reg_no):
        self._reg_no = reg_no

    def set_semester(self, semester):
        self._semester = semester

    def set_gpa(self, gpa):
        self._gpa = gpa

    # Getters
    def get_name(self):
        return self._name

    def get_reg_no(self):
        return self._reg_no

    def get_semester(self):
        return self._semester

    def get_gpa(self):
        return self._gpa
