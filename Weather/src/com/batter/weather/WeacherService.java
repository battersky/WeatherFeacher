package com.batter.weather;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class WeacherService extends Service {

    private IWeatherService.Stub mBinder = new IWeatherService.Stub() {

        @Override
        public WeatherInfo getWeatherInfo(long data) throws RemoteException {
            WeatherInfo wi = new WeatherInfo(20);
            return wi;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

}
