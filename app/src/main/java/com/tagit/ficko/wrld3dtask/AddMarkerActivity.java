package com.tagit.ficko.wrld3dtask;

import android.os.Bundle;

import com.eegeo.mapapi.EegeoApi;
import com.eegeo.mapapi.EegeoMap;
import com.eegeo.mapapi.MapView;
import com.eegeo.mapapi.geometry.LatLng;
import com.eegeo.mapapi.map.OnMapReadyCallback;
import com.eegeo.mapapi.markers.Marker;
import com.eegeo.mapapi.markers.MarkerOptions;

public class AddMarkerActivity extends MapScreen {

    public static final String API_KEY = "9f3eb59fe643c587896f39d7ad4375ce";

    private MapView m_mapView;
    private EegeoMap m_eegeoMap = null;
    private Marker m_marker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EegeoApi.init(this, API_KEY);

        setContentView(R.layout.add_marker_activity);
        m_mapView = (MapView) findViewById(R.id.add_marker_mapview);
        m_mapView.onCreate(savedInstanceState);

        m_mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final EegeoMap map) {
                m_eegeoMap = map;
                m_marker = m_eegeoMap.addMarker(new MarkerOptions()
                        .position(new LatLng(37.784560, -122.402092))
                        .labelText("This is a marker")
                );
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
