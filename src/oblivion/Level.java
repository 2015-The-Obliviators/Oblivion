/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oblivion;

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
//                 barriers.add(new Barrier(new Point(100, 10), 2, 200, BarrierType.WALL));
//        level.setLetters(new ArrayList<>());
//        level.getLetters().add(new LetterI(new Point(0, 300), new Velocity(0, 0)));

//        for (Letter letter : getLetters()) {
//            letter.setAccelerationProvider(this);
//        }
             
                
                break;
            case 2:
                level.setAcceleration(new Vector2D(0, 2));
                level.setBarriers(new ArrayList<>());
                
                
                
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

    private int levelNumber;
    private ArrayList<Barrier> barriers;
    private Vector2D acceleration;
    private String text;
    private ArrayList<Letter> letters;
//    private ArrayList<Block> blocks;

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

    /**
     * @return the letters
     */
    public ArrayList<Letter> getLetters() {
        return letters;
    }

    /**
     * @param letters the letters to set
     */
    public void setLetters(ArrayList<Letter> letters) {
        this.letters = letters;
    }

}