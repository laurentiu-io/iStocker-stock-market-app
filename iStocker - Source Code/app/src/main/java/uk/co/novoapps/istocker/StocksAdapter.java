package uk.co.novoapps.istocker;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class StocksAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> stocksTitles;
    String[] stocksDates;
    ArrayList<String> stocksPrices;
    ArrayList<String> stocksDropPrices;
    LayoutInflater inflater;

    public StocksAdapter(Context context, ArrayList<String>  stocksTitles, String[] stocksDates, ArrayList<String> stocksPrices, ArrayList<String> stocksDropPrices) {
        super(context, R.layout.rowmodel_stocks, stocksTitles);

        this.context = context;
        this.stocksTitles= stocksTitles;
        this.stocksDates = stocksDates;
        this.stocksPrices = stocksPrices;
        this.stocksDropPrices = stocksDropPrices;
    }

    public class ViewHolder{
        TextView stocks_title;
        TextView dateTitleView;
        TextView stocksPriceView;
        TextView dropPrice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.rowmodel_stocks, null);
        }

        //Otherwise
        ViewHolder holder = new ViewHolder();

        //INITIALIZATIONS
        holder.stocks_title = (TextView) convertView.findViewById(R.id.stocks_title);
        holder.dateTitleView = (TextView) convertView.findViewById(R.id.dateTitleView);
        holder.stocksPriceView = (TextView) convertView.findViewById(R.id.stocksPriceView);
        holder.dropPrice = (TextView) convertView.findViewById(R.id.dropPrice);

        //SET TEXT AND IMAGE
        holder.stocks_title.setText(stocksTitles.get(position));
        holder.dateTitleView.setText(stocksDates[position]);
        holder.stocksPriceView.setText(stocksPrices.get(position));
        holder.dropPrice.setText(stocksDropPrices.get(position));
        holder.dropPrice.setTextColor(Color.GREEN);

        return convertView;
    }
}