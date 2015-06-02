/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oblivion;

import audio.AudioPlayer;
import environment.Direction;
import environment.Environment;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import audio.AudioEvent;
import audio.AudioEventListenerIntf;
import audio.SoundManager;
import audio.Source;
import audio.Track;
import audio.Playlist;

/**
 *
 * @author marieleger
 */
class OblivionEnvironment extends Environment {

    private GameState gameLevel = GameState.START;

//    private Clip clip;
//    private ArrayList<Barrier> barriers;
//    private ArrayList<Letter> letters;
    private SoundManager soundManager;
    private AudioEventListenerIntf audioEventListener;
    private Level level;

//    BlockLetterI letterI;
    @Override
    public void initializeEnvironment() {
        this.setBackground(ResourceTools.loadImageFromResource("resources/starstree.jpg").getScaledInstance(1000, 700, Image.SCALE_SMOOTH));

        Playlist myPlaylist = new Playlist(getTracks());
        soundManager = new SoundManager(myPlaylist, new AudioEventListener());

        AudioPlayer.play("/resources/sadnessMusic.wav", 3);
    }

    @Override
    public void timerTaskHandler() {
        checkIntersections();

        if ((level != null) && (level.getLetterI() != null)) {
            level.getLetterI().move();
        }
    }

    private static final String SAD_SOUND = "Sad";
    private static final String WIN_SOUND = "Win";
    private static final String JUMP_SOUND = "Jump";
    private static final String DOWN_SOUND = "Down";

    private ArrayList<Track> getTracks() {
        ArrayList<Track> tracks = new ArrayList<>();

        tracks.add(new Track(SAD_SOUND, Source.RESOURCE, "/resources/sadnessMusic.wav"));
        tracks.add(new Track(WIN_SOUND, Source.RESOURCE, "/resources/win.wav"));
        tracks.add(new Track(JUMP_SOUND, Source.RESOURCE, "/resources/mario_jumping.wav"));
        tracks.add(new Track(DOWN_SOUND, Source.RESOURCE, "/resources/down.wav"));

        return tracks;

//        if ((level != null) && (level.getLetterI() != null)) {
////            for (Letter letter : level.getLetters()) {
//            level.getLetterI().move();
////            }
//        }
    }

    private class AudioEventListener implements AudioEventListenerIntf {

//        @Override
        public void onAudioEvent(AudioEvent event, String trackName) {

        }

    }

