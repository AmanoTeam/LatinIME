# Summary

## Task: 2nd-layer Ctrl and Select active/locked visual state

Implements visual active/locked effect (like Shift on 1st layer) for Ctrl and Select keys on the 2nd (Fn) layer.

## Done

1. **Spacing fix (469f938c9)** — Fn row3 Shift uses `fnKeyStyle` instead of `shiftKeyStyle` (removes stickyOff border misalignment); Fn+Ctrl Del `visualInsetsRight` changed from `1%p` to `0%p`.

2. **Key width changes (a4d20266f)** — Fn row3: Shift `15%p`→`10%p`, Select `15%p`→`20%p`. Fn+Ctrl row3: Del `15%p`→`10%p`.

3. **Enter key on 3rd row of 3rd layer (b535ad667, 62c640ebf)** — Last Del on Fn+Ctrl row3 changed to Enter key.

4. **Fn cycle fix (b7f6382d5)** — `FN_CTRL → FN` instead of `FN_CTRL → OFF`. Comment: `OFF -> FN -> FN_CTRL -> FN <-> FN_CTRL`.

5. **Ctrl/Select active visual state**: Uses three active elements so Ctrl and Select highlight independently:
   - `ELEMENT_ALPHABET_FN_CTRL_ACTIVE = 29` — Ctrl stickyOn, Select functional
   - `ELEMENT_ALPHABET_FN_SELECT_ACTIVE = 30` — Ctrl functional, Select stickyOn
   - `ELEMENT_ALPHABET_FN_BOTH_ACTIVE = 31` — both stickyOn
   - Elements use `baseForFnModifierKeyStyle` as base parent (like `baseForShiftKeyStyle`)
   - `fnAltKeyStyle` always functional (never overridden)
   - Switch/case in `key_styles_fn.xml` uses OR (`|`) to handle combined states
   - `KeyboardState.java`:
     - `SwitchActions.setAlphabetFnActiveKeyboard()`
     - `mFnCtrlActive` / `mFnSelectActive` state fields
     - `onUpdateFnElementState(ctrlActive, selectActive)` — switches between `alphabetFnActive` and `alphabetFn`
   - `KeyboardSwitcher.java`:
     - `setAlphabetFnActiveKeyboard()` — calls `setKeyboard(Element 29, ...)`
     - `updateFnElementState(ctrlActive, selectActive)` — delegates to `mState.onUpdateFnElementState`
   - `InputTransaction.java`: `requireFnElementUpdate()` / `requiresFnElementUpdate()`
   - `InputLogic.java`:
     - `requireFnElementUpdate()` called on Ctrl/Select toggle (handleFunctionalEvent)
     - `requireFnElementUpdate()` called on Ctrl consumption in onCodeInput (letter/number)
     - `mRequiresFnElementDeactivation` flag set in `sendDownUpKeyEvent` when Ctrl was active
   - `LatinIME.java`:
     - `updateStateAfterInputTransaction()` processes the flags and calls `updateFnElementState()`
     - `updateFnElementState()` delegates to `mKeyboardSwitcher`

## Commit messages in reborn branch

When committing, use conventional commit prefixes:
- `fix:` for bug fixes (spacing, Fn cycle)
- `feat:` for new features (key width, Enter key, visual states)
- `refactor:` for code restructuring

## Merge/rebase

This feature branch targets `reborn`.

## Critical Context

- `KeyboardSwitcher.setKeyboard(id, state)` reloads the keyboard XML. The switch from ELEMENT_ALPHABET_FN to/from ELEMENT_ALPHABET_FN_ACTIVE triggers a full keyboard layout reload.
- `ELEMENT_ALPHABET_FN_ACTIVE = 29` must not conflict with other elements (range is 0-32ish).
- The Fn switch (OFF/FN/FN_CTRL) is tracked via `mFnMode` and `mShiftKeyState` in KeyboardState.
- KeyboardState uses `SwitchActions` callbacks to request keyboard switches via KeyboardSwitcher.
- `InputLogic.sendDownUpKeyEvent()` always resets `mIsCtrlActive`. This is a problem for tracking Ctrl state consumption — we handle it via the `mRequiresFnElementDeactivation` flag.
- The `inputTransaction` flag approach defers keyboard element switching until `updateStateAfterInputTransaction()`, which runs after all event processing is complete. This prevents re-entrancy.

## Key Files

| File | Purpose |
|---|---|
| `java/src/.../KeyboardId.java` | Element ID definition |
| `java/src/.../keyboard/internal/KeyboardState.java` | State machine, onUpdateFnElementState |
| `java/src/.../keyboard/KeyboardSwitcher.java` | setKeyboard, updateFnElementState |
| `java/src/.../latin/LatinIME.java` | updateStateAfterInputTransaction, updateFnElementState |
| `java/src/.../inputlogic/InputLogic.java` | Ctrl/Select state tracking, sendDownUpKeyEvent |
| `java/src/.../event/InputTransaction.java` | requiresFnElementUpdate flag |
| `res/xml/key_styles_fn.xml` | fnModifierKeyStyle, fnSelectKeyStyle |
| `res/xml/rowkeys_fn_row3.xml` | Select key style |
| `res/xml/rows_qwerty_fn.xml` | Fn layer layout |
| `res/xml/rows_qwerty_fn_ctrl.xml` | Fn+Ctrl layer layout |
| `res/values/attrs.xml` | alphabetFnActive enum |
| `res/xml/apk_layouts.xml` etc. | Layout set entries |
