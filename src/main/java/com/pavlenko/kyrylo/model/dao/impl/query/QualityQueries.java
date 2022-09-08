package com.pavlenko.kyrylo.model.dao.impl.query;

public class QualityQueries {


    private QualityQueries() {
    }

    public static final String FIND_ALL_QUALITY_CLASSES =
            "SELECT * FROM quality_class";

    public static final String FIND_QUALITY_CLASS_BY_ID =
            FIND_ALL_QUALITY_CLASSES +
                    " WHERE id = ?";
}
