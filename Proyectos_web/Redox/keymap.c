/*
Copyright 2018 Mattia Dal Ben <matthewdibi@gmail.com>

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

#include QMK_KEYBOARD_H

enum layers {
   _QWERTY,
   _SYMB,
   _NAV,
   _ACCENT,
   _ALTGR
};

enum custom_keycodes {
    CK_IEXC = SAFE_RANGE,
    CK_IQUE,
    CK_DEGR,
    CK_ENIE,
    CK_AACT,
    CK_EACT,
    CK_IACT,
    CK_OACT,
    CK_UACT
};

// Layer switching shortcuts
#define SYM_T   TG(_SYMB)
#define SYM_ESC LT(_SYMB, KC_ESC)
#define SYM_MO  MO(_SYMB)
#define NAV_SPC LT(_NAV, KC_SPC)

// Complex keycode shortcuts
#define RA_1    CK_IEXC
#define RA_SLSH CK_IQUE
#define RSA_SCL CK_DEGR
#define SF_SCLN LSFT_T(KC_SCLN)
#define SF_GRV  LSFT_T(KC_GRV)
#define LA_F4   LALT(KC_F4)
#define OSL_ACC OSL(_ACCENT)

// Tap Dance declarations
enum {
    RALT_TD,
};
#define RALT_TP TD(RALT_TD)

const uint16_t PROGMEM keymaps[][MATRIX_ROWS][MATRIX_COLS] = {

  [_QWERTY] = LAYOUT(
  //┌────────┬────────┬────────┬────────┬────────┬────────┐                                           ┌────────┬────────┬────────┬────────┬────────┬────────┐
     KC_CAPS ,KC_1    ,KC_2    ,KC_3    ,KC_4    ,KC_5    ,                                            KC_6    ,KC_7    ,KC_8    ,KC_9    ,KC_0    ,KC_NUM  ,
  //├────────┼────────┼────────┼────────┼────────┼────────┼────────┐                         ┌────────┼────────┼────────┼────────┼────────┼────────┼────────┤
     KC_TAB  ,KC_Q    ,KC_W    ,KC_E    ,KC_R    ,KC_T    ,SYM_T   ,                          SYM_T   ,KC_Y    ,KC_U    ,KC_I    ,KC_O    ,KC_P    ,KC_EQL  ,
  //├────────┼────────┼────────┼────────┼────────┼────────┼────────┤                         ├────────┼────────┼────────┼────────┼────────┼────────┼────────┤
     SYM_ESC ,KC_A    ,KC_S    ,KC_D    ,KC_F    ,KC_G    ,KC_LBRC ,                          KC_RBRC ,KC_H    ,KC_J    ,KC_K    ,KC_L    ,KC_SCLN ,OSL_ACC ,
  //├────────┼────────┼────────┼────────┼────────┼────────┼────────┼────────┐       ┌────────┼────────┼────────┼────────┼────────┼────────┼────────┼────────┤
     KC_LSFT ,KC_Z    ,KC_X    ,KC_C    ,KC_V    ,KC_B    ,RA_1    ,KC_EXLM ,        RA_SLSH ,KC_QUES ,KC_N    ,KC_M    ,KC_COMM ,KC_DOT  ,KC_SLSH ,KC_RSFT ,
  //├────────┼────────┼────────┼────────┼────┬───┴────┬───┼────────┼────────┤       ├────────┼────────┼───┬────┴───┬────┼────────┼────────┼────────┼────────┤
     KC_LCTL ,KC_LGUI ,OS_MEH  ,KC_LALT ,     KC_LCTL ,    KC_SPC  ,KC_ENT  ,        SYM_MO  ,KC_BSPC ,    RALT_TP ,     KC_LEFT ,KC_DOWN ,KC_UP   ,KC_RGHT
  //└────────┴────────┴────────┴────────┘    └────────┘   └────────┴────────┘       └────────┴────────┘   └────────┘    └────────┴────────┴────────┴────────┘
  ),

/*
[_SYMB] = LAYOUT(
  //┌────────┬────────┬────────┬────────┬────────┬────────┐                                           ┌────────┬────────┬────────┬────────┬────────┬────────┐
        º    ,   !    ,   @    ,   #    ,   $    ,   %    ,                                               ^    ,   &    ,   _    ,   !    ,   ?    , TRANS  ,
  //├────────┼────────┼────────┼────────┼────────┼────────┼────────┐                         ┌────────┼────────┼────────┼────────┼────────┼────────┼────────┤
      Bksp   , Nump / , Nump 7 , Nump 8 , Nump 9 , Nump - , TRANS  ,                           TRANS  ,   +    ,   [    ,   ]    ,   /    ,   \    ,   |    ,
  //├────────┼────────┼────────┼────────┼────────┼────────┼────────┤                         ├────────┼────────┼────────┼────────┼────────┼────────┼────────┤
      TRANS  , Nump * , Nump 4 , Nump 5 , Nump 6 , Nump + ,   ,    ,                           TRANS  ,   -    ,   (    ,   )    ,   =    , SHFT/` ,   "    ,
  //├────────┼────────┼────────┼────────┼────────┼────────┼────────┼────────┐       ┌────────┼────────┼────────┼────────┼────────┼────────┼────────┼────────┤
      Shift  ,   ↑    , Nump 1 , Nump 2 , Nump 3 , Nump . ,  PgUp  ,  PgDn  ,         Home   ,  End   ,   *    ,   {    ,   }    ,   <    ,   >    ,   ~    ,
  //├────────┼────────┼────────┼────────┼────┬───┴────┬───┼────────┼────────┤       ├────────┼────────┼───┬────┴───┬────┼────────┼────────┼────────┼────────┤
        ←    ,   ↓    ,   →    , Nump 0 ,      Tab    ,    Nav/Spc , Enter  ,         TRANS  ,  Bksp  ,     AltGr  ,        ←    ,   ↓    ,   ↑    ,   →
  //└────────┴────────┴────────┴────────┘    └────────┘   └────────┴────────┘       └────────┴────────┘   └────────┘    └────────┴────────┴────────┴────────┘
  ),
*/
  [_SYMB] = LAYOUT(
  //┌────────┬────────┬────────┬────────┬────────┬────────┐                                           ┌────────┬────────┬────────┬────────┬────────┬────────┐
     RSA_SCL ,KC_EXLM ,KC_AT   ,KC_HASH ,KC_DLR  ,KC_PERC ,                                            KC_CIRC ,KC_AMPR ,KC_UNDS ,KC_EXLM ,KC_QUES ,_______ ,
  //├────────┼────────┼────────┼────────┼────────┼────────┼────────┐                         ┌────────┼────────┼────────┼────────┼────────┼────────┼────────┤
     KC_BSPC ,KC_PSLS ,KC_P7   ,KC_P8   ,KC_P9   ,KC_PMNS ,_______ ,                          _______ ,KC_PLUS ,KC_LBRC ,KC_RBRC ,KC_SLSH ,KC_BSLS ,KC_PIPE ,
  //├────────┼────────┼────────┼────────┼────────┼────────┼────────┤                         ├────────┼────────┼────────┼────────┼────────┼────────┼────────┤
     _______ ,KC_PAST ,KC_P4   ,KC_P5   ,KC_P6   ,KC_PLUS ,KC_COMM ,                          XXXXXXX ,KC_MINS ,KC_LPRN ,KC_RPRN ,KC_EQL  ,SF_GRV  ,KC_DQUO ,
  //├────────┼────────┼────────┼────────┼────────┼────────┼────────┼────────┐       ┌────────┼────────┼────────┼────────┼────────┼────────┼────────┼────────┤
     KC_LSFT ,KC_UP   ,KC_P1   ,KC_P2   ,KC_P3   ,KC_PDOT ,KC_PGUP ,KC_PGDN ,        KC_HOME ,KC_END  ,KC_ASTR ,KC_LCBR ,KC_RCBR ,KC_LT   ,KC_GT   ,KC_TILD ,
  //├────────┼────────┼────────┼────────┼────┬───┴────┬───┼────────┼────────┤       ├────────┼────────┼───┬────┴───┬────┼────────┼────────┼────────┼────────┤
     KC_LEFT ,KC_DOWN ,KC_RGHT ,KC_P0   ,     KC_TAB  ,    NAV_SPC ,KC_ENT  ,        _______ ,KC_BSPC ,    RALT_TP ,     KC_LEFT ,KC_DOWN ,KC_UP   ,KC_RGHT
  //└────────┴────────┴────────┴────────┘    └────────┘   └────────┴────────┘       └────────┴────────┘   └────────┘    └────────┴────────┴────────┴────────┘
  ),

  [_NAV] = LAYOUT(
  //┌────────┬────────┬────────┬────────┬────────┬────────┐                                           ┌────────┬────────┬────────┬────────┬────────┬────────┐
     XXXXXXX ,KC_F1   ,KC_F2   ,KC_F3   ,KC_F4   ,KC_F5   ,                                            KC_F6   ,KC_F7   ,KC_F8   ,KC_F9   ,KC_F10  ,KC_F11  ,
  //├────────┼────────┼────────┼────────┼────────┼────────┼────────┐                         ┌────────┼────────┼────────┼────────┼────────┼────────┼────────┤
     KC_DEL  ,LA_F4   ,MS_UP   ,MS_WHLL ,MS_WHLU ,MS_WHLR ,QK_BOOT ,                          XXXXXXX  ,XXXXXXX ,XXXXXXX ,XXXXXXX ,XXXXXXX ,KC_PSCR ,KC_F12 ,
  //├────────┼────────┼────────┼────────┼────────┼────────┼────────┤                         ├────────┼────────┼────────┼────────┼────────┼────────┼────────┤
     _______ ,MS_LEFT ,MS_DOWN ,MS_RGHT ,MS_WHLD ,KC_MPLY ,XXXXXXX ,                          XXXXXXX ,XXXXXXX ,XXXXXXX ,XXXXXXX ,XXXXXXX ,XXXXXXX ,XXXXXXX ,
  //├────────┼────────┼────────┼────────┼────────┼────────┼────────┼────────┐       ┌────────┼────────┼────────┼────────┼────────┼────────┼────────┼────────┤
     XXXXXXX ,KC_MPRV ,KC_MNXT ,KC_VOLD ,KC_VOLU ,KC_MUTE ,XXXXXXX ,XXXXXXX ,        XXXXXXX ,XXXXXXX ,XXXXXXX ,XXXXXXX ,XXXXXXX ,XXXXXXX ,XXXXXXX ,XXXXXXX ,
  //├────────┼────────┼────────┼────────┼────┬───┴────┬───┼────────┼────────┤       ├────────┼────────┼───┬────┴───┬────┼────────┼────────┼────────┼────────┤
     XXXXXXX ,XXXXXXX ,XXXXXXX ,XXXXXXX ,     XXXXXXX ,    _______ ,_______ ,        _______ ,_______ ,    XXXXXXX ,     XXXXXXX ,XXXXXXX ,XXXXXXX ,XXXXXXX
  //└────────┴────────┴────────┴────────┘    └────────┘   └────────┴────────┘       └────────┴────────┘   └────────┘    └────────┴────────┴────────┴────────┘
  ),

  [_ACCENT] = LAYOUT(
  //┌────────┬────────┬────────┬────────┬────────┬────────┐                                           ┌────────┬────────┬────────┬────────┬────────┬────────┐
     _______ ,_______ ,_______ ,_______ ,_______ ,_______ ,                                            _______ ,_______ ,_______ ,_______ ,_______ ,_______ ,
  //├────────┼────────┼────────┼────────┼────────┼────────┼────────┐                         ┌────────┼────────┼────────┼────────┼────────┼────────┼────────┤
     _______ ,_______ ,_______ ,CK_EACT ,_______ ,_______ ,_______ ,                          _______ ,_______ ,CK_UACT ,CK_IACT ,CK_OACT ,_______ ,_______ ,
  //├────────┼────────┼────────┼────────┼────────┼────────┼────────┤                         ├────────┼────────┼────────┼────────┼────────┼────────┼────────┤
     _______ ,CK_AACT ,_______ ,_______ ,_______ ,_______ ,_______ ,                          _______ ,_______ ,_______ ,_______ ,_______ ,_______ ,KC_QUOT ,
  //├────────┼────────┼────────┼────────┼────────┼────────┼────────┼────────┐       ┌────────┼────────┼────────┼────────┼────────┼────────┼────────┼────────┤
     _______ ,_______ ,_______ ,_______ ,_______ ,_______ ,_______ ,_______ ,        _______ ,_______ ,_______ ,_______ ,_______ ,_______ ,_______ ,_______ ,
  //├────────┼────────┼────────┼────────┼────┬───┴────┬───┼────────┼────────┤       ├────────┼────────┼───┬────┴───┬────┼────────┼────────┼────────┼────────┤
     _______ ,_______ ,_______ ,_______ ,     _______ ,    _______ ,_______ ,        _______ ,_______ ,    _______ ,     _______ ,_______ ,_______ ,_______
  //└────────┴────────┴────────┴────────┘    └────────┘   └────────┴────────┘       └────────┴────────┘   └────────┘    └────────┴────────┴────────┴────────┘
  ),

  [_ALTGR] = LAYOUT(
  //┌────────┬────────┬────────┬────────┬────────┬────────┐                                           ┌────────┬────────┬────────┬────────┬────────┬────────┐
     _______ ,_______ ,_______ ,_______ ,_______ ,_______ ,                                            _______ ,_______ ,_______ ,_______ ,_______ ,_______ ,
  //├────────┼────────┼────────┼────────┼────────┼────────┼────────┐                         ┌────────┼────────┼────────┼────────┼────────┼────────┼────────┤
     _______ ,_______ ,_______ ,_______ ,_______ ,_______ ,_______ ,                          _______ ,_______ ,_______ ,_______ ,_______ ,_______ ,_______ ,
  //├────────┼────────┼────────┼────────┼────────┼────────┼────────┤                         ├────────┼────────┼────────┼────────┼────────┼────────┼────────┤
     _______ ,_______ ,_______ ,_______ ,_______ ,_______ ,_______ ,                          _______ ,_______ ,_______ ,_______ ,_______ ,_______ ,_______ ,
  //├────────┼────────┼────────┼────────┼────────┼────────┼────────┼────────┐       ┌────────┼────────┼────────┼────────┼────────┼────────┼────────┼────────┤
     _______ ,_______ ,_______ ,_______ ,_______ ,_______ ,_______ ,_______ ,        _______ ,_______ ,CK_ENIE ,_______ ,_______ ,_______ ,_______ ,_______ ,
  //├────────┼────────┼────────┼────────┼────┬───┴────┬───┼────────┼────────┤       ├────────┼────────┼───┬────┴───┬────┼────────┼────────┼────────┼────────┤
     _______ ,_______ ,_______ ,_______ ,     _______ ,    _______ ,_______ ,        _______ ,_______ ,    _______ ,     _______ ,_______ ,_______ ,_______
  //└────────┴────────┴────────┴────────┘    └────────┘   └────────┴────────┘       └────────┴────────┘   └────────┘    └────────┴────────┴────────┴────────┘
  )
};

