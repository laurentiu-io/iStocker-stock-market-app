package uk.co.novoapps.istocker;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class Stocks extends IntentService {

    private ArrayList<String>  names;
    private ArrayList<String>  asks;
    private ArrayList<String>  changes;
    private ArrayList<String>  prevCloses;
    private ArrayList<String>  opens;
    private ArrayList<String>  dayLows;
    private ArrayList<String>  dayHighs;
    private ArrayList<String>  wkLows;
    private ArrayList<String>  wkHighs;
    private ArrayList<Integer>  volumes;
    private ArrayList<Integer>  avgVolumes;

    public static final String ACTION_MyIntentService = "uk.co.novoapps.istocker.RESPONSE";
    public static final String EXTRA_KEY_IN = "EXTRA_IN";
    public static final String EXTRA_KEY_OUT_NAMES = "NAMES_OUT";
    public static final String EXTRA_KEY_OUT_ASKS = "ASKS_OUT";
    public static final String EXTRA_KEY_OUT_CHANGES = "CHANGES_OUT";
    public static final String EXTRA_KEY_OUT_PREVCLOSES = "PREVCLOSES_OUT";
    public static final String EXTRA_KEY_OUT_OPENS = "OPENS_OUT";
    public static final String EXTRA_KEY_OUT_DAYLOWS = "DAYLOWS_OUT";
    public static final String EXTRA_KEY_OUT_DAYHIGHS = "DAYHIGHS_OUT";
    public static final String EXTRA_KEY_OUT_WKLOWS = "WKLOWS_OUT";
    public static final String EXTRA_KEY_OUT_WKHIGHS = "WKHIGHS_OUT";
    public static final String EXTRA_KEY_OUT_VOLUMES = "VOLUMES_OUT";
    public static final String EXTRA_KEY_OUT_AVGVOLUMES = "AVGVOLUMES_OUT";

    public void onCreate() {
        super.onCreate();
        Log.d("Server", ">>>onCreate()");
    }

    public Stocks() {
        super("uk.co.novoapps.istocker.Stocks");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, startId, startId);
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        return START_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        names = new ArrayList<String>();
        asks = new ArrayList<String>();
        changes = new ArrayList<String>();
        prevCloses = new ArrayList<String>();
        opens = new ArrayList<String>();
        dayLows = new ArrayList<String>();
        dayHighs = new ArrayList<String>();
        wkLows = new ArrayList<String>();
        wkHighs = new ArrayList<String>();
        volumes = new ArrayList<Integer>();
        avgVolumes = new ArrayList<Integer>();

        while (true) {

            long endTime = System.currentTimeMillis() + 5 * 1000;
            while (System.currentTimeMillis() < endTime) {
                synchronized (this) {

                    String urlStr = "http://finance.yahoo.com/d/quotes.csv?s=AAPL+GOOG+MSFT+CVX+YHOO+ADBE+XOM+AA+AXP+WFC+BRK-A+PBI+CBS+ORCL+NUE+&f=nacpoghkjva2";
                    String dowLine = "";
                    String spLine = "";

                    try {

                        names.clear();
                        asks.clear();
                        changes.clear();
                        prevCloses.clear();
                        opens.clear();
                        dayLows.clear();
                        dayHighs.clear();
                        wkLows.clear();
                        wkHighs.clear();
                        volumes.clear();
                        avgVolumes.clear();

                        wait(endTime - System.currentTimeMillis());

                        HttpGet httpget = new HttpGet(urlStr);
                        HttpClient httpclient = new DefaultHttpClient();
                        HttpContext localContext = new BasicHttpContext();
                        InputStreamReader is = null;

                        try {
                            HttpResponse response = httpclient.execute(httpget, localContext);

                            is = new InputStreamReader(response.getEntity().getContent());

                            BufferedReader reader = new BufferedReader(is);

                            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                                String[] separated = line.split(",");

                                String nameStock = separated[0];

                                String[] separatedName = nameStock.split(" ");
                                String nameStockSeparated = separatedName[0];
                                String newNameStocksSeparated = nameStockSeparated.substring(1);

                                String askStock = separated[1];
                                String changeStock = separated[2];
                                String newChangeStock = changeStock.substring(1, changeStock.length() - 1);
                                String prevCloseStock = separated[3];
                                String openStock = separated[4];
                                String dayLowStock = separated[5];
                                String dayHighStock = separated[6];
                                String wkLowStock = separated[7];
                                String wkHighStock = separated[8];
                                String volumeStock = separated[9];
                                String avgVolumeStock = separated[10];

                                Integer volumeStockNo = Integer.parseInt(volumeStock);
                                Integer avgVolumeStockNo = Integer.parseInt(avgVolumeStock);

                                names.add(newNameStocksSeparated);
                                asks.add(askStock);
                                changes.add(newChangeStock);
                                prevCloses.add(prevCloseStock);
                                opens.add(openStock);
                                dayLows.add(dayLowStock);
                                dayHighs.add(dayHighStock);
                                wkLows.add(wkLowStock);
                                wkHighs.add(wkHighStock);
                                volumes.add(volumeStockNo);
                                avgVolumes.add(avgVolumeStockNo);
                            }

                        } catch (ClientProtocolException e) {
                            e.printStackTrace();

                        } catch (IllegalStateException e) {
                            e.printStackTrace();

                        } catch (IOException e) {
                            Log.d("YourApp", e.getMessage());
                        } finally {
                            try {
                                if (is != null) {
                                    is.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    } catch (Exception e) {
                    }

                    //Send names
                    Intent intentNames = new Intent();

                    Bundle extras = new Bundle();



                    extras.putStringArrayList(EXTRA_KEY_OUT_NAMES, names);
                    extras.putStringArrayList(EXTRA_KEY_OUT_ASKS, asks);
                    extras.putStringArrayList(EXTRA_KEY_OUT_CHANGES, changes);
                    extras.putStringArrayList(EXTRA_KEY_OUT_PREVCLOSES, prevCloses);
                    extras.putStringArrayList(EXTRA_KEY_OUT_OPENS, opens);
                    extras.putStringArrayList(EXTRA_KEY_OUT_DAYLOWS, dayLows);
                    extras.putStringArrayList(EXTRA_KEY_OUT_DAYHIGHS, dayHighs);
                    extras.putStringArrayList(EXTRA_KEY_OUT_WKLOWS, wkLows);
                    extras.putStringArrayList(EXTRA_KEY_OUT_WKHIGHS, wkHighs);
                    extras.putIntegerArrayList(EXTRA_KEY_OUT_VOLUMES, volumes);
                    extras.putIntegerArrayList(EXTRA_KEY_OUT_AVGVOLUMES, avgVolumes);

                    intentNames.setAction(ACTION_MyIntentService);
                    intentNames.addCategory(Intent.CATEGORY_DEFAULT);
                    intentNames.putExtras(extras);
                    sendBroadcast(intentNames);
                }
            }
        }
    }
}