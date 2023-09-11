class Notebook:

	# Class Variable
	product = 'Notebook'

	# The init method or constructor
	def __init__(self, pages, color):

		# Instance Variable
		self.pages = pages
		self.color = color

	def __str__(self):
        return f"The notebook has {self.pages} and has the color {self.color}."


# Objects of Dog class
first_notebook = Notebook("48", "green")
last_notebook = Notebook("36", "yellow")

print('The first notebook details:')
print("It is a", first_notebook.product)
print('Number of pages: ', first_notebook.pages)
print('Color: ', first_notebook.color)

print(first_notebook)
print(last_notebook)

