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
public class DocumentariesFragment extends ListFragment {

    ListView lv;
    View rootView;
    Button doc_button;

    String[] headlines = {"1.How The Economic Mchine Works", "2.Finance and Investing in Under One Hour", "3.Money - Master The Game",
            "4.Inside Job", "5.Trader", "6.25 Million Pounds", "7.Frontline: Breaking the Bank", "8.The Ascent of Money",
            "9.Frontline: Money, Power and Wall Street", "10.Global Financial Meltdown", "11.The Warning", "12.Freakonomics: The Movie",
            "13.Margin Call", "14.Boiler Room", "15.Wall Street(1987)"};

    String[] descriptions = {"Ray Dalio", "William Ackman", "Tony Robbins", "Charles Ferguson", "Paul Tudor Jones",
            "Nick Leeson", "American Banking Crisis", "Miguel Aldama", "Financial Crisis", "Financial Crisis", "2008",
            "Economics", "Financial Crisis", "Investment Banking", "Stockbrokers"};

    int[] images = {R.drawable.doc1, R.drawable.doc2, R.drawable.doc3,
            R.drawable.doc4, R.drawable.doc5, R.drawable.doc6, R.drawable.doc7, R.drawable.doc8,
            R.drawable.doc9, R.drawable.doc10, R.drawable.doc11, R.drawable.doc12, R.drawable.doc13,
            R.drawable.doc14, R.drawable.doc15};

    protected String[] URLs = {"https://www.youtube.com/watch?v=PHe0bXAIuk0",
            "https://www.youtube.com/watch?v=WEDIj9JBTC8&spfreload=10",
            "https://www.youtube.com/watch?v=OaAMG_cMw9o&spfreload=10",
            "https://www.youtube.com/watch?v=D9ub25WjEK0",
            "https://vimeo.com/108533942",
            "https://www.youtube.com/watch?v=NUaCo_bPePw&list=PLPnhyXN_HgsTYLubY4iQ6tbrr_c81-Z0W&spfreload=10",
            "http://www.pbs.org/video/1168339502/",
            "https://vimeo.com/17803671",
            "https://www.youtube.com/watch?v=MXDUFkLWQJQ&spfreload=10",
            "https://www.youtube.com/watch?v=VQzEWeGJLP0&spfreload=10",
            "https://www.youtube.com/watch?v=ACkiKVtF3nU&spfreload=10",
            "https://www.youtube.com/watch?v=-8n2yhy35Hg&list=PLifUFMxkf1X1urb3YkWIuEtFlAybUrF9t",
            "http://www.imdb.com/title/tt1615147/",
            "http://www.imdb.com/title/tt0181984/",
            "http://www.imdb.com/title/tt0094291/"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_documentaries, container, false);

        //Create adapter and set ListView to it
        ResourcesAdapter adapter = new ResourcesAdapter(getActivity(), headlines, descriptions, images, URLs);
        setListAdapter(adapter);
        setRetainInstance(true);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);

        ViewGroup viewGroup = (ViewGroup) v;
        doc_button = (Button) viewGroup.findViewById(R.id.resource_button);
        doc_button.setBackgroundColor(getResources().getColor(R.color.dark_red));

        String URL = (String) URLs[pos];
        Uri uri = Uri.parse(URL);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


}
