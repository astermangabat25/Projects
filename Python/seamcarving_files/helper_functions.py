def form_energy_matrix(self) -> list[list[float]]:
    energy_matrix = [[self.energy(i,j) for j in range(Picture.width(self))] for i in range(Picture.height(self))]
    return energy_matrix
    
def transpose(energy_matrix: list[list[float]]) -> list[list[float]]:
    transposed_matrix = list(map(list, zip(*energy_matrix)))
    return transposed_matrix