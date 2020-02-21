package su.ternovskiy.interactivenotes.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "categories")
public class Category {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "id")
    private long mId;

    @ColumnInfo (name = "name")
    private String mCategoryName;

    @ColumnInfo(name = "position")
    private long mCategoryPosition;

    public Category(@NonNull String categoryName, long categoryPosition) {
        mCategoryName = categoryName;
        mCategoryPosition= categoryPosition;
    }


    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getCategoryName() {
        return mCategoryName;
    }

    public void setCategoryName(String categoryName) {
        mCategoryName = categoryName;
    }

    public long getCategoryPosition() {
        return mCategoryPosition;
    }

    public void setCategoryPosition(long categoryPosition) {
        mCategoryPosition = categoryPosition;
    }
}
