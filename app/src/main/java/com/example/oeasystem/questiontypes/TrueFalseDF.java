package com.example.oeasystem.questiontypes;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.oeasystem.MyDialogFragment;
import com.example.oeasystem.R;

public class TrueFalseDF extends DialogFragment {

    private onTFDialogFragmentBtnSelected listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view =  inflater.inflate(R.layout.df_truefalse,container,false);
        Button clickme = view.findViewById(R.id.savetfbtn);
        Button four = view.findViewById(R.id.dort);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onTFDialogButtonSelected();
            }
        });
        clickme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.dort();
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof onTFDialogFragmentBtnSelected){
            listener = (onTFDialogFragmentBtnSelected) context;
        }else{
            throw new ClassCastException(context.toString() + " must implement listener");
        }

    }

    public interface onTFDialogFragmentBtnSelected {
        public void onTFDialogButtonSelected();
        public void dort();
    }
}
