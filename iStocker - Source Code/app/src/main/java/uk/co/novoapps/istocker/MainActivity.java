package uk.co.novoapps.istocker;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Set the fragment initially
        NewsFragment fragment = new NewsFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "iStocker - Best Stock Market App", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        NewsFragment news_fragment = new NewsFragment();

        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();

        switch(item.getItemId()) {

            case R.id.login:

                Intent intent_login = new Intent(this, LoginRegister.class);
                this.startActivity(intent_login);

                Toast.makeText(getBaseContext(), "You selected Login/Register", Toast.LENGTH_SHORT).show();
                break;

            case R.id.news:

                fragmentTransaction.replace(R.id.fragment_container, news_fragment);
                fragmentTransaction.commit();

                Toast.makeText(getBaseContext(), "You selected News", Toast.LENGTH_SHORT).show();
                break;

            case R.id.markets:

                Intent intent = new Intent(this, MarketsActivity.class);
                this.startActivity(intent);

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logreg) {
            Intent intent = new Intent(this, LoginRegister.class);
            this.startActivity(intent);

            Toast.makeText(getBaseContext(), "You selected Login/Register", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_markets) {
            Intent intent = new Intent(this, MarketsActivity.class);
            this.startActivity(intent);

            Toast.makeText(getBaseContext(), "You selected Markets", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_news) {

            NewsFragment fragment = new NewsFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

            Toast.makeText(getBaseContext(), "You selected News", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_portfolio) {

            Intent intentPortfolio = new Intent(this, PortfolioActivity.class);
            this.startActivity(intentPortfolio);

            Toast.makeText(getBaseContext(), "Please login to access your portfolio! ", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_resources) {
            Intent intent_resources = new Intent(this, ResourcesActivity.class);
            this.startActivity(intent_resources);

            Toast.makeText(getBaseContext(), "You selected Resources", Toast.LENGTH_SHORT).show();

        }  else if (id == R.id.nav_invite) {

            String URL = "https://www.facebook.com/";
            Uri uriInvite = Uri.parse(URL);
            Intent intentInvite = new Intent(Intent.ACTION_VIEW, uriInvite);
            startActivity(intentInvite);

            Toast.makeText(getBaseContext(), "You selected Invite Friends", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_rate) {

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

            Toast.makeText(getBaseContext(), "You selected Rate App", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
