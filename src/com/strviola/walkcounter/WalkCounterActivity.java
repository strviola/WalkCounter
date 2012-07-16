package com.strviola.walkcounter;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class WalkCounterActivity extends Activity {
	TextView count_view;
	Button reset_button;
	int count;
	SensorManager manager;
	
	SensorManager man;
	Sensor accel;
	List<Sensor> accels;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        /** Called when the activity is first created. */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        count_view = (TextView)this.findViewById(R.id.count_view);
        reset_button = (Button)this.findViewById(R.id.reset);
        count = 0;
        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        
        man = (SensorManager)getSystemService(SENSOR_SERVICE);
        accel = man.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        accels = man.getSensorList(Sensor.TYPE_ACCELEROMETER);
        man.registerListener(new StepListener(), accel, SensorManager.SENSOR_DELAY_UI);
        
        count_view.setText(String.valueOf(count) + " step(s)");
        reset_button.setOnClickListener(new CounterReset());
    }
    
    class StepListener implements SensorEventListener {
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// pass
		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO measure acceleration and count steps
			
			final float val[] = event.values;
			float accel_scalar = val[0] * val[0] + val[1] * val[1] + val[2] * val[2];
			// acceleration to speed
			count_view.setText("accel: " + accel_scalar);
		}
    }
    
    class CounterReset implements OnClickListener {
		@Override
		public void onClick(View v) {
			count++;
			count_view.setText(String.valueOf(count));
			count = 0;
			// count_view.setText(String.valueOf(count) + " step(s)");
		}
    }
}
