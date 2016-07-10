package uk.co.novoapps.istocker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class NewsFragment extends ListFragment {

    public static ArrayList<String> titles = new ArrayList<>();
    public static ArrayList<String> dates = new ArrayList<>();
    public static ArrayList<String> thumbnails = new ArrayList<>();

    ListView lv;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_news, container, false);

        ReadRss readRss = new ReadRss(getActivity());
        readRss.execute();

        try {
            readRss.get(1000, TimeUnit.MILLISECONDS);

            System.out.println(titles);

            String[] titlesNew = new String[ titles.size() ];
            String[] datesNew = new String[ dates.size() ];
            String[] thumbnailsNew = new String[ thumbnails.size() ];

            titles.toArray( titlesNew );
            dates.toArray( datesNew );
            thumbnails.toArray(thumbnailsNew);

            //Initialize ListView
            lv = (ListView) rootView.findViewById(android.R.id.list);

            //Create adapter and set ListView to it
            NewsAdapter adapter = new NewsAdapter(getActivity(), titlesNew, thumbnailsNew, datesNew);
            setListAdapter(adapter);
            setRetainInstance(true);
            //getActivity().findViewById(R.id.loadingPanelNews).setVisibility(View.GONE);

        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);

        ViewGroup viewGroup = (ViewGroup)v;
        RelativeLayout layout = (RelativeLayout)viewGroup.findViewById(R.id.row_news);
        layout.setBackgroundColor(getResources().getColor(R.color.darkBlue));

        String URL = "http://news.sky.com/business";
        Uri uri = Uri.parse(URL);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}