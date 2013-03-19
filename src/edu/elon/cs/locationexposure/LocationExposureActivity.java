package edu.elon.cs.locationexposure;

import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LocationExposureActivity extends Activity {
	
	private TextView textView; 
	private RelativeLayout relativeLayout; 
	private LocationManager locManager; 
	
	private int[] colors = {Color.RED, Color.BLUE}; 
	
	//which color/section of array, showing
	private int which; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location_exposure);
		
		textView = (TextView) findViewById(R.id.textview); 
		relativeLayout = (RelativeLayout) findViewById(R.id.relativelayout); 
		
		//no color is being displayed 
		which = -1; 
	}
	
	@Override
	protected void onResume() {
		super.onResume(); 
		
		locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE); 
		//locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener); 
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener); 
	}

	@Override
	protected void onPause() {
		//have to stop listening for updates 
		super.onPause(); 
		
		locManager.removeUpdates(locationListener); 
	}
	
	private LocationListener locationListener = new LocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onLocationChanged(Location location) {
			//if location is changed, display to screen and flip the color 
			
			//grab location
			double currentLatitude = location.getLatitude(); 
			double currentLongitude = location.getLongitude(); 
			
			//show values to screen
			textView.setText("Latitude: " + currentLatitude + "\n"
					+ "Longitude: " + currentLongitude); 
			
			//switch colors
			which = (which + 1) % colors.length; 
			relativeLayout.setBackgroundColor(colors[which]); 
			
		}
	};
	

}
