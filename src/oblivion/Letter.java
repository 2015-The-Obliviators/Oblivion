/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oblivion;

import environment.Actor;
import environment.Direction;
import environment.Velocity;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author kevin.lawrence
 */
public abstract class Letter extends Actor implements ParentPositionProviderIntf {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        barriers = new HashMap<>();
        parts = new HashMap<>();
    }

    public Letter(Point position, Velocity velocity) {
        super(position, velocity);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Methods">
    int maxX = 3;
    int maxy = 2;

    public void accelerate(Vector2D accelerationVector) {
        if (!hBlocked) {
            int x = getVelocity().x + accelerationVector.x;
            if (Math.abs(x) < maxX) {
                getVelocity().x = x;
            }
        }

        if (!vBlocked) {
            int y = getVelocity().y + accelerationVector.y;
            if (Math.abs(y) < maxX) {
                getVelocity().y = y;
            }
        }

//        this.getVelocity().x += accelerationVector.x;
//        this.getVelocity().y += accelerationVector.y;
    }

    @Override
    public void move() {
        if (getAccelerationProvider() != null) {
            accelerate(getAccelerationProvider().getAcceleration());
        }

        move(getVelocity().x, getVelocity().y);
    }

    public void move(Direction direction, int distance) {
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

    @Override
    public abstract void paint(Graphics graphics);
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    private LetterPart currentFloor;

    private AccelerationProvider accelerationProvider;
    protected HashMap<String, ChildBarrier> barriers;
    protected HashMap<String, LetterPart> parts;

    protected boolean debug = true;
    private boolean vBlocked = false;
    private boolean hBlocked = false;

    @Override
    public void setPosition(Point position) {
        super.setPosition(position);

        // adjust the position of all the component parts: the parent has moved,
        // therefore the parts (and barriers!) must move
        for (Entry<String, LetterPart> letterPartMap : getLetterParts()) {
            LetterPart letterPart = letterPartMap.getValue();
            letterPart.getRectangle().setLocation(position.x + letterPart.getParentOffset().x, position.y + letterPart.getParentOffset().y);
        }

        for (Entry<String, ChildBarrier> barrierMap : getBarriers()) {
            barrierMap.getValue().updatePosition();
        }
    }

    public Set<Entry<String, LetterPart>> getLetterParts() {
        return parts.entrySet();
    }

    public Set<Entry<String, ChildBarrier>> getBarriers() {
        return barriers.entrySet();
    }

    /**
     * @return the currentFloor
     */
    public LetterPart getCurrentFloor() {
        return currentFloor;
    }

    /**
     * @param currentFloor the currentFloor to set
     */
    public void setCurrentFloor(LetterPart currentFloor) {
        this.currentFloor = currentFloor;
    }

    /**
     * @return the blocked
     */
    public boolean isVBlocked() {
        return vBlocked;
    }

    /**
     * @param blocked the blocked to set
     */
    public void setVBlocked(boolean blocked) {
        this.vBlocked = blocked;
        
        if (blocked){
            stop();
        }
    }

    /**
     * @return the blocked
     */
    public boolean isHBlocked() {
        return hBlocked;
    }

    /**
     * @param blocked the blocked to set
     */
    public void setHBlocked(boolean blocked) {
        this.hBlocked = blocked;
        
        if (blocked){
            stop();
        }
    }

    /**
     * @return the accelerationProvider
     */
    public AccelerationProvider getAccelerationProvider() {
        return accelerationProvider;
    }

    /**
     * @param accelerationProvider the accelerationProvider to set
     */
    public void setAccelerationProvider(AccelerationProvider accelerationProvider) {
        this.accelerationProvider = accelerationProvider;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="ParentPositionProviderIntf Interface Methods">
    @Override
    public Point getParentPosition() {
        return this.getPosition();
    }
//</editor-fold>

}
