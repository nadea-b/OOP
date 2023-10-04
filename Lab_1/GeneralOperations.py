from Faculty import Faculty, faculties
from FileManager import Manager
import sys


class GeneralMenu:

    def __init__(self):
        self.choice = ' '

    def display_menu(self):

        while self.choice != 'q':
            print("\t\t...\nGeneral operations\nWhat do you want to do?\n")
            print("nf/<faculty name>/<faculty abbreviation>/<field> - create faculty")
            print("ss/<student email> - search student and show faculty")
            print("df - display faculties")
            print("da/<field> - display all faculties of a field")
            print("\nb - Back")
            print("q - Quit Program\n")

            self.choice = input("Enter your choice: ")

            if self.choice.startswith("nf/"):
                self.create_faculty()
            elif self.choice.startswith("ss/"):
                self.search_student()
            elif self.choice.startswith("df"):
                initialization = GeneralMenu()
                initialization.display_faculties()
            elif self.choice.startswith("da/"):
                self.display_faculties_by_field()
            elif self.choice.startswith("b"):
                break
            elif self.choice.startswith("q"):
                print("Exiting the program.")
                sys.exit()
            else:
                print("Invalid choice. Please choose a valid option.")

    def create_faculty(self):
        # Split the input string using '/' as a delimiter
        parts = self.choice.split('/')
        # Check if there are enough parts
        if len(parts) == 4:
            _, faculty_name, faculty_abbreviation, field = parts
            students = []

            # Create a Faculty object with the extracted information
            faculty = Faculty(faculty_name, faculty_abbreviation, field)
            faculty.study_field = field

            # Add the Faculty object to the list
            faculties.append(faculty)

            # Notify the user
            print("Faculty added successfully.")

            # Export faculties to a file using the Manager class
            self.export_faculties()

    @staticmethod
    def export_faculties():
        try:
            manager = Manager()
            manager.export_faculties("faculties.txt")
        except IOError as e:
            print(f"Error exporting faculties: {e}")

    @staticmethod
    def display_faculties():
        print("Displaying faculties:")
        try:
            with open("faculties.txt", 'r') as file:
                next(file)  # Skip the header line

                for line in file:
                    columns = line.strip().split('\t')

                    if len(columns) >= 3:
                        faculty_name, faculty_abbreviation, study_field = map(str.strip, columns[:3])
                        print(f"Name: {faculty_name}, Abbreviation: {faculty_abbreviation}, Field: {study_field}")
        except FileNotFoundError:
            print(f"File faculties.txt not found.")

    def search_student(self):
        # Split the input string using '/' as a delimiter
        parts = self.choice.split('/')

        # Check if there are enough parts
        if len(parts) == 2:
            _, email = parts

        def find_student_in_file(file_name):
            with open(file_name, 'r') as file:
                file.readline()  # Read the header line to skip it

                for line in file:
                    columns = line.strip().split('\t')

                    if len(columns) >= 4:  # At least four columns are needed for faculty and email
                        faculty_abbreviation = columns[2].strip()
                        student_email = columns[3].strip()

                        if student_email == email:
                            return faculty_abbreviation, True
                return None, False

        faculty_abbreviation_students, is_student = find_student_in_file("students.txt")
        faculty_abbreviation_graduates, is_graduate = find_student_in_file("graduates.txt")

        if is_student:
            print("The student belongs to the faculty", faculty_abbreviation_students)
        elif is_graduate:
            faculty = Faculty.search_faculty_by_abbreviation("faculties.txt", faculty_abbreviation_graduates)
            if faculty:
                print("The student belongs to the faculty", faculty.name, "and graduated")
            else:
                print("The student belongs to an unknown faculty and graduated")
        else:
            print("No, the student does not belong to any faculty")

    def display_faculties_by_field(self):
        # Split the input string using '/' as a delimiter
        parts = self.choice.split('/')

        # Check if there are enough parts
        if len(parts) != 2:
            print("Invalid input format. Please use 'da/<field>' format.")
            return

        _, field = parts

        matching_faculties = []

        with open("faculties.txt", 'r') as file:
            next(file)  # Skip the header line

            for line in file:
                columns = line.strip().split('\t')

                if len(columns) >= 3 and columns[2].strip() == field:
                    faculty_name, faculty_abbreviation = map(str.strip, columns[:2])
                    matching_faculties.append((faculty_name, faculty_abbreviation))

        if matching_faculties:
            print(f"Faculties in the '{field}' field:")
            for faculty_name, faculty_abbreviation in matching_faculties:
                print(f"Faculty Name: {faculty_name}, Faculty Abbreviation: {faculty_abbreviation}")
        else:
            print(f"No faculties found in the '{field}' field.")
