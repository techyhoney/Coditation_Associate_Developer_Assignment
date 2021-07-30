public class Cells {

    String name;
    int rowNum;
    int colNum;
    int state;

    Cells(String cellName, int cellState,int rowNumber, int colNumber){
        name=cellName;
        rowNum=rowNumber;
        colNum=colNumber;
        state=cellState;
    }
    Cells(int rowNumber, int colNumber){
        name=String.valueOf(rowNumber)+String.valueOf(colNumber);
        rowNum=rowNumber;
        colNum=colNumber;
        state=0;
    }
    Cells(int cellState,int rowNumber, int colNumber){
        name=String.valueOf(rowNumber)+String.valueOf(colNumber);
        rowNum=rowNumber;
        colNum=colNumber;
        state=cellState;
    }
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

}
