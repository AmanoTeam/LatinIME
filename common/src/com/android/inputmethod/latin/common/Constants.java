/*
 * Copyright (C) 2012 The Android Open Source Project
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

package com.android.inputmethod.latin.common;

import com.android.inputmethod.annotations.UsedForTesting;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nonnull;

public final class Constants {

    public static final class Color {
        /**
         * The alpha value for fully opaque.
         */
        public final static int ALPHA_OPAQUE = 255;
    }

    public static final class ImeOption {
        /**
         * The private IME option used to indicate that no microphone should be shown for a given
         * text field. For instance, this is specified by the search dialog when the dialog is
         * already showing a voice search button.
         *
         * @deprecated Use {@link ImeOption#NO_MICROPHONE} with package name prefixed.
         */
        @SuppressWarnings("dep-ann")
        public static final String NO_MICROPHONE_COMPAT = "nm";

        /**
         * The private IME option used to indicate that no microphone should be shown for a given
         * text field. For instance, this is specified by the search dialog when the dialog is
         * already showing a voice search button.
         */
        public static final String NO_MICROPHONE = "noMicrophoneKey";

        /**
         * The private IME option used to indicate that no settings key should be shown for a given
         * text field.
         */
        public static final String NO_SETTINGS_KEY = "noSettingsKey";

        /**
         * The private IME option used to indicate that the given text field needs ASCII code points
         * input.
         *
         * @deprecated Use EditorInfo#IME_FLAG_FORCE_ASCII.
         */
        @SuppressWarnings("dep-ann")
        public static final String FORCE_ASCII = "forceAscii";

        /**
         * The private IME option used to suppress the floating gesture preview for a given text
         * field. This overrides the corresponding keyboard settings preference.
         * {@link com.android.inputmethod.latin.settings.SettingsValues#mGestureFloatingPreviewTextEnabled}
         */
        public static final String NO_FLOATING_GESTURE_PREVIEW = "noGestureFloatingPreview";

        private ImeOption() {
            // This utility class is not publicly instantiable.
        }
    }

    public static final class Subtype {
        /**
         * The subtype mode used to indicate that the subtype is a keyboard.
         */
        public static final String KEYBOARD_MODE = "keyboard";

        public static final class ExtraValue {
            /**
             * The subtype extra value used to indicate that this subtype is capable of
             * entering ASCII characters.
             */
            public static final String ASCII_CAPABLE = "AsciiCapable";

            /**
             * The subtype extra value used to indicate that this subtype is enabled
             * when the default subtype is not marked as ascii capable.
             */
            public static final String ENABLED_WHEN_DEFAULT_IS_NOT_ASCII_CAPABLE =
                    "EnabledWhenDefaultIsNotAsciiCapable";

            /**
             * The subtype extra value used to indicate that this subtype is capable of
             * entering emoji characters.
             */
            public static final String EMOJI_CAPABLE = "EmojiCapable";

            /**
             * The subtype extra value used to indicate that this subtype requires a network
             * connection to work.
             */
            public static final String REQ_NETWORK_CONNECTIVITY = "requireNetworkConnectivity";

            /**
             * The subtype extra value used to indicate that the display name of this subtype
             * contains a "%s" for printf-like replacement and it should be replaced by
             * this extra value.
             * This extra value is supported on JellyBean and later.
             */
            public static final String UNTRANSLATABLE_STRING_IN_SUBTYPE_NAME =
                    "UntranslatableReplacementStringInSubtypeName";

            /**
             * The subtype extra value used to indicate this subtype keyboard layout set name.
             * This extra value is private to LatinIME.
             */
            public static final String KEYBOARD_LAYOUT_SET = "KeyboardLayoutSet";

            /**
             * The subtype extra value used to indicate that this subtype is an additional subtype
             * that the user defined. This extra value is private to LatinIME.
             */
            public static final String IS_ADDITIONAL_SUBTYPE = "isAdditionalSubtype";

            /**
             * The subtype extra value used to specify the combining rules.
             */
            public static final String COMBINING_RULES = "CombiningRules";

            private ExtraValue() {
                // This utility class is not publicly instantiable.
            }
        }

        private Subtype() {
            // This utility class is not publicly instantiable.
        }
    }

    public static final class TextUtils {
        /**
         * Capitalization mode for {@link android.text.TextUtils#getCapsMode}: don't capitalize
         * characters.  This value may be used with
         * {@link android.text.TextUtils#CAP_MODE_CHARACTERS},
         * {@link android.text.TextUtils#CAP_MODE_WORDS}, and
         * {@link android.text.TextUtils#CAP_MODE_SENTENCES}.
         */
        // TODO: Straighten this out. It's bizarre to have to use android.text.TextUtils.CAP_MODE_*
        // except for OFF that is in Constants.TextUtils.
        public static final int CAP_MODE_OFF = 0;

        private TextUtils() {
            // This utility class is not publicly instantiable.
        }
    }

    public static final int NOT_A_CODE = -1;
    public static final int NOT_A_CURSOR_POSITION = -1;
    // TODO: replace the following constants with state in InputTransaction?
    public static final int NOT_A_COORDINATE = -1;
    public static final int SUGGESTION_STRIP_COORDINATE = -2;
    public static final int EXTERNAL_KEYBOARD_COORDINATE = -4;

    // A hint on how many characters to cache from the TextView. A good value of this is given by
    // how many characters we need to be able to almost always find the caps mode.
    public static final int EDITOR_CONTENTS_CACHE_SIZE = 1024;
    // How many characters we accept for the recapitalization functionality. This needs to be
    // large enough for all reasonable purposes, but avoid purposeful attacks. 100k sounds about
    // right for this.
    public static final int MAX_CHARACTERS_FOR_RECAPITALIZATION = 1024 * 100;

    // Key events coming any faster than this are long-presses.
    public static final int LONG_PRESS_MILLISECONDS = 200;
    // TODO: Set this value appropriately.
    public static final int GET_SUGGESTED_WORDS_TIMEOUT = 200;
    // How many continuous deletes at which to start deleting at a higher speed.
    public static final int DELETE_ACCELERATE_AT = 20;

    public static final String WORD_SEPARATOR = " ";

    public static boolean isValidCoordinate(final int coordinate) {
        // Detect {@link NOT_A_COORDINATE}, {@link SUGGESTION_STRIP_COORDINATE},
        // and {@link SPELL_CHECKER_COORDINATE}.
        return coordinate >= 0;
    }

    /**
     * Custom request code used in
     * {@link com.android.inputmethod.keyboard.KeyboardActionListener#onCustomRequest(int)}.
     */
    // The code to show input method picker.
    public static final int CUSTOM_CODE_SHOW_INPUT_METHOD_PICKER = 1;

    /**
     * Some common keys code. Must be positive.
     */
    public static final int CODE_ENTER = '\n';
    public static final int CODE_TAB = '\t';
    public static final int CODE_SPACE = ' ';
    public static final int CODE_PERIOD = '.';
    public static final int CODE_COMMA = ',';
    public static final int CODE_DASH = '-';
    public static final int CODE_SINGLE_QUOTE = '\'';
    public static final int CODE_DOUBLE_QUOTE = '"';
    public static final int CODE_SLASH = '/';
    public static final int CODE_BACKSLASH = '\\';
    public static final int CODE_VERTICAL_BAR = '|';
    public static final int CODE_COMMERCIAL_AT = '@';
    public static final int CODE_PLUS = '+';
    public static final int CODE_PERCENT = '%';
    public static final int CODE_CLOSING_PARENTHESIS = ')';
    public static final int CODE_CLOSING_SQUARE_BRACKET = ']';
    public static final int CODE_CLOSING_CURLY_BRACKET = '}';
    public static final int CODE_CLOSING_ANGLE_BRACKET = '>';
    public static final int CODE_INVERTED_QUESTION_MARK = 0xBF; // ¿
    public static final int CODE_INVERTED_EXCLAMATION_MARK = 0xA1; // ¡
    public static final int CODE_GRAVE_ACCENT = '`';
    public static final int CODE_CIRCUMFLEX_ACCENT = '^';
    public static final int CODE_TILDE = '~';

    public static final String REGEXP_PERIOD = "\\.";
    public static final String STRING_SPACE = " ";

    /**
     * Special keys code. Must be negative.
     * These should be aligned with constants in
     * {@link com.android.inputmethod.keyboard.internal.KeyboardCodesSet}.
     */
    public static final int CODE_SHIFT = -1;
    public static final int CODE_CAPSLOCK = -2;
    public static final int CODE_SWITCH_ALPHA_SYMBOL = -3;
    public static final int CODE_OUTPUT_TEXT = -4;
    public static final int CODE_DELETE = -5;
    public static final int CODE_SETTINGS = -6;
    public static final int CODE_SHORTCUT = -7;
    public static final int CODE_ACTION_NEXT = -8;
    public static final int CODE_ACTION_PREVIOUS = -9;
    public static final int CODE_LANGUAGE_SWITCH = -10;
    public static final int CODE_EMOJI = -11;
    public static final int CODE_SHIFT_ENTER = -12;
    public static final int CODE_SYMBOL_SHIFT = -13;
    public static final int CODE_ALPHA_FROM_EMOJI = -14;
    // Code value representing the code is not specified.
    public static final int CODE_UNSPECIFIED = -15;
    // Fn layer and Technical Keyboard codes
    public static final int CODE_FN = -16;
    public static final int CODE_CTRL = -17;
    public static final int CODE_ALT = -18;
    public static final int CODE_ESCAPE = -19;
    public static final int CODE_UNDO = -20;
    public static final int CODE_REDO = -21;
    public static final int CODE_CUT = -22;
    public static final int CODE_COPY = -23;
    public static final int CODE_PASTE = -24;
    public static final int CODE_SELECT_ALL = -25;
    public static final int CODE_SELECT = -26;
    public static final int CODE_HOME = -27;
    public static final int CODE_END = -28;
    public static final int CODE_PAGE_UP = -29;
    public static final int CODE_PAGE_DOWN = -30;
    public static final int CODE_INSERT = -31;
    public static final int CODE_DELETE_FORWARD = -32;
    public static final int CODE_MOVE_LEFT = -33;
    public static final int CODE_MOVE_RIGHT = -34;
    public static final int CODE_MOVE_UP = -35;
    public static final int CODE_MOVE_DOWN = -36;
    public static final int CODE_F1 = -37;
    public static final int CODE_F2 = -38;
    public static final int CODE_F3 = -39;
    public static final int CODE_F4 = -40;
    public static final int CODE_F5 = -41;
    public static final int CODE_F6 = -42;
    public static final int CODE_F7 = -43;
    public static final int CODE_F8 = -44;
    public static final int CODE_F9 = -45;
    public static final int CODE_F10 = -46;
    public static final int CODE_F11 = -47;
    public static final int CODE_F12 = -48;
    // Ctrl+Letter combo codes (for Fn+Fn layer)
    public static final int CODE_CTRL_A = -49;
    public static final int CODE_CTRL_B = -50;
    public static final int CODE_CTRL_C = -51;
    public static final int CODE_CTRL_D = -52;
    public static final int CODE_CTRL_E = -53;
    public static final int CODE_CTRL_F = -54;
    public static final int CODE_CTRL_G = -55;
    public static final int CODE_CTRL_H = -56;
    public static final int CODE_CTRL_I = -57;
    public static final int CODE_CTRL_J = -58;
    public static final int CODE_CTRL_K = -59;
    public static final int CODE_CTRL_L = -60;
    public static final int CODE_CTRL_M = -61;
    public static final int CODE_CTRL_N = -62;
    public static final int CODE_CTRL_O = -63;
    public static final int CODE_CTRL_P = -64;
    public static final int CODE_CTRL_Q = -65;
    public static final int CODE_CTRL_R = -66;
    public static final int CODE_CTRL_S = -67;
    public static final int CODE_CTRL_T = -68;
    public static final int CODE_CTRL_U = -69;
    public static final int CODE_CTRL_V = -70;
    public static final int CODE_CTRL_W = -71;
    public static final int CODE_CTRL_X = -72;
    public static final int CODE_CTRL_Y = -73;
    public static final int CODE_CTRL_Z = -74;
    public static final int CODE_MOVE_WORD_LEFT = -75;
    public static final int CODE_MOVE_WORD_RIGHT = -76;
    // QuickSettings layer codes
    public static final int CODE_QS_PC_LAYOUT = -77;
    public static final int CODE_QS_AUTOCORRECT = -78;
    public static final int CODE_QS_SUGGESTIONS = -79;
    public static final int CODE_QS_NUMBER_ROW = -80;
    public static final int CODE_QS_OPEN_LANGUAGES = -81;
    public static final int CODE_QS_OPEN_SETTINGS = -82;

    // Terminal emulator packages that need special key handling.
    public static final Set<String> CONNECTBOT_PACKAGES;
    public static final Set<String> TERMINALIDE_PACKAGES;
    static {
        final Set<String> connectbot = new HashSet<>();
        connectbot.add("org.connectbot");
        connectbot.add("org.woltage.irssiconnectbot");
        connectbot.add("sk.vx.connectbot");
        connectbot.add("com.pslib.connectbot");
        connectbot.add("com.madgag.ssh.agent");
        CONNECTBOT_PACKAGES = Collections.unmodifiableSet(connectbot);
        final Set<String> terminalIde = new HashSet<>();
        terminalIde.add("com.spartacusrex.spartacuside");
        TERMINALIDE_PACKAGES = Collections.unmodifiableSet(terminalIde);
    }

    public static boolean isLetterCode(final int code) {
        return code >= CODE_SPACE;
    }

    // Whether the code affects the meta modifier state machine in
    // {@link com.android.inputmethod.keyboard.internal.KeyboardState} and should not
    // trigger a single-shot modifier reset.
    public static boolean isMetaCode(final int code) {
        switch (code) {
            case CODE_SHIFT:
            case CODE_CAPSLOCK:
            case CODE_SWITCH_ALPHA_SYMBOL:
            case CODE_SETTINGS:
            case CODE_FN:
            case CODE_CTRL:
            case CODE_ALT:
            case CODE_SELECT:
                return true;
            default:
                return false;
        }
    }

    // Whether the code moves the cursor, so that the Select modifier stays active
    // while these keys are pressed.
    public static boolean isCursorCode(final int code) {
        switch (code) {
            case CODE_MOVE_LEFT:
            case CODE_MOVE_RIGHT:
            case CODE_MOVE_UP:
            case CODE_MOVE_DOWN:
            case CODE_MOVE_WORD_LEFT:
            case CODE_MOVE_WORD_RIGHT:
            case CODE_HOME:
            case CODE_END:
            case CODE_PAGE_UP:
            case CODE_PAGE_DOWN:
                return true;
            default:
                return false;
        }
    }

    @Nonnull
    public static String printableCode(final int code) {
        switch (code) {
        case CODE_SHIFT: return "shift";
        case CODE_CAPSLOCK: return "capslock";
        case CODE_SWITCH_ALPHA_SYMBOL: return "symbol";
        case CODE_OUTPUT_TEXT: return "text";
        case CODE_DELETE: return "delete";
        case CODE_SETTINGS: return "settings";
        case CODE_SHORTCUT: return "shortcut";
        case CODE_ACTION_NEXT: return "actionNext";
        case CODE_ACTION_PREVIOUS: return "actionPrevious";
        case CODE_LANGUAGE_SWITCH: return "languageSwitch";
        case CODE_EMOJI: return "emoji";
        case CODE_SHIFT_ENTER: return "shiftEnter";
        case CODE_ALPHA_FROM_EMOJI: return "alpha";
        case CODE_UNSPECIFIED: return "unspec";
        case CODE_FN: return "fn";
        case CODE_CTRL: return "ctrl";
        case CODE_ALT: return "alt";
        case CODE_ESCAPE: return "escape";
        case CODE_UNDO: return "undo";
        case CODE_REDO: return "redo";
        case CODE_CUT: return "cut";
        case CODE_COPY: return "copy";
        case CODE_PASTE: return "paste";
        case CODE_SELECT_ALL: return "selectAll";
        case CODE_SELECT: return "select";
        case CODE_HOME: return "home";
        case CODE_END: return "end";
        case CODE_PAGE_UP: return "pageUp";
        case CODE_PAGE_DOWN: return "pageDown";
        case CODE_INSERT: return "insert";
        case CODE_DELETE_FORWARD: return "deleteForward";
        case CODE_MOVE_LEFT: return "moveLeft";
        case CODE_MOVE_RIGHT: return "moveRight";
        case CODE_MOVE_UP: return "moveUp";
        case CODE_MOVE_DOWN: return "moveDown";
        case CODE_F1: return "F1";
        case CODE_F2: return "F2";
        case CODE_F3: return "F3";
        case CODE_F4: return "F4";
        case CODE_F5: return "F5";
        case CODE_F6: return "F6";
        case CODE_F7: return "F7";
        case CODE_F8: return "F8";
        case CODE_F9: return "F9";
        case CODE_F10: return "F10";
        case CODE_F11: return "F11";
        case CODE_F12: return "F12";
        case CODE_CTRL_A: return "ctrlA";
        case CODE_CTRL_B: return "ctrlB";
        case CODE_CTRL_C: return "ctrlC";
        case CODE_CTRL_D: return "ctrlD";
        case CODE_CTRL_E: return "ctrlE";
        case CODE_CTRL_F: return "ctrlF";
        case CODE_CTRL_G: return "ctrlG";
        case CODE_CTRL_H: return "ctrlH";
        case CODE_CTRL_I: return "ctrlI";
        case CODE_CTRL_J: return "ctrlJ";
        case CODE_CTRL_K: return "ctrlK";
        case CODE_CTRL_L: return "ctrlL";
        case CODE_CTRL_M: return "ctrlM";
        case CODE_CTRL_N: return "ctrlN";
        case CODE_CTRL_O: return "ctrlO";
        case CODE_CTRL_P: return "ctrlP";
        case CODE_CTRL_Q: return "ctrlQ";
        case CODE_CTRL_R: return "ctrlR";
        case CODE_CTRL_S: return "ctrlS";
        case CODE_CTRL_T: return "ctrlT";
        case CODE_CTRL_U: return "ctrlU";
        case CODE_CTRL_V: return "ctrlV";
        case CODE_CTRL_W: return "ctrlW";
        case CODE_CTRL_X: return "ctrlX";
        case CODE_CTRL_Y: return "ctrlY";
        case CODE_CTRL_Z: return "ctrlZ";
        case CODE_MOVE_WORD_LEFT: return "moveWordLeft";
        case CODE_MOVE_WORD_RIGHT: return "moveWordRight";
        case CODE_TAB: return "tab";
        case CODE_ENTER: return "enter";
        case CODE_SPACE: return "space";
        default:
            if (code < CODE_SPACE) return String.format("\\u%02X", code);
            if (code < 0x100) return String.format("%c", code);
            if (code < 0x10000) return String.format("\\u%04X", code);
            return String.format("\\U%05X", code);
        }
    }

    @Nonnull
    public static String printableCodes(@Nonnull final int[] codes) {
        final StringBuilder sb = new StringBuilder();
        boolean addDelimiter = false;
        for (final int code : codes) {
            if (code == NOT_A_CODE) break;
            if (addDelimiter) sb.append(", ");
            sb.append(printableCode(code));
            addDelimiter = true;
        }
        return "[" + sb + "]";
    }

    /**
     * Screen metrics (a.k.a. Device form factor) constants of
     * {@link com.android.inputmethod.latin.R.integer#config_screen_metrics}.
     */
    public static final int SCREEN_METRICS_SMALL_PHONE = 0;
    public static final int SCREEN_METRICS_LARGE_PHONE = 1;
    public static final int SCREEN_METRICS_LARGE_TABLET = 2;
    public static final int SCREEN_METRICS_SMALL_TABLET = 3;

    @UsedForTesting
    public static boolean isPhone(final int screenMetrics) {
        return screenMetrics == SCREEN_METRICS_SMALL_PHONE
                || screenMetrics == SCREEN_METRICS_LARGE_PHONE;
    }

    @UsedForTesting
    public static boolean isTablet(final int screenMetrics) {
        return screenMetrics == SCREEN_METRICS_SMALL_TABLET
                || screenMetrics == SCREEN_METRICS_LARGE_TABLET;
    }

    /**
     * Default capacity of gesture points container.
     * This constant is used by {@link com.android.inputmethod.keyboard.internal.BatchInputArbiter}
     * and etc. to preallocate regions that contain gesture event points.
     */
    public static final int DEFAULT_GESTURE_POINTS_CAPACITY = 128;

    public static final int MAX_IME_DECODER_RESULTS = 20;
    public static final int DECODER_SCORE_SCALAR = 1000000;
    public static final int DECODER_MAX_SCORE = 1000000000;

    public static final int EVENT_BACKSPACE = 1;
    public static final int EVENT_REJECTION = 2;
    public static final int EVENT_REVERT = 3;

    private Constants() {
        // This utility class is not publicly instantiable.
    }
}
