import java.util.ArrayList;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;



class Path{
    
    private int lhsWheelSpeed, rhsWheelSpeed, timeMiliSeconds, timeSeconds;

    Path(int lhs, int rhs, int time){
        
        lhsWheelSpeed = lhs;
        rhsWheelSpeed = rhs;
        timeMiliSeconds = time;
        timeSeconds = time / 1000;
    }

    public int getLHSspeed(){

        return(lhsWheelSpeed);
    }

    public int getRHSspeed(){

        return(rhsWheelSpeed);
    }
 
    public int getMoveTime(){

        return(timeMiliSeconds);
    }

}


class Route{

    private int shape = 0, rectangleWidth = 0, rectangleHeight = 0, triangleSide1 = 0, triangleSide2 = 0, triangleSide3 = 0;
    private triangleAngle12 = 0, trinagleAngle13 = 0, trinagleAngle23 = 0;

    ArrayList<Path> pathList = new ArrayList<Path>();

    private int noOfMoves = 0;
    private int error = 0;
        /*
            0 - no error;
            1 - 

        */


    Route (){ //argument emply for testing purposes - eventually to become string of command

        // creating dummy object for testing purposes

        shape = 1;
        rectangleWidth = 40;
        rectangleHeight = 40;

        switch(shape){

            case 1: //Rectangle
                error = calculateRouteRectangle(rectangleWidth, rectangleHeight);
            case 2: //Triangle
                error = calculateRouteTriangle(triangleSide1, triangleSide2, triangleSide3);
        }



    }


    private int calculateRouteRectangle(int width, int height){

        //creating dummy path List - eventually should be properly calculated

        Path testMove1 = new Path(100, 100, 1000);
        Path testMove2 = new Path(90, 0, 1000);
        Path testMove3 = new Path(100, 100, 1000);
        Path testMove4 = new Path(90, 0, 1000);
        Path testMove5 = new Path(100, 100, 1000);
        Path testMove6 = new Path(90, 0, 1000);
        Path testMove7 = new Path(100, 100, 1000);
        Path testMove8 = new Path(90, 0, 1000);

        pathList.add(testMove1);
        pathList.add(testMove2);

        noOfMoves = pathList.size();

        return(0);

    }



    private int calculateRouteTriangle(int side1, int side2, int side3){

        //creating dummy path List - eventually should be properly calculated

        Path testMove1 = new Path(100, 100, 1000);
        Path testMove2 = new Path(120, 0, 1000);
        Path testMove3 = new Path(100, 100, 1000);
        Path testMove4 = new Path(120, 0, 1000);
        Path testMove5 = new Path(100, 100, 1000);
        Path testMove6 = new Path(120, 0, 1000);

        pathList.add(testMove1);
        pathList.add(testMove2);

        noOfMoves = pathList.size();

        return(0);

    }



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


    public Path getPath(){

        return(pathList);

    }

}







public class CS1810_Drawing {



	public static void main(final String[] args){

        int movementError;

        Finch HK_14 = new Finch();

        Route testRoute = new Route();

        //testRoute.printSteps();

        movementError = executeMovement(HK_14, testRoute);
        
        
        HK_14.quit();
        System.exit(0);
    }
	

    public static int executeMovement(Finch John, Route routeToDo){

        int error = 0;
        int lhsSpeed, rhsSpeed, moveTime;

        ArrayList<Path> tempPath = new ArrayList<Path>();

        for(int i = 0 ; i < routeToDo.pathList.size(); i++){
        
            tempPath = routeToDo.getPath().clone();
            lhsSpeed = tempPath.get(i).getLHSspeed();
            rhsSpeed = tempPath.get(i).getRHSspeed();
            moveTime = tempPath.get(i).getMoveTime();

            John.setWheelVelocities(lhsSpeed, rhsSpeed, moveTime);

        }

        return(error);
    }
	
	
	
}
