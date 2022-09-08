
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import java.util.Arrays;

@ScriptManifest(author = "B.Dhillon", info = "WoodCutter", name = "Galactic Wood-Cutter", version = 0.0.1, logo = "")

public class Varrock_Woodcutter extends Script
{
    //Walking Back Paths:
    private Position[] path1 = {
            new Position(3262, 3428, 0), 
            new Position(3268, 3428, 0), 
            new Position(3274, 3428, 0),
            new Position(3279, 3423, 0), 
            new Position(3281, 3416, 0)
    };

    // Banking Function:
    void banking() throws InterruptedException
    {
        RS2Object bank1 = getObjects().closest("Bank booth");
        bank1.interact("Bank");
        sleep(1800);
        getBank().deposit("Oak logs", 99);
        sleep(1868);
        getBank().close();
    }

    // Walking Back Function:
    void walkBack() throws InterruptedException
    {
        getWalking().walkPath(Arrays.asList(path1));
        sleep(1800);
    }


    // Executes once on start
    @Override
    public void onStart() {
        log("Let's get started!");
    }

    @Override
    public int onLoop() throws InterruptedException {
        RS2Object tree = getObjects().closest("Oak");
        
        if (inventory.isFull()) {
            log("Inventory full, walking to bank");
            walking.webWalk(Banks.VARROCK_EAST);
            sleep(1800);
            banking();
            sleep(1300);
            walkBack();
        }
        
        else if (tree != null && !myPlayer().isAnimating() && !myPlayer().isMoving() {
            log("Tree is present!");
            tree.interact("Chop down");
            sleep(1100);
        }
        
        return random(400, 900);
    }

    @Override
    public void onExit() {
        log("Thanks for running Galactic Wood-Cutter");
    }


}
