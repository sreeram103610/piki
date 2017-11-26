package org.maadlabs.piki.ui.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import org.maadlabs.piki.R;
import org.maadlabs.piki.ui.model.ImageDataModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by brainfreak on 10/14/17.
 */

public class ImagesListAdapter extends RecyclerView.Adapter<ImagesListAdapter.Holder> {

    List<ImageDataModel> mModelList;
    @Inject
    Context mContext;
    private ItemInfoListener mItemInfoListener;
    int mMaxItemPosition;
    int mItemLimit;

    private int mNewItemsLimit;

    public interface ItemInfoListener {
        void onItemClick(int position, ImageDataModel model);
        void onMaxItemsLimitReached(int position);
    }

    @Inject
    public ImagesListAdapter() {
        mModelList = new ArrayList<>();
    }

    public void setItemInfoListener(ItemInfoListener listener) {
        mItemInfoListener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.image_item_view, parent, false);
        return new Holder(view);
    }

    public void setNewItemsLimit(int limit) {
        mNewItemsLimit = limit;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        final ImageDataModel dataModel = mModelList.get(position);
        final int itemPosition = position;
        mMaxItemPosition = Math.max(mMaxItemPosition, position);

        if (mMaxItemPosition + 1 >= mItemLimit) {
            mItemLimit = mItemLimit + mNewItemsLimit;
            if (mMaxItemPosition >= (mNewItemsLimit - 1)) // Initially mNewwItemsLimit items will be loaded. This condition
                                                            // will prevent to load images a second time.
                mItemInfoListener.onMaxItemsLimitReached(mMaxItemPosition);
        }

        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemInfoListener != null) {
                    mItemInfoListener.onItemClick(itemPosition, dataModel);
                }
            }
        });

        RequestOptions options = new RequestOptions().fitCenter().diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).setDefaultRequestOptions(options).load(dataModel.getUri()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mModelList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        private final View view;
        ImageView imageView;

        public Holder(View itemView) {
            super(itemView);
            view = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.image_view);

        }


        public View getView() {
            return view;
        }
    }

    public void setCollection(Collection<ImageDataModel> imagesList, boolean invalidate) {
        if (invalidate)
            mModelList.clear();
        mModelList.addAll(imagesList);
        notifyDataSetChanged();
    }

}
