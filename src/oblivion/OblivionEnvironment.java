/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oblivion;

import audio.AudioPlayer;
import environment.Direction;
import environment.Environment;
import environment.Velocity;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;
import javax.sound.sampled.Clip;

/**
 *
 * @author marieleger
 */
class OblivionEnvironment extends Environment implements AccelerationProvider {

    private GameState gameLevel = GameState.START;
<<<<<<< HEAD

=======
    private Clip clip;
    private ArrayList<Barrier> barriers;
    private ArrayList<Letter> letters;
>>>>>>> mll-sound-02

    private Level level;
    BlockLetterI letterI;

//    private ArrayList<Letter> constraints;
    {
//        barriers = new ArrayList<>();
//
//        barriers.add(new Barrier(new Point(0, 500), 1000, 100, BarrierType.FLOOR));
//        barriers.add(new Barrier(new Point(0, 0), 1000, 100, BarrierType.CEILING));
//
////      barriers.add(new Barrier(new Point(100, 10), 2, 200, BarrierType.WALL));
//        letters = new ArrayList<>();
//        letters.add(new LetterI(new Point(0, 300), new Velocity(0, 0)));
//
//        for (Letter letter : letters) {
//            letter.setAccelerationProvider(this);
//        }

    }

    @Override
    public void initializeEnvironment() {
        this.setBackground(ResourceTools.loadImageFromResource("resources/starstree.jpg").getScaledInstance(1000, 700, Image.SCALE_SMOOTH));
        
            AudioPlayer.play("/resources/sadnessMusic.wav", 3);
            
        
        
//        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    public void timerTaskHandler() {
//        checkIntersections();

        if ((level != null) && (level.getLetterI() != null)) {
//            for (Letter letter : level.getLetters()) {
                level.getLetterI().move();
//            }
        }
    }

//    private void checkIntersections() {
//        if (level != null) {
//            boolean letterVBlocked = false;
//            boolean letterHBlocked = false;
//
////            for (Letter letter : level.getLetterI()) {
//                letterVBlocked = false;
//                letterHBlocked = false;
//
//                for (Barrier barrier : level.getBarriers()) {
//                    for (Map.Entry<String, ChildBarrier> letterBarrier : level.getLetterI().getBarriers()) {
//                        if (barrier.intersects(letterBarrier.getValue())) {
//                        // assess the nature of the intersection (barrier type) 
//                            // stop the appropriate motion
//                            if (barrier.getType() == BarrierType.FLOOR) {
//                                if (letterBarrier.getValue().getType() == BarrierType.CEILING) {
//                                    letterVBlocked |= true;
//                                }
//                            }
//                            if (barrier.getType() == BarrierType.CEILING) {
//                                if (letterBarrier.getValue().getType() == BarrierType.FLOOR) {
//                                    letterVBlocked |= true;
//                                }
//                            }
//                            if (barrier.getType() == BarrierType.WALL) {
//                                if (letterBarrier.getValue().getType() == BarrierType.WALL) {
//                                    letterHBlocked |= true;
//                                }
//                            }
////                        }
//                    }
//                }
//
//                letter.setVBlocked(letterVBlocked);
//                letter.setHBlocked(letterHBlocked);
//                //optimization... don't need to check other barriers if blocked
////            if (letterBlocked) {
////                break;
////            }
//            }
//        }
//    }

