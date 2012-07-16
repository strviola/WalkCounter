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

public class WalkCounterActivity extends Activity implements SensorEventListener {
    /** Called when the activity is first created. */
	TextView count_view;
	Button reset_button;
	Button inc_button;
	int count;
	SensorManager manager;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        count_view = (TextView)this.findViewById(R.id.count_view);
        reset_button = (Button)this.findViewById(R.id.reset);
        inc_button = (Button)this.findViewById(R.id.increment);
        count = 0;
        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        
        count_view.setText(String.valueOf(count));
        reset_button.setOnClickListener(new CounterReset());
        inc_button.setOnClickListener(new CounterIncrement());
    }
    
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
		}
    }
    
    class CounterIncrement implements OnClickListener {
		@Override
		public void onClick(View v) {
			count++;
			count_view.setText(String.valueOf(count));
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
