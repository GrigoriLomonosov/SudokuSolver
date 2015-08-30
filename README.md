# SudSolverUI
JavaFX application with 49151 sudoku's with 17 clues. The player gets a random sudoku that he can solve.

A solver is included to check the solution of the user. The solver uses 8 algorithms:

candidateChecking
placeFinding
hiddenSingles
nakedTriples
occupyTheorem
swordfish
w-wing
x-wing to minimize the number of possibilities per square or to fill in a certain square.
If the sudoku is not completed by the above 8 algorithms, backtracking is applied.
