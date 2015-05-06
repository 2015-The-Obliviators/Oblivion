/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oblivion;

import environment.Velocity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author kevin.lawrence
 */
public final class BlockLetterI {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
    position = new Point(0, 0);
    velocity = new Velocity(0, 1);
    
    parts = new ArrayList<>();
    
    maxX = 1;
    maxY = 1;
}
    
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
        
        setPosition(x, y);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Movement Methods">
    public static enum Direction {
        UP, DOWN
    };
    
    public void accelerate(Vector2D accelerationVector) {
        if (!isHBlocked()) {
            int x = getVelocity().x + accelerationVector.x;
            if (Math.abs(x) < maxX) {
                getVelocity().x = x;
            }
        }
        
        if (!isVBlocked()) {
            int y = getVelocity().y + accelerationVector.y;
            if (Math.abs(y) < maxY) {
                getVelocity().y = y;
            }
        }
        
//        this.getVelocity().x += accelerationVector.x;
//        this.getVelocity().y += accelerationVector.y;
    }
    
    public void move() {
        if (getAccelerationProvider() != null) {
            accelerate(getAccelerationProvider().getAcceleration());
        }
        
        move(getVelocity().x, getVelocity().y);
    }
    
    public void move(environment.Direction direction, int distance) {
        int x = 0;
        int y = 0;
        
        switch (direction) {
            case LEFT:
                x = -1 * distance;
                break;
            case RIGHT:
                x = 1 * distance;
                break;
            case UP:
                y = -1 * distance;
                break;
            case DOWN:
                y = 1 * distance;
                break;
        }
        
        move(x, y);
    }
    
    public void move(int x, int y) {
        Point newPosition = (Point) getPosition().clone();
        newPosition.x += x;
        newPosition.y += y;
        setPosition(newPosition);
    }
    
    private void stop() {
        velocity.x = 0;
        velocity.y = 0;
    }

    public void grow(Direction direction) {
        stem.height += 1;
        
        if (direction == Direction.UP) {
            setPosition(getPosition().x, getPosition().y - 1);
        } else {
            setPosition(getPosition().x, getPosition().y);
        }
    }
    
    public void shrink(Direction direction) {
        stem.height = Math.max(stem.height -1, 0);
        
        if (direction == Direction.UP) {
            setPosition(getPosition().x, getPosition().y + 1);
        } else {
            setPosition(getPosition().x, getPosition().y);
        }
//        if (direction == Direction.DOWN) {
//            setLocation(getX(), y + 1);
//        } else {
//            setLocation(getX(), y);
//        }
    }
//</editor-fold>
       
//<editor-fold defaultstate="collapsed" desc="Painting">
    public void paint(Graphics graphics) {
        parts.stream().forEach((part) -> {
            part.paint(graphics);
        });
    }
//</editor-fold>
   
//<editor-fold defaultstate="collapsed" desc="Properties">
    private int maxX;
    private int maxY;
    private Point position;
    private Velocity velocity;
    
    private ArrayList<BlockLetterPart> parts;
    private BlockLetterPart topBar, stem, bottomBar;
    private AccelerationProviderIntf accelerationProvider;
    
    private boolean vBlocked = false;
    private boolean hBlocked = false;
    
    /**
     * @return collection of all Barrier objects, constructed form the 
     * constituent BlockLetterParts
     */
    public ArrayList<Barrier> getBarriers(){
        ArrayList<Barrier> barriers = new ArrayList<>();
        
        parts.stream().forEach((part) -> {
            barriers.addAll(part.getBarriers());
        });
        
        return barriers;
    }

    
    /**
     * @param accelerationProvider the accelerationProvider to set
     */
    public void setAccelerationProvider(AccelerationProviderIntf accelerationProvider) {
        this.accelerationProvider = accelerationProvider;
    }
    
    /**
     * @return the vBlocked
     */
    public boolean isVBlocked() {
        return vBlocked;
    }
    
    /**
     * @param blocked the vBlocked to set
     */
    public void setVBlocked(boolean blocked) {
        this.vBlocked = blocked;

        if (blocked){
            stop();
        }
    }
    
    /**
     * @return the hBlocked
     */
    public boolean isHBlocked() {
        return hBlocked;
    }
    
    /**
     * @param blocked the hBlocked to set
     */
    public void setHBlocked(boolean blocked) {
        this.hBlocked = blocked;

        if (blocked){
            stop();
        }
    }
    
    /**
     * @return the position
     */
    public Point getPosition() {
        return position;
    }
    
    /**
     * @param position the position to set
     */
    public void setPosition(Point position) {
        this.position = position;
        topBar.setLocation(position.x, position.y);
    }
    
    /**
     * @param x
     * @param y
     */
    public void setPosition(int x, int y) {
        setPosition(new Point(x, y));
    }
    
    /**
     * @return the velocity
     */
    public Velocity getVelocity() {
        return velocity;
    }
    
    /**
     * @param velocity the velocity to set
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }
    
    /**
     * @return the accelerationProvider
     */
    public AccelerationProviderIntf getAccelerationProvider() {
        return accelerationProvider;
    }
//</editor-fold>
    
}
