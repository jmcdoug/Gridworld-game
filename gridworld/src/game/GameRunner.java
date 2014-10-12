
package game;

import info.gridworld.actor.ActorWorld;
import critters.CrabCritter;
import info.gridworld.grid.Location;
import info.gridworld.actor.*;

public class GameRunner {
    public static boolean testVictory(Player player)
    {
        boolean victory = true;
        for(Location l: player.getGrid().getOccupiedLocations())
        {
            if(player.getGrid().get(l) instanceof Flower)
            {
                victory = false;
            }
        }
        return victory;
    }
    
    public static void victory(ActorWorld world)
    {
        world.add(new Hunter());
        world.add(new Object());
    }
    public static void main(String[] args) {
        
        final ActorWorld world = new ActorWorld();
        final Player player = new Player();
        world.add(new Location(2,3), new Object());
        world.add(new Location(5,6), new Rock());
        world.add(new Location(4,1), new Rock());
        //world.add(new Location(6,3), new TrollCritter());
        world.add(new Location(4,4), player);
        world.add(new Location(7,7), new Hunter());
        
        if(player.getGrid() != null)
        {
            java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager()
                            .addKeyEventDispatcher(new java.awt.KeyEventDispatcher() { 
                            public boolean dispatchKeyEvent(java.awt.event.KeyEvent event) { 
                                    String key = javax.swing.KeyStroke.getKeyStrokeForEvent(event).toString(); 
                                    {
                                        if (key.equals("pressed UP")) 
                                                player.setDirection(0); 
                                        if (key.equals("pressed RIGHT")) 
                                                player.setDirection(90); 
                                        if (key.equals("pressed DOWN")) 
                                                player.setDirection(180); 
                                        if (key.equals("pressed LEFT")) 
                                                player.setDirection(270); 

                                        if(player.getGrid() != null && player.getGrid().isValid(player.getLocation()))
                                        {
                                            info.gridworld.grid.Location loc = player.getLocation().getAdjacentLocation(player.getDirection());

                                            if(player.getGrid().isValid(loc))
                                            {
                                                if(testVictory(player))
                                                {
                                                    victory(world);
                                                    player.flowers++;
                                                    System.out.println("You've taken " + player.flowers + " flowers!!!");
                                                }
                                                if( !(player.getGrid().get(loc) instanceof Rock) && !(player.getGrid().get(loc) instanceof Hunter))
                                                {
                                                    player.moveTo(loc);
                                                }
                                            }
                                        }
                                    }
                                    return true; 
                            }
                    });
        }
        world.show();
    }
}
