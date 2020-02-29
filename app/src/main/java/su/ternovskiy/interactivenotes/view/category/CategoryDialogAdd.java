package su.ternovskiy.interactivenotes.view.category;

import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import su.ternovskiy.interactivenotes.R;
import su.ternovskiy.interactivenotes.data.Category;

public class CategoryDialogAdd extends DialogFragment implements View.OnClickListener {

    private EditText mCategoryNameEditText;
    private CategoryViewModel mCategoryViewModel;
    private Button mCategoryNameButton;

    @SuppressLint("ResourceAsColor")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View v = inflater.inflate(R.layout.category_dialog, null);
        mCategoryNameButton = v.findViewById(R.id.add_category_name_button);
        mCategoryNameEditText = v.findViewById(R.id.add_category_name_edit_text);
        mCategoryNameButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        mCategoryViewModel = new CategoryViewModel(getActivity().getApplication());
        String categoryName = mCategoryNameEditText.getText().toString();
        if (!categoryName.equals("")) {
            Category plusCategory = new Category(categoryName);
            mCategoryViewModel.addCategory(plusCategory);
            mCategoryNameEditText.setText("");
            dismiss();
        }
    }


}
