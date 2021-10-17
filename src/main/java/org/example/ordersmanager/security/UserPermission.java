package org.example.ordersmanager.security;

public enum UserPermission {
    LIST_ALL_ORDERS("list all orders"),
    CREATE_ORDER("create orders"),
    DELETE_ANY_ORDER("delete any order"),
    LIST_OWN_ORDERS("list own orders");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
