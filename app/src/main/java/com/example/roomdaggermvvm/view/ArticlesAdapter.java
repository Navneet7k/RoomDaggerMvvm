package com.example.roomdaggermvvm.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdaggermvvm.R;
import com.example.roomdaggermvvm.databinding.ArticleItemViewBinding;
import com.example.roomdaggermvvm.persistance.ArticleModel;

import java.util.ArrayList;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    private ArrayList<ArticleModel> articleModels;
    private Context context;

    public ArticlesAdapter(Context context, ArrayList<ArticleModel> articleModels) {
        this.articleModels=articleModels;
        this.context=context;
    }

    @NonNull
    @Override
    public ArticlesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item_view,parent,false);
        return new ArticlesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesAdapter.ViewHolder holder, int position) {
        final ArticleModel articleModel=articleModels.get(position);
        holder.bind(articleModel);
    }

    @Override
    public int getItemCount() {
        return articleModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ArticleItemViewBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);
        }

        public void bind(ArticleModel articleModel) {
            binding.setItemModel(articleModel);
        }
    }
}
