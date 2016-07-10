package uk.co.novoapps.istocker;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

@SuppressWarnings("ALL")
public class WebsitesFragment extends ListFragment {

    ListView lv;
    View rootView;
    Button web_button;

    String[] headlines = {"1.Investopedia", "2.Yahoo Finance", "3.Motley Fool",
            "4.The Street", "5.Wall Street Journal", "6.MSN Money", "7.Zacks Investment Research", "8.Investor Guide",
            "9.Seeking Alpha", "10.E-Trade"};

    String[] descriptions = {"investopedia.com", "finance.yahoo.com", "fool.com", "thestreet.com", "wsj.com",
            "money.msn.com", "zacks.com", "investorguide.com", "seekingalpha.com", "us.etrade.com"};

    int[] images = {R.drawable.web1,R.drawable.web2,R.drawable.web3,
            R.drawable.web4,R.drawable.web5,R.drawable.web6, R.drawable.web7, R.drawable.web8,
            R.drawable.web9, R.drawable.web10};

    protected String[] URLs = {"http://www.investopedia.com/",
            "http://finance.yahoo.com/",
            "http://www.fool.com/",
            "http://www.thestreet.com/",
            "http://www.wsj.com/europe",
            "http://www.msn.com/en-us/money/markets",
            "http://www.zacks.com/",
            "http://www.investorguide.com/",
            "http://seekingalpha.com/",
            "https://us.etrade.com/home"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup)container;
        web_button = (Button)viewGroup.findViewById(R.id.resource_button);
        //web_button.setBackgroundColor(web_button.getContext().getResources().getColor(R.color.green));
        //web_button.setText("Visit Now");

        rootView = inflater.inflate(R.layout.fragment_documentaries, container, false);

        //Initialize ListView
        lv = (ListView) rootView.findViewById(android.R.id.list);

        //Create adapter and set ListView to it
        ResourcesAdapter adapter = new ResourcesAdapter(getActivity(), headlines, descriptions, images, URLs);
        setListAdapter(adapter);
        setRetainInstance(true);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);

        ViewGroup viewGroup = (ViewGroup)v;
        web_button = (Button)viewGroup.findViewById(R.id.resource_button);
        web_button.setBackgroundColor(getResources().getColor(R.color.dark_green));

        String URL = (String) URLs[pos];
        Uri uri = Uri.parse(URL);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}
