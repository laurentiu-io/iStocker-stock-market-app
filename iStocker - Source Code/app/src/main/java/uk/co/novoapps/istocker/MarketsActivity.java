package uk.co.novoapps.istocker;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MarketsActivity extends AppCompatActivity implements MaterialTabListener {

    private MaterialTabHost tabHost;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markets);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        tabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(adapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        //Inflate the menu: thi adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {

            case R.id.login:

                Intent intent_login = new Intent(this, LoginRegister.class);
                this.startActivity(intent_login);

                Toast.makeText(getBaseContext(), "You selected Login/Register", Toast.LENGTH_SHORT).show();
                break;

            case R.id.news:

                Intent intent_news = new Intent(this, MainActivity.class);
                this.startActivity(intent_news);

                Toast.makeText(getBaseContext(), "You selected News", Toast.LENGTH_SHORT).show();
                break;

            case R.id.markets:

                Intent intent_markets = new Intent(this, MarketsActivity.class);
                this.startActivity(intent_markets);

                Toast.makeText(getBaseContext(), "You selected Markets", Toast.LENGTH_SHORT).show();
                break;

            case R.id.portfolio:

                Toast.makeText(getBaseContext(), "Please login to access your portfolio", Toast.LENGTH_SHORT).show();
                Intent intentLogin = new Intent(this, LoginActivity.class);
                this.startActivity(intentLogin);

                break;

            case R.id.resources:

                Intent intent_resources = new Intent(this, ResourcesActivity.class);
                this.startActivity(intent_resources);

                Toast.makeText(getBaseContext(), "You selected Resources", Toast.LENGTH_SHORT).show();
                break;

            case R.id.invite_friends:

                String URL = "https://www.facebook.com/";
                Uri uriInvite = Uri.parse(URL);
                Intent intentInvite = new Intent(Intent.ACTION_VIEW, uriInvite);
                startActivity(intentInvite);

                Toast.makeText(getBaseContext(), "You selected Invite Friends", Toast.LENGTH_SHORT).show();
                break;

            case R.id.rate_app:
                Toast.makeText(getBaseContext(), "You selected Rate App", Toast.LENGTH_SHORT).show();

                Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);

                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
                }
                break;
        }
        return true;

    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        viewPager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        public Fragment getItem(int position){
            switch (position) {
                case 0:
                    return new StocksFragment();
                case 1:
                    return new ForexFragment();
                case 2:
                    return new CommoditiesFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount(){
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position){
            return getResources().getStringArray(R.array.tabs)[position];
        }
    }
}