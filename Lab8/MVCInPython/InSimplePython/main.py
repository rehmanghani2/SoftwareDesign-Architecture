# main.py

from model.student import Student
from view.student_view import StudentView
from controller.student_controller import StudentController

def main():
    # Create students
    student1 = Student("Rehman Ghani", "FA22-BSE-099", 5, 3.84)
    student2 = Student("Zain", "FA22-BSE-069", 5, 3.00)
    student3 = Student("Usman", "FA22-BSE-053", 5, 2.99)

    # Create a view
    student_view = StudentView()

    # Create controllers for each student
    controller1 = StudentController(student1, student_view)
    controller2 = StudentController(student2, student_view)
    controller3 = StudentController(student3, student_view)

    # Display details of students
    print("\nDetails of Student 1:")
    controller1.update_student_view()

    print("\nDetails of Student 2:")
    controller2.update_student_view()

    print("\nDetails of Student 3:")
    controller3.update_student_view()

    # Update student details
    print("\nUpdating Student 3 GPA to 3.10...")
    controller3.set_data(gpa=3.10)
    print("\nUpdated Details of Student 3:")
    controller3.update_student_view()

if __name__ == "__main__":
    main()