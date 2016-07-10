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
public class Commodities extends IntentService {

    private ArrayList<String>  namesCommodities;
    private ArrayList<String>  asksCommodities;
    private ArrayList<String>  changesCommodities;
    private ArrayList<String>  prevClosesCommodities;
    private ArrayList<String>  opensCommodities;
    private ArrayList<String>  dayLowsCommodities;
    private ArrayList<String>  dayHighsCommodities;
    private ArrayList<String>  wkLowsCommodities;
    private ArrayList<String>  wkHighsCommodities;

    public static final String ACTION_MyIntentServiceCommodities = "uk.co.novoapps.istocker.COMMODITIES";
    public static final String COMMODITIES_KEY_IN = "COMMODITIES_IN";
    public static final String COMMODITIES_KEY_OUT_NAMES = "COMMODITIES_NAMES_OUT";
    public static final String COMMODITIES_KEY_OUT_ASKS = "COMMODITIES_ASKS_OUT";
    public static final String COMMODITIES_KEY_OUT_CHANGES = "COMMODITIES_CHANGES_OUT";
    public static final String COMMODITIES_KEY_OUT_PREVCLOSES = "COMMODITIES_PREVCLOSES_OUT";
    public static final String COMMODITIES_KEY_OUT_OPENS = "COMMODITIES_OPENS_OUT";
    public static final String COMMODITIES_KEY_OUT_DAYLOWS = "COMMODITIES_DAYLOWS_OUT";
    public static final String COMMODITIES_KEY_OUT_DAYHIGHS = "COMMODITIES_DAYHIGHS_OUT";
    public static final String COMMODITIES_KEY_OUT_WKLOWS = "COMMODITIES_WKLOWS_OUT";
    public static final String COMMODITIES_KEY_OUT_WKHIGHS = "COMMODITIES_WKHIGHS_OUT";

    public void onCreate() {
        super.onCreate();
        Log.d("Server", ">>>onCreate()");
    }

    public Commodities() {
        super("uk.co.novoapps.istocker.Commodities");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, startId, startId);
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        return START_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        namesCommodities = new ArrayList<String>();
        asksCommodities = new ArrayList<String>();
        changesCommodities = new ArrayList<String>();
        prevClosesCommodities = new ArrayList<String>();
        opensCommodities = new ArrayList<String>();
        dayLowsCommodities = new ArrayList<String>();
        dayHighsCommodities = new ArrayList<String>();
        wkLowsCommodities = new ArrayList<String>();
        wkHighsCommodities = new ArrayList<String>();

        while (true) {

            long endTime = System.currentTimeMillis() + 5 * 1000;
            while (System.currentTimeMillis() < endTime) {
                synchronized (this) {

                    String urlStrCommodities = "http://finance.yahoo.com/d/quotes.csv?s=HG.CMX+HGJ16.CMX+GCJ16.CMX+SI.CMX+SIJ16.CMX+HOJ16.NYM+RBJ16.NYM+CK16.CBT+OK16.CBT+BOK16.CBT&f=nbcpoghkj";

                    try {

                        namesCommodities.clear();
                        asksCommodities.clear();
                        changesCommodities.clear();
                        prevClosesCommodities.clear();
                        opensCommodities.clear();
                        dayLowsCommodities.clear();
                        dayHighsCommodities.clear();
                        wkLowsCommodities.clear();
                        wkHighsCommodities.clear();

                        wait(endTime - System.currentTimeMillis());

                        HttpGet httpgetCommodities = new HttpGet(urlStrCommodities);
                        HttpClient httpclientCommodities = new DefaultHttpClient();
                        HttpContext localContextCommodities = new BasicHttpContext();
                        InputStreamReader isCommodities = null;

                        try {
                            HttpResponse responseCommodities = httpclientCommodities.execute(httpgetCommodities, localContextCommodities);

                            isCommodities = new InputStreamReader(responseCommodities.getEntity().getContent());

                            BufferedReader readerCommodities = new BufferedReader(isCommodities);

                            for (String line = readerCommodities.readLine(); line != null; line = readerCommodities.readLine()) {
                                String[] separated = line.split(",");

                                String nameCommodities = separated[0];
                                String newNameCommodities = nameCommodities.substring(1, nameCommodities.length() - 1);

                                String askCommodities = separated[1];
                                String changeCommodities = separated[2];
                                String newChangeCommodities = changeCommodities.substring(1, changeCommodities.length() - 1);

                                String prevCloseCommodities = separated[3];
                                String openCommodities = separated[4];
                                String dayLowCommodities = separated[5];
                                String dayHighCommodities = separated[6];
                                String wkLowCommodities = separated[7];
                                String wkHighCommodities = separated[8];

                                namesCommodities.add(newNameCommodities);
                                asksCommodities.add(askCommodities);
                                changesCommodities.add(newChangeCommodities);
                                prevClosesCommodities.add(prevCloseCommodities);
                                opensCommodities.add(openCommodities);
                                dayLowsCommodities.add(dayLowCommodities);
                                dayHighsCommodities.add(dayHighCommodities);
                                wkLowsCommodities.add(wkLowCommodities);
                                wkHighsCommodities.add(wkHighCommodities);
                            }

                        } catch (ClientProtocolException e) {
                            e.printStackTrace();

                        } catch (IllegalStateException e) {
                            e.printStackTrace();

                        } catch (IOException e) {
                            Log.d("YourApp", e.getMessage());
                        } finally {
                            try {
                                if (isCommodities != null) {
                                    isCommodities.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    } catch (Exception e) {
                    }

                    //Send names
                    Intent commoditiesIntent = new Intent();

                    Bundle commoditiesExtras = new Bundle();
                    commoditiesExtras.putStringArrayList(COMMODITIES_KEY_OUT_NAMES, namesCommodities);
                    commoditiesExtras.putStringArrayList(COMMODITIES_KEY_OUT_ASKS, asksCommodities);
                    commoditiesExtras.putStringArrayList(COMMODITIES_KEY_OUT_CHANGES, changesCommodities);
                    commoditiesExtras.putStringArrayList(COMMODITIES_KEY_OUT_PREVCLOSES, prevClosesCommodities);
                    commoditiesExtras.putStringArrayList(COMMODITIES_KEY_OUT_OPENS, opensCommodities);
                    commoditiesExtras.putStringArrayList(COMMODITIES_KEY_OUT_DAYLOWS, dayLowsCommodities);
                    commoditiesExtras.putStringArrayList(COMMODITIES_KEY_OUT_DAYHIGHS, dayHighsCommodities);
                    commoditiesExtras.putStringArrayList(COMMODITIES_KEY_OUT_WKLOWS, wkLowsCommodities);
                    commoditiesExtras.putStringArrayList(COMMODITIES_KEY_OUT_WKHIGHS, wkHighsCommodities);

                    commoditiesIntent.setAction(ACTION_MyIntentServiceCommodities);
                    commoditiesIntent.addCategory(Intent.CATEGORY_DEFAULT);
                    commoditiesIntent.putExtras(commoditiesExtras);
                    sendBroadcast(commoditiesIntent);
                }
            }
        }
    }
}

