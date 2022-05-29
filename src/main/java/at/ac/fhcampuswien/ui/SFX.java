package at.ac.fhcampuswien.ui;

import javafx.scene.media.AudioClip;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class SFX {


    String clickPath = "src/main/resources/mixkit-plastic-bubble-click-1124.mp3";
    AudioClip click = new AudioClip(new File(clickPath).toURI().toString());

    String hoverPath = "src/main/resources/beep-rollover-13-sound-effect-49884034.mp3";
    AudioClip hover = new AudioClip((new File(hoverPath).toURI().toString()));

    String meowPath = "src/main/resources/meow.mp3";
    AudioClip meow = new AudioClip((new File(meowPath).toURI().toString()));

    String purrPath = "src/main/resources/CatPurr.mp3";
    AudioClip purr = new AudioClip((new File(purrPath).toURI().toString()));

    public void playClick(){
        click.setVolume(0.3);
        click.play();
    }

    public void playHover(){
        hover.setVolume(0.1);
        hover.play();
    }

    public void playMeow(){
        meow.setVolume(0.1);
        meow.play();
    }

    public void playPurr(){
        purr.setVolume(0.1);
        purr.setCycleCount(AudioClip.INDEFINITE);
        purr.play();
    }

    public void stopPurr(){
        purr.stop();
    }
}
