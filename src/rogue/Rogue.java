package rogue;

import jade.core.World;
import jade.ui.TiledTermPanel;
import jade.util.datatype.ColoredChar;
import java.awt.Color;

import rogue.creature.BadPerson_1;
import rogue.creature.Monster;
import rogue.creature.Player;
import rogue.level.Level;

public class Rogue
{
    public static void main(String[] args) throws InterruptedException
    {
        TiledTermPanel term = TiledTermPanel.getFramedTerminal("Jade Rogue");
        
        Player player = new Player(term);
        World world = new Level(69, 24, player);
        world.addActor(new Monster(ColoredChar.create('D', Color.red)));
        world.addActor(new BadPerson_1(ColoredChar.create('X', Color.GREEN)));
        //term.registerCamera(player, 5, 5);

        
        while(!player.expired())
        {
            term.clearBuffer();
            for(int x = 0; x < world.width(); x++)
                for(int y = 0; y < world.height(); y++)
                    term.bufferChar(x + 11, y, world.look(x, y));
            term.bufferCameras();
            term.refreshScreen();
            world.tick();
        }

        System.exit(0);
    }
}
