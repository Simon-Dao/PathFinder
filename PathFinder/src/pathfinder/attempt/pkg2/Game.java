/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.attempt.pkg2;

import java.util.List;
import java.util.Random;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author Simon Dao
 */

public class Game 
{
    
    int[] input = new int[4];
    
    private Map map;
    private List<Node> path;
    
    public int startX;
    public int startY;
    public int goalX;
    public int goalY;
    
    public int timesClicked = 0;
    
    public Game()
    {
        int size = 20;

        map = new Map(size, size);
    }
    
    // move the player 
    public void update(GraphicsContext gc)
    {      
        render(gc);
    }
    
    public void render(GraphicsContext gc)
    {
        //background
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,601,601);
        
        gc.setFill(Color.GREEN);
        gc.fillRect(600, 0, 3, 601);
        ///////////////////////////////////////
        path = map.findPath(startX, startY, goalX, goalY);
        
        ///////////////////////////////////////
        map.drawMap(gc, path);
    }

     public void userInput(Canvas c)
    {
        c.addEventFilter(MouseEvent.MOUSE_CLICKED, mouse ->
        {
            int mouseX = (int)mouse.getX();
            int mouseY = (int)mouse.getY();

            timesClicked = timesClicked > 2 ? 0 : timesClicked +1;
            
            if(timesClicked % 2 == 0)
            {
                goalX = findCoordinate(mouseX);
                goalY = findCoordinate(mouseY);
            }
            else
            {
                startX = findCoordinate(mouseX);
                startY = findCoordinate(mouseY);
            }
        });
    }
    public int findCoordinate(int coord)
    {
        boolean done = false;
        int count = 0; 
        int tile_size = map.getTileSize();
        
            while(done == false)
            {
                // find how many tiles can fit in the coordinate
                coord = coord - tile_size;
                count++;
                if(coord < 0)
                {   
                    done = true;
                    count--;
                    coord = count;
                }
            }
        return coord;
    }
}
