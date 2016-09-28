package info.krushik.android.jsonmedialib.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import info.krushik.android.jsonmedialib.R;
import info.krushik.android.jsonmedialib.model.Book;


public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> bookList;
    private List<Book.BookFiles> bookFilesList;

    public BookAdapter(List<Book> bookListItems) {
        this.bookList = bookListItems;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_book, parent, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Book book = bookList.get(position);

        holder.tvTitleBook.setText(book.getTitle());
        holder.tvAuthor.setText(book.getAuthor());
        holder.tvAnons.setText(book.getAnons());
        holder.tvPictureBook.setText(book.getPicture());
        holder.tvUrlPdf.setText(book.getFileByType("pdf"));
        holder.tvUrlEpub.setText(book.getFileByType("epub"));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitleBook;
        TextView tvAuthor;
        TextView tvAnons;
        TextView tvPictureBook;
        TextView tvUrlPdf;
        TextView tvUrlEpub;

        BookViewHolder(View view) {
            super(view);
            tvTitleBook = (TextView) view.findViewById(R.id.tvTitleBook);
            tvAuthor = (TextView) view.findViewById(R.id.tvAuthor);
            tvAnons = (TextView) view.findViewById(R.id.tvAnons);
            tvPictureBook = (TextView) view.findViewById(R.id.tvPictureBook);
            tvUrlPdf = (TextView) view.findViewById(R.id.tvUrlPdf);
            tvUrlEpub = (TextView) view.findViewById(R.id.tvUrlEpub);
        }
    }
}