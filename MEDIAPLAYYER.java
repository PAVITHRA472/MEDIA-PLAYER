PROGRAM:
MEDIA PLAYER:
ACTIVITY_MAIN.XML:
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
 xmlns:app="http://schemas.android.com/apk/res-auto"
 xmlns:tools="http://schemas.android.com/tools"
 android:layout_width="match_parent"
 android:layout_height="match_parent"
 tools:context=".MainActivity" >
 <ImageView
 android:id="@+id/imageView"
 android:layout_width="wrap_content"
 android:layout_height="wrap_content"
 android:layout_alignParentStart="true"
 android:layout_alignParentTop="true"
 android:layout_marginStart="132dp"
 android:layout_marginTop="88dp"
 tools:src="@tools:sample/avatars" />
 <TextView
 android:id="@+id/textView"
 android:layout_width="wrap_content"
 android:layout_height="wrap_content"
 android:layout_alignParentStart="true"
 android:layout_alignParentTop="true"
 android:layout_marginStart="42dp"
 android:layout_marginTop="146dp"
 android:text="TextView" />
 <TextView
 android:id="@+id/textView2"
 android:layout_width="wrap_content"
 android:layout_height="wrap_content"
 android:layout_alignParentStart="true"
 android:layout_alignParentTop="true"
 android:layout_marginStart="67dp"
 android:layout_marginTop="311dp"
 android:text="TextView" />
 <TextView
 android:id="@+id/textView3"
 android:layout_width="wrap_content"
 android:layout_height="wrap_content"
 android:layout_alignParentTop="true"
 android:layout_alignParentEnd="true"
 android:layout_marginTop="316dp"
 android:layout_marginEnd="58dp"
 android:text="TextView" />
 <SeekBar
 android:id="@+id/seekBar"
 android:layout_width="318dp"
 android:layout_height="wrap_content"
 android:layout_alignParentBottom="true"
 android:layout_centerHorizontal="true"
 android:layout_marginBottom="293dp" />
 <ImageButton
 android:id="@+id/imageButton"
 android:layout_width="wrap_content"
 android:layout_height="wrap_content"
 android:layout_alignParentStart="true"
 android:layout_alignParentBottom="true"
 android:layout_marginStart="59dp"
 android:layout_marginBottom="169dp"
 app:srcCompat="@android:drawable/ic_media_rew" />
 <ImageButton
 android:id="@+id/imageButton2"
 android:layout_width="wrap_content"
 android:layout_height="wrap_content"
 android:layout_alignParentStart="true"
 android:layout_alignParentBottom="true"
 android:layout_marginStart="157dp"
 android:layout_marginBottom="163dp"
 app:srcCompat="@android:drawable/ic_media_play" />
 <ImageButton
 android:id="@+id/imageButton3"
 android:layout_width="wrap_content"
 android:layout_height="wrap_content"
 android:layout_alignParentEnd="true"
 android:layout_alignParentBottom="true"
 android:layout_marginEnd="106dp"
 android:layout_marginBottom="161dp"
 app:srcCompat="@android:drawable/ic_media_pause" />
 <ImageButton
 android:id="@+id/imageButton4"
 android:layout_width="wrap_content"
 android:layout_height="wrap_content"
 android:layout_alignParentEnd="true"
 android:layout_alignParentBottom="true"
 android:layout_marginEnd="37dp"
 android:layout_marginBottom="163dp"
 app:srcCompat="@android:drawable/ic_media_ff" />
