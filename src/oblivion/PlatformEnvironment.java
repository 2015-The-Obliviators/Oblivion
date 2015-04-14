/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformphysics;

import environment.Direction;
import environment.Environment;
import environment.Velocity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map.Entry;

/**
 *
 * @author kevin.lawrence
 */
class PlatformEnvironment extends Environment implements AccelerationProvider {

    private ArrayList<Barrier> barriers;
    private ArrayList<Letter> letters;
    private ArrayList<Letter> constraints;

    {
        barriers = new ArrayList<>();
        barriers.add(new Barrier(new Point(10, 500), 1000, 10, BarrierType.FLOOR));
        barriers.add(new Barrier(new Point(60, 100), 200, 2, BarrierType.FLOOR));
        barriers.add(new Barrier(new Point(0, 10), 200, 2, BarrierType.CEILING));
        
        barriers.add(new Barrier(new Point(100, 10), 2, 200, BarrierType.WALL));

        letters = new ArrayList<>();
        letters.add(new LetterI(new Point(10, 40), new Velocity(0, 0)));

        for (Letter letter : letters) {
            letter.setAccelerationProvider(this);
        }

    }

    @Override
    public void initializeEnvironment() {
        this.setBackground(Color.BLUE);
    }

    @Override
    public void timerTaskHandler() {
        checkIntersections();

        if (letters != null) {
            for (Letter letter : letters) {
                letter.move();
            }
        }
    }

    private void checkIntersections() {
        boolean letterVBlocked = false;
        boolean letterHBlocked = false;

        for (Letter letter : letters) {
            letterVBlocked = false;
            letterHBlocked = false;

            for (Barrier barrier : barriers) {
                for (Entry<String, ChildBarrier> letterBarrier : letter.getBarriers()) {
                    if (barrier.intersects(letterBarrier.getValue())) {
                        // assess the nature of the intersection (barrier type) 
                        // stop the appropriate motion
                        if (barrier.getType() == BarrierType.FLOOR) {
                            if (letterBarrier.getValue().getType() == BarrierType.CEILING) {
                                letterVBlocked |= true;
                            }
                        }
                        if (barrier.getType() == BarrierType.CEILING) {
                            if (letterBarrier.getValue().getType() == BarrierType.FLOOR) {
                                letterVBlocked |= true;
                            }
                        }
                        if (barrier.getType() == BarrierType.WALL) {
                            if (letterBarrier.getValue().getType() == BarrierType.WALL) {
                                letterHBlocked |= true;
                            }
                        }
                    }
                }
            }

            letter.setVBlocked(letterVBlocked);
            letter.setHBlocked(letterHBlocked);
            //optimization... don't need to check other barriers if blocked
//            if (letterBlocked) {
//                break;
//            }
        }
    }

    int speed = 6;

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            letters.stream().forEach((letter) -> {
                letter.move(Direction.LEFT, speed);
            });
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            for (Letter letter : letters) {
                letter.move(Direction.RIGHT, speed);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            for (Letter letter : letters) {
                letter.move(Direction.UP, speed);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            for (Letter letter : letters) {
                letter.move(Direction.DOWN, speed);
            }
        }

    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {

    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {

    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (letters != null) {
            for (Letter letter : letters) {
                letter.paint(graphics);
            }
        }

        if (barriers != null) {
            for (Barrier barrier : barriers) {
                barrier.paint(graphics);
            }
        }
    }

//<editor-fold defaultstate="collapsed" desc="AccelerationProvider">
    private Vector2D gravity = new Vector2D(0, 1);

    @Override
    public Vector2D getAcceleration() {
        return gravity;
    }
//</editor-fold>

}
