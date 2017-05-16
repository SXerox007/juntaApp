package com.skeleton.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skeleton.R;
import com.skeleton.constant.AppConstant;
import com.skeleton.fragment.ProfileCompletenessStep2Fragment;
import com.skeleton.model.Profile2.Categories;

import java.util.HashMap;
import java.util.List;

/**
 * Developer: Sumit Thakur
 * Dated: 16-05-2017.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static int count = 1;
    private Context context;
    private List<Categories> categories;
    private HashMap<String, String> hashMap = new HashMap<>();
    private ProfileCompletenessStep2Fragment profileCompletenessStep2Fragment;


    /**
     * @param context                          context
     * @param categories                       categories
     * @param profileCompletenessStep2Fragment profile completeness
     */
    public RecyclerViewAdapter(final Context context, final List<Categories> categories, final ProfileCompletenessStep2Fragment profileCompletenessStep2Fragment) {
        this.context = context;
        this.categories = categories;
        this.profileCompletenessStep2Fragment = profileCompletenessStep2Fragment;
    }


    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        context = parent.getContext();
        View convertView;
        convertView = LayoutInflater.from(context).inflate(R.layout.second_profile_rowlayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, final int position) {
        Categories obj = categories.get(position);
        holder.mText.setText(obj.getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    /**
     * view holder
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, AppConstant {
        private TextView mText;
        private ImageView ivImage, ivcheck, ivBlur;


        /**
         * @param itemView item view
         */
        public ViewHolder(final View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.tv);
            ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
            ivcheck = (ImageView) itemView.findViewById(R.id.iv_check_mark);
            ivBlur = (ImageView) itemView.findViewById(R.id.iv_image_blur);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(final View v) {
            int pos = getAdapterPosition();
            if (count < VALUE_IMAGE_COUNTER) {
                if (ivcheck.getDrawable() == null) {
                    ivcheck.setImageResource(R.drawable.check_mark);
                    ivBlur.setImageResource(R.color.translucent);
                    count++;
                } else {
                    ivcheck.setImageDrawable(null);
                    ivBlur.setImageDrawable(null);
                    count--;
                }
            } else {
                if (ivcheck.getDrawable() != null) {
                    ivcheck.setImageDrawable(null);
                    ivBlur.setImageDrawable(null);
                    count--;
                }
            }
        }

        /**
         * bar fill
         */
        private void barFill() {
        }
    }
}
