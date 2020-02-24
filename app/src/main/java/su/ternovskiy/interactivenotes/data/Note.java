package su.ternovskiy.interactivenotes.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "notes", foreignKeys =
@ForeignKey(entity = Category.class, parentColumns = "id",
        childColumns = "category_id", onDelete = CASCADE))
public class Note {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id")
    private long mId;

    @ColumnInfo (name = "title")
    private String mTitle;

    @ColumnInfo (name = "text")
    private String mText;

    @ColumnInfo (name = "category_id", index = true)
    private long mCategoryId;

    @ColumnInfo(name = "date", index = true)
    private Date mDate;



    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public long getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(long categoryId) {
        mCategoryId = categoryId;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
