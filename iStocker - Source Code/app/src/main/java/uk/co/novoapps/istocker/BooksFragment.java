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
public class BooksFragment extends ListFragment {

    ListView lv;
    View rootView;
    ResourcesAdapter adapter;

    String[] headlines = {"1.The Intelligent Investor", "2.Common Stocks and Uncommon Profits", "3.Stocks For The Long Run", "4.One Up On Wall Street",
            "5.How To Make Money In Stocks", "6.Rich Dad Poor Dad", "7.Common Sense On Mutual Funds", "8.The Making of an American Capitalist",
            "9.Reminiscences of a Stock Operator", "10.A Random Walk Down Wall Street","11.The Little Book of Common Sense Investing",
            "12.Alchemy of Finance", "13.The Age of Turbulence", "14.Security Analysis", "15.The Warren Buffett Way"};

    String[] descriptions = {"Benjamin Graham", "Philip Fisher", "Jeremy Siegel", "Peter Lynch", "William J. O'Neil",
            "Robert T. Kiyosaki", "John C. Bogle", "Roger Lowenstein", "Edwin Lefèvre", "Burton Malkiel", "Jack Bogle",
            "George Soros", "Alan Greenspan", "Benjamin Graham", "Robert G. Hagstrom"};

    String[] URLs = {"http://www.amazon.co.uk/Intelligent-Investor-Definitive-Investing-Practical/dp/0060555661",
                    "http://www.amazon.co.uk/Uncommon-Profits-Writings-Investment-Classics/dp/0471445509/ref=sr_1_1?s=books&ie=UTF8&qid=1456934885&sr=1-1&keywords=Common+Stocks+and+Uncommon+Profits",
                    "http://www.amazon.co.uk/Stocks-Long-Run-Definitive-Investment/dp/0071800514/ref=sr_1_1?s=books&ie=UTF8&qid=1456934919&sr=1-1&keywords=Stocks+For+The+Long+Run",
                    "http://www.amazon.co.uk/One-Up-Wall-Street-Fireside/dp/0743200403/ref=sr_1_1?s=books&ie=UTF8&qid=1456934955&sr=1-1&keywords=One+Up+On+Wall+Street",
                    "http://www.amazon.co.uk/How-Make-Money-Stocks-Winning/dp/0071614133/ref=sr_1_1?s=books&ie=UTF8&qid=1456934982&sr=1-1&keywords=How+To+Make+Money+In+Stocks",
                    "http://www.amazon.co.uk/Rich-Dad-Poor-Robert-Kiyosaki/dp/1612680003/ref=sr_1_1?s=books&ie=UTF8&qid=1456935030&sr=1-1&keywords=Rich+Dad+Poor+Dad",
                    "http://www.amazon.co.uk/Common-Sense-Mutual-Funds-Imperatives/dp/0470138130/ref=sr_1_1?s=books&ie=UTF8&qid=1456935060&sr=1-1&keywords=Common+Sense+On+Mutual+Funds",
                    "http://www.amazon.co.uk/Buffett-American-Capitalist-Roger-Lowenstein/dp/0752805991/ref=sr_1_1?s=books&ie=UTF8&qid=1456935085&sr=1-1&keywords=The+Making+of+an+American+Capitalist",
                    "http://www.amazon.co.uk/Reminiscences-Stock-Operator-Edwin-Lefevre/dp/1500541052/ref=sr_1_1?s=books&ie=UTF8&qid=1456935116&sr=1-1&keywords=Reminiscences+of+a+Stock+Operator",
                    "http://www.amazon.co.uk/Random-Walk-Down-Wall-Street/dp/0393340740/ref=sr_1_2?s=books&ie=UTF8&qid=1456935154&sr=1-2&keywords=A+Random+Walk+Down+Wall+Street",
                    "http://www.amazon.co.uk/Little-Book-Commonsense-Investing-Guarantee/dp/0470102101/ref=sr_1_1?s=books&ie=UTF8&qid=1456935199&sr=1-1&keywords=The+Little+Book+of+Common+Sense+Investing",
                    "http://www.amazon.co.uk/Alchemy-Finance-Reading-Investment-Classics/dp/0471445495/ref=sr_1_1?s=books&ie=UTF8&qid=1456935234&sr=1-1&keywords=Alchemy+of+Finance",
                    "http://www.amazon.co.uk/Age-Turbulence-Adventures-New-World/dp/0141029919/ref=sr_1_1?s=books&ie=UTF8&qid=1456935264&sr=1-1&keywords=The+Age+of+Turbulence",
                    "http://www.amazon.co.uk/Security-Analysis-Foreword-Warren-Buffett-Editions/dp/0071592539/ref=sr_1_1?s=books&ie=UTF8&qid=1456935308&sr=1-1&keywords=Security+Analysis",
                    "http://www.amazon.co.uk/Warren-Buffett-Way-Website/dp/1118503252/ref=sr_1_1?s=books&ie=UTF8&qid=1456935334&sr=1-1&keywords=The+Warren+Buffett+Way"};

    int[] images = {R.drawable.resource1,R.drawable.resource2,R.drawable.resource3,
            R.drawable.resource4,R.drawable.resource5,R.drawable.resource6, R.drawable.resource7, R.drawable.resource8,
            R.drawable.resource9, R.drawable.resource10, R.drawable.resource11, R.drawable.resource12, R.drawable.resource13,
            R.drawable.resource14, R.drawable.resource15};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_books, container, false);

        //Initialize ListView
        lv = (ListView) rootView.findViewById(android.R.id.list);

        //Create adapter and set ListView to it
        adapter = new ResourcesAdapter(getActivity(), headlines, descriptions, images, URLs);
        setListAdapter(adapter);
        setRetainInstance(true);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);

        ViewGroup viewGroup = (ViewGroup)v;
        Button books_button = (Button)viewGroup.findViewById(R.id.resource_button);
        books_button.setBackgroundColor(getResources().getColor(R.color.light_yellow));

        String URL = (String) URLs[pos];
        Uri uri = Uri.parse(URL);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
