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
	final String COUNT_KEY = "counter_value";
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
        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        
        try {
            count = savedInstanceState.getInt(COUNT_KEY, 0);
        } catch (NullPointerException e) {
        	count = 0;
        }
        
        man = (SensorManager)getSystemService(SENSOR_SERVICE);
        accel = man.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        accels = man.getSensorList(Sensor.TYPE_ACCELEROMETER);
        man.registerListener(new StepCounter(), accel, SensorManager.SENSOR_DELAY_NORMAL);
        
		if (count <= 1) {
			count_view.setText(String.valueOf(count) + " step");
		} else {
			count_view.setText(String.valueOf(count) + " steps");
		}
        reset_button.setOnClickListener(new CounterReset());
    }
    
    class StepCounter implements SensorEventListener {
    	double prev = 0;
    	double w_ave;
    	final double ratio = 0.8;
    	boolean flag = true;
    	final double gate_up = 130;
    	final double gate_down = 80;
    	
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// pass
		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			// get raw sensor data
			final float val[] = event.values;

			// acceleration vector to speed scalar
			float accel_scalar = val[0] * val[0] + val[1] * val[1] + val[2] * val[2];
			
			// calculate weighted average
			w_ave = ratio * accel_scalar + (1 - ratio) * prev;
			
			// count steps
			if (flag && w_ave > gate_up) {
				count++;
				if (count <= 1) {
					count_view.setText(String.valueOf(count) + " step");
				} else {
					CharSequence show = String.valueOf(count) + " steps";
					count_view.setText(show);
				}
				flag = false;
			} else if (!flag && w_ave < gate_down) {
				flag = true;
			}
			
			// this is final operation
			prev = accel_scalar;
		}
    }
    
    class CounterReset implements OnClickListener {
		@Override
		public void onClick(View v) {
			count = 0;
			count_view.setText(String.valueOf(count) + " step");
		}
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    	outState.putInt(COUNT_KEY, count);
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    	super.onRestoreInstanceState(savedInstanceState);
    	count = savedInstanceState.getInt(COUNT_KEY);
    }
}
