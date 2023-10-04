from Faculty import faculties
import os


class Manager:
    @staticmethod
    def export_faculties(file_name):
        try:
            with open(file_name, 'a') as file:
                # Write header row (only if the file is empty)
                if file.tell() == 0:
                    file.write("Faculty Name\tFaculty Abbreviation\tField\n")

                # Write faculty information to the file
                for faculty in faculties:
                    students_str = ', '.join(faculty.students) if faculty.students else ""
                    file.write(f"{faculty.name}\t{faculty.abbreviation}\t{faculty.study_field}\t{students_str}\n")
        except IOError as e:
            print(f"Error: {e}")

    @staticmethod
    def export_students(file_name, students, faculty):
        try:
            # Open the file in append mode ('a')
            with open(file_name, 'a') as file:
                # Check if the file is empty (header not written)
                if os.stat(file_name).st_size == 0:
                    # Write header row
                    file.write("First Name\tLast Name\tFaculty Abbreviation\tEmail\tDate of birth\tEnrollment Date\n")

                # Write student information as a table
                for student in students:
                    file.write(
                        f"{student.first_name}\t{student.last_name}\t{faculty.abbreviation}\t"
                        f"{student.email}\t{student.date_of_birth}\t{student.enrollment_date}\n")

            print(f"Student data exported to {file_name}")
        except IOError as e:
            print(f"Error: {e}")
