package circle.malabarblood.malabarbloodcircle;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static circle.malabarblood.malabarbloodcircle.FirstPage.downloaadusers;


/**
 * Created by Mango on 29-03-2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {




    ArrayList<User> userSet = new ArrayList<>();



    public CustomAdapter(ArrayList<User> data) {
        this.userSet = data;
    }

    // Define listener member variable
    private static OnRecyclerViewItemClickListener mListener;

    // Define the listener interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClicked(int position,String value);
    }

    // Define the method that allows the parent activity or fragment to define the listener.
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        holder.heading.setText(downloaadusers.get(listPosition).name);
        holder.description.setText(downloaadusers.get(listPosition).mobileNo);
        holder.countNumber.setText(downloaadusers.get(listPosition).bloodGroup);
        holder.time.setText(downloaadusers.get(listPosition).taluk);
        holder.place.setText(downloaadusers.get(listPosition).place);


    }

    @Override
    public int getItemCount() {
        return downloaadusers.size();

    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView heading;
        TextView description;
        TextView countNumber;
        TextView time;
        TextView place;

        public MyViewHolder(final View itemView) {
            super(itemView);

            this.countNumber = (TextView) itemView.findViewById(R.id.countId);
            this.heading = (TextView) itemView.findViewById(R.id.textViewName);
            this.description = (TextView) itemView.findViewById(R.id.textViewVersion);
            this.place = (TextView) itemView.findViewById(R.id.placeId);
            this.time = (TextView) itemView.findViewById(R.id.timeId);
            this.countNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // send the text to the listener, i.e Activity.
                    mListener.onItemClicked(getAdapterPosition(), (String) ((TextView)v).getText());
                }
            });
        }
    }
}
