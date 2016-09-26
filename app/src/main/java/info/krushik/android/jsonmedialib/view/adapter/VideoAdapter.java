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

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout_video, parent, false);
        return new VideoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        Video rate = videoList.get(position);
        holder.registrationBonus.setText(rate.getTitle());
        holder.tvPictureVideo.setText(rate.getPicture());
        holder.tvDesc.setText(rate.getDesc());
        holder.tvLength.setText(rate.getLength());
        holder.tvDt.setText(rate.getDt());
        holder.tvVideoHttp.setText(rate.getVideo());
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        public TextView registrationBonus;
        public TextView tvPictureVideo;
        public TextView tvDesc;
        public TextView tvLength;
        public TextView tvDt;
        public TextView tvVideoHttp;

        public VideoViewHolder(View view) {
            super(view);
            registrationBonus = (TextView) view.findViewById(R.id.tvTitleVideo);
            tvPictureVideo = (TextView) view.findViewById(R.id.tvPictureVideo);
            tvDesc = (TextView) view.findViewById(R.id.tvDesc);
            tvLength = (TextView) view.findViewById(R.id.tvLength);
            tvDt = (TextView) view.findViewById(R.id.tvDt);
            tvVideoHttp = (TextView) view.findViewById(R.id.tvVideoHttp);
        }
    }
}