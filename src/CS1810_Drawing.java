

import java.util.ArrayList;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.util.Scanner;


public class CS1810_Drawing {

	public static void main(final String[] args){

        //variable declaration
        String command="";
        int movementError = 0, valid = 0;
        boolean whileCondition = true;

        Route requiredShape;
        ArrayList<Route> requiredShapeList = new ArrayList<Route>();

        //Finch robot init
        Finch HK_14 = new Finch();

        //program core to run until the exit command is recieved (command string first letter is "X")
        while(whileCondition){

            //call for function that reads the command and returns the command string
            command = readCommand();

            //checks for issues in the command string format
            //if the string is valid, it creates the route objects then calls the methode that will execute the movment
            valid = validateCommand(command);
            if (valid == constants.VALID_SUCCESSFUL && (command != "Q" || command != "q")){

                requiredShape = new Route(command);
                movementError = executeMovement(HK_14, requiredShape);
                requiredShape.setExecutionState(movementError);
                requiredShapeList.add(requiredShape);
            }
            else {

                //if the command string format is incorrect the reason will be displayed

                System.out.print("Command not valid. Reason: ");
                switch (valid) {
                    
                    case constants.VALID_ERROR_WRONG_NO_SIDES:
                    System.out.println("Wrong number of sides for required shape ...");
                    break;

                    case constants.VALID_ERROR_SIDE_SIZE:
                    System.out.println("Shape sides sizes not allowed ...");
                    break;

                    case constants.VALID_ERROR_NOT_TRIANGLE:
                    System.out.println("Given sizes cannot form a triangle ...");
                    break;

                    case constants.VALID_ERROR_COMMAND_UNRECOGNISED:
                    System.out.println("Shape unrecognised ...");
                    break;

                    default:
                    System.out.println("Unknown reason");
                    break;
                }

            }

            //The while loop is exited if the quit command has been recieved
            if (command.contains("Q") || command.contains("q")) {  
                System.out.println("Exiting ...");
                HK_14.playTone(440, 1000);
                HK_14.sleep(1000);
                HK_14.playTone(440, 1000);
                whileCondition = false;
            }
        }
        
        //after the exit command is given, the list of all successfully executed shapes is displayed
        printExecutedCommands(requiredShapeList);

        //Finch disconnect and end of program
        HK_14.quit();
        System.exit(0);
    }

    //methid that reads and returns a string from the console
    public static String readCommand(){

        Scanner scan = new Scanner(System.in);
        String command = scan.nextLine();
        System.out.println(command);
    	return(command);
    }

    //method that validates the command string
    public static int validateCommand(String commandToCheck){

        int validateResult = constants.VALID_SUCCESSFUL;
        int i=0, j=0;
        String inputShape = "";


        String[] stringElements = commandToCheck.split(constants.DELIMETER);
        ArrayList<Integer> intElements = new ArrayList<Integer>();
        inputShape = stringElements[0];
        for (j = 1; j < stringElements.length ; ++j){
            intElements.add(Integer.parseInt(stringElements[j]));
        }

        switch (inputShape) {

            case "R":
            case "r":

                //rectangle checks: number of string sides is correct, and the length of the sides is as per the assignment request
                if (intElements.size() != 2) validateResult = constants.VALID_ERROR_WRONG_NO_SIDES;
                for (i = 0; i < intElements.size(); i++) 
                    if (intElements.get(i) < constants.RECTANGLE_MIN_SIDE_SIZE || intElements.get(i) > constants.RECTANGLE_MAX_SIDE_SIZE) 
                        validateResult = constants.VALID_ERROR_SIDE_SIZE;

            break;

            case "T":
            case "t":

                //triangle checks: number of string sides is correct, and the length of the sides is as per the assignment request
                //also checks whether the given sizes can actualy form a triangle
                if (intElements.size() != 3) validateResult = constants.VALID_ERROR_WRONG_NO_SIDES;
                for (i = 0; i < intElements.size(); i++)
                    if (intElements.get(i) < constants.TRIANGLE_MIN_SIDE_SIZE || intElements.get(i) > constants.TRIANGLE_MAX_SIDE_SIZE) 
                        validateResult = constants.VALID_ERROR_SIDE_SIZE;
                if (intElements.get(0) >= intElements.get(1) + intElements.get(2)) validateResult = constants.VALID_ERROR_NOT_TRIANGLE;
                if (intElements.get(1) >= intElements.get(0) + intElements.get(2)) validateResult = constants.VALID_ERROR_NOT_TRIANGLE;
                if (intElements.get(2) >= intElements.get(0) + intElements.get(1)) validateResult = constants.VALID_ERROR_NOT_TRIANGLE;

            break;

            default:

                //shape unrecognised
                validateResult = constants.VALID_ERROR_COMMAND_UNRECOGNISED;

            break;

        }

        return(validateResult);
    }

    //method that prints the list of shapes executed successfully
    public static void printExecutedCommands(ArrayList<Route> listToPrint){

        Route tempRoute;

         for(int i = 0 ; i < listToPrint.size(); i++){

                tempRoute = listToPrint.get(i);
                System.out.print("Drawing " + (i+1) + ": " + tempRoute.getActualCommand() + " - ");
                if (tempRoute.getExecutionState() == 0) System.out.println("Executed successfuly.");
                else System.out.println("Execution failed.");
         }

    }  
	
    //method that executes the movement as per the list of Path steps within the route 
    public static int executeMovement(Finch John, Route routeToDo){

        int error = 0;
        int lhsSpeed, rhsSpeed, moveTime;

        ArrayList<Path> tempPath = new ArrayList<Path>();

        tempPath = routeToDo.getPath();

        for(int i = 0 ; i < tempPath.size(); i++){
        
            lhsSpeed = tempPath.get(i).getLHSspeed();
            rhsSpeed = tempPath.get(i).getRHSspeed();
            moveTime = tempPath.get(i).getMoveTime();

            John.setWheelVelocities(lhsSpeed, rhsSpeed, moveTime);

        }

        return(error);
    }	
	
}
