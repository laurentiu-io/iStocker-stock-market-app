package uk.co.novoapps.istocker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class ForexAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> forexTitles;
    String[] forexDates;
    ArrayList<String> forexPrices;
    ArrayList<String> forexDropPrices;
    LayoutInflater inflater;

    public ForexAdapter(Context context, ArrayList<String>  forexTitles, String[] forexDates, ArrayList<String> forexPrices, ArrayList<String> forexDropPrices) {
        super(context, R.layout.rowmodel_forex, forexTitles);

        this.context = context;
        this.forexTitles= forexTitles;
        this.forexDates = forexDates;
        this.forexPrices = forexPrices;
        this.forexDropPrices = forexDropPrices;
    }

    public class ViewHolder{
        TextView forex_title;
        TextView forexDateTitleView;
        TextView forexPriceView;
        TextView forexDropPrice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.rowmodel_forex, null);
        }

        //Otherwise
        ViewHolder holder = new ViewHolder();

        //INITIALIZATIONS
        holder.forex_title = (TextView) convertView.findViewById(R.id.forex_title);
        holder.forexDateTitleView = (TextView) convertView.findViewById(R.id.forexDateTitleView);
        holder.forexPriceView = (TextView) convertView.findViewById(R.id.forexPriceView);
        holder.forexDropPrice = (TextView) convertView.findViewById(R.id.forexDropPrice);

        //SET TEXT AND IMAGE
        holder.forex_title.setText(forexTitles.get(position));
        holder.forexDateTitleView.setText(forexDates[position]);
        holder.forexPriceView.setText(forexPrices.get(position));
        holder.forexDropPrice.setText(forexDropPrices.get(position));

        return convertView;
    }
}
