/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.inputmethod.latin.settings;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.common.Constants;
import com.android.inputmethod.latin.define.ProductionFlags;

import java.util.Locale;

/**
 * "Appearance" settings sub screen.
 */
public final class AppearanceSettingsFragment extends SubScreenFragment {
    private static final int PREF_KEY_TEXT_SIZE_ADJUST_DEFAULT = 100;

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.prefs_screen_appearance);
        if (!ProductionFlags.IS_SPLIT_KEYBOARD_SUPPORTED ||
                Constants.isPhone(Settings.readScreenMetrics(getResources()))) {
            removePreference(Settings.PREF_ENABLE_SPLIT_KEYBOARD);
        }
        setupKeyTextSizeAdjustmentSettings();
    }

    @Override
    public void onResume() {
        super.onResume();
        CustomInputStyleSettingsFragment.updateCustomInputStylesSummary(
                findPreference(Settings.PREF_CUSTOM_INPUT_STYLES));
        ThemeSettingsFragment.updateKeyboardThemeSummary(findPreference(Settings.SCREEN_THEME));
        updateListPreferenceSummaryToCurrentValue(Settings.PREF_KEY_TYPEFACE);
    }

    @Override
    public void onSharedPreferenceChanged(final SharedPreferences prefs, final String key) {
        updateListPreferenceSummaryToCurrentValue(Settings.PREF_KEY_TYPEFACE);
    }

    private void setupKeyTextSizeAdjustmentSettings() {
        final SeekBarDialogPreference pref =
                (SeekBarDialogPreference) findPreference(Settings.PREF_KEY_TEXT_SIZE_ADJUST);
        if (pref == null) {
            return;
        }
        final SharedPreferences prefs = getSharedPreferences();
        final Resources res = getResources();
        pref.setInterface(new SeekBarDialogPreference.ValueProxy() {
            @Override
            public void writeValue(final int value, final String key) {
                prefs.edit().putInt(key, value).apply();
            }

            @Override
            public void writeDefaultValue(final String key) {
                prefs.edit().remove(key).apply();
            }

            @Override
            public int readValue(final String key) {
                return Settings.readKeyTextSizeAdjust(prefs);
            }

            @Override
            public int readDefaultValue(final String key) {
                return PREF_KEY_TEXT_SIZE_ADJUST_DEFAULT;
            }

            @Override
            public void feedbackValue(final int value) {}

            @Override
            public String getValueText(final int value) {
                return String.format(Locale.ROOT, "%d%%", value);
            }
        });
    }
}
