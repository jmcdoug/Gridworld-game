
package game;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.actor.*;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.awt.Color;

public class Hunter extends info.gridworld.actor.Critter {
    
    public Hunter()
    {
        setColor(Color.RED);
    }
    
    public void act()
    {
        if (getGrid() == null)
            return;
        Location playerLoc = findPlayer();
        if(playerLoc != null && getGrid().isValid(playerLoc))
        {
            setDirection(getLocation().getDirectionToward(playerLoc));
            makeMove(getLocation().getAdjacentLocation(getDirection()));
        }

    }

    public Location findPlayer()
    {
        ArrayList<Location> locs = getGrid().getOccupiedLocations();
        for(Location l: locs)
        {
            if(getGrid().isValid(l) && getGrid().get(l) instanceof Player)
            {
                return l;
            }
        }
        return null;
    }

    public void makeMove(Location loc)
    {
        Actor neighbor = getGrid().get(loc);
        
        if (loc == null) {
            removeSelfFromGrid();
        }
        else if(!(neighbor instanceof info.gridworld.actor.Critter) && !(neighbor instanceof Rock) && !(neighbor instanceof Flower))
        {
            moveTo(loc);
        }
    }
}
