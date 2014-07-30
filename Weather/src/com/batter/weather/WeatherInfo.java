package com.batter.weather;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherInfo implements Parcelable {

    public int mMainWeatherTypes;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mMainWeatherTypes);
    }

    public WeatherInfo(int type) {
        mMainWeatherTypes = type;
    }

    public WeatherInfo(Parcel in) {
        mMainWeatherTypes = in.readInt();
    }

    public static final Parcelable.Creator<WeatherInfo> CREATOR = new Parcelable.Creator<WeatherInfo>() {

        @Override
        public WeatherInfo createFromParcel(Parcel source) {
            return new WeatherInfo(source);
        }

        @Override
        public WeatherInfo[] newArray(int size) {
            return new WeatherInfo[size];
        }

    };

}
