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

/**
 *
 * @author kevin.lawrence
 */
public class Barrier extends Rectangle {
    
//<editor-fold defaultstate="collapsed" desc="Constructors">
    public Barrier(Point position, int width, int height, BarrierType type){
        super(position.x, position.y, width, height);
        this.type = type;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Methods">
    public void draw(Graphics graphics){
        graphics.setColor(getColor());
        graphics.fillRect(x, y, width, height);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    private BarrierType type;
    private Color color;
    
    /**
     * @return the type
     */
    public BarrierType getType() {
        return type;
    }
    
    /**
     * @param type the type to set
     */
    public void setType(BarrierType type) {
        this.type = type;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }
//</editor-fold>
    
}
