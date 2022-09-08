package com.pavlenko.kyrylo.model.dao.impl.query;

public class BrandQueries {

    private BrandQueries(){}

    public static final String FIND_ALL_BRANDS =
            "SELECT * FROM brands";

    public static final String FIND_BRAND_BY_ID =
            FIND_ALL_BRANDS +
                    " WHERE id = ?";

}
