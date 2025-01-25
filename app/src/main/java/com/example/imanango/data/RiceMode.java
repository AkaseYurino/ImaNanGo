package com.example.imanango.data;

import com.example.imanango.R;

/**
 * ライスモード
 */
public enum RiceMode {
    NORMAL(1, R.string.rice_mode_name), MUSENMAI(2), GENMAI(3);

    private int id;
    private int titleResId;

    RiceMode(int id, int titleResId) {
        this.id = id;
        this.titleResId = titleResId;
    }

    public int getId() {
        return id;
    }

    public int getTitleResId() {
        return titleResId;
    }

    public RiceMode toRiceMode(int id) {

        switch (id) {
            case 1:
                return NORMAL;

            case 2:
                return MUSENMAI;

            case 3:
                return GENMAI;

            default:
                return NORMAL;
        }
    }
}
