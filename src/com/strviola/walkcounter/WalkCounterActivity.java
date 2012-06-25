package com.strviola.walkcounter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class WalkCounterActivity extends Activity {
    /** Called when the activity is first created. */
	TextView count_view;
	Button reset_button;
	Button inc_button;
	int count;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        count_view = (TextView)this.findViewById(R.id.count_view);
        reset_button = (Button)this.findViewById(R.id.reset);
        inc_button = (Button)this.findViewById(R.id.increment);
        count = 0;
        
        count_view.setText(String.valueOf(count));
        reset_button.setOnClickListener(new CounterReset());
        inc_button.setOnClickListener(new CounterIncrement());
    }
    
    class CounterReset implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			count = 0;
			count_view.setText(String.valueOf(count));
		}
    }
    
    class CounterIncrement implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			count++;
			count_view.setText(String.valueOf(count));
		}
    }
}
