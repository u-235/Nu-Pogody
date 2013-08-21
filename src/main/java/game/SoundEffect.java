/*
 * http://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
 */

package game;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

/**
 * This enum encapsulates all the sound effects of a game, so as to separate
 * the sound playing codes from the game codes.
 * <p>
 * 1. Define all your sound effect names and the associated wave file.<br>
 * 2. To play a specific sound, simply invoke SoundEffect.SOUND_NAME.play().<br>
 * 3. You might optionally invoke the static method SoundEffect.init() to
 *    pre-load all the sound files, so that the play is not paused while
 *    loading the file for the first time.<br>
 * 4. You can use the static variable SoundEffect.volume to mute the sound.
 */
public enum SoundEffect {
    CATCH("sound/snd_catch.wav"), CRASH("sound/snd_crash.wav"), RESET(
                    "sound/snd_reset.wav"), GAME_OVER("sound/snd_game_over.wav"), MOVE_0(
                    "sound/snd_move0.wav"), MOVE_1("sound/snd_move1.wav"), MOVE_2(
                    "sound/snd_move2.wav"), MOVE_3("sound/snd_move3.wav");

    // Nested class for specifying volume
    public static enum Volume {
        MUTE, LOW, MEDIUM, HIGH
    }

    public static Volume volume = Volume.LOW;

    // Each sound effect has its own clip, loaded with its own sound file.
    private Clip         clip;

    // Constructor to construct each element of the enum with its own sound
    // file.
    SoundEffect(String soundFileName) {
        try {
            // Use URL (instead of File) to read from disk and JAR.
            URL url = this.getClass().getClassLoader()
                            .getResource(soundFileName);
            // Set up an audio input stream piped from the sound file.
            AudioInputStream audioInputStream = AudioSystem
                            .getAudioInputStream(url);
            // Get a clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioInputStream);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Play or Re-play the sound effect from the beginning, by rewinding.
    public void play() {
        if (volume != Volume.MUTE) {
            if (clip.isRunning()) clip.stop(); // Stop the player if it is still
                                               // running
            clip.setFramePosition(0); // rewind to the beginning
            clip.start(); // Start playing
        }
    }

    // Optional static method to pre-load all the sound files.
    static void init() {
        values(); // calls the constructor for all the elements
    }
}
