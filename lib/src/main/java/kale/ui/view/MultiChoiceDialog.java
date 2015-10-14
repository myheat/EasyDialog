package kale.ui.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import kale.ui.view.DialogInterface.OnMultiChoiceClickListener;

/**
 * @author Jack Tony
 * @date 2015/8/11
 */
public class MultiChoiceDialog extends BaseEasyAlertDialog {

    private static final String KEY_ITEM_STR_ARR = "KEY_ITEM_STR_ARR";

    private static final String KEY_DEFAULT_CHOICE_ARR = "KEY_DEFAULT_CHOICE_ARR";

    private static final String KEY_MULTI_CHOICE_LISTENER = "KEY_MULTI_CHOICE_LISTENER";

    public static class Builder extends BaseEasyAlertDialog.Builder {

        public Builder setData(@NonNull String[] itemStrArr, @NonNull boolean[] defaultChoiceArr) {
            bundle.putStringArray(KEY_ITEM_STR_ARR, itemStrArr);
            bundle.putBooleanArray(KEY_DEFAULT_CHOICE_ARR, defaultChoiceArr);
            return this;
        }

        public Builder setOnMultiChoiceClickListener(OnMultiChoiceClickListener listener) {
            bundle.putSerializable(KEY_MULTI_CHOICE_LISTENER, listener);
            return this;
        }

        @Override
        public MultiChoiceDialog create() {
            MultiChoiceDialog dialog = new MultiChoiceDialog();
            dialog.setArguments(bundle);
            return dialog;
        }
    }

    @Override
    protected void setAlertBuilder(AlertDialog.Builder builder, @Nullable Bundle arguments) {
        if (arguments != null) {
            String[] itemStrArr = arguments.getStringArray(KEY_ITEM_STR_ARR);
            boolean[] defaultChoiceArr = arguments.getBooleanArray(KEY_DEFAULT_CHOICE_ARR);
            final OnMultiChoiceClickListener  mListener = (OnMultiChoiceClickListener) arguments.getSerializable(KEY_MULTI_CHOICE_LISTENER);
            
            if (itemStrArr == null) {
                throw new IllegalArgumentException("Item's String Array is null!");
            }

            builder.setMultiChoiceItems(itemStrArr, defaultChoiceArr, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    if (mListener != null) {
                        mListener.onClick(dialog, which, isChecked);
                    }
                }
            });
        }
    }
    
}