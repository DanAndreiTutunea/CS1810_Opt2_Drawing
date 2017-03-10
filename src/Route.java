
import java.util.ArrayList;

public class Route {
	
    //actual command
    String actualCommand;
    //command shape - char represeting the shape ('T', 'R', ... or 'X' for exit)
    String commandShape;
    
    //result of route execution (0 - never executed, 1 - executed succesfully, 2+ - execution error)
    int executed = 0;

    //vector of int values re[presenting the shape to draw, structured as [side 1, angle 1, side 2, angle 2, ..... side n, angle n]
    ArrayList<Integer> shapeToDraw = new ArrayList<Integer>();
    //vector of 'Path' objects
    ArrayList<Path> pathList = new ArrayList<Path>();
    





    //constructor
    Route (String command){

        //store actual command string
        actualCommand = command;

        //takes the string passed as argument and separates the first character (shape type), then builds a string of remaining values
        String[] strings = command.split(constants.DELIMETER);
        commandShape = strings[0];
        String commandSides = "";
        for (int j = 1; j < strings.length ; ++j){
            commandSides += strings[j] + constants.DELIMETER;
        }
        //end of section
       

        //calculating the shape to draw vector and the path depending on the requested shape type
        switch (commandShape){

            case "R": //Rectangle
                
        //log - to remove
                System.out.println("Rectangle selected");
        //log - to remove
                System.out.println("Creating shape based on sides: " + commandSides);

                Rectangle tempR = new Rectangle();
                shapeToDraw = tempR.getRectangleShape("R", commandSides);
         //log - to remove
                System.out.println("Shape to draw: " + shapeToDraw);

                pathList = getShapePath(shapeToDraw);
                break;

            case "T": //Triangle

        //log - to remove
                System.out.println("Triangle selected");
        //log - to remove
                System.out.println("Creating shape based on sides: " + commandSides);

                Triangle tempT = new Triangle();
                shapeToDraw = tempT.getTriangleShape("T", commandSides);
         //log - to remove
                System.out.println("Shape to draw: " + shapeToDraw);

                pathList = getShapePath(shapeToDraw);
                break;
        }
        

    }





    //method that calculates the path elements based on sides and angles of a shape

    public ArrayList<Path> getShapePath(ArrayList<Integer> shapeToCalculate){

        ArrayList<Path> calculatedPathList = new ArrayList<Path>();

        int dist = 0, tmpLHS = 0, tmpRHS = 0, time = 0;
        Path temp;

        for (int i = 0; i < shapeToCalculate.size(); i += 2){

            //calculating the straight movement for drawing a side
            dist = shapeToCalculate.get(i);
            time = dist / (constants.FINCH_SPEED_TO_USE / 100 * constants.FINCH_CALIBRATED_SPEED_100) * constants.MILISEC_PER_SEC;
            tmpLHS = constants.FINCH_SPEED_TO_USE * constants.FINCH_LHSWHEEL_CALIBRATION / 100;
            tmpRHS = constants.FINCH_SPEED_TO_USE * constants.FINCH_RHSWHEEL_CALIBRATION / 100;

            temp = new Path(tmpLHS, tmpRHS, time);
            calculatedPathList.add(temp);

            //calculating the following turn as per specified angle.

            dist = shapeToCalculate.get(i+1) * constants.FINCH_FULL_TURN_DISTANCE / constants.FINCH_FULL_TURN_ANGLE;
            time = dist * constants.MILISEC_PER_SEC / (constants.FINCH_SPEED_TO_USE / 100 * constants.FINCH_CALIBRATED_SPEED_100);
            
            if (shapeToCalculate.get(i+1) == 0){
                    //angle is 0 - record no turn (zero speeds for 0 sec)
                    tmpLHS = 0;
                    tmpRHS = 0;
                    time = 0;
            }
            else {   
                if (shapeToCalculate.get(i+1) > 0){
                        //positive angle value - turn to RHS
                        tmpLHS = constants.FINCH_SPEED_TO_USE * constants.FINCH_LHSWHEEL_TURN_CALIBRATION / 100;
                        tmpRHS = 0;
                }
                else
                {
                        //negative angle value - turn to LHS
                        tmpLHS = 0;
                        tmpRHS = constants.FINCH_SPEED_TO_USE * constants.FINCH_RHSWHEEL_TURN_CALIBRATION / 100;
                }
            }

            temp = new Path(tmpLHS, tmpRHS, time);
            calculatedPathList.add(temp);

        }

        return(calculatedPathList);
    }





    // method that prints path steps
    public void printSteps(){

        for(int i = 0 ; i < pathList.size(); i++){
            System.out.print("LHS Speed: ");
            System.out.print(pathList.get(i).getLHSspeed());
            System.out.print("; RHS Speed: ");
            System.out.print(pathList.get(i).getRHSspeed());
            System.out.print("; for ");
            System.out.print(pathList.get(i).getMoveTime());
            System.out.println(" miliseconds");
        }
    }





    //returns the calculated path steps list
    public ArrayList<Path> getPath(){

        return(pathList);
    }




    //returns the shape of this objects
    public String getShapeType(){

        return(commandShape);
    }

    public void setExecutionState(int state){

        executed = state;

    }

    public int getExecutionState(){

        return(executed);

    }

    public String getActualCommand(){

        return(actualCommand);

    }
	
}
