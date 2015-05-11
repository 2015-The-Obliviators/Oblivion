/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oblivion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author brookeireland
 */
public class Level implements AccelerationProviderIntf {

    public static Level getLevel(int levelNumber) {
        Level level = new Level();
        
        //default gravity acceleration
        level.setAcceleration(new Vector2D(0, 2));

        switch (levelNumber) {
            case 1:
                level.barriers.clear();

                level.barriers.add(new Barrier(new Point(0, 500), 1000, 100, BarrierType.FLOOR));
                level.barriers.add(new Barrier(new Point(0, 0), 1000, 100, BarrierType.CEILING));
                
                level.setText("Use the left and right arrow keys to move");
                for (Barrier barrier : level.barriers){
                    barrier.setColor(new Color (0, 0, 0, 125));
                }

                level.setAcceleration(new Vector2D(0, 1));
                break;

            case 2:
                level.barriers.clear();
                level.barriers.add(new Barrier(new Point(0, 500), 1000, 100, BarrierType.FLOOR));
                level.barriers.add(new Barrier(new Point(0, 0), 1000, 100, BarrierType.CEILING));
                level.barriers.add(new Barrier(new Point(350, 100), 400, 275, BarrierType.WALL));
                
                for (Barrier barrier : level.barriers){
                    barrier.setColor(new Color (0, 0, 0, 110));
                }

                level.setAcceleration(new Vector2D(0, 2));
                //levletterI.setAccelerationProvider(level.acceleration);
                
                break;

            
            case 3:
                level.barriers.clear();
                
                level.barriers.add(new Barrier(new Point(0, 500), 1000, 100, BarrierType.FLOOR));
                level.barriers.add(new Barrier(new Point(0, 0), 1000, 100, BarrierType.CEILING));
                level.barriers.add(new Barrier(new Point(300, 225), 50, 50, BarrierType.WALL));
                level.barriers.add(new Barrier(new Point(475, 400), 300, 100, BarrierType.WALL));
                
                for (Barrier barrier : level.barriers){
                    barrier.setColor(new Color (0, 0, 0));
                }

                level.setAcceleration(new Vector2D(0, 2));
                break;
                 
            case 4:
                level.barriers.clear();
          
                level.setAcceleration(new Vector2D(0, 2));
                level.barriers.add(new Barrier(new Point(0, 500), 1000, 100, BarrierType.FLOOR));
                level.barriers.add(new Barrier(new Point(0, 0), 1000, 100, BarrierType.CEILING));
                level.barriers.add(new Barrier(new Point(200, 170), 50, 50, BarrierType.WALL));
                level.barriers.add(new Barrier(new Point(350, 420), 30, 80, BarrierType.WALL));
                level.barriers.add(new Barrier(new Point(550, 420), 30, 80, BarrierType.WALL));
                level.barriers.add(new Barrier(new Point(600, 220), 300, 400, BarrierType.WALL));
                
                for (Barrier barrier : level.barriers){
                    barrier.setColor(new Color (0, 0, 0));   
                }
            break;
                
            case 5:
                level.setAcceleration(new Vector2D(0, 2));
                level.setBarriers(new ArrayList<>());
                
            break;
                
            case 6:
                level.setAcceleration(new Vector2D(0, 2));
                level.setBarriers(new ArrayList<>());
                
            break;
                
            case 7:
                level.setAcceleration(new Vector2D(0, 2));
                level.setBarriers(new ArrayList<>());
                
            break;
                
            case 8:
                level.setAcceleration(new Vector2D(0, 2));
                level.setBarriers(new ArrayList<>());
                
            break;
                
            case 9:
                level.setAcceleration(new Vector2D(0, 2));
                level.setBarriers(new ArrayList<>());
        }

        return level;
    }

    
    {
        barriers = new ArrayList<>();
//        letters = new ArrayList<>();
        text = "";
        acceleration = new Vector2D(0, 0);
        setLetterI(new BlockLetterI(100, 100, 100, 150, false, this));
    }
    
    private int levelNumber;
    private ArrayList<Barrier> barriers;
    private Vector2D acceleration;
    private String text = "";
//    private ArrayList<Letter> letters;
//    private ArrayList<Block> blocks;
    private BlockLetterI letterI;
    
    private int textX;
    private int textY;

    /**
     * @return the levelNumber
     */
    public int getLevelNumber() {
        return levelNumber;
    }

    /**
     * @param levelNumber the levelNumber to set
     */
    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    /**
     * @return the barriers
     */
    public ArrayList<Barrier> getBarriers() {
        return barriers;
    }

    /**
     * @param barriers the barriers to set
     */
    public void setBarriers(ArrayList<Barrier> barriers) {
        this.barriers = barriers;
    }

    /**
     * @return the acceleration
     */
    public Vector2D getAcceleration() {
        return acceleration;
    }

    /**
     * @param acceleration the acceleration to set
     */
    public void setAcceleration(Vector2D acceleration) {
        this.acceleration = acceleration;
        this.letterI.setAccelerationProvider(this);
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the letterI
     */
    public BlockLetterI getLetterI() {
        return letterI;
    }

    /**
     * @param letterI the letterI to set
     */
    public void setLetterI(BlockLetterI letterI) {
        this.letterI = letterI;
    }

    void drawText(Graphics graphics) {
        graphics.drawString(text, textX, textY);
    }

}