    int speed = 6;

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//            level.getLetterI().stream().forEach((letter) -> {
                level.getLetterI().move(Direction.LEFT, speed);
//            });
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            for (Letter letter : level.getLetters()) {
                level.getLetterI().move(Direction.RIGHT, speed);
//            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if ((level != null) && (level.getLetterI() != null)) {
                level.getLetterI().grow(BlockLetterI.Direction.UP);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if ((level != null) && (level.getLetterI() != null)) {
                level.getLetterI().shrink(BlockLetterI.Direction.DOWN);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            gameLevel = GameState.STORY;
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            gameLevel = GameState.START;
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            gameLevel = GameState.PLAYING;
            level = Level.getLevel(1);
        }
        if (e.getKeyCode() == KeyEvent.VK_O) {
            gameLevel = GameState.PLAYING;
            level = Level.getLevel(2);
        }
        if (e.getKeyCode() == KeyEvent.VK_I) {
            gameLevel = GameState.PLAYING;
            level = Level.getLevel(3);
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
        Graphics2D g2d = (Graphics2D) graphics;
        switch (gameLevel) {

            //<editor-fold defaultstate="collapsed" desc="START">
            case START:

                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.rotate(Math.toRadians(0));
                this.setBackground(ResourceTools.loadImageFromResource("resources/starstree.jpg").getScaledInstance(1000, 700, Image.SCALE_SMOOTH));
                graphics.setColor(new Color(230, 230, 230, 25));
                graphics.setFont(new Font("FOOTLIGHTMT LIGHT", Font.BOLD, 80));
                graphics.drawString("OBLIVION", 450, 100);
                graphics.setColor(new Color(230, 230, 230, 75));
                graphics.setFont(new Font("FOOTLIGHTMT LIGHT", Font.ITALIC, 30));
                graphics.drawString("Start", 450, 200);
                graphics.drawString("Instructions", 450, 300);
                graphics.drawString("Credits", 450, 400);
                graphics.drawString("Music", 450, 500);
                break;
//</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="STORY">
            case STORY:

                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.rotate(Math.toRadians(0));
                this.setBackground(ResourceTools.loadImageFromResource("resources/stars.png").getScaledInstance(1000, 700, Image.SCALE_SMOOTH));

                graphics.setColor(new Color(230, 230, 230, 25));
                graphics.setFont(new Font("FOOTLIGHTMT LIGHT", Font.BOLD, 80));
                graphics.drawString("OBLIVION", 100, 100);
                graphics.setColor(new Color(230, 230, 230, 75));
                graphics.setFont(new Font("FOOTLIGHTMT LIGHT", Font.ITALIC, 30));
                graphics.drawString("Where are we really? This world is nothing.", 100, 200);
                graphics.drawString("It is Oblivion.", 100, 250);
                graphics.drawString("But even Oblivion cannot always stay together.", 100, 300);
                graphics.drawString("What if our world were to break?", 100, 350);
                graphics.drawString("And the complete nothing-ness would scatter.", 100, 400);
                break;
//</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="PLAYING">
            case PLAYING:

                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.rotate(Math.toRadians(0));
                this.setBackground(ResourceTools.loadImageFromResource("resources/stars.png").getScaledInstance(1000, 700, Image.SCALE_SMOOTH));
//                graphics.setColor(new Color(230, 230, 230, 75));
//                graphics.setFont(new Font("FOOTLIGHTMT LIGHT", Font.ITALIC, 30));
//                graphics.drawString("To Move", 375, 250);
//                graphics.setFont(new Font("FOOTLIGHTMT LIGHT", Font.ITALIC, 25));
//
//                graphics.drawString("Use the left and right arrow keys to move backwards and forwards.", 25, 300);

                if (level != null && (level.getLetterI()) != null) {
//                    for (Letter letter : level.getLetterI()) {
                        level.getLetterI().paint(graphics);
//                    }
                }

                if (level != null && (level.getBarriers()) != null) {
                    for (Barrier barrier : level.getBarriers()) {
                        barrier.paint(graphics);
                       
                    }

//                    if (level != null) {
//                    for (Text text : level.getText()) {
//                        text.paint(graphics);
//                    }
                }

                break;

//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="LEVEL 2">
//            case LEVEL_2:
//
//                g2d.setRenderingHint(
//                        RenderingHints.KEY_ANTIALIASING,
//                        RenderingHints.VALUE_ANTIALIAS_ON);
//                g2d.rotate(Math.toRadians(0));
//                this.setBackground(ResourceTools.loadImageFromResource("resources/stars.png").getScaledInstance(1000, 700, Image.SCALE_SMOOTH));
//                graphics.setColor(new Color(230, 230, 230, 75));
//                graphics.setFont(new Font("FOOTLIGHTMT LIGHT", Font.ITALIC, 30));
//                graphics.drawString("To Grow and Shrink", 300, 50);
//                graphics.setFont(new Font("FOOTLIGHTMT LIGHT", Font.ITALIC, 25));
//                graphics.drawString("Use the down and up arrow keys to move change length.", 75, 100);
//                break;
//
////</editor-fold>
            //            //<editor-fold defaultstate="collapsed" desc="LEVEL 3">
//            case LEVEL_3:
//
//                g2d.setRenderingHint(
//                        RenderingHints.KEY_ANTIALIASING,
//                        RenderingHints.VALUE_ANTIALIAS_ON);
//                g2d.rotate(Math.toRadians(0));
//                this.setBackground(ResourceTools.loadImageFromResource("resources/stars.png").getScaledInstance(1000, 700, Image.SCALE_SMOOTH));
//                graphics.setColor(new Color(230, 230, 230, 75));
//                graphics.setFont(new Font("FOOTLIGHTMT LIGHT", Font.ITALIC, 30));
//                graphics.drawString("Look, there's the O!", 300, 50);
//                graphics.setFont(new Font("FOOTLIGHTMT LIGHT", Font.ITALIC, 25));
//                graphics.drawString("", 75, 100);
//                break;
//
//        }
//
////</editor-fold>
        }
    }
    private Vector2D gravity = new Vector2D(0, 1);

    public Vector2D getAcceleration() {
        return gravity;
    }
}
