package circle.malabarblood.malabarbloodcircle;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddUserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public Spinner spinnerDist, spinnerTaluk;

    String uName,bGroup,uDistrict,uTaluk,uPlace,uMobile;

    public EditText iName,iMobile,iPlace;

    private DatabaseReference mDatabase;

    String timeNow;

    int usersNo = 0;




   public FirebaseDatabase mFirebaseDatabase;
   public GenericTypeIndicator<ArrayList<User>> quesListGenericTypeIndicator;
   public ArrayList<User> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

      /**  if (!calledAlready)
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }
**/
        mDatabase = FirebaseDatabase.getInstance().getReference();

        iName = (EditText)findViewById(R.id.addUserNameid);
        iMobile = (EditText)findViewById(R.id.addDonnerMobileid);
        iPlace = (EditText)findViewById(R.id.addDonnerPlaceId);

        radioBtn();


        spinnerDist = (Spinner) findViewById(R.id.spinnerDistricId);
        spinnerDist.setOnItemSelectedListener(this);

        spinnerTaluk = (Spinner) findViewById(R.id.spinnerTalukId);
        spinnerTaluk.setOnItemSelectedListener(this);

        dateAndTime();

    }


    public void findUsersNumber() {




        quesListGenericTypeIndicator = new GenericTypeIndicator<ArrayList<User>>() {};


        mFirebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference mDatabaseReference = mFirebaseDatabase.getReference();
        DatabaseReference mQuestionReference = mDatabaseReference.child(uDistrict +"/" + uTaluk + "/" +bGroup);

        mQuestionReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                userArrayList = dataSnapshot.getValue(quesListGenericTypeIndicator);



                try {
                    usersNo = userArrayList.size();

                } catch (NullPointerException e) {
                    usersNo =0;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w("mTag", "Failed to read value.", databaseError.toException());
                //mCallbackForUI.showUI(userArrayList);
            }
        });
    }

    private void writeNewUser(String userId,String bloodgroup, String name, String Mobile,String district,String taluk,String Place,String lastDate) {
        User user = new User(name, Mobile,bloodgroup,district,taluk,Place,lastDate);

        mDatabase.child(district+"/"+taluk+"/"+bGroup).child(userId).setValue(user);
    }

    public void radioBtn(){


        MultiLineRadioGroup mMultiLineRadioGroup = (MultiLineRadioGroup) findViewById(R.id.main_activity_multi_line_radio_group);

        mMultiLineRadioGroup.setOnCheckedChangeListener(new MultiLineRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ViewGroup group, RadioButton button) {

                       bGroup = (String) button.getText();
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
                    findUsersNumber();
                    delay();
                }

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




    public void saveDonor(View view) {

        if (uDistrict.equals("Select") ){

            AlertDialog.Builder  builder = new AlertDialog.Builder(this);
            builder.setMessage("Select District!").setTitle("Warning..");

            AlertDialog dialog = builder.create();
            dialog.show();


        }
        else if (uTaluk.equals("Select")){

            AlertDialog.Builder  builder = new AlertDialog.Builder(this);
            builder.setMessage("Select Taluk!").setTitle("Warning..");

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else {
            uName = iName.getText().toString();
            uMobile = iMobile.getText().toString();
            uPlace = iPlace.getText().toString();

            dateAndTime();

            String serialNo = String.valueOf(usersNo);

            writeNewUser(serialNo,bGroup,uName,uMobile,uDistrict,uTaluk,uPlace,"20-05-14");

            Toast.makeText(this, "Donor Added Successfully", Toast.LENGTH_SHORT).show();

            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }



    }

    public void dateAndTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddhhmm");
        String format = simpleDateFormat.format(new Date());
        //Toast.makeText(this, format, Toast.LENGTH_SHORT).show();
        timeNow = format;
    }
    void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent = new Intent(AddUserActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}
