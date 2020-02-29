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

    public Category(@NonNull String categoryName) {
        mCategoryName = categoryName;
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

}
