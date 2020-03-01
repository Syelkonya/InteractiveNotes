package su.ternovskiy.interactivenotes.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "notes", foreignKeys =
@ForeignKey(entity = Category.class, parentColumns = "id",
        childColumns = "category_id", onDelete = CASCADE))
public class Note implements Parcelable {

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


    @ColumnInfo(name = "position")
    private long mPosition;


    protected Note(Parcel in) {
        mId = in.readLong();
        mTitle = in.readString();
        mText = in.readString();
        mCategoryId = in.readLong();
        mDate = new Date(in.readLong());
        mPosition = in.readLong();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public Note(String title, String text, long categoryId, Date date){
        mTitle = title;
        mText = text;
        mCategoryId = categoryId;
        mDate = date;
    }

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

    public long getPosition() {
        return mPosition;
    }

    public void setPosition(long position) {
        mPosition = position;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mTitle);
        dest.writeString(mText);
        dest.writeLong(mCategoryId);
        dest.writeLong(mDate.getTime());
        dest.writeLong(mPosition);
    }
}
