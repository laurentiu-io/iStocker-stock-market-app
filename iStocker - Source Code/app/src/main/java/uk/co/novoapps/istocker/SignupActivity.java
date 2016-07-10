package uk.co.novoapps.istocker;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressWarnings("ALL")
public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    Button signupDB;
    EditText etName, etUsername, etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupDB = (Button) findViewById(R.id.signupDB);
        etName = (EditText) findViewById(R.id.name);
        etUsername = (EditText) findViewById(R.id.pUsername);
        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.pPassword);

        signupDB.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

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
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.signupDB:

                String name = etName.getText().toString();
                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                User user = new User(name, username, email, password);

                registerUser(user);
                break;
        }
    }

    private void registerUser(User user){
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });
    }
}