// ---------------------------------------------------------------------------
// Alt code helper: sends a Windows Alt code (OEM CP437, 3 digits)
// ---------------------------------------------------------------------------
static const uint16_t kp_keys[] = {KC_P0, KC_P1, KC_P2, KC_P3, KC_P4, KC_P5, KC_P6, KC_P7, KC_P8, KC_P9};

void send_alt_code(uint16_t code) {
    uint8_t saved_mods = get_mods();
    clear_mods();
    clear_oneshot_mods();
    register_code(KC_LALT);
    tap_code(kp_keys[(code / 100) % 10]);
    tap_code(kp_keys[(code / 10) % 10]);
    tap_code(kp_keys[code % 10]);
    unregister_code(KC_LALT);
    set_mods(saved_mods);
}

// ---------------------------------------------------------------------------
// Custom keycode handling
// ---------------------------------------------------------------------------
bool process_record_user(uint16_t keycode, keyrecord_t *record) {
    if (!record->event.pressed) return true;

    bool uppercase = (get_mods() & MOD_MASK_SHIFT) || host_keyboard_led_state().caps_lock;

    switch (keycode) {
        case CK_ENIE: send_alt_code(uppercase ? 165 : 164); return false;
        case CK_AACT: send_alt_code(uppercase ? 181 : 160); return false;
        case CK_EACT: send_alt_code(uppercase ? 144 : 130); return false;
        case CK_IACT: send_alt_code(uppercase ? 214 : 161); return false;
        case CK_OACT: send_alt_code(uppercase ? 224 : 162); return false;
        case CK_UACT: send_alt_code(uppercase ? 233 : 163); return false;
        case CK_IEXC: send_alt_code(173);                   return false;
        case CK_IQUE: send_alt_code(168);                   return false;
        case CK_DEGR: send_alt_code(167);                   return false;
    }
    return true;
}

