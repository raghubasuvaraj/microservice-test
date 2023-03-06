package com.sta.settings.enums;

public enum CommonStatus {
    PENDING(0, "Pending"), INPROGRESS(1, "In-progress"), APPROVED(2, "approved"),
    HOLD(3, "Hold"),REJECTED(4, "Rejected"),DELETE(5, "Deleted");
    private int code;
    private String value;

    CommonStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public CommonStatus getRoleFromRoleCode(int code) {
        for (CommonStatus enumObj : CommonStatus.values()) {
            if (enumObj.code == code) {
                return enumObj;
            }
        }
        return CommonStatus.PENDING;
    }

    public CommonStatus getRoleFromRoleValue(String role) {
        for (CommonStatus enumObj : CommonStatus.values()) {
            if (enumObj.value.equalsIgnoreCase(role)) {
                return enumObj;
            }
        }
        return CommonStatus.PENDING;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
