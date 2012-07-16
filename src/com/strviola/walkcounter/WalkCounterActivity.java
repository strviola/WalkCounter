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

<<<<<<< HEAD
public class WalkCounterActivity extends Activity implements SensorEventListener {
    /** Called when the activity is first created. */
=======
public class WalkCounterActivity extends Activity {
>>>>>>> 769e16f03b9b6b198c062428807fdd3c747626ed
	TextView count_view;
	TextView debug_view_0;
	TextView debug_view_1;
	TextView debug_view_2;
	Button reset_button;
	int count;
<<<<<<< HEAD
	SensorManager manager;
	
=======
	SensorManager man;
	Sensor accel;
	List<Sensor> accels;

>>>>>>> 769e16f03b9b6b198c062428807fdd3c747626ed
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
    
<<<<<<< HEAD
    @Override
    protected void onResume() {
    	List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ACCELEROMETER);
    	
    	if (sensors.size() > 0) {
    		Sensor s = sensors.get(0);
    		manager.registerListener(this, s, SensorManager.SENSOR_DELAY_UI);
    	}
    }
        
    class CounterReset implements OnClickListener {
		@Override
		public void onClick(View v) {
			count = 0;
			count_view.setText(String.valueOf(count));
=======
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
>>>>>>> 769e16f03b9b6b198c062428807fdd3c747626ed
		}
    }
    
    class CounterReset implements OnClickListener {
		@Override
		public void onClick(View v) {
<<<<<<< HEAD
			count++;
			count_view.setText(String.valueOf(count));
=======
			count = 0;
			// count_view.setText(String.valueOf(count) + " step(s)");
>>>>>>> 769e16f03b9b6b198c062428807fdd3c747626ed
		}
    }

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// pass		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO main process
		
	}	
}
