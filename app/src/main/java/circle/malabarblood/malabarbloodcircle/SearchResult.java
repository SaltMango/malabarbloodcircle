package circle.malabarblood.malabarbloodcircle;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import static circle.malabarblood.malabarbloodcircle.FirstPage.downloaadusers;

public class SearchResult extends AppCompatActivity {


    CustomAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);


      //  downloaadusers = (ArrayList<User>) getIntent().getSerializableExtra("downloadUsers");


      //  String no = String.valueOf(downloaadusers.size());
       // Toast.makeText(this,no, Toast.LENGTH_SHORT).show();


        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new CustomAdapter(downloaadusers);
        recyclerView.setAdapter(adapter);


        adapter.setOnRecyclerViewItemClickListener(new CustomAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClicked(int position, String value) {


                Intent intent = new Intent(SearchResult.this,call.class);

                String place = downloaadusers.get(position).place;
                String name = downloaadusers.get(position).name;
                String mobile = downloaadusers.get(position).mobileNo;
                String taluk = downloaadusers.get(position).taluk;
                String group = downloaadusers.get(position).bloodGroup;

                intent.putExtra("group",group);
                intent.putExtra("place",place);
                intent.putExtra("name",name);
                intent.putExtra("mobile",mobile);
                intent.putExtra("taluk",taluk);

                startActivity(intent);


                //adapter.notifyItemChanged(position-1);
            }

        });




    }
}
