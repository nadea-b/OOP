from GeneralOperations import GeneralMenu
from FacultyOperations import FacultyMenu
import sys


class University:
    def __init__(self):
        # Define a username and password for login
        self.username = "admin"
        self.password = "password"

    def login(self):
        while True:
            entered_username = input("Enter username: ")
            entered_password = input("Enter password: ")

            if entered_username == self.username and entered_password == self.password:
                print("Login successful!\n")
                return  # Exit the login loop if credentials are correct
            else:
                print("Invalid credentials. Please try again.\n")

    def main_menu(self):
        # Call the login function to validate credentials
        self.login()

        # User is logged in, display the main menu
        choice = ""
        while choice != "q":
            print("\t\t...\nWelcome to TUM' s student management system!\nWhat do you want to do?\n")
            print("g - General operations")
            print("f - Faculty operations")
            print("s - Student operations")
            print("\nq - Quit Program\n")

            choice = input("Your choice: ")

            if choice.lower() == 'g':
                initialization = GeneralMenu()
                initialization.display_menu()
            elif choice.lower() == 'f':
                initialization = FacultyMenu()
                initialization.display_menu()
                # test
                # ns/ME/John/Doe/john.doe@email.com/05/15/2000
                # ns/CA/Alice/Johnson/alice@email.com/12/10/2002
                # ns/ME/Bob/Smith/bob@email.com/08/20/2001
            elif choice.lower() == 's':
                print("Student operations")
            elif choice.lower() == 'q':
                print("Exiting the program.")
                sys.exit()  # This will exit the entire program
            else:
                print("Invalid choice. Please choose a valid option.")


# Create an instance of the University class and start the program
University().main_menu()
