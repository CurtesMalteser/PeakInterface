package com.curtesmalteser.peakinterface;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by António "Curtes Malteser" Bastião on 07/03/2018.
 */


public class RadarData implements Parcelable {
    public static final Creator<RadarData> CREATOR = new Creator<RadarData>() {
        @Override
        public RadarData createFromParcel(Parcel in) {
            return new RadarData(in);
        }

        @Override
        public RadarData[] newArray(int size) {
            return new RadarData[size];
        }
    };
    /**
     * The value of this item
     * <p>
     * This is rendered as the distance of the polygon from the center of the {@link RadarData}.
     */
    @SuppressWarnings("WeakerAccess")
    public int value;

    /**
     * Construct a new {@link RadarData}.
     *
     * @param value The value of this item
     */
    public RadarData(@NonNull int value) {
        this.value = value;
    }

    private RadarData(Parcel in) {
        this(in.readInt());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(value);
    }

}