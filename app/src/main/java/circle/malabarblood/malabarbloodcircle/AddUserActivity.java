package circle.malabarblood.malabarbloodcircle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class AddUserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public Spinner spinnerDist, spinnerTaluk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);





        spinnerDist = (Spinner) findViewById(R.id.spinnerDistricId);
        spinnerDist.setOnItemSelectedListener(this);

        spinnerTaluk = (Spinner) findViewById(R.id.spinnerTalukId);
        spinnerTaluk.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId())
        {
            case R.id.spinnerDistricId:

                String test = (String) parent.getItemAtPosition(position);

                if (test.equals("Select")){

                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.spinnerSelect, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerTaluk.setAdapter(adapter);
                }

                else if (test.equals("Kannur")){

                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.spinnerKannur, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerTaluk.setAdapter(adapter);
                }

                else if (test.equals("Kasargod")){

                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.spinnerKasargod, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerTaluk.setAdapter(adapter);
                }

                else if (test.equals("Wayanad")){

                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.spinnerWayanad, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerTaluk.setAdapter(adapter);
                }

                else if (test.equals("kozhikode")){

                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.spinnerKozhikode, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerTaluk.setAdapter(adapter);
                }

                else if (test.equals("Malappuram")){

                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.spinnerMalappuram, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerTaluk.setAdapter(adapter);
                }


                break;

            case R.id.spinnerTalukId:
                String taluk = (String) parent.getItemAtPosition(position);


                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
