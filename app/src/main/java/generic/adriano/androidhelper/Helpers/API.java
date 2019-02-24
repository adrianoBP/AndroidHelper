package generic.adriano.androidhelper.Helpers;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class API {

    public static void POSTJsonRequest(String dummyData,final Context context){

        final String logLocation = "HLPR.API.POSTJREQ";

        Map<String, String> data = new HashMap<>();
        data.put("dummyData", dummyData);

        String url = "https://myUrl.com/api/dummy";

        JsonObjectRequest sendJsonRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(sendJsonRequest);
    }

    public static void POSTUrlEncodedRequest(final String dummyData, final Context context){

        final String logLocation = "HLPR.API.POSTURLEN";

        String url = "https://myUrl.com/api/dummy";

        StringRequest retrieveUIDRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        boolean displayError = false;

                        try{
                            JSONObject responseObject = new JSONObject(response);

                        }catch (JSONException jex){
                            displayError = true;
                            Log.e(logLocation, jex.getMessage());
                        }catch (Exception ex){
                            displayError = true;
                            Log.e(logLocation, ex.getMessage());
                        }finally {
                            if (displayError) {
                                Log.e(logLocation, "Generic error!");
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try{
                            Log.e(logLocation, new String(error.networkResponse.data, StandardCharsets.UTF_8));
                        }catch (Exception ex){
                            Log.e(logLocation, ex.getMessage());
                        }
                    }
                }
        ){
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("dummyData", dummyData);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(retrieveUIDRequest);

    }

}
