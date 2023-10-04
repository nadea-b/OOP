from Student import create_student
from Lab_1.FacultyOperations import faculty_menu
from Lab_1.GeneralOperations import general_menu


# TODO move .py files in folders to structure
# TODO Everything should be in a class
def main_menu():
    choice = ' '
    while choice != 'q':
        # move to method
        print("\t\t...\nWelcome to TUM' s student management system!\nWhat do you want to do?\n")
        print("g - General operations")
        print("f - Faculty operations")
        print("s - Student operations")
        print("\nq - Quit Program\n")

        choice = input("your input>")

        if choice.lower() == 'g':
            general_menu()
        elif choice.lower() == 'f':
            faculty_menu()
        elif choice.lower() == 's':
            print("Student operations")
            create_student()
        elif choice.lower() == 'q':
            print("Exiting the program.")
            break
        else:
            print("Invalid choice. Please choose a valid option.")


if __name__ == "__main__":
    main_menu()
