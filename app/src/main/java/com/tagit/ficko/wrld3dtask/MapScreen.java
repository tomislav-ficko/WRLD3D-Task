package com.tagit.ficko.wrld3dtask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.eegeo.indoors.IndoorMapView;
import com.eegeo.mapapi.EegeoApi;
import com.eegeo.mapapi.EegeoMap;
import com.eegeo.mapapi.MapView;
import com.eegeo.mapapi.map.OnMapReadyCallback;

public class MapScreen extends AppCompatActivity {

    public static final String API_KEY = "9f3eb59fe643c587896f39d7ad4375ce";

    private MapView m_mapView;
//    private EegeoMap m_eegeoMap = null;
//    private IndoorMapView m_interiorView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EegeoApi.init(this, API_KEY);

        setContentView(R.layout.activity_map_screen);
        m_mapView = (MapView) findViewById(R.id.mapView);
        m_mapView.onCreate(savedInstanceState);

        m_mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final EegeoMap map) {
//                m_eegeoMap = map;
//
//                RelativeLayout uiContainer = (RelativeLayout) findViewById(R.id.eegeo_ui_container);
//                m_interiorView = new IndoorMapView(m_mapView, uiContainer, m_eegeoMap);

                Toast.makeText(MapScreen.this, "Welcome to Eegeo Maps", Toast.LENGTH_LONG).show();
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
