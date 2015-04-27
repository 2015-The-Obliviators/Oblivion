/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oblivion;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author brookeireland
 */
public class Level {

    public static Level getLevel(int levelNumber) {
        Level level = new Level();

        switch (levelNumber) {
            case 1:
                level.barriers.clear();
                level.setAcceleration(new Vector2D(0, 1));
                level.barriers.add(new Barrier(new Point(0, 500), 1000, 100, BarrierType.FLOOR));
                level.barriers.add(new Barrier(new Point(0, 0), 1000, 100, BarrierType.CEILING));
                
                for (Barrier barrier : level.barriers){
                    barrier.setColor(new Color (0, 0, 0, 125));
                }
//                 barriers.add(new Barrier(new Point(100, 10), 2, 200, BarrierType.WALL));
//        level.setLetters(new ArrayList<>());
//        level.getLetters().add(new LetterI(new Point(0, 300), new Velocity(0, 0)));

//        for (Letter letter : getLetters()) {
//            letter.setAccelerationProvider(this);
//        }
             
                
                break;
            case 2:
                level.barriers.clear();
    
                level.setAcceleration(new Vector2D(0, 2));
<<<<<<< HEAD
                level.barriers.add(new Barrier(new Point(0, 500), 1000, 100, BarrierType.FLOOR));
                level.barriers.add(new Barrier(new Point(0, 0), 1000, 100, BarrierType.CEILING));
                level.barriers.add(new Barrier(new Point(50, 0), 100, 10, BarrierType.WALL));
                for (Barrier barrier : level.barriers){
                    barrier.setColor(new Color (0, 0, 0, 125));
                }
                break;
=======
                level.setBarriers(new ArrayList<>());
                
>>>>>>> mll-sound-02
                  
            case 3:
                level.setAcceleration(new Vector2D(0, 2));
                level.setBarriers(new ArrayList<>());
                
                 
            case 4:
                level.setAcceleration(new Vector2D(0, 2));
                level.setBarriers(new ArrayList<>());
                
                
            case 5:
                level.setAcceleration(new Vector2D(0, 2));
                level.setBarriers(new ArrayList<>());
                
                
            case 6:
                level.setAcceleration(new Vector2D(0, 2));
                level.setBarriers(new ArrayList<>());
                
                
            case 7:
                level.setAcceleration(new Vector2D(0, 2));
                level.setBarriers(new ArrayList<>());
                
                
            case 8:
                level.setAcceleration(new Vector2D(0, 2));
                level.setBarriers(new ArrayList<>());
                
                
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
        setLetterI(new BlockLetterI(100, 100, 100, 150, false));
    }
    
    private int levelNumber;
    private ArrayList<Barrier> barriers;
    private Vector2D acceleration;
    private String text = "";
//    private ArrayList<Letter> letters;
//    private ArrayList<Block> blocks;
    private BlockLetterI letterI;

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

//    /**
//     * @return the letters
//     */
//    public ArrayList<Letter> getLetters() {
//        return letters;
//    }
//
//    /**
//     * @param letters the letters to set
//     */
//    public void setLetters(ArrayList<Letter> letters) {
//        this.letters = letters;
//    }

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

}
