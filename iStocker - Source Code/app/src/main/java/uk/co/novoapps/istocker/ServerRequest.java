package uk.co.novoapps.istocker;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public class ServerRequest {

    ProgressDialog progressDialog;
    public static final String SERVER_ADDRESS = "http://192.168.0.2:80/iStocker/";

    public ServerRequest(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please wait...");
    }

    public void storeUserDataInBackground(User user, GetUserCallback userCallback) {
        progressDialog.show();
        new StoreUserDataAsyncTask(user, userCallback).execute();
    }

    public void fetchUserDataInBackground(User user, GetUserCallback callback) {
        new fetchUserDataAsyncTask(user, callback).execute();
        progressDialog.show();
    }

    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void> {
        User user;
        GetUserCallback userCallback;

        public StoreUserDataAsyncTask(User user, GetUserCallback userCallback) {
            this.user = user;
            this.userCallback = userCallback;
        }

        @Override
        protected Void doInBackground(Void... params) {

            //Use HashMap to store the user details
            Map<String,String> dataToSend = new HashMap<>();
            dataToSend.put("name", user.name);
            dataToSend.put("username", user.username);
            dataToSend.put("email", user.email);
            dataToSend.put("password", user.password);

            //Server Communication part

            //Encoded String - Encode string by custom method
            String encodedStr = getEncodedData(dataToSend);

            //Used to read data from server
            BufferedReader reader = null;

            //Connection Handling
            try {
                //Converting address String to URL
                URL url = new URL(SERVER_ADDRESS + "Signup.php");

                //Opening the connection (Not setting or using CONNECTION_TIMEOUT)
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                //Post Method
                con.setRequestMethod("POST");
                //To enable inputting values using POST method
                //After this we can write the dataToSend to the body of POST method

                con.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());

                //Writing dataToSend to outputstreamwriter
                writer.write(encodedStr);
                //Sending the data to the server

                //But to read the response of the server, you will have to implement the procedure below
                writer.flush();

                //Data Read Procedure - Read data coming from the server line by line
                StringBuilder sb = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String line;

                while((line = reader.readLine()) != null) { //Read till there is something available
                    sb.append(line + "\n");     //Reading and saving line by line - not all at once
                }
                line = sb.toString();           //Saving complete data received in string

                //Just check to the values received in Logcat
                Log.i("custom_check","The values received in the store part are as follows:");
                Log.i("custom_check",line);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            userCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }

    private String getEncodedData(Map<String,String> data) {
        StringBuilder sb = new StringBuilder();
        for(String key : data.keySet()) {
            String value = null;
            try {
                value = URLEncoder.encode(data.get(key), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if(sb.length()>0)
                sb.append("&");

            sb.append(key + "=" + value);
        }
        return sb.toString();
    }

    public class fetchUserDataAsyncTask extends AsyncTask<Void, Void, User> {
        User user;
        GetUserCallback userCallback;

        public fetchUserDataAsyncTask(User user, GetUserCallback userCallback) {
            this.user = user;
            this.userCallback = userCallback;
        }

        @Override
        protected User doInBackground(Void... params) {

            //Use HashMap to store the user details
            Map<String,String> dataToSend = new HashMap<>();
            dataToSend.put("email", user.email);
            dataToSend.put("password", user.password);

            String encodedStr = getEncodedData(dataToSend);

            BufferedReader reader = null;
            User returnedUser = null;

            //Connection Handling
            try {
                URL url = new URL(SERVER_ADDRESS + "FetchUserData.php");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                //Post Method
                con.setRequestMethod("POST");
                con.setDoOutput(true);

                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                writer.write(encodedStr);
                writer.flush();

                StringBuilder sb = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String line;

                JSONObject jObject;

                while((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }

                line = sb.toString();
                jObject = new JSONObject(line);

                if (jObject.length() == 0){
                    returnedUser = null;
                }
                else {
                    String name = jObject.getString("name");
                    Log.i("tagconvertstr", "[" + name + "]");

                    String email = jObject.getString("email");
                    Log.i("tagconvertstr", "[" + email + "]");


                    returnedUser = new User(name, email, user.email, user.password);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return returnedUser;
        }

        @Override
        protected void onPostExecute(User returnedUser) {
            progressDialog.dismiss();
            userCallback.done(returnedUser);
            super.onPostExecute(returnedUser);
        }

    }
}