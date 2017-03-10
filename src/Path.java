
public class Path {

    
    private int lhsWheelSpeed, rhsWheelSpeed;
    private int timeMiliSeconds, timeSeconds;

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
