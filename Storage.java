
/**
 * This class represents a storage facility and calculates the area
 * of the cross-section of the cavern in the storage facility.
 * 
 * @author Maxime Sotsky 3637964
 */
import java.util.*;
import java.io.*;

public class Storage {
    private int valve;
    /** number of rows in storage facility graph */
    private int numRow;
    /** number of columns in storage facility graph */
    private int numCol;
    /** the storage facility graph */
    private int[][] cross;
    /**
     * Constructor for storage facility
     * @param file - file containing graph to be read by scanner to construct
     * storage facility.
     * @throw - exception if file is not found
     */
    public Storage(File file) throws FileNotFoundException {

        Scanner sc = new Scanner(file);
        numRow = sc.nextInt();
        numCol = sc.nextInt();
        cross = new int[numRow][numCol];

        for(int i = 0; i < numRow; i++){
            for(int j = 0; j < numCol; j++){
                cross[i][j] = sc.nextInt();
                if(i == 0 && cross[i][j] == 0){
                    valve = j;
                }
            }
        }
        sc.close();
    }

    /** Method to display storage facility in tabular form */
    public void show(){
        for(int[] x : this.cross){
            for(int y : x){
                System.out.print(y + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    /**
     * Method to find the valve/opening location of the storage facility cavern
     * and sets its value to 3, meaning it has been processed in finding the area
     * assuming it will always be found within the first row of the graph.
     * In addition, this method provides a starting point for finding the area
     * of the cavern.
     * @return true if a valve is found, false otherwise (pre-caution)
     */
    public int getValve(){
        return this.valve;
    }
    /**
     * Method to find area of the convern, calls recursive private helper method
     * @return -integer represeting the area of the cavern
     */

    public int findArea(){
        this.cross[0][getValve()] = 3;
        return findAreaRec(1,0, this.cross, 1);
    }
    /**
     * recursive method to find the area of the cavern
     * @return -integer represeting the area of the
     * @param r - represents the row of 2d array
     * @param c - represents the column of 2d array
     * @param cross -represents the facility where the cavern is located
     * @param count - counts the area of the cavern
     */
    private int findAreaRec(int r, int c, int[][] cross, int count){
        if(r == cross.length){
            return count;
        }
        if(c == cross[0].length){
            return findAreaRec(++r, 0, cross, count);
        }
        if(cross[r][c] == 0 && checkNeighbors(r,c)){
            cross[r][c] = 3;
            c = 0;
            r = 0;
            count++;
        }
        return findAreaRec(r, ++c, cross, count);
    }
    /**
     * checks the neighbors of a certain index inside the matrix
     * checks left right down up - not including diagnals
     * @return boolean if a valid neighbor is found
     */
    private boolean checkNeighbors(int x, int y){
        if(checkLeft(x,y))
            return true;
        else if(checkRight(x,y))
            return true;
        else if(checkDown(x,y))
            return true;
        else if(checkUp(x,y))
            return true;
        else
            return false;
    }
    /**
     * check the left neighbor method
     * @param x - the row index of current index
     * @param y - the column index of current index
     * @return true if the left neighbor is valid
     */
    private boolean checkLeft(int x, int y){
        if(y-1 >= 0){
            if(this.cross[x][y-1] == 3){
                return true;
            }
        }
        return false;
    }
    /**
     * check right neighbor method
     * @param x - the row index of current index
     * @param y - the column index of current index
     * @return true if the right neighbor is valid
     */
    private boolean checkRight(int x, int y){ 
        if(y+1 < numCol){
            if(this.cross[x][y+1] == 3){
                return true;
            }
        }
        return false;
    }
    /**
     * check upper neighbor method
     * @param x - the row index of current index
     * @param y - the column index of current index
     * @return true if the upper neighbor is valid
     */
    private boolean checkUp(int x, int y){ 
        if(x-1 >= 0){
            if(this.cross[x-1][y] == 3){
                return true;
            }
        }
        return false;
    }
    /**
     * checks lower neighbor method
     * @param x - the row index of current index
     * @param y - the column index of current index
     * @return true if the upper neighbor is valid
     */
    private boolean checkDown(int x, int y){  
        if(x+1 < numRow){
            if(this.cross[x+1][y] == 3){
                return true;
            }
        }
        return false;
    }
            
}
