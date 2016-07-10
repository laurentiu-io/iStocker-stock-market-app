package uk.co.novoapps.istocker;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@SuppressWarnings("ALL")
public class ReadRss extends AsyncTask<Void, Void, Void>{

    Context context;
    ProgressDialog progressDialog;
    String address = "http://feeds.skynews.com/feeds/rss/business.xml";
    URL url;

    public ReadRss(Context context){
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid){
        progressDialog.dismiss();
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(Void... params) {
        ProcessXml(getData());
        return null;
    }

    private void ProcessXml(Document data) {
        if (data != null){

            NewsFragment.titles.clear();
            NewsFragment.dates.clear();
            NewsFragment.thumbnails.clear();

            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);
            NodeList items = channel.getChildNodes();

            for (int i = 0; i < items.getLength(); i++){
                Node currentChild = items.item(i);
                if (currentChild.getNodeName().equalsIgnoreCase("item")){
                    NodeList itemChild = currentChild.getChildNodes();

                    for (int j = 0; j < itemChild.getLength(); j++){
                        Node current = itemChild.item(j);

                        if (current.getNodeName().equalsIgnoreCase("title")){
                            String title = current.getTextContent().toString();
                            NewsFragment.titles.add(title);
                        }
                        else if (current.getNodeName().equalsIgnoreCase("pubDate")){
                            String date = current.getTextContent().toString();
                            NewsFragment.dates.add(date);
                        }
                        else if (current.getNodeName().equalsIgnoreCase("media:thumbnail")){
                            String url = current.getAttributes().item(0).getTextContent();
                            NewsFragment.thumbnails.add(url);
                        }
                    }
                }
            }
        }
    }

    public Document getData(){
        try {
            url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDoc = builder.parse(inputStream);

            return xmlDoc;

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

}
