package com.sandy.mbclient;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

import us.zoom.sdk.MeetingActivity;

public class MyMeetingActivity extends MeetingActivity {

    private Button btnLeaveZoomMeeting;
    private Button btnSwitchToNextCamera;
    private Button btnAudio;
    private Button btnParticipants;
    private Button btnShare;
    private Button btnStopShare;
    private Button btnMoreOptions;


    @Override
    protected int getLayout() {
        return R.layout.my_meeting_layout;
    }

    @Override
    protected boolean isAlwaysFullScreen() {
        return false;
    }

    @Override
    protected boolean isSensorOrientationEnabled() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        disableFullScreenMode();

        btnLeaveZoomMeeting = (Button) findViewById(R.id.btnLeaveZoomMeeting);
        btnSwitchToNextCamera = (Button) findViewById(R.id.btnSwitchToNextCamera);
        btnAudio = (Button) findViewById(R.id.btnAudio);
        btnParticipants = (Button) findViewById(R.id.btnParticipants);
        btnShare = (Button) findViewById(R.id.btnShare);
        btnStopShare = (Button) findViewById(R.id.btnStopShare);
        btnMoreOptions = (Button) findViewById(R.id.btnMoreOptions);

        btnLeaveZoomMeeting.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showLeaveDialog();
            }
        });

        btnSwitchToNextCamera.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                switchToNextCamera();
            }
        });

        btnAudio.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //doAudioAction();
                if (!isAudioConnected()) {
                    connectVoIP();
                } else {
                    muteAudio(!isAudioMuted());
                }
            }
        });

        btnParticipants.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showParticipants();
            }
        });

        btnShare.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showShareOptions();
            }
        });

        btnStopShare.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                stopShare();
            }
        });

        btnMoreOptions.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showMoreOptions();
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        disableFullScreenMode();
    }

    private void disableFullScreenMode() {
        getWindow().setFlags(~WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateButtonsStatus();
    }

    @Override
    protected void onMeetingConnected() {
        updateButtonsStatus();
    }

    @Override
    protected void onSilentModeChanged(boolean inSilentMode) {
        updateButtonsStatus();
    }

    @Override
    protected void onStartShare() {
        btnShare.setVisibility(View.GONE);
        btnStopShare.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStopShare() {
        btnShare.setVisibility(View.VISIBLE);
        btnStopShare.setVisibility(View.GONE);
    }

    private void updateButtonsStatus() {

        boolean enabled = (isMeetingConnected() && !isInSilentMode());

        btnSwitchToNextCamera.setEnabled(enabled);
        btnAudio.setEnabled(enabled);
        btnParticipants.setEnabled(enabled);
        btnShare.setEnabled(enabled);
        btnMoreOptions.setEnabled(enabled);

        if (isSharingOut()) {
            btnShare.setVisibility(View.GONE);
            btnStopShare.setVisibility(View.VISIBLE);
        } else {
            btnShare.setVisibility(View.VISIBLE);
            btnStopShare.setVisibility(View.GONE);
        }
    }
}
