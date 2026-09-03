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

package com.android.inputmethod.keyboard;

/**
 * Modifier parameters that flow through the keyboard state machine into the
 * keyboard builder, so that Fn layer layouts can be selected depending on the
 * state of the Ctrl, Alt and Select modifier keys.
 *
 * Each modifier state is off (0), single (1) or lock (2).
 */
public final class ModifierParams {
    public static final int OFF = 0;
    public static final int SINGLE = 1;
    public static final int LOCK = 2;

    public static final ModifierParams DEFAULT = new ModifierParams(OFF, OFF, OFF, OFF);

    public final int mShiftState;
    public final int mCtrlState;
    public final int mAltState;
    public final int mSelectState;

    public ModifierParams(final int shiftState, final int ctrlState, final int altState,
            final int selectState) {
        mShiftState = shiftState;
        mCtrlState = ctrlState;
        mAltState = altState;
        mSelectState = selectState;
    }

    public static String stateToName(final String keyName, final int state) {
        switch (state) {
            case OFF: return keyName + "Off";
            case SINGLE: return keyName + "Single";
            case LOCK: return keyName + "Lock";
            default: return null;
        }
    }

    public ModifierParams newShiftState(final int newShiftState) {
        return new ModifierParams(newShiftState, mCtrlState, mAltState, mSelectState);
    }

    public ModifierParams newControlState(final int newCtrlState) {
        return new ModifierParams(mShiftState, newCtrlState, mAltState, mSelectState);
    }

    public ModifierParams newAltState(final int newAltState) {
        return new ModifierParams(mShiftState, mCtrlState, newAltState, mSelectState);
    }

    public ModifierParams newSelectState(final int newSelectState) {
        return new ModifierParams(mShiftState, mCtrlState, mAltState, newSelectState);
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ModifierParams)) {
            return false;
        }
        final ModifierParams that = (ModifierParams) o;
        return mShiftState == that.mShiftState
                && mCtrlState == that.mCtrlState
                && mAltState == that.mAltState
                && mSelectState == that.mSelectState;
    }

    @Override
    public int hashCode() {
        return (mSelectState << 12) + (mAltState << 8) + (mCtrlState << 4) + mShiftState;
    }

    @Override
    public String toString() {
        return "s=" + mShiftState + ",c=" + mCtrlState + ",a=" + mAltState + ",sel=" + mSelectState;
    }
}
