package su.ternovskiy.interactivenotes.view.note;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.text.SimpleDateFormat;
import java.util.List;

import su.ternovskiy.interactivenotes.R;
import su.ternovskiy.interactivenotes.data.Note;

public class NoteRecyclerAdapter extends Adapter<NoteRecyclerAdapter.BaseViewHolder> {

    private List<Note> mNoteList;
    private final LayoutInflater mLayoutInflater;
    private OnNoteClickListener mNoteClickListener;


    NoteRecyclerAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    void setClickListener(@Nullable OnNoteClickListener onNoteClickListener) {
        mNoteClickListener = onNoteClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.note_view_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteRecyclerAdapter.BaseViewHolder holder, int position) {
        Note note = mNoteList.get(position);
        ((NoteViewHolder) holder).bindView(note);
    }


    @Override
    public int getItemCount() {
        if (mNoteList != null)
            return mNoteList.size();
        else return 0;
    }

    void setNoteList(List<Note> notes) {
        mNoteList = notes;
        notifyDataSetChanged();
    }

    static abstract class BaseViewHolder extends RecyclerView.ViewHolder {
        BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class NoteViewHolder extends NoteRecyclerAdapter.BaseViewHolder {

        private TextView mNoteTitleNameTextView;
        private TextView mNoteFirstLineTextView;
        private TextView mNoteDateTextView;
        private SimpleDateFormat mSimpleDateFormat;

        private NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            mNoteTitleNameTextView = itemView.findViewById(R.id.note_title_text_view);
            mNoteFirstLineTextView = itemView.findViewById(R.id.note_first_line_text_view);
            mNoteDateTextView = itemView.findViewById(R.id.note_date_text_view);
        }


        @SuppressLint("SimpleDateFormat")
        void bindView(@NonNull Note note) {
            mNoteTitleNameTextView.setText(note.getTitle());
            mNoteFirstLineTextView.setText(note.getText());
            mSimpleDateFormat = new SimpleDateFormat("dd-MM-yy HH:mm");
            mNoteDateTextView.setText(mSimpleDateFormat.format(note.getDate()));
            itemView.setOnClickListener(v -> {
                if (mNoteClickListener != null) {
                    mNoteClickListener.onItemClick(note);
                }
            });

        }

    }
}


