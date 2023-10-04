class StudyField:
    MECHANICAL_ENGINEERING = 1
    SOFTWARE_ENGINEERING = 2
    FOOD_TECHNOLOGY = 3
    URBANISM_ARCHITECTURE = 4
    VETERINARY_MEDICINE = 5


faculties = []


class Faculty:
    def __init__(self, name: str, abbreviation: str, study_field: StudyField):
        self.name = name
        self.abbreviation = abbreviation
        self.students = []
        self.study_field = study_field

    def __str__(self):
        return f"Faculty Name: {self.name}\nAbbreviation: {self.abbreviation}\nStudy Field: {self.study_field}"

    @staticmethod
    def search_faculty_by_abbreviation(file_name, target_abbreviation):
        try:
            with open(file_name, 'r') as file:
                # Read the header line to skip it
                header = file.readline()

                found_faculty = None

                for line in file:
                    # Split the line into columns using tab as the delimiter
                    columns = line.strip().split('\t')

                    if len(columns) >= 2:
                        # Extract faculty abbreviation and check if it matches the target abbreviation
                        abbreviation = columns[1].strip()
                        if abbreviation == target_abbreviation:
                            # Create and return the faculty object
                            found_faculty = Faculty(columns[0].strip(), abbreviation, None)
                            break

                return found_faculty
        except FileNotFoundError:
            print(f"File '{file_name}' not found.")
            return None