package com.example.judekayode.homeautomation;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

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




public class DeviceGroupActivity extends Activity {
    Session session;
    private JSONParser jsonparser;
    private String Response;
    private CustomAdapter adapter;


    List<Devices> listitem;

    ListView lv;

    private static final String TAG_GROUP_NAME = "groupname";
    private static final String TAG_PREV_STATUS = "prev";


    String[] groups = null;
    String[] prevss = null;
    private String[] groupnames;
    private String[] groupdevcount;
    private String[] device_grp_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new GetAsync().execute(Constant.DEVICE_GROUP_PAGE_URL);
//        Toast.makeText(getApplicationContext(), "User login status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
//
//        session.checkLogin();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        //emulating data coming from json


//        Toast.makeText(DeviceGroupActivity.this, "Yes iam"+ groupnames[2], Toast.LENGTH_SHORT).show();

//        new GetDeviceAsyn().execute();
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

                prevss = getResources().getStringArray(R.array.devices_on);
                Integer[] images = new Integer[]{R.drawable.siittingroom, R.drawable.dining, R.drawable.bedroommaster, R.drawable.bedroomedited, R.drawable.securitylig};

                listitem = new ArrayList<Devices>();

                for (int i = 0; i < groupnames.length; i++) {
                    Devices dv = new Devices(groupnames[i], groupdevcount[i] + " devices", prevss[i], images[i], device_grp_id[i]);

                    listitem.add(dv);
                }
                lv = (ListView) findViewById(R.id.list);
                adapter = new CustomAdapter(DeviceGroupActivity.this, listitem);
                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(DeviceGroupActivity.this, DetailActivity.class);
                        TextView devicegroupid = (TextView)view.findViewById(R.id.devicegroupid);
                        intent.putExtra("device_group_id", devicegroupid.getText().toString());
                        startActivity(intent);

                    }
                });






//                Toast.makeText(DeviceGroupActivity.this,device_grp_id[3], Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(DeviceGroupActivity.this, "Error retrieving data, Check your network connectiom", Toast.LENGTH_LONG).show();
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

            groupnames = new String[data.length()];
            groupdevcount = new String[data.length()];
            device_grp_id = new String[data.length()];

            for(int i=0; i< data.length(); i++ )
            {
                JSONObject details = data.optJSONObject(i);
                String group_name = details.optString("device_group_name");
                String group_dev_count = details.optString("no_of_devices");
                String group_devi_id = details.optString("device_group_id");
                groupnames[i] = group_name;
                groupdevcount[i] = group_dev_count;
                device_grp_id[i] = group_devi_id;

            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }



}
