package info.krushik.android.jsonmedialib;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

import info.krushik.android.jsonmedialib.models.VideoModel;

public class VideoAdapter extends ArrayAdapter {

    private List<VideoModel> videoModelList;
    private int resource;
    private LayoutInflater inflater;

    public VideoAdapter(Context context, int resource, List<VideoModel> objects) {
        super(context, resource, objects);
        videoModelList = objects;
        this.resource = resource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        ViewHolder holder = null;
//
        if (convertView == null) {
//            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.row_video, null);
//            holder.ivMovieIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
//            holder.tvMovie = (TextView) convertView.findViewById(R.id.tvMovie);
//            holder.tvTagline = (TextView) convertView.findViewById(R.id.tvTagline);
//            holder.tvYear = (TextView) convertView.findViewById(R.id.tvYear);
//            holder.tvDuration = (TextView) convertView.findViewById(R.id.tvDuration);
//            holder.tvDirector = (TextView) convertView.findViewById(R.id.tvDirector);
//            holder.rbMovieRating = (RatingBar) convertView.findViewById(R.id.rbMovie);
//            holder.tvCast = (TextView) convertView.findViewById(R.id.tvCast);
//            holder.tvStory = (TextView) convertView.findViewById(R.id.tvStory);
//            convertView.setTag(holder);
//        }else {
//            holder = (ViewHolder) convertView.getTag();
        }

        ImageView ivPictureVideo;
        TextView tvTitleVideo;
        TextView tvDesc;
        TextView tvLength;
        TextView tvDt;
        TextView tvVideoHttp;

        ivPictureVideo = (ImageView) convertView.findViewById(R.id.ivPictureVideo);
        tvTitleVideo = (TextView) convertView.findViewById(R.id.tvTitleVideo);
        tvDesc = (TextView) convertView.findViewById(R.id.tvDesc);
        tvLength = (TextView) convertView.findViewById(R.id.tvLength);
        tvDt = (TextView) convertView.findViewById(R.id.tvDt);
        tvVideoHttp = (TextView) convertView.findViewById(R.id.tvVideoHttp);

        tvTitleVideo.setText(videoModelList.get(position).getTitle());
        tvDesc.setText(videoModelList.get(position).getDesc());
        tvLength.setText("Длинна видео: " + videoModelList.get(position).getLength());
        tvDt.setText("Дата: " + videoModelList.get(position).getDt());
        tvVideoHttp.setText(videoModelList.get(position).getVideo());

//
//        final ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
//
//        // Then later, when you want to display image
//        ImageLoader.getInstance().displayImage(movieModelList.get(position).getImage(), holder.ivMovieIcon, new ImageLoadingListener() {
//            @Override
//            public void onLoadingStarted(String imageUri, View view) {
//                progressBar.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//                progressBar.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                progressBar.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onLoadingCancelled(String imageUri, View view) {
//                progressBar.setVisibility(View.GONE);
//            }
//        });
//
//        holder.tvMovie.setText(movieModelList.get(position).getMovie());
//        holder.tvTagline.setText(movieModelList.get(position).getTagline());
//        holder.tvYear.setText("Year: " + movieModelList.get(position).getYear());
//        holder.tvDuration.setText("Duration: " + movieModelList.get(position).getDuration());
//        holder.tvDirector.setText("Director: " + movieModelList.get(position).getDirector());
//
//        // rating bar
//        holder.rbMovieRating.setRating(movieModelList.get(position).getRaiting() / 2);
//
//        StringBuffer stringBuffer = new StringBuffer();
//        for (MovieModel.Cast cast : movieModelList.get(position).getCastList()) {
//            stringBuffer.append(cast.getName() + ", ");
//        }
//
//        holder.tvCast.setText("Cast: " + stringBuffer);
//        holder.tvStory.setText(movieModelList.get(position).getStory());

        return convertView;
    }

//    class ViewHolder {
//        private ImageView ivMovieIcon;
//        private TextView tvMovie;
//        private TextView tvTagline;
//        private TextView tvYear;
//        private TextView tvDuration;
//        private TextView tvDirector;
//        private RatingBar rbMovieRating;
//        private TextView tvCast;
//        private TextView tvStory;
//    }
}
