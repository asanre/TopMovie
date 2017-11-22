package com.example.asanre.topmovies.ui.movieList.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asanre.topmovies.R;
import com.example.asanre.topmovies.domain.model.IMovie;
import com.example.asanre.topmovies.ui.utils.GlideHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private final Context context;
    private final AdapterOnItemClickListener listener;
    private List<IMovie> movies;

    public MovieListAdapter(Context context, AdapterOnItemClickListener listener) {

        this.listener = listener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_list_item, parent, false);
        view.setFocusable(true);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final IMovie currentMovie = movies.get(position);

        bindViews(holder, currentMovie);

        setItemClickedListener(holder.container, currentMovie);
    }

    @Override
    public int getItemCount() {

        return movies == null ? 0 : movies.size();
    }

    public void setMovies(@NonNull final List<IMovie> movies) {

        this.movies = movies;
        notifyDataSetChanged();
    }

    private void bindViews(ViewHolder holder, IMovie currentMovie) {

        setImage(holder.image, context, GlideHelper.getListImageUrl(currentMovie.getImageUrl()));

        holder.tvTitle.setText(currentMovie.getTitle());
        String voteAvg = String.valueOf(currentMovie.getRating());
        holder.tvRating.setText(voteAvg);
    }

    private void setImage(ImageView imageView, Context context, String url) {

        GlideHelper.getImageForList(imageView, context, url);
    }

    private void setItemClickedListener(View view, final IMovie movie) {

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                listener.onItemClick(movie);
            }
        });
    }

    /**
     * The interface that receives onItemClick messages.
     */
    public interface AdapterOnItemClickListener {

        void onItemClick(IMovie movie);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_container)
        LinearLayout container;
        @BindView(R.id.iv_image)
        ImageView image;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_rating)
        TextView tvRating;

        public ViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}