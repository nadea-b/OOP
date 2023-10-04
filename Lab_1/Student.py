import Date

students_list = []
graduates_list = []


class Student:
    def __init__(self, first_name: str, last_name: str, email: str, enrollment_date: Date, date_of_birth: Date):
        self.first_name = first_name
        self.last_name = last_name
        self.email = email
        self.enrollment_date = enrollment_date
        self.date_of_birth = date_of_birth
