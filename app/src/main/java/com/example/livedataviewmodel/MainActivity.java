package com.example.livedataviewmodel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextViewModel textViewModel;
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);

        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        textViewModel = viewModelProvider.get(TextViewModel.class);
        textViewModel.getTheTextModel().observe(this, new Observer<TextModel>() {
            @Override
            public void onChanged(@Nullable final TextModel textModel) {
                // Update the cached copy of the words in the adapter.
                if (textModel != null) {
                    textView.setText(textModel.getText());
                } else {
                    textView.setText("No word yet!");
                }
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0) {
                    String str = editText.getText().toString();
                    textViewModel.update(str);
                }
            }
        });
    }

    public void btnDeleteAllClicked(View view) {
        editText.setText("");
        textViewModel.deleteAll();
    }
}