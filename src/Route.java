
import java.util.ArrayList;

public class Route {
	
    //actual command string
    String actualCommand;
    //command shape - char represeting the shape ('T', 'R', ... or 'X' for exit)
    String commandShape;
    
    //result of route execution (0 - never executed, 1 - executed succesfully, 2+ - execution error)
    int executed = 0;

    //vector of int values re[presenting the shape to draw, structured as [side 1, angle 1, side 2, angle 2, ..... side n, angle n]
    ArrayList<Integer> shapeToDraw = new ArrayList<Integer>();
    //vector of 'Path' objects
    ArrayList<Path> pathList = new ArrayList<Path>();
    
    //constructor - builds an object by getting the array of int representing the shape and an array of 'Path' representing the route to take
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
            case "r":
                
                //log
                //System.out.println("Rectangle selected");
                //System.out.println("Creating shape based on sides: " + commandSides);

                //calculates the array of int represanting the shape, from the command string
                Rectangle tempR = new Rectangle();
                shapeToDraw = tempR.getRectangleShape("R", commandSides);
                
                //log
                //System.out.println("Shape to draw: " + shapeToDraw);

                //calculates the array of path steps
                pathList = getShapePath(shapeToDraw);
                break;

            case "T": //Triangle
            case "t":

                //log
                //System.out.println("Triangle selected");
                //System.out.println("Creating shape based on sides: " + commandSides);

                //calculates the array of int represanting the shape, from the command string
                Triangle tempT = new Triangle();
                shapeToDraw = tempT.getTriangleShape("T", commandSides);

                //log
                //System.out.println("Shape to draw: " + shapeToDraw);

                //calculates the array of path steps
                pathList = getShapePath(shapeToDraw);
                break;

            default:

                //default case

            break;
        }
        
    }

    //method that calculates the path steps based on sides and angles of a shape

    public ArrayList<Path> getShapePath(ArrayList<Integer> shapeToCalculate){

        ArrayList<Path> calculatedPathList = new ArrayList<Path>();

        int dist = 0, tmpLHS = 0, tmpRHS = 0, time = 0;
        Path temp;

        for (int i = 0; i < shapeToCalculate.size(); i += 2){

            //calculating the straight movement for drawing a side - LHS and RHS wheels speeds are used in conjunction to calubration data and set target speed
            //time to move is based on the formula time = distance / speed - also uses calibration data
            dist = shapeToCalculate.get(i);
            time = dist / (constants.FINCH_SPEED_TO_USE / 100 * constants.FINCH_CALIBRATED_SPEED_100) * constants.MILISEC_PER_SEC;
            tmpLHS = constants.FINCH_SPEED_TO_USE * constants.FINCH_LHSWHEEL_CALIBRATION / 100;
            tmpRHS = constants.FINCH_SPEED_TO_USE * constants.FINCH_RHSWHEEL_CALIBRATION / 100;

            temp = new Path(tmpLHS, tmpRHS, time);
            calculatedPathList.add(temp);


            //calculating the following turn as per specified angle.
            //if angle is positive it will always turn to right
            //dist represents the distance that a wheel should cover while the other wheel is stationary, so the robot is turned to the correct angle

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

    //returns the calculated path steps list
    public ArrayList<Path> getPath(){

        return(pathList);
    }

    //returns the shape of this objects
    public String getShapeType(){

        return(commandShape);
    }

    //sets the executed state after the move methid carries out the command
    public void setExecutionState(int state){

        executed = state;

    }

    //gets the executed state
    public int getExecutionState(){

        return(executed);

    }

    //gets the actual command string used to create this object
    public String getActualCommand(){

        return(actualCommand);

    }
	
}
