/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oblivion;

import java.awt.Point;

/**
 *
 * @author kevin.lawrence
 */
public class ChildBarrier extends Barrier {
        
    public ChildBarrier(Point position, int width, int height, BarrierType type, 
             ParentPositionProviderIntf parentPositionProvider, Point offset) {
        super(position, width, height, type);
        this.parentPositionProvider = parentPositionProvider;
        this.offset = offset;
    }
    
    public void updatePosition(){
        if (parentPositionProvider != null){
            this.setLocation(calcPosition(parentPositionProvider.getParentPosition(), offset));
        }
        
    }
    
    private Point calcPosition(Point base, Point offset){
        return new Point(base.x + offset.x, base.y + offset.y);
    }

//<editor-fold defaultstate="collapsed" desc="Properties">
    private ParentPositionProviderIntf parentPositionProvider;
    private Point offset;
    
    /**
     * @return the parentPositionProvider
     */
    public ParentPositionProviderIntf getParentPositionProvider() {
        return parentPositionProvider;
    }
    
    /**
     * @param parentPositionProvider the parentPositionProvider to set
     */
    public void setParentPositionProvider(ParentPositionProviderIntf parentPositionProvider) {
        this.parentPositionProvider = parentPositionProvider;
    }

    /**
     * @return the offset
     */
    public Point getOffset() {
        return offset;
    }

    /**
     * @param offset the offset to set
     */
    public void setOffset(Point offset) {
        this.offset = offset;
    }
//</editor-fold>
}
