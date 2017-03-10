import java.util.ArrayList;

public class Rectangle {

    public static final int RECTANGLEANGLE = 90;

    Rectangle(){
    }

    public ArrayList<Integer> getRectangleShape(String shapeType, String shapeSides){

        //variable declaration
        int j = 0;
        String[] stringParts = shapeSides.split(constants.DELIMETER);
        ArrayList<Integer> intParts = new ArrayList<Integer>();

        //add sides and angles to array
        for (j = 0; j < stringParts.length ; ++j){
            intParts.add(Integer.parseInt(stringParts[j]));
            intParts.add(RECTANGLEANGLE);
        }
        for (j = 0; j < stringParts.length ; ++j){
            intParts.add(Integer.parseInt(stringParts[j]));
            intParts.add(RECTANGLEANGLE);
        }

        return(intParts);
    }

}
