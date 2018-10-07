package keehansullivan.berkeley.edu.whosreppn;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<CongressMemberInfo> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private int color;
    private String Party;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<CongressMemberInfo> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
//        if (data.size() > 0 && data.get(0).party.equals("Democrat")){
//            color = Color.parseColor("#0000ff");
//        } else {
//            color = Color.parseColor("#ff3300");
//        }
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycleview_row, parent, false);


        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CongressMemberInfo names = mData.get(position);
        holder.oneTextView.setText(names.firstName + " " + names.lastName);
        holder.twoTextView.setText(names.party+ "\n"+ "tel: "+names.phoneNumber + "\n" + "web: " + names.url + "\n" + names.representativeType);
        Party = names.party;
        if (names.party.equals("Democrat")) {
            //color = Color.parseColor("#0000ff");
            holder.row_linearlayout.setBackgroundColor(Color.parseColor("#33adff"));


        } else {
            //color = Color.parseColor("#ff3300");
            holder.row_linearlayout.setBackgroundColor(Color.parseColor("#ff5050"));
        }



    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView oneTextView;
        TextView twoTextView;
        ConstraintLayout row_linearlayout;







        ViewHolder(View itemView) {
            super(itemView);
            oneTextView = itemView.findViewById(R.id.supporting_text);
            twoTextView = itemView.findViewById(R.id.textView4);
            row_linearlayout= (ConstraintLayout) itemView.findViewById(R.id.row_linrLayout);

            itemView.setBackgroundColor(color);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    CongressMemberInfo getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

