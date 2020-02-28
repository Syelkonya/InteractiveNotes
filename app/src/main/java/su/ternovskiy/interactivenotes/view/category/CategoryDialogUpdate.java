package su.ternovskiy.interactivenotes.view.category;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import java.util.Objects;

import su.ternovskiy.interactivenotes.R;


public class CategoryDialogUpdate extends DialogFragment {
    private Button mCategoryUpdateButton;
    private EditText mCategoryDialogUpdateEditText;
    private CategoryDialogUpdateListener mCategoryDialogUpdateListener;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View v = inflater.inflate(R.layout.category_dialog_update, null);
        mCategoryUpdateButton = v.findViewById(R.id.update_category_name_button);
        mCategoryDialogUpdateEditText = v.findViewById(R.id.update_category_name_edit_text);


        mCategoryUpdateButton.setOnClickListener(v12 -> {
            String categoryNameNew = mCategoryDialogUpdateEditText.getText().toString();
            if (!categoryNameNew.equals("")) {
                mCategoryDialogUpdateListener.isUpdateButtonClicked(categoryNameNew);
                dismiss();
            }
        });

        return v;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCategoryDialogUpdateListener = (CategoryDialogUpdate.CategoryDialogUpdateListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface CategoryDialogUpdateListener {
        void isUpdateButtonClicked(String categoryNameToUpdate);
    }
}

