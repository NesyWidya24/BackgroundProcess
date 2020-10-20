package com.nessy.alarmmanager;

import android.app.TimePickerDialog;
import android.app.Dialog;
import androidx.fragment.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.TimePicker;

import androidx.annotation.NonNull;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    DialogTimeListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null){
            listener = (DialogTimeListener) context;
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
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this, hour,minute, true);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        listener.onDialogTimeSet(getTag(), hourOfDay, minute);
    }

    public interface DialogTimeListener{
        void onDialogTimeSet(String tag, int hourOfDay, int minute);
    }
}

