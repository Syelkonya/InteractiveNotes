package su.ternovskiy.interactivenotes.view.category;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

import su.ternovskiy.interactivenotes.R;

public class CategoryDialogUpdateOrDelete extends DialogFragment {

    private Button mCategoryUpdateButton;
    private Button mCategoryDeleteButton;
    private CategoryDialogUpdateOrDeleteListener mCategoryDialogUpdateOrDeleteListener;
    private final int mUPDATE_WAS_PUSHED = 1;
    private final int mDELETE_WAS_PUSHED = 2;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View v = inflater.inflate(R.layout.category_update_or_delete_dialog, null);
        mCategoryUpdateButton = v.findViewById(R.id.update_category_dialog);
        mCategoryDeleteButton = v.findViewById(R.id.delete_category_dialog);

        mCategoryDeleteButton.setOnClickListener(v1 -> {
            mCategoryDialogUpdateOrDeleteListener.whichButtonWasPushed(mDELETE_WAS_PUSHED);
            CategoryDialogUpdateOrDelete.this.dismiss();
        });

        mCategoryUpdateButton.setOnClickListener(v12 -> {
            mCategoryDialogUpdateOrDeleteListener.whichButtonWasPushed(mUPDATE_WAS_PUSHED);
            CategoryDialogUpdateOrDelete.this.dismiss();
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCategoryDialogUpdateOrDeleteListener = (CategoryDialogUpdateOrDeleteListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface CategoryDialogUpdateOrDeleteListener {
        void whichButtonWasPushed(int pushedButton);
    }

}
