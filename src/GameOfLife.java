import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

// A simple Java program to implement Game of Life
public class GameOfLife
{
    public static void main(String[] args)  throws IOException
    {
        int M = 1001, N = 1001, seed= 200;
        Grid grid = new Grid(M,N,seed);
        System.out.println("Original Generation");
        grid.printGrid();
        grid.nextGeneration();
        System.out.println("First Generation");
        grid.printGrid();

        System.out.println("Press a to add cells, s to search, e to exit!!!");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
         String input = reader.readLine();
        while(!input.toLowerCase().equals("e")){
            switch(input){
                case "a": {
                    System.out.println("Add list of Cells separated by semicolon(;) in format rowNum1,colNum1,name1,state1;rowNum2,colNum2,name2,state2");
                    System.out.println("Note: Rows and Columns should be less than 1000 and max number of cells less than 100 and state should be in 1 and 0");
                    String[] userInput =  reader.readLine().split(";");
                    if(userInput.length > 100){
                        System.out.println("Number of cells greater than 100");
                        break;
                    }
                    for(String cellValues : userInput){
                        String[] cellValuesArray = cellValues.split(",");
                        if(cellValuesArray.length != 4){
                            System.out.println("Invalid Input Format");
                            break;
                        }
                        if(grid.checkMap(cellValuesArray[2]).isPresent()){
                            System.out.println("Please enter unique name!!!");
                            break;
                        }
                        Cells cell = grid.getGrid()[Integer.valueOf(cellValuesArray[0])][Integer.valueOf(cellValuesArray[1])];
                        cell.setName(cellValuesArray[2]);
                        cell.setState(Integer.valueOf(cellValuesArray[3]));
                        grid.mapEntry(cellValuesArray[2],cell);

                    }
                    System.out.println("After Input");
                    grid.printGrid();
                    System.out.println("Next Generation");
                    grid.nextGeneration();
                    grid.printGrid();
                    break;
                }
                case "s": {
                    System.out.println("Please enter name of cell for which state need to be fetched... Or Input RowNumberColumnNumber");
                    System.out.println("So For eg. for 4th row 3rd column cell, either give its name or 43 as input");
                    String userInput =  reader.readLine();
                    Optional<Cells> cellValue=grid.checkMap(userInput);
                    if(!cellValue.isPresent()){
                        System.out.println("Cell with this name doesn't exist..Please try another name!!!");
                        break;
                    }
                    System.out.println("Cell State is:");
                    int state = cellValue.get().getState();
                    String display = state == 0 ? "Dead" : "Alive";
                    System.out.println(display);
                    break;
                }
                case "e": {
                    System.out.println("Game Ended Successfully.Good Bye!!");
                    break;
                }
                default:
                    System.out.println("Wrong Input!!!!. Try Again...");
            }
            System.out.println("Press a to add cells, s to search, e to exit!!!");
            input = reader.readLine();
        }
        System.out.println("Game Ended Successfully.Good Bye!!");
    }
}