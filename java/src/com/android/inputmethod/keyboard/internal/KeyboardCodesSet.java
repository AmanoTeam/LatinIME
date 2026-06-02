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

package com.android.inputmethod.keyboard.internal;

import com.android.inputmethod.latin.common.Constants;

import java.util.HashMap;

public final class KeyboardCodesSet {
    public static final String PREFIX_CODE = "!code/";

    private static final HashMap<String, Integer> sNameToIdMap = new HashMap<>();

    private KeyboardCodesSet() {
        // This utility class is not publicly instantiable.
    }

    public static int getCode(final String name) {
        Integer id = sNameToIdMap.get(name);
        if (id == null) throw new RuntimeException("Unknown key code: " + name);
        return DEFAULT[id];
    }

    private static final String[] ID_TO_NAME = {
        "key_tab",
        "key_enter",
        "key_space",
        "key_shift",
        "key_capslock",
        "key_switch_alpha_symbol",
        "key_output_text",
        "key_delete",
        "key_settings",
        "key_shortcut",
        "key_action_next",
        "key_action_previous",
        "key_shift_enter",
        "key_language_switch",
        "key_emoji",
        "key_alpha_from_emoji",
        "key_unspecified",
        "key_fn",
        "key_ctrl",
        "key_alt",
        "key_escape",
        "key_undo",
        "key_redo",
        "key_cut",
        "key_copy",
        "key_paste",
        "key_select_all",
        "key_select",
        "key_home",
        "key_end",
        "key_page_up",
        "key_page_down",
        "key_insert",
        "key_delete_forward",
        "key_move_left",
        "key_move_right",
        "key_move_up",
        "key_move_down",
        "key_f1",
        "key_f2",
        "key_f3",
        "key_f4",
        "key_f5",
        "key_f6",
        "key_f7",
        "key_f8",
        "key_f9",
        "key_f10",
        "key_f11",
        "key_f12",
        "key_ctrl_a",
        "key_ctrl_b",
        "key_ctrl_c",
        "key_ctrl_d",
        "key_ctrl_e",
        "key_ctrl_f",
        "key_ctrl_g",
        "key_ctrl_h",
        "key_ctrl_i",
        "key_ctrl_j",
        "key_ctrl_k",
        "key_ctrl_l",
        "key_ctrl_m",
        "key_ctrl_n",
        "key_ctrl_o",
        "key_ctrl_p",
        "key_ctrl_q",
        "key_ctrl_r",
        "key_ctrl_s",
        "key_ctrl_t",
        "key_ctrl_u",
        "key_ctrl_v",
        "key_ctrl_w",
        "key_ctrl_x",
        "key_ctrl_y",
        "key_ctrl_z",
    };

    private static final int[] DEFAULT = {
        Constants.CODE_TAB,
        Constants.CODE_ENTER,
        Constants.CODE_SPACE,
        Constants.CODE_SHIFT,
        Constants.CODE_CAPSLOCK,
        Constants.CODE_SWITCH_ALPHA_SYMBOL,
        Constants.CODE_OUTPUT_TEXT,
        Constants.CODE_DELETE,
        Constants.CODE_SETTINGS,
        Constants.CODE_SHORTCUT,
        Constants.CODE_ACTION_NEXT,
        Constants.CODE_ACTION_PREVIOUS,
        Constants.CODE_SHIFT_ENTER,
        Constants.CODE_LANGUAGE_SWITCH,
        Constants.CODE_EMOJI,
        Constants.CODE_ALPHA_FROM_EMOJI,
        Constants.CODE_UNSPECIFIED,
        Constants.CODE_FN,
        Constants.CODE_CTRL,
        Constants.CODE_ALT,
        Constants.CODE_ESCAPE,
        Constants.CODE_UNDO,
        Constants.CODE_REDO,
        Constants.CODE_CUT,
        Constants.CODE_COPY,
        Constants.CODE_PASTE,
        Constants.CODE_SELECT_ALL,
        Constants.CODE_SELECT,
        Constants.CODE_HOME,
        Constants.CODE_END,
        Constants.CODE_PAGE_UP,
        Constants.CODE_PAGE_DOWN,
        Constants.CODE_INSERT,
        Constants.CODE_DELETE_FORWARD,
        Constants.CODE_MOVE_LEFT,
        Constants.CODE_MOVE_RIGHT,
        Constants.CODE_MOVE_UP,
        Constants.CODE_MOVE_DOWN,
        Constants.CODE_F1,
        Constants.CODE_F2,
        Constants.CODE_F3,
        Constants.CODE_F4,
        Constants.CODE_F5,
        Constants.CODE_F6,
        Constants.CODE_F7,
        Constants.CODE_F8,
        Constants.CODE_F9,
        Constants.CODE_F10,
        Constants.CODE_F11,
        Constants.CODE_F12,
        Constants.CODE_CTRL_A,
        Constants.CODE_CTRL_B,
        Constants.CODE_CTRL_C,
        Constants.CODE_CTRL_D,
        Constants.CODE_CTRL_E,
        Constants.CODE_CTRL_F,
        Constants.CODE_CTRL_G,
        Constants.CODE_CTRL_H,
        Constants.CODE_CTRL_I,
        Constants.CODE_CTRL_J,
        Constants.CODE_CTRL_K,
        Constants.CODE_CTRL_L,
        Constants.CODE_CTRL_M,
        Constants.CODE_CTRL_N,
        Constants.CODE_CTRL_O,
        Constants.CODE_CTRL_P,
        Constants.CODE_CTRL_Q,
        Constants.CODE_CTRL_R,
        Constants.CODE_CTRL_S,
        Constants.CODE_CTRL_T,
        Constants.CODE_CTRL_U,
        Constants.CODE_CTRL_V,
        Constants.CODE_CTRL_W,
        Constants.CODE_CTRL_X,
        Constants.CODE_CTRL_Y,
        Constants.CODE_CTRL_Z,
    };

    static {
        for (int i = 0; i < ID_TO_NAME.length; i++) {
            sNameToIdMap.put(ID_TO_NAME[i], i);
        }
    }
}
