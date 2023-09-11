class Notebook:

	# Class Variable
	product = 'Notebook'

	# The init method or constructor
	def __init__(self, pages, color):

		# Instance Variable
		self.pages = pages
		self.color = color


# Objects of Dog class
first_notebook = Notebook("48", "green")
last_notebook = Notebook("36", "yellow")

print('The first notebook details:')
print("It is a", first_notebook.product)
print('Number of pages: ', first_notebook.pages)
print('Color: ', first_notebook.color)


