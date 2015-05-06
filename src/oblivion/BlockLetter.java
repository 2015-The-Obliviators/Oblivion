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

/**
 *
 * @author kevin.lawrence
 */
public abstract class BlockLetter {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        position = new Point(0, 0);
        velocity = new Velocity(0, 1);

        setParts(new ArrayList<>());

        setMaxX(1);
        setMaxY(1);
    }

    public BlockLetter(int x, int y, int width, int height, boolean stationary,
            AccelerationProviderIntf accelerationProvider) {

        this.accelerationProvider = accelerationProvider;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Movement Methods">
    public static enum Direction {
        UP, DOWN
    };

    public void accelerate(Vector2D accelerationVector) {
        if (!isHBlocked()) {
            int x = getVelocity().x + accelerationVector.x;
            if (Math.abs(x) < getMaxX()) {
                getVelocity().x = x;
            }
        }

        if (!isVBlocked()) {
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

    public abstract void grow(Direction direction);
    public abstract void shrink(Direction direction);
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Painting">
    public void paint(Graphics graphics) {
        getParts().stream().forEach((part) -> {
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
    private AccelerationProviderIntf accelerationProvider;

    private boolean vBlocked = false;
    private boolean hBlocked = false;

    /**
     * @return collection of all Barrier objects, constructed form the
     * constituent BlockLetterParts
     */
    public ArrayList<Barrier> getBarriers() {
        ArrayList<Barrier> barriers = new ArrayList<>();

        getParts().stream().forEach((part) -> {
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

        if (blocked) {
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

        if (blocked) {
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

}
