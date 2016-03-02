package com.example.judekayode.homeautomation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetailActivity extends Activity {
    private ImageButton  mybutt;
    private Boolean clicked;
    private String Response;
    private String[] device_id;
    private String[] device_name;
    private String[] device_value;
    private String[] device_type_id;
    private ProgressDialog pDialog;
    private DeviceDetailCustomAdapter adapter;
    private String mydevice_id;
    String valueon = "1";
    String valueoff = "0";
    String id;

    List<DevicesDetails> listitem;

    ListView ls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list);

        Intent intent = getIntent();



        id = intent.getStringExtra("device_group_id");

        new GetAsync().execute(Constant.DEVICE_APPLIANCES_BY_GRPID + id);

//        for(int i =0; i<device_value.length; i++) {
//            System.out.println(device_value[i]);
//            Toast.makeText(DetailActivity.this, device_value[i], Toast.LENGTH_LONG).show();
//        }
    }
//    public void turnonoffdevice(View v)
//    {
//        RelativeLayout vwparent = (RelativeLayout) v.getParent();
//        TextView devid = (TextView) vwparent.getChildAt(0);
//        final ToggleButton toggle = (ToggleButton) vwparent.getChildAt(2);
//        mydevice_id = devid.getText().toString();
//
//        Toast.makeText(DetailActivity.this, mydevice_id, Toast.LENGTH_SHORT).show();
//        toggle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(toggle.getText() == "ON"){
//                    Toast.makeText(DetailActivity.this,"Thanks for clicking me", Toast.LENGTH_LONG).show();
//                    new PostAsync().execute(valueoff);
//                    Toast.makeText(DetailActivity.this, "device switched off successfully", Toast.LENGTH_LONG).show();
////                    new GetAsync().execute(Constant.DEVICE_APPLIANCES_BY_GRPID + id);
//                }
//                else {
//                    Toast.makeText(DetailActivity.this,"No Thanks for clicking me", Toast.LENGTH_LONG).show();
//                    new PostAsync().execute(valueon);
//                    Toast.makeText(DetailActivity.this, "device switched on successfully", Toast.LENGTH_LONG).show();
////                    new GetAsync().execute(Constant.DEVICE_APPLIANCES_BY_GRPID + id);
//                }
//
//
//
//            }
//        });
//
//
//
//    }

    public void turnonoffdevice(View v)
    {
        RelativeLayout vwparent = (RelativeLayout) v.getParent();
        TextView devid = (TextView) vwparent.getChildAt(0);
        final ToggleButton toggle = (ToggleButton) vwparent.getChildAt(2);
        mydevice_id = devid.getText().toString();

//        Toast.makeText(DetailActivity.this, mydevice_id, Toast.LENGTH_SHORT).show();
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getval = toggle.getText().toString();
                if(getval.equals("ON")){
//                    Toast.makeText(DetailActivity.this,"Thanks for clickingoff  me", Toast.LENGTH_LONG).show();
                    new PostAsync().execute(valueoff);
                    Toast.makeText(DetailActivity.this, "device switched off successfully", Toast.LENGTH_LONG).show();
                }

                else if(getval.equals("OFF")) {
//                    Toast.makeText(DetailActivity.this,"Thanks for clickingon  me", Toast.LENGTH_LONG).show();
                    new PostAsync().execute(valueon);
                    Toast.makeText(DetailActivity.this, "Device switched on successfully", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public class GetAsync extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {

            InputStream inputstream = null;
            HttpURLConnection conn = null;
            Integer result = 0;

            try {
                URL url = new URL(params[0]);
                conn = (HttpURLConnection) url.openConnection();

                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestMethod("GET");
                Integer statusCode = conn.getResponseCode();

                if (statusCode == 200) {
                    inputstream = new BufferedInputStream(conn.getInputStream());
                    Response = getStringfromStream(inputstream);
                    parse(Response);
                    result = 1;
                } else {

                    result = 0;
                }

            } catch (Exception e) {
                Log.d("TAG_ERROR", e.getMessage());
            }

            return result;
        }

        @Override
        protected void onPostExecute(Integer integer) {

            super.onPostExecute(integer);


            if (integer == 1) {

//                prevss = getResources().getStringArray(R.array.devices_on);
//                Integer[] images = new Integer[]{R.drawable.siittingroom, R.drawable.dining, R.drawable.bedroommaster, R.drawable.bedroomedited, R.drawable.securitylig};

//                Toast.makeText(DetailActivity.this, Response, Toast.LENGTH_LONG).show();
                listitem = new ArrayList<DevicesDetails>();

                for (int i = 0; i < device_name.length; i++) {
                    DevicesDetails dv = new DevicesDetails(device_id[i], device_name[i],device_type_id[i],device_value[i]);

                    listitem.add(dv);
                }
                ls = (ListView) findViewById(R.id.newlistdetail);
                adapter = new DeviceDetailCustomAdapter(DetailActivity.this, listitem);
                ls.setAdapter(adapter);

                ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


//                        Toast.makeText(DetailActivity.this, "This is me welcome", Toast.LENGTH_LONG).show();
                    }
                });






//                Toast.makeText(DetailActivity.this,device_name[0], Toast.LENGTH_LONG).show();
            } else {
//                Toast.makeText(DetailActivity.this, "Nope", Toast.LENGTH_LONG).show();
            }

        }
    }
    private String getStringfromStream(InputStream inputstream) throws IOException {


        BufferedReader buffer = new BufferedReader(new InputStreamReader(inputstream));
        String line = "";
        String result = "";

        while ((line = buffer.readLine()) != null) {

            result += line;

        }

        if (null != inputstream) {
            inputstream.close();
        }

        return result;


    }


    private void parse(String response) {


        try{
            JSONObject responses = new JSONObject(response);

            JSONArray data = responses.optJSONArray("data");

            device_id = new String[data.length()];
            device_name = new String[data.length()];
            device_value = new String[data.length()];
            device_type_id = new String[data.length()];

            for(int i=0; i< data.length(); i++ )
            {
                JSONObject details = data.optJSONObject(i);
                String group_name = details.optString("device_name");
                String group_dev_count = details.optString("device_id");
                String group_devi_id = details.optString("device_value");
                String grp_dev_type_id = details.optString("device_type_id");
                device_id[i] = group_dev_count;
                device_name[i] = group_name;
                device_value[i] = group_devi_id;
                device_type_id[i] = grp_dev_type_id;

            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }



    private class PostAsync extends AsyncTask<String,String,JSONObject> {

        JSONParser jsonParser = new JSONParser();


        private static final String TAG_status = "status";
        private static final String TAG_DATA = "data";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(DetailActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected JSONObject doInBackground(String... params) {

            try {
                HashMap<String, String> parameter = new HashMap<>();
                parameter.put("device_value", params[0]);


                Log.d("request", "starting");
                JSONObject json = jsonParser.makeHttpRequest(Constant.TURNONDEVICE + mydevice_id, "POST", parameter);
                if (json !=null)
                {
                    Log.d("JSON result", json.toString());
                    return json;
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject json) {


            Boolean check = false;
            String error = "";
            String device_id = "";

            super.onPostExecute(json);
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (json !=null)
            {

                try{

                    check = json.optBoolean(TAG_status);
                    device_id = json.optString("device_id");




                    Log.e("BOOLEAN", check.toString());
//                    Toast.makeText(DetailActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
            else {
                Toast.makeText(DetailActivity.this,"error connecting", Toast.LENGTH_LONG).show();
            }

            if(check)
            {
                Log.d("Success!", "good boy");

//                Toast.makeText(DetailActivity.this,Constant.TURNONDEVICE + mydevice_id, Toast.LENGTH_LONG).show();

            }
            else{

                Log.d("Failure", error);
                Toast.makeText(DetailActivity.this,  "error fetching" , Toast.LENGTH_LONG).show();

            }



        }
    }

//
//    public void turnonoffdevicse(View v)
//    {
//        RelativeLayout vwparent = (RelativeLayout) v.getParent();
//        TextView devid = (TextView) vwparent.getChildAt(0);
//        final ToggleButton toggle = (ToggleButton) vwparent.getChildAt(2);
//        mydevice_id = devid.getText().toString();
//
//        Toast.makeText(DetailActivity.this, mydevice_id, Toast.LENGTH_SHORT).show();
//        toggle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(toggle.getText() == "ON"){
//
//                    new PostAsync().execute(valueoff);
//                    Toast.makeText(DetailActivity.this, "device switched off successfully", Toast.LENGTH_LONG).show();
//                    new GetAsync().execute(Constant.DEVICE_APPLIANCES_BY_GRPID + id);
//                }
//                else {
//
//                    new PostAsync().execute(valueon);
//                    Toast.makeText(DetailActivity.this, "device switched on successfully", Toast.LENGTH_LONG).show();
//                    new GetAsync().execute(Constant.DEVICE_APPLIANCES_BY_GRPID + id);
//                }
//
//
//
//            }
//        });
//
//
//    }
}
