import java.io.StringReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SquareDetector {
    private final int n;
    private List<List<Cell>> grid;
    private Set<Cell> blackCells;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        for (int i = 1; i <= t; ++i) {
            int n = scanner.nextInt();
            scanner.nextLine();

            StringBuilder grid = new StringBuilder();

            for (int j = 0; j < n; ++j) grid.append(scanner.nextLine());

            boolean result = new SquareDetector(n, new StringReader(grid.toString())).hasOnlyOneFilledSquare();

            System.out.printf("Case #%d: %s\n", i, result ? "YES" : "NO");
        }
    }

    private static boolean isPerfectSquare(int n) {
        int squareRoot = (int) Math.sqrt(n);

        return squareRoot * squareRoot == n;
    }

    public SquareDetector(final int n, final StringReader grid) throws IOException {
        this.n = n;
        this.grid = new ArrayList<>(n);
        blackCells = new HashSet<>();

        for (int i = 0; i < n; ++i) {
            this.grid.add(i, new ArrayList<Cell>(n));

            for (int j = 0; j < n; ++j) {
                Cell cell = new Cell((char) grid.read());
                this.grid.get(i).add(j, cell);

                if (cell.isBlack()) blackCells.add(cell);
            }
        }
    }

    public boolean hasOnlyOneFilledSquare() {
        if (blackCells.isEmpty() || !isPerfectSquare(blackCells.size())) return false;

        int size = (int) Math.sqrt(blackCells.size());
        int firstBlackX = -1;
        int firstBlackY = -1;

        {
            boolean done = false;

            for (int i = 0; !done && i < n; ++i)
                for (int j = 0; !done && j < n; ++j)
                    if (grid.get(i).get(j).isBlack()) {
                        firstBlackX = i;
                        firstBlackY = j;
                        done = true;
                    }
        }

        for (int i = firstBlackX; i < firstBlackX + size; ++i)
            for (int j = firstBlackY; j < firstBlackY + size; ++j) {
                Cell cell = grid.get(i).get(j);

                if (!cell.isBlack()) return false;
                else cell.setVisited(true);
            }

        for (Cell cell : blackCells)
            if (!cell.isVisited())
                return false;

        return true;
    }
}

class Cell {
    private boolean black;
    private boolean visited;

    public Cell(char c) {
        switch (c) {
        case '.':
            black = false;

            break;

        case '#':
            black = true;

            break;

        default:
            throw new IllegalArgumentException(c + " is not '.' or '#'.");
        }
    }

    public boolean isBlack() {
        return black;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}