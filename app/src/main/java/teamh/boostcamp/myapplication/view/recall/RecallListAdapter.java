package teamh.boostcamp.myapplication.view.recall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import teamh.boostcamp.myapplication.R;
import teamh.boostcamp.myapplication.data.model.Recall;
import teamh.boostcamp.myapplication.databinding.ItemRecallListBinding;

public class RecallListAdapter extends RecyclerView.Adapter<RecallListAdapter.ViewHolder> {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM.dd", Locale.KOREA);
    private Context context;
    private List<Recall> itemList;
    private ButtonClickListener buttonClickListener;

    RecallListAdapter(Context context) {
        this.context = context;
        this.itemList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecallListBinding binding = ItemRecallListBinding.inflate(LayoutInflater.from(context),
                parent,
                false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvSubTitleDate.setText(generateRecallTitleDate(itemList.get(position)));
        holder.binding.tvSubTitle.setText(emotionToString(itemList.get(position).getEmotion().getEmotion()));
        holder.binding.rvDiary.setHasFixedSize(true);
        holder.binding.rvDiary.setVerticalScrollbarPosition(0);
        holder.binding.rvDiary.setLayoutManager(new LinearLayoutManager(context));

        DiaryTitleListAdapter adapter = new DiaryTitleListAdapter(context);
        adapter.addItems(itemList.get(position).getDiaryList());
        holder.binding.rvDiary.setAdapter(adapter);

        holder.binding.ivDelete.setOnClickListener(v -> {
            buttonClickListener.onDeleteButtonClicked(position, itemList.get(position).getIndex());
        });
        holder.binding.ivPlay.setOnClickListener(v -> {
            buttonClickListener.onPlayButtonClicked(itemList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    void setButtonClickListener(ButtonClickListener buttonClickListener) {
        this.buttonClickListener = buttonClickListener;
    }

    void updateItems(List<Recall> items) {
        this.itemList.clear();
        this.itemList.addAll(items);
        notifyDataSetChanged();
    }

    void addItem(Recall recall) {
        this.itemList.add(0, recall);
        notifyDataSetChanged();
    }

    void deleteItem(int position) {
        this.itemList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemRecallListBinding binding;

        ViewHolder(@NonNull ItemRecallListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    private String generateRecallTitleDate(Recall recall) {
        String startDateString = DateToSimpleFormat(recall.getStartDate());
        String endDateString = DateToSimpleFormat(recall.getEndDate());
        return String.format("%s ~ %s", startDateString, endDateString);
    }

    @NonNull
    private String DateToSimpleFormat(@NonNull Date date) {
        return simpleDateFormat.format(date);
    }

    @NonNull
    private String emotionToString(int emotion) {
        switch (emotion) {
            case 0:
                return context.getString(R.string.emotion_0_to_title);
            case 1:
                return context.getString(R.string.emotion_1_to_title);
            case 2:
                return context.getString(R.string.emotion_2_to_title);
            case 3:
                return context.getString(R.string.emotion_3_to_title);
            case 4:
            default:
                return context.getString(R.string.emotion_4_to_title);
        }
    }

    public interface ButtonClickListener {
        void onPlayButtonClicked(Recall recall);

        void onDeleteButtonClicked(int position, int id);
    }

}
