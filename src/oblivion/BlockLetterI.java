/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oblivion;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author kevin.lawrence
 */
public final class BlockLetterI extends BlockLetter {
    
    //TODO: need left and right barrier, and left and right blocked to allow 
    //      horizontal motion when blocked one side
    //      do the same for UP and DOWN blocked
    //TODO: consider level with Block instead of barriers, compute barrier
    //TODO: optimize checkCollisions, bail out when VBlock or HBlock is found
    

//<editor-fold defaultstate="collapsed" desc="Constructors">
    public BlockLetterI(int x, int y, int width, int height, boolean stationary,
            AccelerationProviderIntf accelerationProvider) {
        super(x, y, width, height, stationary, accelerationProvider);
        
        topBar = new BlockLetterPart(x, y, width / 2, height / 10);
        stem = new BlockLetterPart(x, y, width / 6, height * 6 / 10);
        bottomBar = new BlockLetterPart(x, y, width / 2, height / 10);
        
        topBar.setFillColor(Color.BLUE);
        stem.setFillColor(Color.BLUE);
        bottomBar.setFillColor(Color.BLUE);
        
        getParts().add(topBar);
        getParts().add(stem);
        getParts().add(bottomBar);
        
        stem.setConnectionUpdateHandler(new ConnectionUpdateHandlerIntf() {
            @Override
            public void onUpdate(Rectangle connector, Rectangle connected) {
                connected.setLocation(connector.x + (connector.width / 2) - (connected.width / 2), connector.y + connector.height);
            }
        });
        
        bottomBar.setConnectionUpdateHandler(new ConnectionUpdateHandlerIntf() {
            @Override
            public void onUpdate(Rectangle connector, Rectangle connected) {
                connected.setLocation(connector.x + (connector.width / 2) - (connected.width / 2), connector.y + connector.height);
            }
        });
        
        topBar.registerConnectionListeners(stem);
        stem.registerConnectionListeners(bottomBar);
        
        setPosition(x, y);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Movement Methods">
    @Override
    public void grow(Direction direction) {
        stem.height += 1;
        
        if (direction == Direction.UP) {
            setPosition(getPosition().x, getPosition().y - 1);
        } else {
            setPosition(getPosition().x, getPosition().y);
        }

        setVBlocked(false);
    }
    
    @Override
    public void shrink(Direction direction) {
        stem.height = Math.max(stem.height -1, 0);
        
        if (direction == Direction.UP) {
            setPosition(getPosition().x, getPosition().y + 1);
        } else {
            setPosition(getPosition().x, getPosition().y);
        }

        setVBlocked(false);
    }
//</editor-fold>
       
//<editor-fold defaultstate="collapsed" desc="Properties">
    private BlockLetterPart topBar, stem, bottomBar;
    
    /**
     * @param position the position to set
     */
    @Override
    public void setPosition(Point position) {
        super.setPosition(position);
        topBar.setLocation(position.x, position.y);
    }

//</editor-fold>
    
}
