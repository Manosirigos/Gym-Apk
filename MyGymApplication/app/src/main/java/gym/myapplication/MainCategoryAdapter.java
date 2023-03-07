package gym.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.core.content.ContextCompat.startActivity;

//import androidx.annotation.NonNull;

public class MainCategoryAdapter  extends RecyclerView.Adapter<MainCategoryAdapter.MyViewHolder> {


        private Context mContext;
        private List<MainCategory> mData;

        // constructor
        public MainCategoryAdapter(Context mContext, List<MainCategory> mData) {
            this.mContext = mContext;
            this.mData = mData;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view;
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            view = mInflater.inflate(R.layout.main_category_item, parent, false);
            return new MainCategoryAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

            holder.categoryTitle.setText(mData.get(position).getCategoryTitle());
            holder.categoryImageThumbnail.setImageResource(mData.get(position).getImageResource());
            holder.categoryCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (position == 0) {

                        Intent intent = new Intent(mContext, ExercisesCategoriesActivity.class);
                        mContext.startActivity(intent);
                    } else if (position == 1) {

                        Intent intent = new Intent(mContext, SelectTargetActivity.class);
                        mContext.startActivity(intent);
                    } else if (position == 2) {

                        Intent intent = new Intent(mContext, CalenderActivity.class);
                        mContext.startActivity(intent);
                    }


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

                categoryTitle = (TextView) itemView.findViewById(R.id.main_category_title);
                categoryImageThumbnail = (ImageView) itemView.findViewById(R.id.main_category_image);
                categoryCard = (CardView) itemView.findViewById(R.id.main_category_item_card_view);
            }
        }

}
