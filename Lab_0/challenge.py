class Diamond:
    # A sample method
    def fun(self, size):
        for x in range(size):
            for y in range(size):
                if (x + y < size / 2 - 1) or (x - y >= size / 2) or (-x+y > size/2) or (x + y >= 3 * size / 2 - 1):
                    print("   ", end="")
                else:
                    print("*  ", end="")
            print()


# Driver code
# Object instantiation
Cristal = Diamond()

# Accessing class attributes
# and method through objects
Cristal.fun(11)