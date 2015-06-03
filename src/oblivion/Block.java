/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oblivion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author kevin.lawrence
 */
public class Block extends Rectangle {
    
//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        barriers = new ArrayList<>();

        topBarrier = true;
        bottomBarrier = true;
        leftBarrier = true;
        rightBarrier = true;
        
        fillColor = new Color(0, 0, 0, 200);
        borderColor = new Color(0, 0, 0);
    }
    
    public Block(int x, int y, int width, int height, boolean stationary){
        super(x, y, width, height);
        setStationary(stationary);
    }
    
    public Block(int x, int y, int width, int height,
            boolean topBarrier, boolean bottomBarrier, 
            boolean leftBarrier, boolean rightBarrier,
            boolean stationary){
        super(x, y, width, height);
        
        this.topBarrier = topBarrier;
        this.bottomBarrier = bottomBarrier;
        this.leftBarrier = leftBarrier;
        this.rightBarrier = rightBarrier;
        
        setStationary(stationary);
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Drawing">
    private Color fillColor;
    private Color borderColor;
    
    private boolean drawBarriers;
    
    public void draw(Graphics graphics){
        graphics.setColor(fillColor);
        graphics.fillRect(x, y, width, height);
        
        graphics.setColor(borderColor);
        graphics.drawRect(x, y, width, height);
        
<<<<<<< HEAD
//        graphics.setColor(Color.RED);
        if (isDrawBarriers()){
            for(Barrier barrier : getBarriers()){
                barrier.setColor(Color.RED);
                barrier.draw(graphics);
            }
        }
=======

        if (isDrawBarriers()){
            for(Barrier barrier : getBarriers()){
                barrier.draw(graphics);
            }
        }

>>>>>>> origin/master
    }
    
    /**
     * @return the fillColor
     */
    public Color getFillColor() {
        return fillColor;
    }
    
    /**
     * @param fillColor the fillColor to set
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
    
    /**
     * @return the borderColor
     */
    public Color getBorderColor() {
        return borderColor;
    }
    
    /**
     * @param borderColor the borderColor to set
     */
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }
    
    /**
     * @return the drawBarriers
     */
    public boolean isDrawBarriers() {
        return drawBarriers;
    }
    
    /**
     * @param drawBarriers the drawBarriers to set
     */
    public void setDrawBarriers(boolean drawBarriers) {
        this.drawBarriers = drawBarriers;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private boolean topBarrier;
    private boolean bottomBarrier;
    private boolean leftBarrier;
    private boolean rightBarrier;
    
    private boolean stationary;
    
    /**
     * @return the topBarrier
     */
    public boolean hasTopBarrier() {
        return topBarrier;
    }
    
    /**
     * @param topBarrier the topBarrier to set
     */
    public void setTopBarrier(boolean topBarrier) {
        this.topBarrier = topBarrier;
        
        if (stationary){
            updateBarriers();
        }
    }
    
    /**
     * @return the bottomBarrier
     */
    public boolean hasBottomBarrier() {
        return bottomBarrier;
    }
    
    /**
     * @param bottomBarrier the bottomBarrier to set
     */
    public void setBottomBarrier(boolean bottomBarrier) {
        this.bottomBarrier = bottomBarrier;
        
        if (stationary){
            updateBarriers();
        }
    }
    
    /**
     * @return the leftBarrier
     */
    public boolean hasLeftBarrier() {
        return leftBarrier;
    }
    
    /**
     * @param leftBarrier the leftBarrier to set
     */
    public void setLeftBarrier(boolean leftBarrier) {
        this.leftBarrier = leftBarrier;
        
        if (stationary){
            updateBarriers();
        }
    }
    
    /**
     * @return the rightBarrier
     */
    public boolean hasRightBarrier() {
        return rightBarrier;
    }
    
    /**
     * @param rightBarrier the rightBarrier to set
     */
    public void setRightBarrier(boolean rightBarrier) {
        this.rightBarrier = rightBarrier;
        
        if (stationary){
            updateBarriers();
        }
    }
    
    ArrayList<Barrier> barriers;
    
    public ArrayList<Barrier> getBarriers(){
        if (stationary){
            return barriers;
        } else {
            return getUpdatedBarriers();
        }
    }
    
    private ArrayList<Barrier> getUpdatedBarriers() {
        ArrayList<Barrier> updatedBarriers = new ArrayList<>();
        
        if (hasTopBarrier()){
            updatedBarriers.add(new Barrier(new Point (x + 2 , y - 1), width - 4, 2, BarrierType.FLOOR));
        }
        
        if (hasBottomBarrier()){
            updatedBarriers.add(new Barrier(new Point(x + 2 , y + (height - 2 )), width - 4, 2, BarrierType.CEILING));
        }
        
        if (hasLeftBarrier()){
            updatedBarriers.add(new Barrier(new Point (x - 1, y + 2), 2, height - 4, BarrierType.LEFT_WALL));
        }
        
        
        if (hasRightBarrier()){
            updatedBarriers.add(new Barrier(new Point(x + (width  - 2), y + 2), 2, height - 4 , BarrierType.RIGHT_WALL));
        }

        return updatedBarriers;
    }
    
    private void updateBarriers() {
        barriers = getUpdatedBarriers();
    }

    /**
     * @return the stationary
     */
    public boolean isStationary() {
        return stationary;
    }

    /**
     * @param stationary the stationary to set
     */
    public final void setStationary(boolean stationary) {
        this.stationary = stationary;
        
        if (stationary){
            updateBarriers();
        }
    }
    
//</editor-fold>
 
}
