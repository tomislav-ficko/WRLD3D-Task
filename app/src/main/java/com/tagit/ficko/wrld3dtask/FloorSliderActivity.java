package com.tagit.ficko.wrld3dtask;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.eegeo.indoors.IndoorMapView;
import com.eegeo.mapapi.EegeoApi;
import com.eegeo.mapapi.EegeoMap;
import com.eegeo.mapapi.MapView;
import com.eegeo.mapapi.map.OnMapReadyCallback;

public class FloorSliderActivity extends AppCompatActivity {

    public static final String API_KEY = "e86c3136930aa05c1d0efe0642fa1f26";

    private MapView m_mapView;
    private IndoorMapView m_interiorView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EegeoApi.init(this, API_KEY);

        setContentView(R.layout.floor_slider_activity);
        m_mapView = findViewById(R.id.indoor_slider_mapview);
        m_mapView.onCreate(savedInstanceState);

        m_mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final EegeoMap map) {
                RelativeLayout uiContainer = findViewById(R.id.eegeo_ui_container);
                m_interiorView = new IndoorMapView(m_mapView, uiContainer, map);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        m_mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        m_mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        m_mapView.onDestroy();
    }
}
