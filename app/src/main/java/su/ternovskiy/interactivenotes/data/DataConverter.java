package su.ternovskiy.interactivenotes.data;

import androidx.room.TypeConverter;

import java.util.Date;

public class DataConverter {

    @TypeConverter
    public static long fromDate(Date date) {
        return date.getTime();
    }

    @TypeConverter
    public static Date toDate(long timestamp) {
        return new Date(timestamp);
    }
}
