from Student import Student, students_list, graduates_list
from Faculty import faculties, Faculty
from FileManager import Manager
from Date import Date
import datetime
import sys
import os


class FacultyMenu:
    def __init__(self):
        self.choice = ' '
        self.faculties = faculties

    def display_menu(self):
        while self.choice != 'q':
            print("\t\t...\nFaculty operations\nWhat do you want to do?\n")
            print("ns/<faculty abbreviation>/<first name>/<last name>/<email>/<day>/<month>/<year> - create a student")
            print("gs/<email> - graduate student")
            print("ds/<faculty abbreviation> - display enrolled students")
            print("dg/<faculty abbreviation> - display graduated students")
            print("bf/<faculty abbreviation>/<email> - check if student belongs to faculty")
            print("\nb - Back")
            print("q - Quit Program\n")

            self.choice = input("Enter your choice: ")

            if self.choice.startswith("ns/"):
                self.add_student()
            elif self.choice.startswith("gs/"):
                self.graduate_student()
            elif self.choice.startswith("ds/"):
                self.display_enrolled_students()
            elif self.choice.startswith("dg/"):
                self.display_graduated_students()
            elif self.choice.startswith("bf/"):
                self.check_faculty()
            elif self.choice.startswith("b"):
                break
            elif self.choice.startswith("q"):
                print("Exiting the program.")
                sys.exit()
            else:
                print("Invalid choice. Please choose a valid option.")

    def add_student(self):
        parts = self.choice.split('/')

        if len(parts) != 8:
            print(
                "Invalid input. Expected: ns/<faculty abbreviation>/<first name>/<last name>/<email>/<day>/<month>/<year>")
            return

        _, faculty_abbr, first_name, last_name, email, day, month, year = parts

        try:
            day, month, year = int(day), int(month), int(year)
            date_of_birth = Date(day, month, year)
        except ValueError:
            print("Invalid date format. Please provide day, month, and year as integers.")
            return

        current_datetime = datetime.date.today()
        current_date = Date(current_datetime.day, current_datetime.month, current_datetime.year)

        # Create a new student and add it to the faculty
        new_student = Student(first_name, last_name, email, current_date, date_of_birth)

        file_name = "faculties.txt"
        faculty = Faculty.search_faculty_by_abbreviation(file_name, faculty_abbr)

        if faculty:
            faculty.students.append(new_student)
            students_list.append(new_student)

            # Export students and faculties
            manager = Manager()
            manager.export_students("students.txt", faculty.students, faculty)
            manager.export_faculties("faculties.txt")

            print(f"Student added to faculty: {faculty.name}")
        else:
            print(f"Faculty with abbreviation {faculty_abbr} not found.")

    def graduate_student(self):
        parts = self.choice.split('/')

        if len(parts) != 2:
            print("Invalid input. Expected: gs/<email>")
            return

        _, email = parts

        with open("students.txt", 'r') as students_file:
            lines = students_file.readlines()

        # Initialize lists to store students data
        updated_students = []
        graduates_data = []

        # Iterate through the lines, skipping the header (start from index 1)
        header = lines[0]
        is_graduates_file_empty = os.stat("graduates.txt").st_size == 0

        for line in lines[1:]:
            # Split the line into columns using tab as the delimiter
            columns = line.strip().split('\t')

            if len(columns) >= 6:
                student_name, student_last_name, faculty_abbreviation, student_line_email, student_date_of_birth, student_enrollment_date = map(
                    str.strip, columns)

                # Check if the student should be graduated based on email
                if student_line_email == email:
                    graduates_data.append(
                        f"{student_name}\t{student_last_name}\t{faculty_abbreviation}\t{student_line_email}\t{student_date_of_birth}\t{student_enrollment_date}\n")
                    graduate_student = Student(student_name, student_last_name, email, student_date_of_birth,
                                               student_enrollment_date)
                    graduates_list.append(graduate_student)
                else:
                    updated_students.append(line)

        # Write the updated students data back to the "students.txt" file
        with open("students.txt", 'w') as students_file:
            students_file.write(header)
            students_file.writelines(updated_students)

        # Append graduates data to the "graduates.txt" file
        with open("graduates.txt", 'a') as graduates_file:
            if is_graduates_file_empty:
                graduates_file.write(header)
            graduates_file.writelines(graduates_data)

        if graduates_data:
            print("Student graduated and removed from 'students.txt'")
        else:
            print(f"Student with email '{email}' not found in the 'students.txt' file.")

    def display_enrolled_students(self):
        parts = self.choice.split('/')

        if len(parts) == 2:
            _, abbreviation = parts

        with open("students.txt", 'r') as students_file:
            lines = students_file.readlines()

        # Iterate through the lines, skipping the header (start from index 1)
        for line in lines[1:]:
            # Split the line into columns using tab as the delimiter
            columns = line.strip().split('\t')

            if len(columns) >= 3:
                student_name = columns[0].strip()
                student_last_name = columns[1].strip()
                faculty_abbreviation = columns[2].strip()
                student_email = columns[3].strip()
                student_date_of_birth = columns[4].strip()
                student_enrollment_date = columns[5].strip()

                if faculty_abbreviation == abbreviation:
                    student_data = f"Name: {student_name}\t{student_last_name}\tEmail: {student_email}\tBirth date:{student_date_of_birth}\tEnrollment date:{student_enrollment_date}"  # Concatenate the first two columns
                    print(student_data)

    def display_graduated_students(self):
        parts = self.choice.split('/')

        if len(parts) == 2:
            _, abbreviation = parts

        with open("graduates.txt", 'r') as students_file:
            lines = students_file.readlines()

        # Iterate through the lines, skipping the header (start from index 1)
        for line in lines[1:]:
            # Split the line into columns using tab as the delimiter
            columns = line.strip().split('\t')

            if len(columns) >= 3:
                student_name = columns[0].strip()
                student_last_name = columns[1].strip()
                faculty_abbreviation = columns[2].strip()
                student_email = columns[3].strip()
                student_date_of_birth = columns[4].strip()
                student_enrollment_date = columns[5].strip()

                if faculty_abbreviation == abbreviation:
                    student_data = f"Name: {student_name},\t{student_last_name},\tEmail: {student_email},\tBirth date:{student_date_of_birth},\tEnrollment date:{student_enrollment_date}"
                    print(student_data)

    def check_faculty(self):
        parts = self.choice.split('/')

        if len(parts) != 3:
            print("Invalid input. Expected: bf/<faculty abbreviation>/<email>")
            return

        _, abbreviation, email = parts

        found_student = False  # Initialize a flag to track whether a match is found

        def check_student_in_file(file_name):
            with open(file_name, 'r') as file:
                next(file)  # Read the header line and skip it

                for line in file:
                    # Split the line into columns using tab as the delimiter
                    columns = line.strip().split('\t')

                    if len(columns) >= 3:
                        faculty_abbreviation = columns[2].strip()
                        student_email = columns[3].strip()

                        if faculty_abbreviation == abbreviation and student_email == email:
                            return True

            return False

        if check_student_in_file("students.txt"):
            print("Yes, the student belongs to that faculty")
            found_student = True
        elif check_student_in_file("graduates.txt"):
            print("Yes, the student belongs to that faculty, and is a graduate")
            found_student = True

        if not found_student:
            print("No, the student does not belong to the faculty")
