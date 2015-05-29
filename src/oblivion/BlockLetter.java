/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oblivion;

import environment.Direction;
import environment.Velocity;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.EnumSet;

/**
 *
 * @author kevin.lawrence
 */
public abstract class BlockLetter {

//<editor-fold defaultstate="collapsed" desc="Movement Methods">
    public void accelerate(Vector2D accelerationVector) {
        if (((accelerationVector.x < 0) && (!isBlocked(Direction.LEFT))) ||
            ((accelerationVector.x > 0) && (!isBlocked(Direction.RIGHT)))) {
            int x = getVelocity().x + accelerationVector.x;
            if (Math.abs(x) < getMaxX()) {
                getVelocity().x = x;
            }
        }

        if (((accelerationVector.y < 0) && (!isBlocked(Direction.UP))) ||
            ((accelerationVector.y > 0) && (!isBlocked(Direction.DOWN)))) {
            int y = getVelocity().y + accelerationVector.y;
            if (Math.abs(y) < getMaxY()) {
                getVelocity().y = y;
            }
        }
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
<<<<<<< HEAD
                if (!isBlocked(Direction.LEFT)){
                    x = -distance;
                }
=======
                x = -3 * distance;
>>>>>>> bim-physics-08
                break;
                
            case RIGHT:
<<<<<<< HEAD
                if (!isBlocked(Direction.RIGHT)){
                    x = distance;
                }
=======
                x = 3 * distance;
>>>>>>> bim-physics-08
                break;
                
            case UP:
<<<<<<< HEAD
                if (!isBlocked(Direction.UP)){
                    y = -distance;
                }
=======
                y = -3 * distance;
>>>>>>> bim-physics-08
                break;
                
            case DOWN:
<<<<<<< HEAD
                if (!isBlocked(Direction.DOWN)){
                    y = distance;
                }
=======
                y = 3 * distance;
                break;
>>>>>>> bim-physics-08
        }

        move(x, y);
    }

    public void move(int x, int y) {
        Point newPosition = (Point) getPosition().clone();
        
        if ((x < 0) && !isBlocked(Direction.LEFT)) {
            newPosition.x += x;
            setBlocked(Direction.RIGHT, false);
        } else if (((x > 0) && !isBlocked(Direction.RIGHT))) {
            newPosition.x += x;
            setBlocked(Direction.LEFT, false);
        }
 
        if ((y < 0) && !isBlocked(Direction.UP)) {
            newPosition.y += y;
            setBlocked(Direction.DOWN, false);
        } else if ((y > 0) && !isBlocked(Direction.DOWN)) {
            newPosition.y += y;
            setBlocked(Direction.UP, false);
        }
        
        setPosition(newPosition);
    }

    private void stop() {
        velocity.x = 0;
        velocity.y = 0;
    }

    public abstract void grow();
    public abstract void shrink();
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Drawing">
    public void draw(Graphics graphics) {
        getParts().stream().forEach((part) -> {
            part.draw(graphics);
        });
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    private int maxX;
    private int maxY;
    private Point position;
    private Velocity velocity;

    private ArrayList<BlockLetterPart> parts;
    private AccelerationProviderIntf accelerationProvider;

//    private boolean vBlocked = false;
//    private boolean hBlocked = false;

    private EnumSet<Direction> blockedDirections;

    /**
     * @param direction
     * @return the blocked state for the provided direction
     */
    public boolean isBlocked(Direction direction) {
        return blockedDirections.contains(direction);
    }

    /**
     * @param direction the direction to add to the blocking list
     * @param blocked
     */
    public void setBlocked(Direction direction, boolean blocked) {
        if (blocked) {
            blockedDirections.add(direction);
        } else {
            blockedDirections.remove(direction);
        }
    }

    /**
     * @param direction the direction to add to the blocking list
     */
<<<<<<< HEAD
    public void addBlock(Direction direction) {
        blockedDirections.add(direction);
=======
    public void setVBlocked(boolean blocked) {
        if (this.vBlocked != blocked){
            this.vBlocked = blocked;
            System.out.println("VBlocked = " + blocked);
        }        
>>>>>>> bim-physics-08
    }

    /**
     * @param direction to release the block from
     */
    public void removeBlock(Direction direction) {
        blockedDirections.remove(direction);
    }

    /**
     * @return collection of all Barrier objects, constructed form the
     * constituent BlockLetterParts
     */
<<<<<<< HEAD
    public ArrayList<Barrier> getBarriers() {
        ArrayList<Barrier> barriers = new ArrayList<>();

        getParts().stream().forEach((part) -> {
            barriers.addAll(part.getBarriers());
        });

        return barriers;
=======
    public void setHBlocked(boolean blocked) {
        if (this.hBlocked != blocked){
            this.hBlocked = blocked;
            System.out.println("HBlocked = " + blocked);
        }        
>>>>>>> bim-physics-08
    }

    /**
     * @param accelerationProvider the accelerationProvider to set
     */
    public void setAccelerationProvider(AccelerationProviderIntf accelerationProvider) {
        this.accelerationProvider = accelerationProvider;
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

    /**
     * @return the maxX
     */
    public int getMaxX() {
        return maxX;
    }

    /**
     * @param maxX the maxX to set
     */
    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    /**
     * @return the maxY
     */
    public int getMaxY() {
        return maxY;
    }

    /**
     * @param maxY the maxY to set
     */
    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    /**
     * @return the parts
     */
    public ArrayList<BlockLetterPart> getParts() {
        return parts;
    }

    /**
     * @param parts the parts to set
     */
    public void setParts(ArrayList<BlockLetterPart> parts) {
        this.parts = parts;
    }

//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        position = new Point(0, 0);
        velocity = new Velocity(0, 1);

        setParts(new ArrayList<>());

        setMaxX(1);
        setMaxY(1);

        blockedDirections = EnumSet.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT);
        blockedDirections.clear();
    }

    public BlockLetter(int x, int y, int width, int height, boolean stationary,
            AccelerationProviderIntf accelerationProvider) {

        this.accelerationProvider = accelerationProvider;
    }
//</editor-fold>

}
