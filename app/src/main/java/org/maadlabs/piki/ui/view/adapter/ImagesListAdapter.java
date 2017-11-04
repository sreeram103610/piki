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

    List<ImageDataModel> mModelList;
    @Inject
    Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position, ImageDataModel model);
    }

    @Inject
    public ImagesListAdapter() {
        mModelList = Collections.emptyList();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.image_item_view, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        final ImageDataModel dataModel = mModelList.get(position);
        final int itemPosition = position;

        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(itemPosition, dataModel);
                }
            }
        });

        RequestOptions options = new RequestOptions().fitCenter().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
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

    public void setCollection(Collection<ImageDataModel> imagesList) {
        mModelList.clear();
        mModelList = (List<ImageDataModel>) imagesList;
        notifyDataSetChanged();
    }

}