// ---------------------------------------------------------------------------
// Tap Dance: Right Alt / AltGr Layer
// ---------------------------------------------------------------------------
typedef enum {
    TD_NONE,
    TD_SINGLE_TAP,
    TD_SINGLE_HOLD,
    TD_DOUBLE_TAP,
    TD_DOUBLE_HOLD
} td_state_t;

static td_state_t td_ralt_state = TD_NONE;

td_state_t cur_dance(tap_dance_state_t *state) {
    if (state->count == 1) {
        return state->pressed ? TD_SINGLE_HOLD : TD_SINGLE_TAP;
    } else if (state->count == 2) {
        return state->pressed ? TD_DOUBLE_HOLD : TD_DOUBLE_TAP;
    }
    return TD_NONE;
}

void td_ralt_finished(tap_dance_state_t *state, void *user_data) {
    td_ralt_state = cur_dance(state);
    switch (td_ralt_state) {
        case TD_SINGLE_HOLD:
            layer_on(_ALTGR);
            break;
        case TD_DOUBLE_TAP:
            tap_code(KC_RALT);
            break;
        case TD_DOUBLE_HOLD:
            register_code(KC_RALT);
            break;
        default:
            break;
    }
}

void td_ralt_reset(tap_dance_state_t *state, void *user_data) {
    switch (td_ralt_state) {
        case TD_SINGLE_HOLD:
            layer_off(_ALTGR);
            break;
        case TD_DOUBLE_HOLD:
            unregister_code(KC_RALT);
            break;
        default:
            break;
    }
    td_ralt_state = TD_NONE;
}

tap_dance_action_t tap_dance_actions[] = {
    [RALT_TD] = ACTION_TAP_DANCE_FN_ADVANCED(NULL, td_ralt_finished, td_ralt_reset),
};
