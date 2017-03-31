package circle.malabarblood.malabarbloodcircle;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import java.util.ArrayList;
import java.util.Objects;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;


public class FirstPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public Button searchButton;
    public Spinner spinnerDist, spinnerTaluk;
    public boolean calledAlready =false;


    String uGroup,uDistrict,uTaluk;


    FirebaseDatabase mFirebaseDatabase;

    GenericTypeIndicator<ArrayList<User>> quesListGenericTypeIndicator;
    public static ArrayList<User> downloaadusers;
    int totalUsers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // final Intent intent = new Intent(this,AddUserActivity.class);


     /**   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });**/




        radioBtn();

        searchButton = (Button)findViewById(R.id.searchButtonId);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (uGroup == null ){
                    AlertDialog.Builder  builder = new AlertDialog.Builder(FirstPage.this);
                    builder.setMessage("Select Blood Group").setTitle("Warning");

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if (Objects.equals(uDistrict, "Select")){
                    AlertDialog.Builder  builder = new AlertDialog.Builder(FirstPage.this);
                    builder.setMessage("Select District").setTitle("Warning");

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if (Objects.equals(uTaluk, "Select")){
                    AlertDialog.Builder  builder = new AlertDialog.Builder(FirstPage.this);
                    builder.setMessage("Select Taluk").setTitle("Warning");

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {


                    progressbar();
                    Runnable progressRunnable = new Runnable() {

                        @Override
                        public void run() {
                            if (totalUsers==0){
                                AlertDialog.Builder  builder = new AlertDialog.Builder(FirstPage.this);
                                builder.setMessage("No Donor Available Now !").setTitle("Search Result");
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        finish();
                                        Intent intent = getIntent();
                                        startActivity(intent);
                                    }
                                });
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            else {

                                finish();
                                Intent intent = new Intent(FirstPage.this,SearchResult.class);
                                startActivity(intent);
                            }
                        }
                    };

                    Handler pdCanceller = new Handler();
                    pdCanceller.postDelayed(progressRunnable, 10000);





                }


            }
        });

        spinnerDist = (Spinner) findViewById(R.id.spinnerDistricId);
        spinnerDist.setOnItemSelectedListener(this);

        spinnerTaluk = (Spinner) findViewById(R.id.spinnerTalukId);
        spinnerTaluk.setOnItemSelectedListener(this);
    }




    public void radioBtn(){


        MultiLineRadioGroup mMultiLineRadioGroup = (MultiLineRadioGroup) findViewById(R.id.main_activity_multi_line_radio_group);

        mMultiLineRadioGroup.setOnCheckedChangeListener(new MultiLineRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ViewGroup group, RadioButton button) {

                uGroup = (String) button.getText();

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        switch (parent.getId())
        {
            case R.id.spinnerDistricId:

                uDistrict = (String) parent.getItemAtPosition(position);

                if (uDistrict.equals("Select")){

                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.spinnerSelect, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerTaluk.setAdapter(adapter);
                }

                else if (uDistrict.equals("Kannur")){

                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.spinnerKannur, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerTaluk.setAdapter(adapter);
                }

                else if (uDistrict.equals("Kasargod")){

                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.spinnerKasargod, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerTaluk.setAdapter(adapter);
                }

                else if (uDistrict.equals("Wayanad")){

                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.spinnerWayanad, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerTaluk.setAdapter(adapter);
                }

                else if (uDistrict.equals("kozhikode")){

                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.spinnerKozhikode, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerTaluk.setAdapter(adapter);
                }

                else if (uDistrict.equals("Malappuram")){

                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.spinnerMalappuram, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerTaluk.setAdapter(adapter);
                }


                break;

            case R.id.spinnerTalukId:
                uTaluk = (String) parent.getItemAtPosition(position);

                if (uTaluk.equals("Select") || uTaluk.equals("Select District") ){

                }
                else {
                    firebaseDownload();

                }
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void firebaseDownload() {



        quesListGenericTypeIndicator = new GenericTypeIndicator<ArrayList<User>>() {
        };

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference mDatabaseReference = mFirebaseDatabase.getReference();
        DatabaseReference mQuestionReference = mDatabaseReference.child(uDistrict+"/"+uTaluk+"/"+uGroup);



        mQuestionReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                downloaadusers = dataSnapshot.getValue(quesListGenericTypeIndicator);



                Runnable progressRunnable = new Runnable() {

                    @Override
                    public void run() {
                        try {
                            totalUsers = downloaadusers.size();

                        } catch (NullPointerException e) {

                            totalUsers =0;
                        }
                    }
                };

                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 10000);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();


        Intent intent = new Intent(FirstPage.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    public void progressbar(){
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Searching...");
        progress.setMessage("Please wait...");
        progress.setCancelable(false);
        progress.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                progress.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 10000);
    }
}
