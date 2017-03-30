package circle.malabarblood.malabarbloodcircle;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static android.R.attr.name;

public class call extends AppCompatActivity {

    String place,taluk,mobile,uname,group;

    TextView tname,tplace,tmobile,ttaluk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);


        Intent intent = getIntent();

       place= intent.getStringExtra("place");
        group= intent.getStringExtra("group");

        uname = intent.getStringExtra("name");
       mobile= intent.getStringExtra("mobile");
       taluk= intent.getStringExtra("taluk");


        tplace = (TextView)findViewById(R.id.callplace);
        tname = (TextView)findViewById(R.id.callname);
        tmobile = (TextView)findViewById(R.id.callno);
        ttaluk = (TextView)findViewById(R.id.calltaluk);

        tplace.setText(place);
        tname.setText(uname);
        tmobile.setText(mobile);
        ttaluk.setText(taluk);
    }

    public void call(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+mobile));
        startActivity(intent);

    }

    public void share(View view) {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,uname +"\n" +mobile+"\n"+"Group-"+group+"\n"+place+"\n"+"---Malabar Blood Circle");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
