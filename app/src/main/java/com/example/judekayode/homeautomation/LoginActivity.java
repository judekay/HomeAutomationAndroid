package com.example.judekayode.homeautomation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;


public class LoginActivity extends AppCompatActivity {
    private URL url;
    private HttpURLConnection conn;
    private ProgressDialog pDialog;
    private ProgressDialog nDialog;
    private String response = "";
    private Integer result;
    private EditText email;
    private EditText password;
    Session session;
    private String emailtext = "";
    private String passtext = "";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new Session(getApplicationContext());
        email = (EditText) findViewById(R.id.input_username);
        password = (EditText) findViewById(R.id.input_password);

        Button loginbut  = (Button) findViewById(R.id.btn_login);



        loginbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailtext = email.getText().toString();
                passtext = password.getText().toString();

                new PostAsync().execute(emailtext, passtext);

            }
        });


    }

//    private class NetworkCheck extends AsyncTask<String, String, Boolean>{
//
//        @Override
//        protected void onPreExecute(){
//            super.onPreExecute();
//            nDialog = new ProgressDialog(LoginActivity.this);
//            nDialog.setMessage("Checking Network Connection");
//            nDialog.setIndeterminate(false);
//            nDialog.show();
//        }
//        @Override
//        protected Boolean doInBackground(String... params) {
//
//            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo net = cm.getActiveNetworkInfo();
//
//            if(net != null && net.isConnected()){
//                return true;
//            }
//            else{
//                return false;
//            }
//
//        }
//
//        @Override
//        protected void onPostExecute(Boolean nt)
//        {
//            super.onPostExecute(nt);
//            if (nDialog.isShowing()) {
//                nDialog.dismiss();
//            }
//
//            if(nt){
//                emailtext = email.getText().toString();
//                passtext = password.getText().toString();
//
//                new PostAsync().execute(emailtext, passtext);
//
//            }
//
//        }
//    }

    private class PostAsync extends AsyncTask<String,String,JSONObject> {

        JSONParser jsonParser = new JSONParser();

        private static final String TAG_status = "status";
        private static final String TAG_DATA = "data";
        private static final String TAG_ACCESS = "access_token";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected JSONObject doInBackground(String... params) {

            try {
                HashMap<String, String> parameter = new HashMap<>();
                parameter.put("username", params[0]);
                parameter.put("password", params[1]);

                Log.d("request", "starting");
                JSONObject json = jsonParser.makeHttpRequest(Constant.LOGIN_URL, "POST", parameter);
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
            String access_token = "";
            String error = "";
            String user_type_id = "";

            super.onPostExecute(json);
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (json !=null)
            {

                try{

                    check = json.optBoolean(TAG_status);

                    JSONObject js = json.getJSONObject(TAG_DATA);
                    access_token = js.getString(TAG_ACCESS);
                    user_type_id = js.getString("user_type_id");




                    Log.e("BOOLEAN", check.toString());
//                    Toast.makeText(LoginActivity.this, access_token, Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
            else {
                Toast.makeText(LoginActivity.this,"No network connection", Toast.LENGTH_LONG).show();
            }

            if(check)
            {
                Log.d("Success!", access_token);
                session.createloginSession(emailtext, user_type_id);
                Toast.makeText(LoginActivity.this,user_type_id , Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, DeviceGroupActivity.class );
                startActivity(intent);
            }
            else{

                Log.d("Failure", error);
                Toast.makeText(LoginActivity.this,  "Invalid username or password" , Toast.LENGTH_LONG).show();

            }

        }
    }

}
