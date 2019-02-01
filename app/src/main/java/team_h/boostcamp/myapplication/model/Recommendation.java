package team_h.boostcamp.myapplication.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

/*
@ForeignKey(entity = Memory.class,
        parentColumns = "id",
        childColumns = "memoryId", onDelete = CASCADE),
*/

@Entity(tableName = "recommended", foreignKeys = {
        @ForeignKey(entity = Diary.class,
        parentColumns = "id",
        childColumns = "id", onDelete = CASCADE)
}) // Search 를 빠르게 하기위해 Index 걸어두기
public class Recommendation {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    private int id;

    // 이화중님 수정
    @ColumnInfo(name = "memoryId")
    @NonNull
    private int memoryId;

    @ColumnInfo(name = "diaryId")
    @NonNull
    private int diaryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(int memoryId) {
        this.memoryId = memoryId;
    }

    public int getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(int diaryId) {
        this.diaryId = diaryId;
    }
}