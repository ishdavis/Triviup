package cs1635.triviup;

import java.io.Serializable;

/**
 * Created by Jonathan on 3/21/2016.
 */
public class TeamInfo implements Serializable{
    protected String teamName;
    protected int teamSize;
    protected int roundPoints;

    public TeamInfo(String teamName, int teamSize, int roundPoints){
        this.teamName = teamName;
        this.teamSize = teamSize;
        this.roundPoints = roundPoints;
    }
}