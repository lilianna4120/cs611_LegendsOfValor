public class Position{
    // private variables for position class
    private int row;
    private int col;

    // public constructor for position class
    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }

    // accessor methods of position class
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }

    // mutator methods of position class
    public void setRow(int row){
        this.row = row;
    }
    public void setCol(int col){
        this.col = col;
    }
}