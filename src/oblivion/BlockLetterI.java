/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oblivion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author kevin.lawrence
 */
public final class BlockLetterI {

    public BlockLetterI(int x, int y, int width, int height, boolean stationary, 
            AccelerationProviderIntf accelerationProvider) {
        topBar = new BlockLetterPart(x, y, width, height / 5);
        stem = new BlockLetterPart(x, y, width / 3, height * 3 / 5);
        bottomBar = new BlockLetterPart(x, y, width, height / 5);

        topBar.setFillColor(Color.YELLOW);
        stem.setFillColor(Color.BLUE);
        bottomBar.setFillColor(Color.CYAN);

        parts = new ArrayList<>();
        parts.add(topBar);
        parts.add(stem);
        parts.add(bottomBar);

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
        
        this.accelerationProvider = accelerationProvider;
        
        setLocation(x, y);
    }

    private ArrayList<BlockLetterPart> parts;
    private BlockLetterPart topBar, stem, bottomBar;
    private int x, y;
    private AccelerationProviderIntf accelerationProvider;

    /**
     * @param accelerationProvider the accelerationProvider to set
     */
    public void setAccelerationProvider(AccelerationProviderIntf accelerationProvider) {
        this.accelerationProvider = accelerationProvider;
    }

    
    
    
    
//    Iterable<Map.Entry<String, ChildBarrier>> getBarriers() {
//
//    }

    
    

    public void setLocation(int x, int y) {
        this.setX(x);
        this.y = y;

        topBar.setLocation(x, y);
    }

    void move(environment.Direction direction, int speed) {

    }

    void move() {

    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    public static enum Direction {
        UP, DOWN
    };

    public void grow(Direction direction) {
        stem.height += 1;
        if (direction == Direction.UP) {
            setLocation(getX(), y - 1);
        } else {
            setLocation(getX(), y);
        }
    }

    public void shrink(Direction direction) {
        stem.height = Math.max(stem.height -1, 0);
        if (direction == Direction.DOWN) {
            setLocation(getX(), y + 1);
        } else {
            setLocation(getX(), y);
        }
    }

    public void paint(Graphics graphics) {
        parts.stream().forEach((part) -> {
            part.paint(graphics);
        });
    }
    
//    public 

}