    private void checkIntersections() {
        if (level != null) {
            boolean upBlocked = false;
            boolean downBlocked = false;
            boolean leftBlocked = false;
            boolean rightBlocked = false;

//
            for (Barrier barrier : level.getBarriers()) {
                for (Barrier letterBarrier : level.getLetterI().getBarriers()) {
                    if (barrier.intersects(letterBarrier)) {
                        // assess the nature of the intersection (barrier type) 
                        // stop the appropriate motion

//                            System.out.println(" Intersect");
//                            System.out.printf("   B [%d, %d, %d, %d] %s\n", barrier.x, barrier.y, barrier.width, barrier.height, barrier.getType().toString());
//                            System.out.printf("   LB[%d, %d, %d, %d] %s\n", letterBarrier.x, letterBarrier.y, letterBarrier.width, letterBarrier.height, letterBarrier.getType().toString());
                        //check down blocks
                        if (!downBlocked && (barrier.getType() == BarrierType.FLOOR)) {
                            if (letterBarrier.getType() == BarrierType.CEILING) {

                                downBlocked = true;
//                                System.out.println("Down Blocked");
                            }
                        }

                        //check up blocks
                        if (!upBlocked && (barrier.getType() == BarrierType.CEILING)) {
                            if (letterBarrier.getType() == BarrierType.FLOOR) {
                                upBlocked = true;
//                                System.out.println("Up Blocked");
                            }
                        }

                        //check right blocks
                        if (!rightBlocked && (barrier.getType() == BarrierType.LEFT_WALL)) {
                            if (letterBarrier.getType() == BarrierType.RIGHT_WALL) {
                                rightBlocked = true;
//                                System.out.println("Right Blocked");
                            }
                        }

                        //check left blocks
                        if (!leftBlocked && (barrier.getType() == BarrierType.RIGHT_WALL)) {
                            if (letterBarrier.getType() == BarrierType.LEFT_WALL) {
                                leftBlocked = true;
//                                System.out.println("Left Blocked");

//                                letterVBlocked |= true;
//                            }
//                        }
//
//                        if (barrier.getType() == BarrierType.CEILING) {
//                            if (letterBarrier.getType() == BarrierType.FLOOR) {
//                                letterVBlocked |= true;
//                            }
//                        }
//
//                        if (barrier.getType() == BarrierType.WALL) {
//                            if (letterBarrier.getType() == BarrierType.WALL) {
//                                letterHBlocked |= true;
//
//                            }
                            }
                        }
                    }

                    level.getLetterI().setBlocked(Direction.UP, upBlocked);
                    level.getLetterI().setBlocked(Direction.DOWN, downBlocked);
                    level.getLetterI().setBlocked(Direction.LEFT, leftBlocked);
                    level.getLetterI().setBlocked(Direction.RIGHT, rightBlocked);

                }
            }
        }
    }
    int speed = 1;

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            level.getLetterI().move(Direction.LEFT, speed);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            level.getLetterI().move(Direction.RIGHT, speed);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if ((level != null) && (level.getLetterI() != null)) {
                level.getLetterI().grow();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if ((level != null) && (level.getLetterI() != null)) {
                level.getLetterI().shrink();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            gameLevel = GameState.PLAYING;
            level = Level.getLevel(1);
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            gameLevel = GameState.START;
        } else if (e.getKeyCode() == KeyEvent.VK_O) {
            gameLevel = GameState.PLAYING;
            level = Level.getLevel(2);
        } else if (e.getKeyCode() == KeyEvent.VK_I) {
            gameLevel = GameState.PLAYING;
            level = Level.getLevel(3);
        } else if (e.getKeyCode() == KeyEvent.VK_U) {
            gameLevel = GameState.PLAYING;
            level = Level.getLevel(4);
        } else if (e.getKeyCode() == KeyEvent.VK_3) {
            soundManager.play(WIN_SOUND);
        } else if (e.getKeyCode() == KeyEvent.VK_4) {
            soundManager.play(JUMP_SOUND);
        } else if (e.getKeyCode() == KeyEvent.VK_5) {
            soundManager.play(DOWN_SOUND);
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {

    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked");
        if (/*gameLevel = GameState.START &&*/ e.getX() > 450 & e.getX() < 600 && e.getY() > 200 & e.getY() < 250) {
            gameLevel = GameState.STORY;
//            level = Level.getLevel(1);
        } else if (e.getX() > 450 & e.getX() < 600 && e.getY() > 200 & e.getY() < 250) {
            gameLevel = GameState.CREDITS;
        } else if (e.getX() > 450 & e.getX() < 600 && e.getY() > 200 & e.getY() < 250) {
            gameLevel = GameState.PLAYING;
            level = Level.getLevel(1);
        }
        
    }


@Override
        public void paintEnvironment(Graphics graphics
    ) {
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
//                graphics.drawString("Instructions", 450, 300);
                graphics.drawString("Credits", 450, 300);
                graphics.drawString("Music", 450, 400);
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
                
                graphics.drawString("Press Enter To Begin", 100, 450);
                break;
//</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="PLAYING">
            case PLAYING:

                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.rotate(Math.toRadians(0));
                this.setBackground(level.getBackgroundImage());

                if (level != null && (level.getLetterI()) != null) {
                    level.getLetterI().draw(graphics);
                }

                if (level != null && (level.getBarriers()) != null) {
                    for (Block block : level.getBlocks()) {
                        block.draw(graphics);
                    }
                     
                    graphics.setFont(level.getTextFont());
                    graphics.setColor(level.getTextColor());
                    graphics.drawString(level.getText(), level.getTextX(), level.getTextY());
                    graphics.drawImage(level.getLetterImage(), level.getLetterX(), level.getLetterY(), level.getLetterWidth(), level.getLetterHeight(), this);

//                   
                }

                break;

//</editor-fold>
        
       
            case CREDITS:
                 g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.rotate(Math.toRadians(0));
                this.setBackground(ResourceTools.loadImageFromResource("resources/stars.png").getScaledInstance(1000, 700, Image.SCALE_SMOOTH));

                
        }
    }

}
