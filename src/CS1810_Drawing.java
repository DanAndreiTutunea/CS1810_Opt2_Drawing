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
    private int triangleAngle12 = 0, trinagleAngle13 = 0, trinagleAngle23 = 0;

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

        switch (shape){

            case 1: //Rectangle
                error = calculateRouteRectangle(rectangleWidth, rectangleHeight);
                break;
            case 2: //Triangle
                error = calculateRouteTriangle(triangleSide1, triangleSide2, triangleSide3);
                break;
        }



    }


    private int calculateRouteRectangle(int width, int height){

        //creating dummy path List - eventually should be properly calculated

        Path testMove101 = new Path(100, 100, 1000);
        Path testMove102 = new Path(90, 0, 1000);
        Path testMove103 = new Path(100, 100, 1000);
        Path testMove104 = new Path(90, 0, 1000);
        Path testMove105 = new Path(100, 100, 1000);
        Path testMove106 = new Path(90, 0, 1000);
        Path testMove107 = new Path(100, 100, 1000);
        Path testMove108 = new Path(90, 0, 1000);

        pathList.add(testMove101);
        pathList.add(testMove102);
        pathList.add(testMove103);
        pathList.add(testMove104);
        pathList.add(testMove105);
        pathList.add(testMove106);
        pathList.add(testMove107);
        pathList.add(testMove108);

        noOfMoves = pathList.size();

        return(0);

    }



    private int calculateRouteTriangle(int side1, int side2, int side3){

        //creating dummy path List - eventually should be properly calculated

        Path testMove201 = new Path(150, 150, 1000);
        Path testMove202 = new Path(120, 0, 1000);
        Path testMove203 = new Path(150, 150, 1000);
        Path testMove204 = new Path(120, 0, 1000);
        Path testMove205 = new Path(150, 150, 1000);
        Path testMove206 = new Path(120, 0, 1000);

        pathList.add(testMove201);
        pathList.add(testMove202);
        pathList.add(testMove203);
        pathList.add(testMove204);
        pathList.add(testMove205);
        pathList.add(testMove206);


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


    public ArrayList<Path> getPath(){

        return(pathList);

    }

}







public class CS1810_Drawing {



	public static void main(final String[] args){

        int movementError;

        Finch HK_14 = new Finch();

        Route testRoute = new Route();

        testRoute.printSteps();

        movementError = executeMovement(HK_14, testRoute);
        
        
        HK_14.quit();
        System.exit(0);
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
