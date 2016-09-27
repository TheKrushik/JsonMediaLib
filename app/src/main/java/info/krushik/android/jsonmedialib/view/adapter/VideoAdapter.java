package info.krushik.android.jsonmedialib.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import info.krushik.android.jsonmedialib.R;
import info.krushik.android.jsonmedialib.model.Video;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<Video> videoList;

    public VideoAdapter(List<Video> videoListItems) {
        this.videoList = videoListItems;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_video, parent, false);
        return new VideoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        Video video = videoList.get(position);
        holder.tvTitleVideo.setText(video.getTitle());
        holder.tvPictureVideo.setText(video.getPicture());
        holder.tvDesc.setText(video.getDesc());
        holder.tvLength.setText(video.getLength());
        holder.tvDt.setText(video.getDt());
        holder.tvVideoHttp.setText(video.getVideo());
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitleVideo;
        TextView tvPictureVideo;
        TextView tvDesc;
        TextView tvLength;
        TextView tvDt;
        TextView tvVideoHttp;

        VideoViewHolder(View view) {
            super(view);
            tvTitleVideo = (TextView) view.findViewById(R.id.tvTitleVideo);
            tvPictureVideo = (TextView) view.findViewById(R.id.tvPictureVideo);
            tvDesc = (TextView) view.findViewById(R.id.tvDesc);
            tvLength = (TextView) view.findViewById(R.id.tvLength);
            tvDt = (TextView) view.findViewById(R.id.tvDt);
            tvVideoHttp = (TextView) view.findViewById(R.id.tvVideoHttp);
        }
    }
}