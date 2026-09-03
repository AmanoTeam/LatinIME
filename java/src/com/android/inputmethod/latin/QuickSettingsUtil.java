/*
 * Copyright (C) 2013 The Android Open Source Project
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

package com.android.inputmethod.latin;

public final class QuickSettingsUtil {
    private static final long QUICK_SETTINGS_PRESS_IGNORE_INTERVAL = 500;
    private static long mLastQuickSettingsOptionPress = 0;

    public static void updateLastQuickSettingsOptionPress() {
        mLastQuickSettingsOptionPress = System.currentTimeMillis();
    }

    public static boolean allowQuickSettingsOptionPress() {
        final long time = System.currentTimeMillis();
        return time - mLastQuickSettingsOptionPress
                > QUICK_SETTINGS_PRESS_IGNORE_INTERVAL;
    }
}
