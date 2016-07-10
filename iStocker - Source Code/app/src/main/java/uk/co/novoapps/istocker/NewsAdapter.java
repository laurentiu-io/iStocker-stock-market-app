package uk.co.novoapps.istocker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

@SuppressWarnings("ALL")
public class NewsAdapter extends ArrayAdapter<String>{

    Context context;
    String[] titles;
    String[] dates;
    String[] thumbnails;
    LayoutInflater inflater;

    public NewsAdapter(Context context, String[] titles, String[] thumbnails, String[] dates) {
        super(context, R.layout.rowmodel_news, titles);

        this.context = context;
        this.titles = titles;
        this.thumbnails = thumbnails;
        this.dates = dates;
    }

    public class ViewHolder{
        ImageView imageView;
        TextView textView;
        TextView date;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.rowmodel_news, null);
        }

        //Otherwise
        ViewHolder holder = new ViewHolder();

        //INITIALIZATIONS
        holder.textView = (TextView) convertView.findViewById(R.id.textView);
        holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
        holder.date = (TextView) convertView.findViewById(R.id.date);

        //SET TEXT AND IMAGE
        holder.textView.setText(titles[position]);
        //holder.imageView.setImageResource(thumbnails[position]);
        Picasso.with(context).load(thumbnails[position]).into(holder.imageView);
        holder.date.setText(dates[position]);

        return convertView;
    }
}