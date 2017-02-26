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



class Shape{

    private int noOfEdges = 0;
    private int noOfAngles = 0;
    ArrayList<Integer> elements = new ArrayList<Integer>();

    Shape (String shapeType, String shapeString){

        int i = 0, j = 0;
        String[] stringParts = shapeString.split(CS1810_Drawing.delimeter);

        switch(shapeType){

            case "R":
                // shape is a rectangle
                // to use rectangle class here !!! - the following code is for testing only
                for (j = 0; j < stringParts.length ; ++j){
                    elements.add(Integer.parseInt(stringParts[j]));
                    noOfEdges++;
                    elements.add(CS1810_Drawing.rectangleAngle);
                    noOfAngles++;
                }
                for (j = 0; j < stringParts.length ; ++j){
                    elements.add(Integer.parseInt(stringParts[j]));
                    noOfEdges++;
                    elements.add(CS1810_Drawing.rectangleAngle);
                    noOfAngles++;
                }
            break;
            case "T":
                // shape is a triangle
            
            break;
        }
        
    }

    public int getShapeNoOfEdges(){
        return(noOfEdges);
    }

    public int getShapeNoOfAngles(){
        return(noOfAngles);
    }

    public int getShapeSide(int x){
        return(elements.get((x-1)*2));
    }

    public int getShapeAngle(int x){
        return(elements.get(x*2-1));
    }

}

/*
class Rectangle{


    private int rectangleEdge1 = 0;
    private int rectangleEdge2 = 0;
}
*/

class Route{

    Shape shapeToDraw;
    ArrayList<Path> pathList = new ArrayList<Path>();
    private int error = 0;

        /*
            0 - no error;
            1 - 

        */


    Route (String command){

        int i=0, j=0;

        System.out.println("Command for route object:" + command); //test only - to remove!!

        String[] strings = command.split(" ");
        String commandShape = strings[0];
        String commandSides = "";

        for (j = 1; j < strings.length ; ++j){
            commandSides += strings[j] + CS1810_Drawing.delimeter;
        }
       
       
        switch (commandShape){

            case "R": //Rectangle
                shapeToDraw = new Shape(commandShape, commandSides);
                //error = calculateRouteRectangle(rectangleWidth, rectangleHeight);
                
               


                //test only - to remove!!
                System.out.println(shapeToDraw.getShapeNoOfEdges());
                for (i = 1; i <= shapeToDraw.getShapeNoOfEdges(); i++){
                    System.out.println("Side " + i + ": " + shapeToDraw.getShapeSide(i));
                }
                for (i = 1; i <= shapeToDraw.getShapeNoOfAngles(); i++){
                    System.out.println("Angle " + i + ": " + shapeToDraw.getShapeAngle(i));
                }


                break;
            case "T": //Triangle
                //error = calculateRouteTriangle(triangleSide1, triangleSide2, triangleSide3);
                break;
        }
        

    }

/* to be moved in respecitve classes

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



        return(0);

    }

*/

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

    //constants
    public static final int rectangleAngle = 90;
    public static final String delimeter = " ";


	public static void main(final String[] args){

        int movementError;

        Finch HK_14 = new Finch();

        Route testRoute = new Route("R 40 60");



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
