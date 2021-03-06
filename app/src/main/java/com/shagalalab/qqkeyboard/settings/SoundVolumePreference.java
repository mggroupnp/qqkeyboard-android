package com.shagalalab.qqkeyboard.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.shagalalab.qqkeyboard.R;
import com.shagalalab.qqkeyboard.util.SettingsUtil;

/**
 * Created by atabek on 8/09/2016.
 */

public class SoundVolumePreference extends DialogPreference implements SeekBar.OnSeekBarChangeListener {
    private TextView caption;
    private SeekBar seekBar;

    public SoundVolumePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SoundVolumePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        setSummary(String.format(getContext().getString(R.string.text_with_percent), SettingsUtil.getSoundVolume(getContext())));
        return super.onCreateView(parent);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (positiveResult) {
            setSummary(String.format(getContext().getString(R.string.text_with_percent), this.seekBar.getProgress()));
        }
    }

    @Override
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        super.onPrepareDialogBuilder(builder);
        builder.setPositiveButton(R.string.ok, this);
        builder.setNegativeButton(R.string.cancel, this);
    }

    @Override
    protected View onCreateDialogView() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.sound_volume_preference, null);

        caption = (TextView) view.findViewById(R.id.sound_volume_preference_caption);
        seekBar = (SeekBar) view.findViewById(R.id.sound_volume_preference_seekbar);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setProgress(SettingsUtil.getSoundVolume(getContext()));

        return view;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        super.onClick(dialog, which);
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                SettingsUtil.setSoundVolume(getContext(), seekBar.getProgress());
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                break;
            default:
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        caption.setText(String.format(getContext().getString(R.string.text_with_percent), progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
