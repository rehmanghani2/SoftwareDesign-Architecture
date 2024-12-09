# controller/student_controller.py

from model.student import Student
from view.student_view import StudentView

class StudentController:
    def __init__(self, student, view):
        self._student = student
        self._view = view

    # Update student details
    def set_data(self, name=None, reg_no=None, semester=None, gpa=None):
        if name is not None:
            self._student.set_name(name)
        if reg_no is not None:
            self._student.set_reg_no(reg_no)
        if semester is not None:
            self._student.set_semester(semester)
        if gpa is not None:
            self._student.set_gpa(gpa)

    # Get student details
    def get_data(self):
        return self._student

    # Update view
    def update_student_view(self):
        self._view.show_student_details(self._student)