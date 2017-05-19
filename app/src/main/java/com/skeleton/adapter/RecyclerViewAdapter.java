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
import com.skeleton.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Developer: Sumit Thakur
 * Dated: 16-05-2017.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements AppConstant {
    private static int count = 0;
    private Context context;
    private List<Categories> categories;
    private ArrayList<String> stringArrayList = new ArrayList<>();
    private ProfileCompletenessStep2Fragment profileCompletenessStep2Fragment;


    /**
     * @param context                          context
     * @param categories                       categories
     * @param profileCompletenessStep2Fragment profile context
     */
    public RecyclerViewAdapter(final Context context,
                               final List<Categories> categories,
                               final ProfileCompletenessStep2Fragment profileCompletenessStep2Fragment) {
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
        final Categories obj = categories.get(position);
        holder.mText.setText(obj.getName());
        if (obj.getIscheck()) {
            holder.ivcheck.setImageResource(R.drawable.check_mark);
            holder.ivBlur.setImageResource(R.color.translucent);
        } else {
            holder.ivcheck.setImageDrawable(null);
            holder.ivBlur.setImageDrawable(null);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (obj.getIscheck()) {
                    obj.setIscheck(false);
                    count--;
                    profileCompletenessStep2Fragment.barUnfill();
                    stringArrayList.remove(obj.get_id());
                } else {
                    if (count == 5) {
                        Log.e("debug", "Sucess100");
                    } else {
                        obj.setIscheck(true);
                        count++;
                        profileCompletenessStep2Fragment.barFill();
                        stringArrayList.add(obj.get_id());
                    }
                }
                notifyDataSetChanged();
            }
        });


    }

    /**
     * @return return arraylist create in adapter
     */
    public ArrayList<String> parseArrayList() {
        return stringArrayList;
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    /**
     * view holder
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
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
        }


    }
}
