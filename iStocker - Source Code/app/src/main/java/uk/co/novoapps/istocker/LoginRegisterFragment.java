package uk.co.novoapps.istocker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LoginRegisterFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.loginButton:

                Intent intent_login = new Intent(getActivity(), LoginActivity.class);
                this.startActivity(intent_login);

                break;

            case R.id.signupButton:

                Intent intent_signup = new Intent(getActivity(), SignupActivity.class);
                this.startActivity(intent_signup);

                break;
        }
    }

    public LoginRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_login_register, container, false);

        Button loginButton = (Button) rootView.findViewById(R.id.loginButton);
        Button signupButton = (Button) rootView.findViewById(R.id.signupButton);
        loginButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);

        // Inflate the layout for this fragment
        return rootView;
    }

}
