package critters;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class PaladinGG extends Critter
{
        public ArrayList<Actor> getActors()
        {   
            ArrayList<Actor> temp = new ArrayList<Actor>();
            return temp;
        }
        
	public ArrayList getMoveLocations()
	{
		Location loc = getLocation();
		ArrayList locs = new ArrayList();
		for (int k = Location.NORTH; k < Location.FULL_CIRCLE; k += Location.RIGHT) 
                {
			Location neighborLocation = loc.getAdjacentLocation(k);
			if (getGrid().isValid(neighborLocation)) 
                        {
				Location[] diagonals = new Location[2];
				diagonals[0] = 
					neighborLocation.getAdjacentLocation((k+Location.HALF_LEFT+Location.FULL_CIRCLE) % 
							Location.FULL_CIRCLE);
				diagonals[1] = 
					neighborLocation.getAdjacentLocation((k+Location.HALF_RIGHT+Location.FULL_CIRCLE) % 
							Location.FULL_CIRCLE);
				for (Location d : diagonals) 
                                {
					if (getGrid().isValid(d)) 
                                        { 
						Actor a = getGrid().get(d);
						if (a == null || a instanceof Rock) 
                                                {
							locs.add(d);
						}
					}
				}
			}
		}
		return locs;
	}
        public void makeMove(Location loc)
        {   
            Grid<Actor> Grid = getGrid();
            if(Grid.get(loc)instanceof Rock)
                {
                    moveTo(loc);
                    removeSelfFromGrid();
                }
            else
                moveTo(loc);
        }
}