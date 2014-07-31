
package com.batter.weatherclient;

import com.batter.weather.IWeatherService;
import com.batter.weather.WeatherInfo;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView mTextView;
    IWeatherService mWeatherService;

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mWeatherService = IWeatherService.Stub.asInterface(service);
            try {
                WeatherInfo info = mWeatherService.getWeatherInfo(0);
                mTextView.setText(new Integer(info.mMainWeatherTypes).toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mWeatherService = null;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView)this.findViewById(R.id.weather_info_number);

    }


    protected void onResume () {
        super.onResume();
        Intent intent = new Intent("com.batter.weather.weatherInfo");
        this.bindService(intent, mConnection, this.BIND_AUTO_CREATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.unbindService(mConnection);
    }

}
