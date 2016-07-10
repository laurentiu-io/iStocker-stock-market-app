package uk.co.novoapps.istocker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@SuppressWarnings("ALL")
public class CommoditiesFragment extends ListFragment {

    private MyBroadcastReceiver myBroadcastReceiverCommodities;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_commodities, container, false);

        //prepare MyParcelable passing to intentMyIntentService
        String msgToIntentService = "YES - Loud and clear BRO !!!";

        //Start MyIntentService
        Intent intentMyIntentService = new Intent(getActivity(), Commodities.class);
        intentMyIntentService.putExtra(Commodities.COMMODITIES_KEY_IN, msgToIntentService);
        getActivity().startService(intentMyIntentService);

        myBroadcastReceiverCommodities = new MyBroadcastReceiver();

        //register BroadcastReceiver
        IntentFilter intentFilter = new IntentFilter(Commodities.ACTION_MyIntentServiceCommodities);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        getActivity().registerReceiver(myBroadcastReceiverCommodities, intentFilter);

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //un-register BroadcastReceiver
        getActivity().unregisterReceiver(myBroadcastReceiverCommodities);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {

        ArrayList<String> names;
        ArrayList<String> asks;
        ArrayList<String> changes;
        ArrayList<String> prevCloses;
        ArrayList<String> opens;
        ArrayList<String> dayLows;
        ArrayList<String> dayHighs;
        ArrayList<String> wkLows;
        ArrayList<String> wkHighs;

        @Override
        public void onReceive(Context context, Intent intent) {

            names = intent.getStringArrayListExtra(Commodities.COMMODITIES_KEY_OUT_NAMES);
            asks = intent.getStringArrayListExtra(Commodities.COMMODITIES_KEY_OUT_ASKS);
            changes = intent.getStringArrayListExtra(Commodities.COMMODITIES_KEY_OUT_CHANGES);
            prevCloses = intent.getStringArrayListExtra(Commodities.COMMODITIES_KEY_OUT_PREVCLOSES);
            opens = intent.getStringArrayListExtra(Commodities.COMMODITIES_KEY_OUT_OPENS);
            dayLows = intent.getStringArrayListExtra(Commodities.COMMODITIES_KEY_OUT_DAYLOWS);
            dayHighs = intent.getStringArrayListExtra(Commodities.COMMODITIES_KEY_OUT_DAYHIGHS);
            wkLows = intent.getStringArrayListExtra(Commodities.COMMODITIES_KEY_OUT_WKLOWS);
            wkHighs = intent.getStringArrayListExtra(Commodities.COMMODITIES_KEY_OUT_WKHIGHS);

            ArrayList<String> prices = asks;
            ArrayList<String> dropPrices = changes;

            Calendar c = Calendar.getInstance();
            System.out.println("Current time => " + c.getTime());

            SimpleDateFormat df = new SimpleDateFormat("dd-MMM");
            String formattedDate = df.format(c.getTime());

            String[] dates = {formattedDate, formattedDate, formattedDate, formattedDate, formattedDate, formattedDate, formattedDate, formattedDate, formattedDate, formattedDate, formattedDate, formattedDate, formattedDate, formattedDate, formattedDate};

            ArrayList<String> titles =  names;

            CommoditiesAdapter adapter = new CommoditiesAdapter(getActivity(), titles, dates, prices, dropPrices);
            setListAdapter(adapter);
            setRetainInstance(true);
            getActivity().findViewById(R.id.loadingPanelCo).setVisibility(View.GONE);

            System.out.println("The names received: " + names);
            System.out.println("The asks received: " + asks);
            System.out.println("The changes received: " + changes);
            System.out.println("The prevCloses received: " + prevCloses);
            System.out.println("The opens received: " + opens);
            System.out.println("The dayLows received: " + dayLows);
            System.out.println("The dayHighs received: " + dayHighs);
            System.out.println("The wkLows received: " + wkLows);
            System.out.println("The wkHighs received: " + wkHighs);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ViewGroup viewGroup = (ViewGroup)v;
        RelativeLayout layout = (RelativeLayout)viewGroup.findViewById(R.id.row_commodities);
        layout.setBackgroundColor(getResources().getColor(R.color.darkBlue));

        MarketsItem marketsItem = new MarketsItem();
        // Get a reference to the FragmentManager
        FragmentManager fragmentManager = getFragmentManager();
        // Begin a new FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_commodities, marketsItem);
        fragmentTransaction.commit();
    }
}

