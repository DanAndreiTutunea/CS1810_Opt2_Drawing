

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

        while(whileCondition){

            command = readCommand();

            valid = validateCommand(command);
            if (valid == constants.VALID_SUCCESSFUL && command != "X"){

                requiredShape = new Route(command);
                movementError = executeMovement(HK_14, requiredShape);
                //movementError = 0; //temp statement
                requiredShape.setExecutionState(movementError);
                requiredShapeList.add(requiredShape);


            }
            else {

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

                    //System.out.println("Unspecified reason");

                }

            }

            //System.out.println(command);
            if (command.contains("X")) {  
                System.out.println("Exiting ...");
                whileCondition = false;
            }
        }
        
        
        
        printExecutedCommands(requiredShapeList);

        //Finch disconnect
        HK_14.quit();
        System.exit(0);
    }





    public static String readCommand(){

        Scanner scan = new Scanner(System.in);
        String command = scan.nextLine();
        System.out.println(command);
    	return(command);
    }





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

                if (intElements.size() != 2) validateResult = constants.VALID_ERROR_WRONG_NO_SIDES;
                for (i = 0; i < intElements.size(); i++) 
                    if (intElements.get(i) < constants.RECTANGLE_MIN_SIDE_SIZE || intElements.get(i) > constants.RECTANGLE_MAX_SIDE_SIZE) 
                        validateResult = constants.VALID_ERROR_SIDE_SIZE;

            break;

            case "T":

                if (intElements.size() != 3) validateResult = constants.VALID_ERROR_WRONG_NO_SIDES;
                for (i = 0; i < intElements.size(); i++)
                    if (intElements.get(i) < constants.TRIANGLE_MIN_SIDE_SIZE || intElements.get(i) > constants.TRIANGLE_MAX_SIDE_SIZE) 
                        validateResult = constants.VALID_ERROR_SIDE_SIZE;
                if (intElements.get(0) >= intElements.get(1) + intElements.get(2)) validateResult = constants.VALID_ERROR_NOT_TRIANGLE;
                if (intElements.get(1) >= intElements.get(0) + intElements.get(2)) validateResult = constants.VALID_ERROR_NOT_TRIANGLE;
                if (intElements.get(2) >= intElements.get(0) + intElements.get(1)) validateResult = constants.VALID_ERROR_NOT_TRIANGLE;

            break;


        }




        return(validateResult);
    }





    public static void printExecutedCommands(ArrayList<Route> listToPrint){

        Route tempRoute;

         for(int i = 0 ; i < listToPrint.size(); i++){

                tempRoute = listToPrint.get(i);
                System.out.print("Drawing " + (i+1) + ": " + tempRoute.getActualCommand() + " - ");
                if (tempRoute.getExecutionState() == 0) System.out.println("Executed successfuly.");
                else System.out.println("Execution failed.");


         }

    }  


	

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
