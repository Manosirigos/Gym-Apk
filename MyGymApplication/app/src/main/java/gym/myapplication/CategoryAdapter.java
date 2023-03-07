package gym.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

//import androidx.annotation.NonNull;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {


    private Context mContext;
    private List<ExercisesCategory> mData;

    // constructor
    public CategoryAdapter(Context mContext, List<ExercisesCategory> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.category_item, parent, false);
        return new CategoryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.categoryTitle.setText(mData.get(position).getCategoryTitle());
        holder.categoryImageThumbnail.setImageResource(mData.get(position).getImageResource());
        holder.categoryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, ExercisesListActivity.class);

                // passing data to list activity
                intent.putExtra("selected_category_position", position);

                mContext.startActivity(intent);

                //Toast.makeText(view.getContext(),"You select category at position: "+position, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTitle;
        ImageView categoryImageThumbnail;
        CardView categoryCard;

        public MyViewHolder(View itemView) {
            super(itemView);

            categoryTitle = (TextView) itemView.findViewById(R.id.category_title);
            categoryImageThumbnail = (ImageView) itemView.findViewById(R.id.category_image);
            categoryCard = (CardView) itemView.findViewById(R.id.category_item_card_view);
        }
    }
}
