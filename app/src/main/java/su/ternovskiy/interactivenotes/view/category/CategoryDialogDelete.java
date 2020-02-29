package su.ternovskiy.interactivenotes.view.category;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

import su.ternovskiy.interactivenotes.R;
import su.ternovskiy.interactivenotes.data.Category;

public class CategoryDialogDelete extends DialogFragment {
    private Button mCategoryDeleteButton;
    private Button mCategoryCancelButton;
    private CategoryViewModel mCategoryViewModel;
    private Category mCategoryToDelete;
    private CategoryDialogDeleteListener mCategoryDialogDeleteListener;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View v = inflater.inflate(R.layout.category_dialog_delete, null);
        mCategoryDeleteButton = v.findViewById(R.id.delete_category_button);
        mCategoryCancelButton = v.findViewById(R.id.cancel_button_delete_category_dialog);

        mCategoryCancelButton.setOnClickListener(v1 -> {
            dismiss();
        });

        mCategoryDeleteButton.setOnClickListener(v12 -> {
            mCategoryDialogDeleteListener.isDeleteButtonPushedSecondly(true);
            dismiss();
        });

        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCategoryDialogDeleteListener = (CategoryDialogDelete.CategoryDialogDeleteListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface CategoryDialogDeleteListener {
        void isDeleteButtonPushedSecondly(Boolean isDeleteClicked);
    }
}
