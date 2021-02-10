package com.example.livedataviewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TextViewModel extends AndroidViewModel {

    private TextModelRepository textModelRepository;
    private LiveData<TextModel> theTextModel;

    public TextViewModel(@NonNull Application application) {
        super(application);

        textModelRepository = new TextModelRepository(application);
        theTextModel = textModelRepository.getFirstText();
    }

    public LiveData<TextModel> getTheTextModel() {
        return theTextModel;
    }

    public void update(String txt) {
        if (theTextModel.getValue() == null) {
            textModelRepository.insert(new TextModel(txt));
        } else {
            textModelRepository.update(txt);
        }
    }

    public void deleteAll() {
        textModelRepository.deleteAll();
        theTextModel = textModelRepository.getFirstText();
    }

}
