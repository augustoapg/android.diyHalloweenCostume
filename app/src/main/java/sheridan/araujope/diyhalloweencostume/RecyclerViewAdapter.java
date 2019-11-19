/**
 * Project: DIY Halloween Costume
 * Author: Augusto A P Goncalez
 * Date: Nov. 18, 2019
 */

package sheridan.araujope.diyhalloweencostume;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import sheridan.araujope.diyhalloweencostume.beans.Costume;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Costume> mCostumes = new ArrayList<>();
    private Context mContext;
    private OnCostumeListener mOnCostumeListener;

    public RecyclerViewAdapter(Context mContext, ArrayList<Costume> mCostumes, OnCostumeListener onCostumeListener) {
        this.mCostumes = mCostumes;
        this.mContext = mContext;
        this.mOnCostumeListener = onCostumeListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item,
                parent, false);
        ViewHolder holder = new ViewHolder(view, mOnCostumeListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        Picasso.with(mContext).load(mCostumes.get(position).getImage()).fit().into(holder.mImage);
        holder.mName.setText(mCostumes.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mCostumes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mImage;
        private TextView mName;
        private LinearLayout mParentLayout;
        private OnCostumeListener mOnCostumeListener;

        public ViewHolder(@NonNull View itemView, OnCostumeListener onCostumeListener) {
            super(itemView);

            mImage = itemView.findViewById(R.id.imgList);
            mName = itemView.findViewById(R.id.txtListName);
            mParentLayout = itemView.findViewById(R.id.list_item_layout);
            mOnCostumeListener = onCostumeListener;
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mOnCostumeListener.onCostumeClick(position);
        }
    }

    public interface OnCostumeListener {
        void onCostumeClick(int position);
    }
}
