package com.example.ryan.synthesizerapp;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.audiofx.EnvironmentalReverb;
import android.util.Log;



public class AudioEngine {

  private Thread t;
  private final int sr = 44100; //sample rate, HQ Audio = 44khz
  private boolean audioOn, reverb, sin, square, saw;
  private int  amp, ampVal, pitch;
  private double freqVal;



  public void setSound(boolean audioOn, boolean reverb, boolean sin, boolean square, boolean saw, int ampVal, int pitch, double freqVal) {
    this.audioOn = audioOn;
    this.reverb = reverb;
    this.sin = sin;
    this.square = square;
    this.saw = saw;
    this.ampVal = ampVal;
    this.pitch = pitch;
    this.freqVal = freqVal;
  }

  public void updateAudioOn(boolean audioOn) {
    this.audioOn = audioOn;
  }

  public void playSound() {
    Log.e("e","TruAmp:" + (amp-1));
    Log.e("e","audioOn:" + audioOn + " reverb:" + reverb + " sinOn:" + sin + " squareOn:" + square + " sawOn:" + saw + " ampVal:" + ampVal+ " pitch: " + pitch + " freqVal: " + freqVal);
    t = new Thread() {
      public void run() {
        setPriority(Thread.MAX_PRIORITY);
        int bufferSize = AudioTrack.getMinBufferSize(sr, AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT);

        AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, sr,
                AudioFormat.CHANNEL_OUT_MONO,AudioFormat.ENCODING_PCM_16BIT, bufferSize,
                AudioTrack.MODE_STREAM);

        if(reverb) {
          EnvironmentalReverb reverb = new EnvironmentalReverb(0, 0);
          audioTrack.attachAuxEffect(reverb.getId());
          reverb.setDiffusion((short) 2000);
          reverb.setDecayTime(10000);
          reverb.setReverbDelay(100);
          reverb.setReverbLevel((short) 2000);
          reverb.setDensity((short) 2000);
          reverb.setEnabled(true);
          audioTrack.setAuxEffectSendLevel(1.0f);
        }

        short samples[] = new short[bufferSize];
        amp = ampVal+10001; //amp range 10000min to 30000max, set sliderMax(20000) higher to boost
        double twoPi = 2.*Math.PI;
        double freq;
        double phaseIndex = 0.0; // position on waveform
        double pitchMulti = (Math.pow(2,pitch)); //one pitch up =*2, down =/2

        audioTrack.play();

        while (audioOn) {
          freq = freqVal*pitchMulti;

          for (int i = 0; i < bufferSize; i++ ) {
            if (sin) {
              samples[i] = (short) (amp * Math.sin(phaseIndex)); //create 44100 samples along the sine waveform
            }
            else if (square) {
              samples[i] = (short)(amp*Math.signum(Math.sin(phaseIndex))); //square = sign function of sin
            }
            else if (saw){
              samples[i] = (short)(amp*(phaseIndex/Math.PI)); //divide out pi, spike and floor
            }
            phaseIndex += twoPi*freq/sr;  //increment waveform position
          }
          audioTrack.write(samples,0, bufferSize);
        }
        audioTrack.stop();
        audioTrack.release();
      }
    };
    t.start();
  }
}
