package uk.co.novoapps.istocker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressWarnings("ALL")
public class ResourcesAdapter extends ArrayAdapter<String> {

    Context context;
    String[] resourceHeadlines;
    String[] resourceDescriptions;
    int[] resourceImages;
    String[] resourceURLs;
    LayoutInflater inflater;

    public ResourcesAdapter(Context context, String[] resourceHeadlines, String[] resourceDescriptions,
                            int[] resourceImages, String[] resourceURLs) {
        super(context, R.layout.rowmodel_resources, resourceHeadlines);

        this.context = context;
        this.resourceHeadlines = resourceHeadlines;
        this.resourceDescriptions = resourceDescriptions;
        this.resourceImages = resourceImages;
        this.resourceURLs = resourceURLs;
    }

    public class ViewHolder{
        ImageView imageView;
        TextView headlineView;
        TextView descriptionView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.rowmodel_resources, null);
        }

        //Otherwise
        ViewHolder holder = new ViewHolder();

        //INITIALIZATIONS
        holder.headlineView = (TextView) convertView.findViewById(R.id.resourceHeadline);
        holder.descriptionView = (TextView) convertView.findViewById(R.id.description);
        holder.imageView = (ImageView) convertView.findViewById(R.id.imageResource);

        //SET TEXT AND IMAGE
        holder.headlineView.setText(resourceHeadlines[position]);
        holder.descriptionView.setText(resourceDescriptions[position]);
        holder.imageView.setImageResource(resourceImages[position]);

        return convertView;
    }

}
