# view/student_view.py

class StudentView:
    @staticmethod
    def show_student_details(student):
        print("---------- STUDENT INFO -----------")
        print(f"Name: {student.get_name()}")
        print(f"Reg No: {student.get_reg_no()}")
        print(f"Semester: {student.get_semester()}")
        print(f"GPA: {student.get_gpa()}")