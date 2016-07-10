package uk.co.novoapps.istocker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@SuppressWarnings("ALL")
public class PortfolioActivity extends AppCompatActivity implements View.OnClickListener {

    Button logoutDB;
    TextView txtName;
    UserLocalStore userLocalStore;

    private MyBroadcastReceiver myBroadcastReceiver;

    ArrayList<String> titles;
    ArrayList<String> prices;
    ArrayList<String> dropPrices;

    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);

        txtName = (TextView) findViewById(R.id.pName);

        logoutDB = (Button) findViewById(R.id.logoutDB);
        logoutDB.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);

        //prepare MyParcelable passing to intentMyIntentService
        String msgToIntentService = "YES - Loud and clear BRO!!!";

        //Start MyIntentService
        Intent intentMyIntentService = new Intent(this, Stocks.class);
        intentMyIntentService.putExtra(Stocks.EXTRA_KEY_IN, msgToIntentService);
        this.startService(intentMyIntentService);

        myBroadcastReceiver = new MyBroadcastReceiver();

        //register BroadcastReceiver
        IntentFilter intentFilter = new IntentFilter(Stocks.ACTION_MyIntentService);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        this.registerReceiver(myBroadcastReceiver, intentFilter);

        list = (ListView)findViewById(R.id.android_list2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //un-register BroadcastReceiver
        this.unregisterReceiver(myBroadcastReceiver);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {

        ArrayList<String> names;
        ArrayList<String> asks;
        ArrayList<String> changes;

        @Override
        public void onReceive(Context context, Intent intent) {

            names = intent.getStringArrayListExtra(Stocks.EXTRA_KEY_OUT_NAMES);
            asks = intent.getStringArrayListExtra(Stocks.EXTRA_KEY_OUT_ASKS);
            changes = intent.getStringArrayListExtra(Stocks.EXTRA_KEY_OUT_CHANGES);

            titles = names;
            prices = asks;
            dropPrices = changes;

            Calendar c = Calendar.getInstance();
            System.out.println("Current time => " + c.getTime());

            SimpleDateFormat df = new SimpleDateFormat("dd-MMM");
            String formattedDate = df.format(c.getTime());

            String[] dates = {formattedDate, formattedDate, formattedDate, formattedDate, formattedDate, formattedDate, formattedDate, formattedDate, formattedDate, formattedDate, formattedDate, formattedDate, formattedDate, formattedDate, formattedDate};

            PortfolioAdapter adapter = new PortfolioAdapter(PortfolioActivity.this, titles, dates, prices, dropPrices);
            list.setAdapter(adapter);

            findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (authenticate() == true){
            System.out.println("User is logged in! !!!!!!");
            displayUserDetails();
        }
        else {
            startActivity(new Intent(PortfolioActivity.this, LoginActivity.class));
        }
    }

    private boolean authenticate() {
        return userLocalStore.getUserLoggedIn();
    }

    private void displayUserDetails() {
        User user = userLocalStore.getLoggedInUser();

        txtName.setText(user.name);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.logoutDB:

                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);

                startActivity(new Intent(this, LoginActivity.class));

                break;
        }
    }
}
