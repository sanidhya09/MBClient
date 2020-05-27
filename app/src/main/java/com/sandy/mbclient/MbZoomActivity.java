package com.sandy.mbclient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import us.zoom.sdk.InviteOptions;
import us.zoom.sdk.JoinMeetingOptions;
import us.zoom.sdk.JoinMeetingParams;
import us.zoom.sdk.MeetingError;
import us.zoom.sdk.MeetingService;
import us.zoom.sdk.MeetingServiceListener;
import us.zoom.sdk.MeetingStatus;
import us.zoom.sdk.MeetingViewsOptions;
import us.zoom.sdk.ZoomError;
import us.zoom.sdk.ZoomSDK;
import us.zoom.sdk.ZoomSDKInitParams;
import us.zoom.sdk.ZoomSDKInitializeListener;

public class MbZoomActivity extends Activity implements Constants, MeetingServiceListener, ZoomSDKInitializeListener {

    private final static String TAG = "Sandy Test";

    public final static String ACTION_RETURN_FROM_MEETING = "com.sandy.mbclient.action.ReturnFromMeeting";
    public final static String EXTRA_TAB_ID = "tabId";

    public final static int TAB_MEETING = 2;

    private final static String DISPLAY_NAME = "Attendee 1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mb_zoom);

        ZoomSDK zoomSDK = ZoomSDK.getInstance();

        if (savedInstanceState == null) {
            ZoomSDKInitParams initParams = new ZoomSDKInitParams();
//			initParams.jwtToken = SDK_JWTTOKEN;
            initParams.appKey = Constants.SDK_KEY;
            initParams.appSecret = Constants.SDK_SECRET;
            initParams.domain = WEB_DOMAIN;
            zoomSDK.initialize(this, this, initParams);
        }

        if (zoomSDK.isInitialized()) {
            registerMeetingServiceListener();
        }
    }


    @Override
    public void onZoomSDKInitializeResult(int errorCode, int internalErrorCode) {
        Log.i(TAG, "onZoomSDKInitializeResult, errorCode=" + errorCode + ", internalErrorCode=" + internalErrorCode);

        if (errorCode != ZoomError.ZOOM_ERROR_SUCCESS) {
            Toast.makeText(this, "Failed to initialize Zoom SDK. Error: " + errorCode + ", internalErrorCode=" + internalErrorCode, Toast.LENGTH_LONG);
        } else {
            Toast.makeText(this, "Initialize Zoom SDK successfully.", Toast.LENGTH_LONG).show();

            registerMeetingServiceListener();

        }
    }

    private void registerMeetingServiceListener() {
        ZoomSDK zoomSDK = ZoomSDK.getInstance();
        MeetingService meetingService = zoomSDK.getMeetingService();
        if (meetingService != null) {
            meetingService.addListener(this);
        }
    }

    @Override
    protected void onDestroy() {
        ZoomSDK zoomSDK = ZoomSDK.getInstance();

        if (zoomSDK.isInitialized()) {
            MeetingService meetingService = zoomSDK.getMeetingService();
            meetingService.removeListener(this);
        }

        super.onDestroy();
    }

    public void startMeeting() {

        ZoomSDK zoomSDK = ZoomSDK.getInstance();

        if (!zoomSDK.isInitialized()) {
            Toast.makeText(this, "ZoomSDK has not been initialized successfully", Toast.LENGTH_LONG).show();
            return;
        }

        if (MEETING_ID == null) {
            Toast.makeText(this, "MEETING_ID in Constants can not be NULL", Toast.LENGTH_LONG).show();
            return;
        }

        MeetingService meetingService = zoomSDK.getMeetingService();

        //int ret = meetingService.startMeetingWithParams(this, params, opts);
        JoinMeetingOptions opts = ZoomMeetingUISettingHelper.getJoinMeetingOptions();

// some available options
        opts.no_driving_mode = true;
        opts.no_invite = true;
        opts.no_meeting_end_message = true;
        opts.no_titlebar = true;
        opts.no_bottom_toolbar = true;
        opts.no_dial_in_via_phone = true;
        opts.no_dial_out_to_phone = true;
        opts.no_disconnect_audio = true;
        opts.no_share = true;
        opts.invite_options = InviteOptions.INVITE_VIA_EMAIL + InviteOptions.INVITE_VIA_SMS;
        opts.no_audio = true;
        opts.no_video = true;
        opts.meeting_views_options = MeetingViewsOptions.NO_BUTTON_SHARE;
        opts.no_meeting_error_message = true;
//        opts.participant_id = "participant id";

        JoinMeetingParams params = new JoinMeetingParams();

        params.displayName = DISPLAY_NAME;
        params.meetingNo = MEETING_ID;
        params.password = "2hEqcS";

        int ret = meetingService.joinMeetingWithParams(this, params, opts);


        Log.i(TAG, "onClickBtnStartMeeting, ret=" + ret);
    }

    @Override
    public void onMeetingStatusChanged(MeetingStatus meetingStatus, int errorCode,
                                       int internalErrorCode) {

        if (meetingStatus == meetingStatus.MEETING_STATUS_FAILED && errorCode == MeetingError.MEETING_ERROR_CLIENT_INCOMPATIBLE) {
            Toast.makeText(this, "Version of ZoomSDK is too low!", Toast.LENGTH_LONG).show();
        }

        if (meetingStatus == MeetingStatus.MEETING_STATUS_IDLE || meetingStatus == MeetingStatus.MEETING_STATUS_FAILED) {
//            selectTab(TAB_WELCOME);
            Toast.makeText(this, "Meeting END", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onZoomAuthIdentityExpired() {

    }

    public void startMeeting(View view) {
        ZoomSDK zoomSDK = ZoomSDK.getInstance();
        if (!zoomSDK.isInitialized()) {
            Toast.makeText(this, "ZoomSDK has not been initialized successfully", Toast.LENGTH_LONG).show();
            return;
        }

        MeetingService meetingService = zoomSDK.getMeetingService();
        if (meetingService == null)
            return;

        if (meetingService.getMeetingStatus() == MeetingStatus.MEETING_STATUS_IDLE) {
            startMeeting();
        } else {
            meetingService.returnToMeeting(this);
        }
    }

    public void exitApp(View view) {
        onBackPressed();
    }
}
