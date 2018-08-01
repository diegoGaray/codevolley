package com.smartbox.diegotest.testdiegogarayandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String URL = "http://fxservicesstaging.nunchee.com/api/1.0/sport/events";
    private ArrayList<Partidos> partidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        partidos = new ArrayList<>();

        JSONRequestPartidos();
    }

    private void JSONRequestPartidos() {

        RequestQueue queue = SingletonPatron.getInstance(getApplicationContext()).getRequestQueue();
        final String urlp = String.format(URL);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlp, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    String res = obj.getJSONObject("data").getString("items");
                    JSONArray parArray = new JSONArray(res);
                    for (int i = 0; i < parArray.length(); i++) {
                        JSONObject parObj = (JSONObject) parArray.get(i);
                        Partidos par = new Partidos();
                        par.setHomeScore(parObj.getString("homeScore"));
                        par.setAwayScore(parObj.getString("awayScore"));
                        JSONObject objStatus = new JSONObject(parObj.getString("eventStatus"));
                        par.setEventStatus(objStatus.getString("category"));
                        JSONObject objTeam = new JSONObject(parObj.getString("homeTeam"));
                        JSONObject objTeamAway = new JSONObject(parObj.getString("awayTeam"));
                        par.setAwayTeam(objTeamAway.getString("name"));
                        par.setHomeTeam(objTeam.getString("name"));
                        String dateF = parObj.getString("startDate");
                        String str = dateF.replace('T',' ');
                        String dateFinal = str.replace('Z', ' ');
                        par.setStartDate(dateFinal);
                        partidos.add(par);
                    }

                } catch (JSONException e) {
                    Log.e("errorjson", "json parsing error: " + e.getMessage());
                }

            }

        }, errorListener)
        {
            @Override
            public Priority getPriority() {
                return Priority.LOW;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer GCMAVDlt3rMpRrxSNrvi0LuKAaPwFCVQl6WpshbKXIK0gYlUU5jeYEIrFDcs1AqprECeXW201GIKjF2VHHcusgPSRj4f93iNUJVaA5C7NWZjg3nTgI4AVaV95hCwsIKcPzBaGOkjjHvPghfGC13nxP5IcXxULzraqwzYijhIfW1MW7nxI8GENiNlN1BXIGWwlBumBtt53C77PTliw8zas0EqI03nG9f124vJmel8kN2uOKPpDKyXho6L6Q3CjNGX");
                return headers;
            }
        };
        queue.add(stringRequest);
        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {

            @Override
            public void onRequestFinished(Request<Object> request) {
                try {
                    if (request.getCacheEntry() != null) {
                        String cacheValue = new String(request.getCacheEntry().data, "UTF-8");

                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                startActivity(new Intent(MainActivity.this, ContentViewActivity.class).putExtra("partidos", partidos));
            }
        });

    }

    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            if (error instanceof NetworkError) {
                Toast.makeText(getApplicationContext(), "No network available", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }

    };

}
