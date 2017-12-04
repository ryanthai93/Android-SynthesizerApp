package com.example.ryan.synthesizerapp;

import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

  boolean audioOn, reverb, sin, square, saw;
  SeekBar ampSlider;
  double freqVal;
  int ampVal;
  private Rect keyRect;
  int pitch = 0;
  TextView pitchTextView;
  CheckBox reverbCheck;
  RadioGroup waveGroup;
  RadioButton sinRadio, squareRadio, sawRadio;
  int amp;

  AudioEngine audioEngine = new AudioEngine();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

    ampSlider = findViewById(R.id.ampSlider);
    ampSlider.setMax(20000);

    Button cKey = findViewById(R.id.cKey);
    Button dKey = findViewById(R.id.dKey);
    Button eKey = findViewById(R.id.eKey);
    Button fKey = findViewById(R.id.fKey);
    Button gKey = findViewById(R.id.gKey);
    Button aKey = findViewById(R.id.aKey);
    Button bKey = findViewById(R.id.bKey);
    Button c2Key = findViewById(R.id.c2Key);
    Button csKey = findViewById(R.id.csKey);
    Button dsKey = findViewById(R.id.dsKey);
    Button fsKey = findViewById(R.id.fsKey);
    Button gsKey = findViewById(R.id.gsKey);
    Button asKey = findViewById(R.id.asKey);
    Button pitchUp = findViewById(R.id.pitchUp);
    Button pitchDown = findViewById(R.id.pitchDown);

    pitchTextView = findViewById(R.id.pitchTextView);
    pitchTextView.setText((String.valueOf(pitch)));

    reverbCheck = findViewById(R.id.reverbCheck);
    waveGroup = findViewById(R.id.waveGroup);
    sinRadio = findViewById(R.id.sin);
    squareRadio = findViewById(R.id.square);
    sawRadio = findViewById(R.id.saw);
    waveGroup.check(R.id.sin);

    cKey.setOnTouchListener(this);
    dKey.setOnTouchListener(this);
    eKey.setOnTouchListener(this);
    fKey.setOnTouchListener(this);
    gKey.setOnTouchListener(this);
    aKey.setOnTouchListener(this);
    bKey.setOnTouchListener(this);
    c2Key.setOnTouchListener(this);
    csKey.setOnTouchListener(this);
    dsKey.setOnTouchListener(this);
    fsKey.setOnTouchListener(this);
    gsKey.setOnTouchListener(this);
    asKey.setOnTouchListener(this);
    pitchUp.setOnTouchListener(this);
    pitchDown.setOnTouchListener(this);

    SeekBar.OnSeekBarChangeListener ampSeekListener = new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int i, boolean bool) {
        if(bool) ampVal = i;
      }
      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {
      }
      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {
      }
    };

    ampSlider.setOnSeekBarChangeListener(ampSeekListener);

  }

  @Override
  protected void onPause() {
    super.onPause();
    audioOn = false;
    audioEngine.updateAudioOn(audioOn);
  }

  public Rect createRect(View v) {
    keyRect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
    return keyRect;
}

  public boolean onTouch(View v, MotionEvent motionEvent) {

    reverb = reverbCheck.isChecked();
    sin = sinRadio.isChecked();
    square = squareRadio.isChecked();
    saw = sawRadio.isChecked();

      switch (motionEvent.getAction()) {

        case MotionEvent.ACTION_DOWN:
          switch (v.getId()) {
            case R.id.cKey:
              freqVal = 261.626;
              createRect(v);
              audioOn = true;
              audioEngine.setSound(audioOn, reverb,sin, square,saw, ampVal,pitch, freqVal);
              audioEngine.playSound();
              break;
            case R.id.csKey:
              freqVal = 277.183;
              createRect(v);
              audioOn = true;
              audioEngine.setSound(audioOn, reverb,sin, square,saw, ampVal,pitch, freqVal);
              audioEngine.playSound();
              break;
            case R.id.dKey:
              freqVal = 293.665;
              createRect(v);
              audioOn = true;
              audioEngine.setSound(audioOn, reverb,sin, square,saw, ampVal,pitch, freqVal);
              audioEngine.playSound();
              break;
            case R.id.dsKey:
              freqVal = 311.127;
              createRect(v);
              audioOn = true;
              audioEngine.setSound(audioOn, reverb,sin, square,saw, ampVal,pitch, freqVal);
              audioEngine.playSound();
              break;
            case R.id.eKey:
              freqVal = 329.628;
              createRect(v);
              audioOn = true;
              audioEngine.setSound(audioOn, reverb,sin, square,saw, ampVal,pitch, freqVal);
              audioEngine.playSound();
              break;
            case R.id.fKey:
              freqVal = 349.228;
              createRect(v);
              audioOn = true;
              audioEngine.setSound(audioOn, reverb,sin, square,saw, ampVal,pitch, freqVal);
              audioEngine.playSound();
              break;
            case R.id.fsKey:
              freqVal = 369.994;
              createRect(v);
              audioOn = true;
              audioEngine.setSound(audioOn, reverb,sin, square,saw, ampVal,pitch, freqVal);
              audioEngine.playSound();
              break;
            case R.id.gKey:
              freqVal = 391.995;
              createRect(v);
              audioOn = true;
              audioEngine.setSound(audioOn, reverb,sin, square,saw, ampVal,pitch, freqVal);
              audioEngine.playSound();
              break;
            case R.id.gsKey:
              freqVal = 415.305;
              createRect(v);
              audioOn = true;
              audioEngine.setSound(audioOn, reverb,sin, square,saw, ampVal,pitch, freqVal);
              audioEngine.playSound();
              break;
            case R.id.aKey:
              freqVal = 440.000;
              createRect(v);
              audioOn = true;
              audioEngine.setSound(audioOn, reverb,sin, square,saw, ampVal,pitch, freqVal);
              audioEngine.playSound();
              break;
            case R.id.asKey:
              freqVal = 466.164;
              createRect(v);
              audioOn = true;
              audioEngine.setSound(audioOn, reverb,sin, square,saw, ampVal,pitch, freqVal);
              audioEngine.playSound();
              break;
            case R.id.bKey:
              freqVal = 493.883;
              createRect(v);
              audioOn = true;
              audioEngine.setSound(audioOn, reverb,sin, square,saw, ampVal,pitch, freqVal);
              audioEngine.playSound();
              break;
            case R.id.c2Key:
              freqVal = 523.251;
              createRect(v);
              audioOn = true;
              audioEngine.setSound(audioOn, reverb,sin, square,saw, ampVal,pitch, freqVal);
              audioEngine.playSound();
              break;
            case R.id.pitchUp:
              pitch += 1;
              pitchTextView.setText((String.valueOf(pitch)));
              break;
            case R.id.pitchDown:
              pitch -=1;
              pitchTextView.setText(
                      (String.valueOf(pitch)));
              break;
          }
        break;

      case MotionEvent.ACTION_UP:
        audioOn = false;
        audioEngine.updateAudioOn(audioOn);
        break;

      case MotionEvent.ACTION_MOVE:
        if(!keyRect.contains(v.getLeft() + (int) motionEvent.getX(), v.getTop() + (int) motionEvent.getY())){
          audioOn = false;
          audioEngine.updateAudioOn(audioOn);
        }
        break;
    }
    return true;
  }
}
