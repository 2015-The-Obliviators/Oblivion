/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oblivion;

import images.ResourceTools;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author brookeireland
 */
public class Level implements AccelerationProviderIntf {

//<editor-fold defaultstate="collapsed" desc="Constructors and Factory Methods">
    public static Level getLevel(int levelNumber) {
        Level level = new Level();
        
        //default gravity acceleration
        level.setAcceleration(new Vector2D(0, 2));
        
        //TODO: remove this when the background images are fixed/available
        level.setBackgroundImage(ResourceTools.loadImageFromResource("resources/stars.png").getScaledInstance(1000, 700, Image.SCALE_SMOOTH));
        
        switch (levelNumber) {
            case 1:
                level.barriers.clear();
                level.barriers.add(new Barrier(new Point(0, 500), 1000, 100, BarrierType.FLOOR));
                level.barriers.add(new Barrier(new Point(0, 0), 1000, 100, BarrierType.CEILING));
                
//                level.setBackgroundImage(ResourceTools.loadImageFromResource("resources/starryforest.jpg").getScaledInstance(1000, 700, Image.SCALE_SMOOTH));
                
                level.setTextFont(new Font("FOOTLIGHTMT LIGHT", Font.ITALIC, 30));
                level.setTextColor(new Color(230, 230, 230, 75));
                level.setTextX(100);
                level.setTextY(75);
                level.setText("Use the left and right arrow keys to move");
                
                for (Barrier barrier : level.barriers){
                    barrier.setColor(new Color (0, 0, 0));
                }
                
                level.setAcceleration(new Vector2D(0, 1));
                break;
                
            case 2:
                level.barriers.clear();
                level.barriers.add(new Barrier(new Point(0, 500), 1000, 100, BarrierType.FLOOR));
                level.barriers.add(new Barrier(new Point(0, 0), 1000, 100, BarrierType.CEILING));
                level.barriers.add(new Barrier(new Point(350, 100), 400, 325, BarrierType.WALL));
                
                for (Barrier barrier : level.barriers){
                    barrier.setColor(new Color (0, 0, 0));
                }
                
//                level.setBackgroundImage(ResourceTools.loadImageFromResource("resources/hubble.jpg").getScaledInstance(1000, 700, Image.SCALE_SMOOTH));
                
                level.setTextFont(new Font("FOOTLIGHTMT LIGHT", Font.ITALIC, 30));
                level.setTextColor(new Color(230, 230, 230, 75));
                level.setTextX(100);
                level.setTextY(75);
                level.setText("Use the up and down arrows to grow and shrink");
                
                level.setAcceleration(new Vector2D(0, 2));
                
                break;
                
                
            case 3:
                level.barriers.clear();
                
                level.barriers.add(new Barrier(new Point(0, 500), 1000, 100, BarrierType.FLOOR));
                level.barriers.add(new Barrier(new Point(0, 0), 1000, 100, BarrierType.CEILING));
                level.barriers.add(new Barrier(new Point(300, 225), 50, 50, BarrierType.WALL));
                level.barriers.add(new Barrier(new Point(475, 400), 300, 100, BarrierType.WALL));
                
                //            level.setBackgroundImage(ResourceTools.loadImageFromResource("resources/moons.jpg").getScaledInstance(1000, 700, Image.SCALE_SMOOTH));
                
                for (Barrier barrier : level.barriers){
                    barrier.setColor(new Color (0, 0, 0));
                }
                level.setTextFont(new Font("FOOTLIGHTMT LIGHT", Font.ITALIC, 30));
                level.setTextColor(new Color(230, 230, 230, 75));
                level.setTextX(100);
                level.setTextY(75);
                level.setText("Can you chin up?");
                
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
                
//                level.setBackgroundImage(ResourceTools.loadImageFromResource("resources/greystars.jpg").getScaledInstance(1000, 700, Image.SCALE_SMOOTH));
                
                for (Barrier barrier : level.barriers){
                    barrier.setColor(new Color (0, 0, 0));
                }
                
                level.setTextFont(new Font("FOOTLIGHTMT LIGHT", Font.ITALIC, 30));
                level.setTextColor(new Color(230, 230, 230, 75));
                level.setTextX(100);
                level.setTextY(75);
                level.setText("I wonder what that button does...");
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
        text = "";
        acceleration = new Vector2D(0, 0);
        setLetterI(new BlockLetterI(100, 100, 100, 150, false, this));
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private int levelNumber;
    private ArrayList<Barrier> barriers;
    private Vector2D acceleration;
    private String text = "";
    private Color TextColor;
    private Font TextFont;
    private int TextX;
    private int TextY;
    private BlockLetterI letterI;
    private Image backgroundImage;
    
    /**
     * @return the backgroundImage
     */
    public Image getBackgroundImage() {
        return backgroundImage;
    }

    /**
     * @param backgroundImage the backgroundImage to set
     */
    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
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
    
    
    /**
     * @return the TextColor
     */
    public Color getTextColor() {
        return TextColor;
    }
    
    /**
     * @param TextColor the TextColor to set
     */
    public void setTextColor(Color TextColor) {
        this.TextColor = TextColor;
    }
    
    /**
     * @return the TextFont
     */
    public Font getTextFont() {
        return TextFont;
    }
    
    /**
     * @param TextFont the TextFont to set
     */
    public void setTextFont(Font TextFont) {
        this.TextFont = TextFont;
    }
    
    /**
     * @return the TextX
     */
    public int getTextX() {
        return TextX;
    }
    
    /**
     * @param TextX the TextX to set
     */
    public void setTextX(int TextX) {
        this.TextX = TextX;
    }
    
    /**
     * @return the TextY
     */
    public int getTextY() {
        return TextY;
    }
    
    /**
     * @param TextY the TextY to set
     */
    public void setTextY(int TextY) {
        this.TextY = TextY;
    }
//</editor-fold>


}
