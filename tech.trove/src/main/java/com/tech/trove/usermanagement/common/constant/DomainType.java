package com.tech.trove.usermanagement.common.constant;

import lombok.Getter;

/**
 * The enum Domain type.
 */
@Getter
public enum DomainType {

    /**
     * Operations domain type.
     */
    OPERATIONS("Operations"),

    /**
     * The Admin.
     */
    ADMIN("Admin Ecommerce"),

    /**
     * Customer domain type.
     */
    CUSTOMER("Customer");

    private String title;

    DomainType(String title) {
        this.title = title;
    }

}
