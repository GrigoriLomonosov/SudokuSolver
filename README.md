# SudSolverUI
Solves a sudoku. An 81 character string, consisting only out of the numbers 0-9, should be given as parameter. When the sudoku has a solution the solution is printed to the Stdout. The solver uses 8 algorithms:

candidateChecking
placeFinding
hiddenSingles
nakedTriples
occupyTheorem
swordfish
w-wing
x-wing to minimize the number of possibilities per square or to fill in a certain square.
If the sudoku is not completed by the above 8 algorithms, backtracking is applied.

Everything is implemented with a javaFX UI.
