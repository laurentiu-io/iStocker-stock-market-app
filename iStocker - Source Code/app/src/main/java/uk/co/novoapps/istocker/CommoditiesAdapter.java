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
public class CommoditiesAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> commoditiesTitles;
    String[] commoditiesDates;
    ArrayList<String> commoditiesPrices;
    ArrayList<String> commoditiesDropPrices;
    LayoutInflater inflater;

    public CommoditiesAdapter(Context context, ArrayList<String>  commoditiesTitles, String[] commoditiesDates, ArrayList<String> commoditiesPrices, ArrayList<String> commoditiesDropPrices) {
        super(context, R.layout.rowmodel_commodities, commoditiesTitles);

        this.context = context;
        this.commoditiesTitles= commoditiesTitles;
        this.commoditiesDates = commoditiesDates;
        this.commoditiesPrices = commoditiesPrices;
        this.commoditiesDropPrices = commoditiesDropPrices;
    }

    public class ViewHolder{
        TextView commodities_title;
        TextView commoditiesDateTitleView;
        TextView commoditiesPriceView;
        TextView commoditiesDropPrice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.rowmodel_commodities, null);
        }

        //Otherwise
        ViewHolder holder = new ViewHolder();

        //INITIALIZATIONS
        holder.commodities_title = (TextView) convertView.findViewById(R.id.commodities_title);
        holder.commoditiesDateTitleView = (TextView) convertView.findViewById(R.id.commoditiesDateTitleView);
        holder.commoditiesPriceView = (TextView) convertView.findViewById(R.id.commoditiesPriceView);
        holder.commoditiesDropPrice = (TextView) convertView.findViewById(R.id.commoditiesDropPrice);

        //SET TEXT AND IMAGE
        holder.commodities_title.setText(commoditiesTitles.get(position));
        holder.commoditiesDateTitleView.setText(commoditiesDates[position]);
        holder.commoditiesPriceView.setText(commoditiesPrices.get(position));
        holder.commoditiesDropPrice.setText(commoditiesDropPrices.get(position));
        holder.commoditiesDropPrice.setTextColor(Color.GREEN);

        return convertView;
    }
}