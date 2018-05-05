package com.sekwah.battleswans.audio;

import javax.sound.sampled.*;
import java.io.File;

public class AudioPlayer {

    private Clip clip;

    // May not be best way of doing all the sounds but itll work for now. See if possible to change to a single channel and
    //  track sounds
    public AudioPlayer(String s) {

        try {

            AudioInputStream ais =
                    AudioSystem.getAudioInputStream(
                            getClass().getResource(s));
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );
            AudioInputStream dais =
                    AudioSystem.getAudioInputStream(
                            decodeFormat, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public AudioPlayer(File file) {

        try {

            AudioInputStream ais =
                    AudioSystem.getAudioInputStream(
                            file.getAbsoluteFile());
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );
            AudioInputStream dais =
                    AudioSystem.getAudioInputStream(
                            decodeFormat, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static void getMixer(){

        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        System.out.println(
                "There are " + mixers.length + " mixer info objects");
        for (Mixer.Info mixerInfo : mixers) {
            System.out.println("mixer name: " + mixerInfo.getName());
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            Line.Info[] lineInfos = mixer.getSourceLineInfo();
            for (Line.Info lineInfo : lineInfos) {
                System.out.println("  Line.Info: " + lineInfo);
                try {
                    Line line = mixer.getLine(lineInfo);
                    FloatControl volCtrl = (FloatControl)line.getControl(
                            FloatControl.Type.MASTER_GAIN);
                    System.out.println(
                            "    volCtrl.getValue() = " + volCtrl.getValue());
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException iaEx) {
                    System.out.println("    " + iaEx);
                }
            }
        }
    }

    public void play() {
        if(clip == null) return;
        //System.out.println("");
        //System.out.println(clip.getMicrosecondLength());
        //System.out.println(clip.isRunning());
        //stop();
        if(clip.isRunning()){
            //if(clip.getMicrosecondLength())
            if(clip.getMicrosecondLength() > 1000000){
                clip.setMicrosecondPosition(0);
            }
            else{
                stop();
                clip.setMicrosecondPosition(0);
                clip.start();
            }
            //clip.start();
        }
        else{
            clip.setMicrosecondPosition(0);
            clip.start();
        }
    }

    public Clip getClip(){
        return clip;

    }

    public void stop() {
        if(clip.isRunning()) clip.stop();
    }

    public void close() {
        stop();
        clip.close();
    }

    public void loop(int count) {
        if(clip == null) return;
        stop();
        clip.setFramePosition(0);
        clip.loop(count);
    }

    public void setLoopPoints(int start, int end) {
        if(clip == null) return;
        clip.setLoopPoints(start,end);
    }

}


