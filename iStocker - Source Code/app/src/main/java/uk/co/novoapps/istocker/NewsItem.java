package uk.co.novoapps.istocker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsItem extends Fragment{

    ImageView newsItem_image;
    TextView headline;
    TextView textContainer;
    String headlineText = "Wall Street down for second day as data disappoints";
    String article = "<p>U.S. stocks posted losses for the second day in a row on Wednesday as investors looked for clues as to the Federal Reserve’s next policy move while economic data painted a grim picture of the economy and attempted to stage a recovery.</p>" +
            "<p>The Dow 30 dropped 153 points or 0.93%, while the S&P 500 traded down 15 points or 0.79% and the tech-heavy NASDAQ Compositefell 32 points or 0.71% at 16:50GMT or 11:50AM ET.</p>" +
            "<p>Even though the next monetary policy meeting is three weeks away stateside, investors digested speeches from several Fed officials to gauge the pulse of the U.S. central bank on the next move on interest rates.</p>" +
            "<p>Nevertheless, Fed Vice Chair Stanley Fischer admitted after the market close on Tuesday that speculation was useless as “we simply don’t know”.</p>" +
            "<p>Still, Kansas City Fed president Esther George did insist on Tuesday that a rate hike should definitely be on the table.</p>" +
            "<p>On Wednesday, Richmond Fed president Jeffrey Lacker, while a non-voter this year and known for his hawkish stance, said that the case for a rate hike was bolstered by recent data.</p>" +
            "<p>Still on the docket, Dallas Fed President Rob Kaplan will speak at 18:15GMT, or 13:15PM ET, while St. Louis Fed President James Bullard is scheduled to give remarks at 00:00GMT or 19:00ET.</p>" +
            "<p>Oil managed to stage a turnaround near mid-session despite worries over the supply glut and lack of commitment to cut production although West Texas remained in the red.</p>" +
            "<p>Speaking before an audience at the CERAWeek 2016 Energy conference in Houston, Saudi Arabia oil minister Ali al-Naimi said that the kingdom will not lower production from its current levels, resisting calls to slash output in an effort to boost prices.</p>" +
            "<p>Meanwhile, Iran made clear it has no interest in freezing production after sanctions against it were lifted, calling last week’s joint Russian/Saudi proposal for major exporters to cap output “ridiculous” and \\\"laughable\\\".</p>" +
            "<p>Losses in black gold did get a brief respite and moved off session lows as the U.S. Energy Information Administration said in its weekly report that crude oil inventories increased by 3.5 million barrels in the week ended February 19. Market analysts\\' expected a crude-stock rise of 3.4 million barrels.</p>" +
            "<p>Crude oil futures for April delivery on the New York Mercantile Exchange struggled to turn positive but still slipped $0.02, or 0.06%, to trade at $31.85 a barrel by 16:56GMT, or 11:56AM ET, while Brent oil managed to leave early losses behind and rise $0.57 or 1.71% to $33.84.</p>" +
            "<p>Meanwhile on the macro data front, new home sales tumbled 9.2% in January, more than double the analyst forecast for a 4.4% decline.</p>" +
            "<p>In other bad news, the services PMI for February unexpectedly fell to 49.8 from the prior month’s 53.2. Readings below 50 indicate that activity contracted in the sector.</p>" +
            "<p>In business news, shares in Target Corporation (N:TGT)gained 2% despite worse-than-expected earnings. The second-largest retailer in the U.S. gave a bullish annual guidance that topped analyst’s estimates.</p>" +
            "<p>Lowe`s Companies Inc (N:LOW) didn’t share a similar fate and shed 2.4%, even though the home improvement retailer reported better-than-expected quarterly earnings.</p>" +
            "<p>Without a doubt, Avis Budget Group Inc (O:CAR) was the hardest hit by earnings with shares crashing 24.1% after the rental car company posted profit that missed consensus.";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_news_item, container, false);

        headline = (TextView) view.findViewById(R.id.headline);
        headline.setText(headlineText);

        textContainer = (TextView) view.findViewById(R.id.textFull);
        textContainer.setText(Html.fromHtml(article));

        newsItem_image = (ImageView) view.findViewById(R.id.newsItem_image);
        newsItem_image.setImageResource(R.drawable.markets1);

        return view;
    }

}
