/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oblivion;

import environment.Direction;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author kevin.lawrence
 */
public final class BlockLetterI extends BlockLetter {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    public BlockLetterI(int x, int y, int width, int height, boolean stationary,
            AccelerationProviderIntf accelerationProvider) {
        super(x, y, width, height, stationary, accelerationProvider);
        
        topBar = new BlockLetterPart(x, y, width / 2, height / 10);
        stem = new BlockLetterPart(x, y, width / 6, height * 6 / 10);
        bottomBar = new BlockLetterPart(x, y, width / 2, height / 10);
        
        getParts().add(topBar);
        getParts().add(stem);
        getParts().add(bottomBar);
        
        topBar.setDrawBarriers(true);
        stem.setDrawBarriers(true);
        bottomBar.setDrawBarriers(true);

//        for (BlockLetterPart blockLetterPart: getParts()){
//            blockLetterPart.setFillColor(Color.BLUE);
//            blockLetterPart.setBorderColor(Color.BLUE);
//        }
        
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
    public void grow() {
        if (!isBlocked(Direction.DOWN)){
            stem.height += 1;
        } else if (!isBlocked(Direction.UP)) {
            stem.height += 1;
            setPosition(getPosition().x, getPosition().y - 1);
        }
    }
    
    @Override
    public void shrink() {
        if (!isBlocked(Direction.DOWN)){
            stem.height -= 1;
            setPosition(getPosition().x, getPosition().y - 1);
        } else if (!isBlocked(Direction.UP)) {
            stem.height -= 1;
            setPosition(getPosition().x, getPosition().y + 1);
        }
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
