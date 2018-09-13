package com.tagit.ficko.wrld3dtask;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.eegeo.indoors.IndoorMapView;
import com.eegeo.mapapi.EegeoApi;
import com.eegeo.mapapi.EegeoMap;
import com.eegeo.mapapi.MapView;
import com.eegeo.mapapi.geometry.LatLng;
import com.eegeo.mapapi.map.OnMapReadyCallback;
import com.eegeo.mapapi.markers.Marker;
import com.eegeo.mapapi.markers.MarkerOptions;

public class IndoorMarkerActivity extends MapScreen {

    private MapView m_mapView;
    private EegeoMap m_eegeoMap = null;
    private IndoorMapView m_indoorMapView = null;

    private Marker m_marker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EegeoApi.init(this, API_KEY);

        setContentView(R.layout.indoor_marker_activity);
        m_mapView = (MapView) findViewById(R.id.add_indoor_marker_mapview);
        m_mapView.onCreate(savedInstanceState);

        m_mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final EegeoMap map) {
                m_eegeoMap = map;

                RelativeLayout uiContainer = (RelativeLayout) findViewById(R.id.eegeo_ui_container);
                m_indoorMapView = new IndoorMapView(m_mapView, uiContainer, m_eegeoMap);

                m_marker = m_eegeoMap.addMarker(new MarkerOptions()
                        .position(
                                new LatLng(
                                        Long.parseLong("@string/latitude"),
                                        Long.parseLong("@string/longitude"))
                        )
                        .indoor("california_academy_of_sciences", 1)
                        .labelText("Coffee time"));
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

        if (m_eegeoMap != null) {
                m_eegeoMap.removeMarker(m_marker);
        }

        m_mapView.onDestroy();
    }
}
