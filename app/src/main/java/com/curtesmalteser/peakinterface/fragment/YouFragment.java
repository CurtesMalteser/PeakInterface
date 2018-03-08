package com.curtesmalteser.peakinterface.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.curtesmalteser.peakinterface.R;
import com.curtesmalteser.peakinterface.RadarChart;
import com.curtesmalteser.peakinterface.RadarChartOriginal;
import com.curtesmalteser.peakinterface.RadarData;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YouFragment extends Fragment {


    public YouFragment() {
        // Required empty public constructor
    }


    public class RadarViewHolder {
        @BindView(R.id.myRadar)
        RadarChart myRadar;

        @BindView(R.id.peakBrainScore)
        TextView peakBrainScore;
        @BindView(R.id.focusScore)
        TextView focusScore;
        @BindView(R.id.memoryScore)
        TextView memoryScore;
        @BindView(R.id.mentalAgilityScore)
        TextView mentalAgilityScore;
        @BindView(R.id.problemSolvingScore)
        TextView problemSolvingScore;
        @BindView(R.id.languageScore)
        TextView languageScore;
        @BindView(R.id.rubiksCubeScore)
        TextView rubiksCubeScore;
    }

    public class RadarOriginalViewHolder {
        @BindView(R.id.myRadarOriginal)
        RadarChartOriginal myRadarOriginal;

        @BindView(R.id.peakBrainScore)
        TextView peakBrainScore;
        @BindView(R.id.focusScore)
        TextView focusScore;
        @BindView(R.id.memoryScore)
        TextView memoryScore;
        @BindView(R.id.mentalAgilityScore)
        TextView mentalAgilityScore;
        @BindView(R.id.problemSolvingScore)
        TextView problemSolvingScore;
        @BindView(R.id.languageScore)
        TextView languageScore;
    }

    private boolean newVersion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        newVersion = getResources().getBoolean(R.bool.new_version);
        RadarViewHolder radarViewHolder = new RadarViewHolder();
        RadarOriginalViewHolder radarOriginalViewHolder = new RadarOriginalViewHolder();

        if (newVersion) {
            View view = inflater.inflate(R.layout.fragment_radar_chart, container, false);

            ButterKnife.bind(radarViewHolder, view);
            ArrayList<RadarData> data = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                Random random = new Random();
                int n = random.nextInt(100 - 50) + 50;
                data.add(new RadarData(n)); //0
               }
            radarViewHolder.myRadar.setData(data);

            radarViewHolder.rubiksCubeScore.setText(String.valueOf(data.get(0).value)); //100
            radarViewHolder.languageScore.setText(String.valueOf(data.get(1).value)); //33
            radarViewHolder.mentalAgilityScore.setText(String.valueOf(data.get(2).value)); //200
            radarViewHolder.focusScore.setText(String.valueOf(data.get(3).value)); //50
            radarViewHolder.peakBrainScore.setText(String.valueOf(data.get(4).value)); //180
            radarViewHolder.memoryScore.setText(String.valueOf(data.get(5).value)); //69
            radarViewHolder.problemSolvingScore.setText(String.valueOf(data.get(6).value)); //200*/
            return view;
        } else {
            View view = inflater.inflate(R.layout.fragment_radar_chart_original, container, false);
            ButterKnife.bind(radarOriginalViewHolder, view);
            ArrayList<RadarData> data = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                Random random = new Random();
                int n = random.nextInt(100 - 50) + 50;
                data.add(new RadarData(n));
              }

            radarOriginalViewHolder.myRadarOriginal.setData(data);

            radarOriginalViewHolder.mentalAgilityScore.setText(String.valueOf(data.get(0).value)); //200
            radarOriginalViewHolder.focusScore.setText(String.valueOf(data.get(1).value)); //50
            radarOriginalViewHolder.peakBrainScore.setText(String.valueOf(data.get(2).value)); //180
            radarOriginalViewHolder.memoryScore.setText(String.valueOf(data.get(3).value)); //69*/
            radarOriginalViewHolder.problemSolvingScore.setText(String.valueOf(data.get(4).value)); //100
            radarOriginalViewHolder.languageScore.setText(String.valueOf(data.get(5).value)); //33
            return view;
        }
    }

}
