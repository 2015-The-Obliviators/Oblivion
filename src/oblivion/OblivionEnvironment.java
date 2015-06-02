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
class OblivionEnvironment extends Environment implements SoundEventHandlerIntf {

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
//        soundManager = new SoundManager(myPlaylist, new AudioEventListener());
        soundManager = new SoundManager(myPlaylist);

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
    private static final String GONG_SOUND = "Gong";
    private static final String BELL_SOUND = "Bell";
    private static final String THUNDER_SOUND = "Thunder";

    private ArrayList<Track> getTracks() {
        ArrayList<Track> tracks = new ArrayList<>();

        tracks.add(new Track(SAD_SOUND, Source.RESOURCE, "/resources/sadnessMusic.wav"));
        tracks.add(new Track(WIN_SOUND, Source.RESOURCE, "/resources/win.wav"));
        tracks.add(new Track(JUMP_SOUND, Source.RESOURCE, "/resources/mario_jumping.wav"));
        tracks.add(new Track(DOWN_SOUND, Source.RESOURCE, "/resources/down.wav"));
        tracks.add(new Track(GONG_SOUND, Source.RESOURCE, "/resources/Asian_Gong_Hit.wav"));
        tracks.add(new Track(BELL_SOUND, Source.RESOURCE, "/resources/Metal_Gong.wav"));
        tracks.add(new Track(THUNDER_SOUND, Source.RESOURCE, "/resources/Thunder.wav"));

        return tracks;
    }

//<editor-fold defaultstate="collapsed" desc="SoundEventHandlerIntf">
    private static final String EVENT_COLLISION = "Collision";
    private static final String EVENT_CHANGE = "Change";

    @Override
    public void onEvent(String event) {
        String trackName = "";

        if (event.equals(EVENT_COLLISION)) {
            trackName = GONG_SOUND;
        } else if (event.equals(EVENT_CHANGE)) {
            trackName = BELL_SOUND;
        }

        if (soundManager != null) {
            soundManager.play(trackName);
        }
    }
//</editor-fold>

//    private class AudioEventListener implements AudioEventListenerIntf {
////        @Override
//        public void onAudioEvent(AudioEvent event, String trackName) {
//
//        }
//    }
    private void checkIntersections() {
        if (level != null) {
            boolean letterVBlocked;
            boolean letterHBlocked;

//            for (Letter letter : level.getLetterI()) {
            letterVBlocked = false;
            letterHBlocked = false;

            for (Barrier barrier : level.getBarriers()) {

                for (Barrier letterBarrier : level.getLetterI().getBarriers()) {
                    if (barrier.intersects(letterBarrier)) {
                        // assess the nature of the intersection (barrier type) 
                        // stop the appropriate motion

//                            System.out.println(" Intersect");
//                            System.out.printf("   B [%d, %d, %d, %d] %s\n", barrier.x, barrier.y, barrier.width, barrier.height, barrier.getType().toString());
//                            System.out.printf("   LB[%d, %d, %d, %d] %s\n", letterBarrier.x, letterBarrier.y, letterBarrier.width, letterBarrier.height, letterBarrier.getType().toString());
                        if (barrier.getType() == BarrierType.FLOOR) {
                            if (letterBarrier.getType() == BarrierType.CEILING) {
                                letterVBlocked |= true;
                                System.out.println("V Blocked");

                            }
                        }
                        if (barrier.getType() == BarrierType.CEILING) {
                            if (letterBarrier.getType() == BarrierType.FLOOR) {
                                letterVBlocked |= true;
                                System.out.println("V Blocked");
                            }
                        }
                        if (barrier.getType() == BarrierType.WALL) {
                            if (letterBarrier.getType() == BarrierType.WALL) {
                                letterHBlocked |= true;
                                System.out.println("H Blocked");
                            }
                        }
//                        }
                    }
                }

                level.getLetterI().setVBlocked(letterVBlocked);
                level.getLetterI().setHBlocked(letterHBlocked);
            }
        }
    }
    int speed = 6;

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            level.getLetterI().move(Direction.LEFT, speed);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            level.getLetterI().move(Direction.RIGHT, speed);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if ((level != null) && (level.getLetterI() != null)) {
                level.getLetterI().grow(BlockLetterI.Direction.UP);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if ((level != null) && (level.getLetterI() != null)) {
                level.getLetterI().shrink(BlockLetterI.Direction.DOWN);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            gameLevel = GameState.STORY;
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            gameLevel = GameState.START;
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            gameLevel = GameState.PLAYING;
            level = Level.getLevel(1);
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
        } else if (e.getKeyCode() == KeyEvent.VK_6) {
            soundManager.play(THUNDER_SOUND);
//            soundManager.play(GONG_SOUND);
        } else if (e.getKeyCode() == KeyEvent.VK_7) {
//            soundManager.play(BELL_SOUND);
            onEvent(EVENT_CHANGE);
        } else if (e.getKeyCode() == KeyEvent.VK_8) {
            onEvent(EVENT_COLLISION);
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {

    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {

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
                this.setBackground(level.getBackgroundImage());

                if (level != null && (level.getLetterI()) != null) {
                    level.getLetterI().draw(graphics);
                }

                if (level != null && (level.getBarriers()) != null) {
                    for (Barrier barrier : level.getBarriers()) {
                        barrier.draw(graphics);

                    }

                    graphics.setFont(level.getTextFont());
                    graphics.setColor(level.getTextColor());
                    graphics.drawString(level.getText(), level.getTextX(), level.getTextY());

//                   
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

//    public void playSound() {
////make sounds when:
////level changes, key press 6
////collision occurs, key press 7
////finds new letter
//        if (letterVBlocked != true){
//        play tracks.add(new Track(BELL_SOUND, Source.RESOURCE, "/resources/Metal_Gong.wav"));
//        }
//        else if letterHBlocked != true{
//        play tracks.add(new Track(BELL_SOUND, Source.RESOURCE, "/resources/Metal_Gong.wav"));
//    }
}
