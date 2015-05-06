/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oblivion;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author kevin.lawrence
 */
public class BlockLetterPart extends Block implements ConnectionListenerIntf {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
    connectionListeners = new ArrayList<>();
}
    
    public BlockLetterPart(int x, int y, int width, int height) {
        super(x, y, width, height, false);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    @Override
    public void setLocation(Point location){
        super.setLocation(location);
        onAfterUpdatePosition();
    }
    
    @Override
    public void setLocation(int x, int y){
        super.setLocation(x, y);
        onAfterUpdatePosition();
    }
    
    private void onAfterUpdatePosition(){
        connectionListeners.stream().forEach((listener) -> {
            listener.onUpdate(this);
        });
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Connection Listeners">
    private ArrayList<ConnectionListenerIntf> connectionListeners;
    
    /**
     * @return the connectionProvider
     */
    public ArrayList<ConnectionListenerIntf> getConnectionListeners() {
        return connectionListeners;
    }
    
    /**
     * @param listener the ConnectionListenter to add
     */
    public void registerConnectionListeners(ConnectionListenerIntf listener) {
        connectionListeners.add(listener);
    }
    
    /**
     * @param listener the ConnectionListenter to add
     */
    public void deregisterConnectionListeners(ConnectionListenerIntf listener) {
        connectionListeners.remove(listener);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="ConnectionListenerIntf">
    private ConnectionUpdateHandlerIntf connectionUpdateHandler;

    /**
     * @return the connectionUpdateHandler
     */
    public ConnectionUpdateHandlerIntf getConnectionUpdateHandler() {
        return connectionUpdateHandler;
    }

    /**
     * @param connectionUpdateHandler the connectionUpdateHandler to set
     */
    public void setConnectionUpdateHandler(ConnectionUpdateHandlerIntf connectionUpdateHandler) {
        this.connectionUpdateHandler = connectionUpdateHandler;
    }
    
    @Override
    public void onUpdate(Rectangle connectorRect) {
        if (getConnectionUpdateHandler() != null){
            getConnectionUpdateHandler().onUpdate(connectorRect, this);
        }
    }
//</editor-fold>

    
}
