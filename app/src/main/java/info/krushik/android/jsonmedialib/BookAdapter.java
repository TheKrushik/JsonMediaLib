package info.krushik.android.jsonmedialib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import info.krushik.android.jsonmedialib.models.BookModel;
import info.krushik.android.jsonmedialib.models.VideoModel;

public class BookAdapter extends ArrayAdapter {

    private List<BookModel> bookModelList;
    private int resource;
    private LayoutInflater inflater;

    public BookAdapter(Context context, int resource, List<BookModel> objects) {
        super(context, resource, objects);
        bookModelList = objects;
        this.resource = resource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        ViewHolder holder = null;
//
        if (convertView == null) {
//            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.row_book, null);
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

        ImageView ivPictureBook;
        TextView tvTitleBook;
        TextView tvAuthor;
        TextView tvAnons;
        TextView tvTypePdf;
        TextView tvUrlPdf;
        TextView tvTypeEpub;
        TextView tvUrlEpub;

        ivPictureBook = (ImageView) convertView.findViewById(R.id.ivPictureBook);
        tvTitleBook = (TextView) convertView.findViewById(R.id.tvTitleBook);
        tvAuthor = (TextView) convertView.findViewById(R.id.tvAuthor);
        tvAnons = (TextView) convertView.findViewById(R.id.tvAnons);
        tvTypePdf = (TextView) convertView.findViewById(R.id.tvTypePdf);
        tvUrlPdf = (TextView) convertView.findViewById(R.id.tvUrlPdf);
        tvTypeEpub = (TextView) convertView.findViewById(R.id.tvTypeEpub);
        tvUrlEpub = (TextView) convertView.findViewById(R.id.tvUrlEpub);

        tvTitleBook.setText(bookModelList.get(position).getTitle());
        tvAuthor.setText(bookModelList.get(position).getАuthor());
        tvAnons.setText("Длинна видео: " + bookModelList.get(position).getAnons());

        StringBuffer stringBuffer = new StringBuffer();
        for(BookModel.Files files : bookModelList.get(position).getFilesList()){
            stringBuffer.insert(0, files.getType());
        }
        tvTypePdf.setText(stringBuffer);
        tvUrlPdf.setText(bookModelList.get(position).getVideo());



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
