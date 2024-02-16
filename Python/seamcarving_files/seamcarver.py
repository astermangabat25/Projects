#!/usr/bin/env python3

from math import sqrt
from picture import Picture

class SeamCarver(Picture):
    ## TO-DO: fill in the methods below
    def energy(self, i: int, j: int) -> float:
        if (i > Picture.width(self)-1 or i < 0) or (j > Picture.height(self)-1 or j < 0):
            raise IndexError(Exception)
        else:
            '''
            Return the energy of pixel at column i and row j
            '''
            #Getting of RGB
            if i == 0: #Leftmost pixels except at corners
                Rr, Rg, Rb = self.get((i + 1, j))
                Lr, Lg, Lb = self.get((Picture.width(self) - 1, j))
                
            elif i == Picture.width(self) - 1: #Rightmost except at corners
                Rr, Rg, Rb = self.get((0, j))
                Lr, Lg, Lb = self.get((i - 1, j))
                
            else: #Every pixel in between
                Rr, Rg, Rb = self.get((i + 1, j))
                Lr, Lg, Lb = self.get((i - 1, j))
                
            if j == 0:
                Dr, Dg, Db = self.get((i, j + 1))
                Ur, Ug, Ub = self.get((i, Picture.height(self)-1))
                
            elif j == Picture.height(self)-1:
                Dr, Dg, Db = self.get((i, 0))
                Ur, Ug, Ub = self.get((i, j - 1))
            
            else:
                Dr, Dg, Db = self.get((i, j + 1))
                Ur, Ug, Ub = self.get((i, j - 1))
            
            #Calculation
            rx, gx, bx = Rr-Lr, Rg-Lg, Rb-Lb
            ry, gy, by = Ur-Dr, Ug-Dg, Ub-Db
            
            deltaX = (rx**2) + (gx**2) + (bx**2)
            deltaY = (ry**2) + (gy**2) + (by**2)
            energy = sqrt(deltaX + deltaY)
            return energy
    
    def find_vertical_seam(self) -> list[int]:      #list of pixels with least energy (1 per row[x-value only])
        '''
        Return a sequence of indices representing the lowest-energy
        vertical seam
        '''
            
        # return verticalSeam
        width = Picture.width(self)
        height = Picture.height(self)

        # Initialize a 2D table to store minimum energy values
        dp_table = [[0] * height for _ in range(width)]

        # Initialize the first column of the table with the energy values of the top row
        for i in range(width):
            dp_table[i][0] = self.energy(i, 0)

        # Populate the rest of the table using dynamic programming
        for j in range(1, height):
            for i in range(width):
                # Handle boundary cases
                if i == 0:
                    dp_table[i][j] = self.energy(i, j) + min(dp_table[i][j - 1], dp_table[i + 1][j - 1])
                elif i == width - 1:
                    dp_table[i][j] = self.energy(i, j) + min(dp_table[i][j - 1], dp_table[i - 1][j - 1])
                else:
                    dp_table[i][j] = self.energy(i, j) + min(dp_table[i][j - 1], dp_table[i - 1][j - 1], dp_table[i + 1][j - 1])

        # Find the index of the minimum energy in the last row
        minimum = dp_table[0][height-1]
        min_index = 0
        for i in range(1, width):
            if minimum > dp_table[i][height-1]:
                minimum = dp_table[i][height-1]
                min_index = i
        
        # Find vertical seam
        vertical_seam = [min_index]     #First instance of the vertical seam
        
        for j in range(height-1, 0, -1):
            if min_index == 0:
                min_index_temp = min_index
                for i in range(2):
                    if min_index + i < width and dp_table[min_index + i][j - 1] < dp_table[min_index_temp][j - 1]:
                        min_index_temp = min_index + i
                min_index = min_index_temp

            elif min_index == width - 1:
                min_index_temp = min_index
                for i in range(2):
                    if min_index - 1 + i >= 0 and min_index - 1 + i < width and dp_table[min_index - 1 + i][j - 1] < dp_table[min_index_temp][j - 1]:
                        min_index_temp = min_index - 1 + i

                min_index = min_index_temp
            else:
                min_index_temp = min_index
                for i in range(3):
                    if min_index - 1 + i >= 0 and dp_table[min_index - 1 + i][j - 1] < dp_table[min_index_temp][j - 1]:
                        min_index_temp = min_index - 1 + i

                min_index = min_index_temp
            vertical_seam.append(min_index)
        return vertical_seam[::-1]
   
    def find_horizontal_seam(self) -> list[int]:        ##list of pixels with least energy (1 per column[y-value only])
        '''
        Return a sequence of indices representing the lowest-energy
        horizontal seam
        '''

        width = Picture.width(self)
        height = Picture.height(self)

        # Initialize a 2D table to store minimum energy values
        dp_table = [[0] * width for _ in range(height)]

        # Initialize the first row of the table with the energy values of the leftmost column
        for j in range(height):
            dp_table[j][0] = self.energy(0, j)

        # Populate the rest of the table using dynamic programming
        for i in range(1, width):
            for j in range(height):
                # Handle boundary cases
                if j == 0:
                    dp_table[j][i] = self.energy(i, j) + min(dp_table[j][i - 1], dp_table[j + 1][i - 1])
                elif j == height - 1:
                    dp_table[j][i] = self.energy(i, j) + min(dp_table[j][i - 1], dp_table[j - 1][i - 1])
                else:
                    dp_table[j][i] = self.energy(i, j) + min(dp_table[j][i - 1], dp_table[j - 1][i - 1], dp_table[j + 1][i - 1])

        # Find the index of the minimum energy in the last column
        minimum = dp_table[0][width-1]
        min_index = 0
        for j in range(1, height):
            if minimum > dp_table[j][width-1]:
                minimum = dp_table[j][width-1]
                min_index = j

        # Horizontal Seam Finding
        horizontal_seam = [min_index]

        for i in range(width - 1, 0, -1):
            if min_index == 0:
                min_index_temp = min_index
                for j in range(2):
                    if min_index + j < height and dp_table[min_index + j][i - 1] < dp_table[min_index_temp][i - 1]:
                        min_index_temp = min_index + j

                min_index = min_index_temp

            elif min_index == height - 1:
                min_index_temp = min_index
                for j in range(2):
                    if min_index - 1 + j >= 0 and dp_table[min_index - 1 + j][i - 1] < dp_table[min_index_temp][i - 1]:
                        min_index_temp = min_index - 1 + j

                min_index = min_index_temp
            else:
                min_index_temp = min_index
                for j in range(3):
                    if min_index - 1 + j >= 0 and min_index - 1 + j < height and dp_table[min_index - 1 + j][i - 1] < dp_table[min_index_temp][i - 1]:
                        min_index_temp = min_index - 1 + j

                min_index = min_index_temp

            horizontal_seam.append(min_index)

        return horizontal_seam[::-1]

        

    def remove_vertical_seam(self, seam: list[int]):
        '''
        Remove a vertical seam from the picture
        '''
        checker = [abs(j-i) for i, j in zip(seam[:-1], seam[1:])]
        if (len(seam) != Picture.height(self) or Picture.width(self)==1 or any(i > 1 for i in checker)):
            raise SeamError(Exception)
        else:
            for j in range(Picture.height(self)):
                i = seam[j]
                while i < Picture.width(self) - 1:
                    self[(i, j)] = self[(i + 1, j)]
                    i += 1
            
            for j in range(Picture.height(self)):
                del self[(Picture.width(self) - 1, j)]
            self._width = self._width-1

    def remove_horizontal_seam(self, seam: list[int]):
        '''
        Remove a horizontal seam from the picture
        '''
        checker = [abs(j-i) for i, j in zip(seam[:-1], seam[1:])]
        if (len(seam) != Picture.width(self) or Picture.height(self)==1 or any(i > 1 for i in checker)):
            raise SeamError(Exception)
        else:
            for i in range(Picture.width(self)):
                j = seam[i]
                while j < Picture.height(self) - 1:
                    self[(i, j)] = self[(i, j + 1)]
                    j += 1
            
            for i in range(Picture.width(self)):
                del self[(i, Picture.height(self)-1)]
            self._height = self._height-1
    
class SeamError(Exception):
    pass

class IndexError(Exception):
    pass


#References
# https://www.geeksforgeeks.org/python-check-values-list-greater-given-value/
# https://stackoverflow.com/questions/2400840/python-finding-differences-between-elements-of-a-list
# https://www.programiz.com/python-programming/methods/dictionary/get
