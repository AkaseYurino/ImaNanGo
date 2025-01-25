package com.example.imanango.data;

/**
 * ライスモード
 */
public enum RiceMode {
    NORMAL(1), MUSENMAI(2), GENMAI(3);

    private int id;

    RiceMode(int id) {
        this.id = id;
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
