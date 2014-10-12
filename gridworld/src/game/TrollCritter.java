
package game;

import info.gridworld.actor.*;
import java.awt.Color;
import java.util.ArrayList;

public class TrollCritter extends Critter {
    public TrollCritter()
    {
        setColor(Color.ORANGE);
    }
    
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (!(a instanceof Rock) && !(a instanceof Player) && !(a instanceof Object))
                a.removeSelfFromGrid();
        }
    }
}
