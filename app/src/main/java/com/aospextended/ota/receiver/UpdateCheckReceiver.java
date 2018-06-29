/*
 * Copyright (C) 2012 The CyanogenMod Project
 * Copyright (C) 2018 Pixel Experience
 * Copyright (C) 2017 The LineageOS Project
 *
 * * Licensed under the GNU GPLv2 license
 *
 * The text of the license can be found in the LICENSE file
 * or at https://www.gnu.org/licenses/gpl-2.0.txt
 */

package com.aospextended.ota.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.aospextended.ota.misc.Constants;
import com.aospextended.ota.utils.Utils;

public class UpdateCheckReceiver extends BroadcastReceiver {
    private static final String TAG = "UpdateCheckReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // Load the required settings from preferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        int updateFrequency = Constants.UPDATE_DEFAULT_FREQ;

        // Not set to manual updates, parse the received action
        final String action = intent.getAction();
        if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            // clean temp dir
            Utils.deleteTempFolder();
        }

        // Handle the actual update check based on the defined frequency
        if (updateFrequency > 0) {
            Log.i(TAG, "Scheduling future, repeating update checks.");
            Utils.scheduleUpdateService(context, updateFrequency * 1000);
        }
    }
}