</RelativeLayout>
MAINACTIVITY.JAVA:
package com.tutlane.mediaplayerexample;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.concurrent.TimeUnit;
public class MainActivity extends AppCompatActivity {
private ImageButton forwardbtn, backwardbtn, pausebtn, playbtn;
private MediaPlayer mPlayer;
private TextView songName, startTime, songTime;
private SeekBar songPrgs;
private static 
int oTime =0, sTime =0, eTime =0, fTime = 5000, bTime = 5000;
private Handler hdlr = new Handler();
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
backwardbtn = (ImageButton)findViewById(R.id.btnBackward);
forwardbtn = (ImageButton)findViewById(R.id.btnForward);
playbtn = (ImageButton)findViewById(R.id.btnPlay);
pausebtn = (ImageButton)findViewById(R.id.btnPause);
songName = (TextView)findViewById(R.id.txtSname);
startTime = (TextView)findViewById(R.id.txtStartTime);
songTime = (TextView)findViewById(R.id.txtSongTime);
songName.setText("Baitikochi Chuste");
mPlayer = MediaPlayer.create(this, R.raw.baitikochi_chuste);
songPrgs = (SeekBar)findViewById(R.id.sBar);
songPrgs.setClickable(false);
pausebtn.setEnabled(false);
playbtn.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
Toast.makeText(MainActivity.this, "Playing Audio", 
Toast.LENGTH_SHORT).show();
mPlayer.start();
eTime = mPlayer.getDuration();
sTime = mPlayer.getCurrentPosition();
if(oTime == 0){
songPrgs.setMax(eTime);
oTime =1;
}
songTime.setText(String.format("%d min, %d sec", 
TimeUnit.MILLISECONDS.toMinutes(eTime),
TimeUnit.MILLISECONDS.toSeconds(eTime) -
TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS. 
toMinutes(eTime))) );
startTime.setText(String.format("%d min, %d sec", 
TimeUnit.MILLISECONDS.toMinutes(sTime),
TimeUnit.MILLISECONDS.toSeconds(sTime) -
TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS. 
toMinutes(sTime))) );
songPrgs.setProgress(sTime);
hdlr.postDelayed(UpdateSongTime, 100);
pausebtn.setEnabled(true);
playbtn.setEnabled(false);
}
});
pausebtn.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
mPlayer.pause();
pausebtn.setEnabled(false);
playbtn.setEnabled(true);
Toast.makeText(getApplicationContext(),"Pausing Audio", 
Toast.LENGTH_SHORT).show();
}
});
forwardbtn.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
if((sTime + fTime) <= eTime)
{
sTime = sTime + fTime;
mPlayer.seekTo(sTime);
}
else
{
Toast.makeText(getApplicationContext(), "Cannot jump 
forward 5 seconds", Toast.LENGTH_SHORT).show();
}
if(!playbtn.isEnabled()){
playbtn.setEnabled(true);
}
}
});
backwardbtn.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
if((sTime - bTime) > 0)
{
sTime = sTime - bTime;
mPlayer.seekTo(sTime);
}
else
{
Toast.makeText(getApplicationContext(), "Cannot jump 
backward 5 seconds", Toast.LENGTH_SHORT).show();
}
if(!playbtn.isEnabled()){
playbtn.setEnabled(true);
}
}
});
}
private Runnable UpdateSongTime = new Runnable() {
@Override
public void run() {
sTime = mPlayer.getCurrentPosition();
startTime.setText(String.format("%d min, %d sec", 
TimeUnit.MILLISECONDS.toMinutes(sTime),
TimeUnit.MILLISECONDS.toSeconds(sTime) -
TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime)
)) );
songPrgs.setProgress(sTime);
hdlr.postDelayed(this, 100);
}
};
}
Widgets Used
<TextView
android:id="@+id/txtVw1"
<TextView
android:id="@+id/txtSname"
<ImageView
android:id="@+id/imgLogo"
/>
<ImageButton
android:id="@+id/btnBackward"
/>
<ImageButton
/>
<ImageButton
android:id="@+id/btnPause"
/>
<ImageButton
android:id="@+id/btnForward"
/>
<TextView
android:id="@+id/txtStartTime"
/>
<SeekBar
android:id="@+id/sBar"
<TextView
android:id="@+id/txtSongTime"
