package teamh.boostcamp.myapplication.view.diarylist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import teamh.boostcamp.myapplication.R;
import teamh.boostcamp.myapplication.data.model.Diary;
import teamh.boostcamp.myapplication.databinding.ItemRecordDiaryBinding;
import teamh.boostcamp.myapplication.view.diarylist.listener.OnKakaoLinkClickListener;
import teamh.boostcamp.myapplication.view.diarylist.listener.OnRecordItemClickListener;


public class DiaryListAdapter extends RecyclerView.Adapter<DiaryListAdapter.DiaryHolder> {

    private static final int NOTHING_PLAYED = -1;

    private List<Diary> diaryList;
    private OnRecordItemClickListener onRecordItemClickListener;
    private OnKakaoLinkClickListener onKakaoLinkClickListener;
    private int lastPlayedIndex = NOTHING_PLAYED;

    DiaryListAdapter() {
        this.diaryList = new ArrayList<>();
    }

    void setOnRecordItemClickListener(@NonNull OnRecordItemClickListener onRecordItemClickListener) {
        this.onRecordItemClickListener = onRecordItemClickListener;
    }

    void setOnKakaoLinkClickListener(@NonNull OnKakaoLinkClickListener onKakaoLinkClickListener) {
        this.onKakaoLinkClickListener = onKakaoLinkClickListener;
    }

    @NonNull
    @Override
    public DiaryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final ItemRecordDiaryBinding itemRecordDiaryBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_record_diary,
                parent,
                false
        );

        return new DiaryHolder(itemRecordDiaryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryHolder holder, int position) {

        final Diary diary = diaryList.get(position);

        if (onRecordItemClickListener != null) {
            holder.itemRecordDiaryBinding.ivItemDiaryPlay.setOnClickListener(v ->
                    onRecordItemClickListener.onDiaryItemClicked(position));
        }

        if (lastPlayedIndex == position) {
            holder.itemRecordDiaryBinding.ivItemDiaryPlay.setImageDrawable(
                    ContextCompat.getDrawable(holder.itemRecordDiaryBinding.getRoot().getContext(), R.drawable.ic_pause_circle_filled_black_24dp));
        } else {
            holder.itemRecordDiaryBinding.ivItemDiaryPlay.setImageDrawable(
                    ContextCompat.getDrawable(holder.itemRecordDiaryBinding.getRoot().getContext(), R.drawable.ic_play_circle_filled_black_24dp));
        }

        holder.itemRecordDiaryBinding.tvItemDiaryEmotion.setText(diary.getSelectedEmotion().getEmoji());
        holder.itemRecordDiaryBinding.tvItemDiaryTags.setText(diary.toString());

        holder.itemRecordDiaryBinding.setDate(diary.getRecordDate());

        holder.itemRecordDiaryBinding.ivShareDiary.setOnClickListener(v -> onKakaoLinkClickListener.onShareButtonClicked(position));
    }

    @Override
    public int getItemCount() {
        return diaryList.size();
    }

    void addDiaryItems(@NonNull List<Diary> diaries, final boolean clear) {
        if(clear) {
            diaryList.clear();
            notifyDataSetChanged();
        }
        int from = diaryList.size();
        diaryList.addAll(diaries);
        notifyItemMoved(from, diaryList.size());
    }

    void changePlayItemIcon(final int lastPlayedIndex, final boolean isFinished) {
        if (isFinished) {
            this.lastPlayedIndex = NOTHING_PLAYED;
        } else {
            this.lastPlayedIndex = lastPlayedIndex;
        }
        notifyItemChanged(lastPlayedIndex);
    }

    void insertDiaryItem(@NonNull Diary diary) {
        diaryList.add(0, diary);
        notifyDataSetChanged();
    }

    void clear() {
        diaryList.clear();
        notifyDataSetChanged();
    }

    Diary getDiary(final int pos) {
        return diaryList.get(pos);
    }

    class DiaryHolder extends RecyclerView.ViewHolder {

        ItemRecordDiaryBinding itemRecordDiaryBinding;

        DiaryHolder(@NonNull ItemRecordDiaryBinding itemRecordDiaryBinding) {
            super(itemRecordDiaryBinding.getRoot());
            this.itemRecordDiaryBinding = itemRecordDiaryBinding;
        }
    }
}

