package teamh.boostcamp.myapplication.view.diarylist;

import java.util.List;

import androidx.annotation.NonNull;
import teamh.boostcamp.myapplication.data.model.Diary;
import teamh.boostcamp.myapplication.data.model.Emotion;

public interface DiaryListView {

    void addDiaryList(@NonNull List<Diary> diaryList, final boolean clear);

    void insertDiaryList(@NonNull Diary diary);

    void showLoadDiaryListFailMsg();

    void showRecordNotFinished();

    void showEmotionNotSelected();

    void setIsBackup(boolean isBackup);

    void setIsSaving(boolean isSaving);

    void setRecordCardVisibilityGone();

    void showAnalyzeIgnore();

    void showAnalyzedEmotion(Emotion emotion);

    void onPlayFileChanged(final int lastPlayedIndex, final boolean isFinished);
}
