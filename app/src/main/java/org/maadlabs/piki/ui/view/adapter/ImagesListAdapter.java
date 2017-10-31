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

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by brainfreak on 10/14/17.
 */

public class ImagesListAdapter extends RecyclerView.Adapter<ImagesListAdapter.Holder> {

    List<ImageDataModel> mImageDataModelList;
    @Inject
    Context mContext;

    @Inject
    public ImagesListAdapter() {
        mImageDataModelList = Collections.emptyList();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.image_item_view, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        ImageDataModel dataModel = mImageDataModelList.get(position);
        RequestOptions options = new RequestOptions().fitCenter().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(mContext).setDefaultRequestOptions(options).load(dataModel.getUri()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mImageDataModelList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public Holder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
        }
    }

    public void setCollection(Collection<ImageDataModel> imagesList) {
        mImageDataModelList.clear();
        mImageDataModelList = (List<ImageDataModel>) imagesList;
        notifyDataSetChanged();
    }
}
