package com.tagit.ficko.wrld3dtask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.eegeo.mapapi.EegeoApi;
import com.eegeo.mapapi.EegeoMap;
import com.eegeo.mapapi.MapView;
import com.eegeo.mapapi.camera.CameraUpdateFactory;
import com.eegeo.mapapi.geometry.LatLng;
import com.eegeo.mapapi.indoors.OnIndoorEnteredListener;
import com.eegeo.mapapi.indoors.OnIndoorExitedListener;
import com.eegeo.mapapi.map.OnInitialStreamingCompleteListener;
import com.eegeo.mapapi.map.OnMapReadyCallback;

public class IndoorActivity extends AppCompatActivity {

    public static final String API_KEY = "e86c3136930aa05c1d0efe0642fa1f26";

    private MapView m_mapView;
    private EegeoMap m_eegeoMap = null;
    private boolean m_indoors = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EegeoApi.init(this, API_KEY);

        setContentView(R.layout.indoor_activity);
        m_mapView = findViewById(R.id.exit_indoor_mapview);
        m_mapView.onCreate(savedInstanceState);

        final Button button = findViewById(R.id.exit_indoor_button);

        m_mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final EegeoMap map) {
                map.addInitialStreamingCompleteListener(new OnInitialStreamingCompleteListener() {
                    @Override
                    public void onInitialStreamingComplete() {
                        IndoorEventListener listener = new IndoorEventListener(button);
                        map.addOnIndoorEnteredListener(listener);
                        map.addOnIndoorExitedListener(listener);
                        m_eegeoMap = map;
                        button.setEnabled(true);
                    }
                });
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

    public void onClick(View view) {
        if (m_indoors) {
            m_eegeoMap.exitIndoorMap();
        } else {
            LatLng indoorMapLatLng = new LatLng(
                    Long.parseLong("@string/marker_latitude"),
                    Long.parseLong("@string/marker_longitude")
            );
            m_eegeoMap.moveCamera(CameraUpdateFactory.newLatLng(indoorMapLatLng));

            m_eegeoMap.enterIndoorMap("california_academy_of_sciences_19794");
        }
    }

    public class IndoorEventListener implements OnIndoorEnteredListener, OnIndoorExitedListener {
        private Button m_button;

        IndoorEventListener(Button button) {
            this.m_button = button;
        }

        @Override
        public void onIndoorEntered() {
            m_button.setText("Exit");
            m_indoors = true;
        }

        @Override
        public void onIndoorExited() {
            m_button.setText("Enter");
            m_indoors = false;
        }
    }
}
