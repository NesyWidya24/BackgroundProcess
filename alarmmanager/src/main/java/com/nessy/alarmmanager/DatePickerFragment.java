package com.nessy.alarmmanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import androidx.fragment.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    DialogDateListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null){
            listener = (DialogDateListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (listener != null){
            listener = null;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        return new DatePickerDialog(getActivity(), this, year,month, date);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        listener.onDialogDataSet(getTag(), year, month, dayOfMonth);
    }

    public interface DialogDateListener{
        void onDialogDataSet(String tag, int year, int month, int dayOfMonth);
    }
}
