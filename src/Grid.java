import java.util.*;

public class Grid {
    int rows;
    int cols;
    int seed;
    Cells[][] grid;
    Map<String, Cells> nameToCellMap;
    public Map<String, Cells> getNameToCellMap() {
        return nameToCellMap;
    }

    public void setNameToCellMap(Map<String, Cells> nameToCellMap) {
        this.nameToCellMap = nameToCellMap;
    }
    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public Cells[][] getGrid() {
        return grid;
    }

    public void setGrid(Cells[][] grid) {
        this.grid = grid;
    }
    public void printGrid(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                System.out.print(grid[i][j].getState());
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    Grid(int r, int c, int s){
        rows=r;
        cols=c;
        seed=s;
        grid = new Cells[r][c];
        nameToCellMap=new HashMap<>();
        initilaiseGrid(r,c);
        fillRandomValue(s);
    }

    public void initilaiseGrid(int r, int c){
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                Cells cell = new Cells(r,c);
                grid[i][j]=cell;
                nameToCellMap.put(String.valueOf(i)+String.valueOf(j),cell);
            }
        }
    }
    public Optional<Cells> checkMap(String name){
        if(nameToCellMap.containsKey(name)){
            return Optional.of(nameToCellMap.get(name));
        }
        return Optional.empty();
    }
    public void mapEntry(String name, Cells c){
        nameToCellMap.put(name,c);
    }
    public void fillRandomValue(int s) {
        Random r = new Random();
        for(int i=0;i<=s;i++){
            int row = r.nextInt(rows);
            int col = r.nextInt(cols);
            Cells cell = grid[row][col];
            cell.setState(1);
        }
    }

   public void nextGeneration()
    {
        for (int l = 0; l < rows ; l++)
        {
            for (int m =0; m < cols; m++)
            {
                int aliveNeighbours = 0;

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if(((l+i) >=0) && ((l+i) < rows) && ((m + j)>=0) && ((m+j)<cols))
                        aliveNeighbours += grid[l + i][m + j].getState();
                    }
                }

                // The cell needs to be subtracted from
                // its neighbours as it was counted before
                aliveNeighbours -= grid[l][m].getState();
                // Implementing the Rules of Life

                // Cell is lonely and dies
                if ((grid[l][m].getState() == 1) && (aliveNeighbours < 2))
                    grid[l][m].setState(0);

                    // Cell dies due to over population
                else if ((grid[l][m].getState() == 1) && (aliveNeighbours > 3))
                    grid[l][m].setState(0);

                    // A new cell is born
                else if ((grid[l][m].getState() == 0) && (aliveNeighbours == 3))
                    grid[l][m].setState(1);
            }
        }
    }
}
