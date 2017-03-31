package circle.malabarblood.malabarbloodcircle;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.FirebaseDatabase;



public class MainActivity extends AppCompatActivity {

    public static boolean calledAlready = false;

    public FirebaseAnalytics mFirebaseAnalytics;

    public FirebaseAuth mFirebaseAuth;
    public FirebaseUser mFirebaseUser;
    public String mUsername ;

    public SignInButton signInButton;



    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    private TextView mStatusTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        findViewById(R.id.firstpageAddId).setVisibility(View.GONE);

        signInButton = (SignInButton)findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        mStatusTextView = (TextView) findViewById(R.id.status);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(MainActivity.this, "Try Again Later", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarId);
        setSupportActionBar(toolbar);

        TextView about = (TextView)findViewById(R.id.aboutUs);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder  builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("--MBC - A Youth Initiative For Transforming the Purpose  Of Blood Donation.--\n\n"+"Contact:\n\n" +"Kozhikode:\n9037811407, 8113080704.\n\n"+
                        "Kannur:\n8907226896, 9037811407.\n\n"+
                        "Malappuram:\n9633573285, 8089185739.").setTitle(" MALABAR BLOOD CIRCLE ");

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        if (isNetworkAvailable()){

        }
        else {
            AlertDialog.Builder  builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("No Internet Connection ").setTitle("Warning..");
            builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        if (!calledAlready)
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }

    }



    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public void AddUser(View view) {


        finish();

        final Intent intent = new Intent(this,AddUserActivity.class);
        startActivity(intent);

    }

    public void searchUsers(View view) {

        finish();

        Intent intent = new Intent(this,FirstPage.class);
        startActivity(intent);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            String email = acct.getEmail();
            mStatusTextView.setText(acct.getDisplayName());

            if (email.equals("yaseenkarumara@gmail.com") ||
                    email.equals("brijinrajpgj@gmail.com") ||
                    email.equals("basimnana@gmail.com")||
                    email.equals("suparna0489@gmail.com")||
                    email.equals("meetpranavts@gmail.com")||
                    email.equals("mkmbwawa313@gmail.com")||
                    email.equals("midhunchakravarty@gmail.com")||
                    email.equals("jithu.nitc@gmail.com")||
                    email.equals("mvsreenath@gmail.com") ){
                updateUI(true);
            }
            else {
                findViewById(R.id.sign_in_button).setVisibility(View.GONE);

            }

        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    private void updateUI(boolean signedIn) {
        if (signedIn) {
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.firstpageAddId).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.firstpageAddId).setVisibility(View.GONE);

            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
        }
    }


}

