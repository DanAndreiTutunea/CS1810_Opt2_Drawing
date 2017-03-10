

//Objects of Path class record path steps (LHS wheel speed, RHS wheel speed and move time)


public class Path {

    private int lhsWheelSpeed, rhsWheelSpeed;
    private int timeMiliSeconds;

    Path(int lhs, int rhs, int time){
        
        lhsWheelSpeed = lhs;
        rhsWheelSpeed = rhs;
        timeMiliSeconds = time;
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
