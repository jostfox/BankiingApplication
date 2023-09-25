package org.example.enums;

public enum Permissions {
    PERMISSIONS_USER("permission:user"),
    PERMISSIONS_ADMIN("permission:admin"),
    PERMISSIONS_MANAGER("permission:manager");

    private final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
