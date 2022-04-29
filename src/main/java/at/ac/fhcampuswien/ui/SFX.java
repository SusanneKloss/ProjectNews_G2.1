package at.ac.fhcampuswien.ui;

import javafx.scene.media.AudioClip;

import java.io.File;

public class SFX {


    String clickPath = "src/main/resources/mixkit-plastic-bubble-click-1124.mp3";
    AudioClip click = new AudioClip(new File(clickPath).toURI().toString());

    String hoverPath = "src/main/resources/beep-rollover-13-sound-effect-49884034.mp3";
    AudioClip hover = new AudioClip((new File(hoverPath).toURI().toString()));

    public void playClick(){
        click.setVolume(0.3);
        click.play();
    }

    public void playHover(){
        hover.setVolume(0.1);
        hover.play();
    }


}