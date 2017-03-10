import java.util.ArrayList;

//Class used to create an array of int (side 1, angle 1, side 2, angle 2, ....) representing a triangle

public class Triangle {

    Triangle(){
    }

    //this method creates the array of int 
    public ArrayList<Integer> getTriangleShape(String shapeType, String shapeSides){

        //variable declaration
        int j = 0;
        String[] stringParts = shapeSides.split(constants.DELIMETER);
        ArrayList<Integer> intParts = new ArrayList<Integer>();

        //add sides to the array and add 0 as angles for now
        for (j = 0; j < stringParts.length; j++){
            intParts.add(Integer.parseInt(stringParts[j]));
            intParts.add(0);
        }

        //calculates angles a, b and c according to the formula, where x, y and z are the triangle sides
        int x = intParts.get(0), y = intParts.get(2), z = intParts.get(4);
        int a = (int)(Math.toDegrees(Math.acos((Math.pow(x,2) + Math.pow(z,2) - Math.pow(y,2)) / (2 * x * z))));
        int b = (int)(Math.toDegrees(Math.acos((Math.pow(y,2) + Math.pow(z,2) - Math.pow(x,2)) / (2 * y * z))));
        int c = (int)(Math.toDegrees(Math.acos((Math.pow(x,2) + Math.pow(y,2) - Math.pow(z,2)) / (2 * x * y))));

        //adds calculated angle values at the correct position in the array
        //the actual value added is 180deg - angle, as this is the angle the robot will have to use to rotate
        intParts.set(1,180 - a);
        intParts.set(3,180 - b);
        intParts.set(5,180 - c);

        return(intParts);
    }

}
