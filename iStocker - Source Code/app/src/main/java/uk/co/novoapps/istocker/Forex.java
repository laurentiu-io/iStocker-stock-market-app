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
public class Forex extends IntentService {

    private ArrayList<String>  namesForex;
    private ArrayList<String>  asksForex;
    private ArrayList<String>  changesForex;
    private ArrayList<String>  prevClosesForex;
    private ArrayList<String>  opensForex;
    private ArrayList<String>  dayLowsForex;
    private ArrayList<String>  dayHighsForex;
    private ArrayList<String>  wkLowsForex;
    private ArrayList<String>  wkHighsForex;

    public static final String ACTION_MyIntentServiceForex = "uk.co.novoapps.istocker.FOREX";
    public static final String FOREX_KEY_IN = "FOREX_IN";
    public static final String FOREX_KEY_OUT_NAMES = "FOREX_NAMES_OUT";
    public static final String FOREX_KEY_OUT_ASKS = "FOREX_ASKS_OUT";
    public static final String FOREX_KEY_OUT_CHANGES = "FOREX_CHANGES_OUT";
    public static final String FOREX_KEY_OUT_PREVCLOSES = "FOREX_PREVCLOSES_OUT";
    public static final String FOREX_KEY_OUT_OPENS = "FOREX_OPENS_OUT";
    public static final String FOREX_KEY_OUT_DAYLOWS = "FOREX_DAYLOWS_OUT";
    public static final String FOREX_KEY_OUT_DAYHIGHS = "FOREX_DAYHIGHS_OUT";
    public static final String FOREX_KEY_OUT_WKLOWS = "FOREX_WKLOWS_OUT";
    public static final String FOREX_KEY_OUT_WKHIGHS = "FOREX_WKHIGHS_OUT";

    public void onCreate() {
        super.onCreate();
        Log.d("Server", ">>>onCreate()");
    }

    public Forex() {
        super("uk.co.novoapps.istocker.Forex");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, startId, startId);
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        return START_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        namesForex = new ArrayList<String>();
        asksForex = new ArrayList<String>();
        changesForex = new ArrayList<String>();
        prevClosesForex = new ArrayList<String>();
        opensForex = new ArrayList<String>();
        dayLowsForex = new ArrayList<String>();
        dayHighsForex = new ArrayList<String>();
        wkLowsForex = new ArrayList<String>();
        wkHighsForex = new ArrayList<String>();

        while (true) {

            long endTime = System.currentTimeMillis() + 5 * 1000;
            while (System.currentTimeMillis() < endTime) {
                synchronized (this) {

                    String urlStrForex = "http://finance.yahoo.com/d/quotes.csv?s=EURUSD=X+AUDUSD=X+GBPUSD=X+USDJPY=X+EURJPY=X+EURGBP=X+USDCAD=X+USDCHF=X+RONGBP=X+GBPRON=X+&f=nacpoghkj";
                    String dowLine = "";
                    String spLine = "";

                    try {

                        namesForex.clear();
                        asksForex.clear();
                        changesForex.clear();
                        prevClosesForex.clear();
                        opensForex.clear();
                        dayLowsForex.clear();
                        dayHighsForex.clear();
                        wkLowsForex.clear();
                        wkHighsForex.clear();

                        wait(endTime - System.currentTimeMillis());

                        HttpGet httpgetForex = new HttpGet(urlStrForex);
                        HttpClient httpclientForex = new DefaultHttpClient();
                        HttpContext localContextForex = new BasicHttpContext();
                        InputStreamReader isForex = null;

                        try {
                            HttpResponse responseForex = httpclientForex.execute(httpgetForex, localContextForex);

                            isForex = new InputStreamReader(responseForex.getEntity().getContent());

                            BufferedReader readerForex = new BufferedReader(isForex);

                            for (String line = readerForex.readLine(); line != null; line = readerForex.readLine()) {
                                String[] separated = line.split(",");

                                String nameForex = separated[0];
                                String newNameForex = nameForex.substring(1, nameForex.length() - 1);

                                String askForex = separated[1];
                                String changeForex = separated[2];
                                String newChangeForex = changeForex.substring(1, changeForex.length() - 1);

                                String prevCloseForex = separated[3];
                                String openForex = separated[4];
                                String dayLowForex = separated[5];
                                String dayHighForex = separated[6];
                                String wkLowForex = separated[7];
                                String wkHighForex = separated[8];

                                namesForex.add(newNameForex);
                                asksForex.add(askForex);
                                changesForex.add(newChangeForex);
                                prevClosesForex.add(prevCloseForex);
                                opensForex.add(openForex);
                                dayLowsForex.add(dayLowForex);
                                dayHighsForex.add(dayHighForex);
                                wkLowsForex.add(wkLowForex);
                                wkHighsForex.add(wkHighForex);
                            }

                        } catch (ClientProtocolException e) {
                            e.printStackTrace();

                        } catch (IllegalStateException e) {
                            e.printStackTrace();

                        } catch (IOException e) {
                            Log.d("YourApp", e.getMessage());
                        } finally {
                            try {
                                if (isForex != null) {
                                    isForex.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    } catch (Exception e) {
                    }

                    //Send names
                    Intent forexIntent = new Intent();

                    Bundle forexExtras = new Bundle();
                    forexExtras.putStringArrayList(FOREX_KEY_OUT_NAMES, namesForex);
                    forexExtras.putStringArrayList(FOREX_KEY_OUT_ASKS, asksForex);
                    forexExtras.putStringArrayList(FOREX_KEY_OUT_CHANGES, changesForex);
                    forexExtras.putStringArrayList(FOREX_KEY_OUT_PREVCLOSES, prevClosesForex);
                    forexExtras.putStringArrayList(FOREX_KEY_OUT_OPENS, opensForex);
                    forexExtras.putStringArrayList(FOREX_KEY_OUT_DAYLOWS, dayLowsForex);
                    forexExtras.putStringArrayList(FOREX_KEY_OUT_DAYHIGHS, dayHighsForex);
                    forexExtras.putStringArrayList(FOREX_KEY_OUT_WKLOWS, wkLowsForex);
                    forexExtras.putStringArrayList(FOREX_KEY_OUT_WKHIGHS, wkHighsForex);

                    forexIntent.setAction(ACTION_MyIntentServiceForex);
                    forexIntent.addCategory(Intent.CATEGORY_DEFAULT);
                    forexIntent.putExtras(forexExtras);
                    sendBroadcast(forexIntent);
                }
            }
        }
    }
}
