package org.example.persistencemodules.repository.util;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import java.math.BigDecimal;
import org.springframework.data.jpa.domain.Specification;

/**
 * The SpecificationsUtil class represents the utility class for building Specifications.
 * This class is used to build Specifications for the search criteria. The search criteria can be a nested property.
 * The search criteria can be of type String, Integer, Long, BigDecimal.
 *
 * @author pgaikwad
 */

public class SpecificationsUtil {

    private String fieldName;
    private String searchStringValue;
    private Integer searchIntegerValue;
    private Long searchLongValue;
    private BigDecimal searchBigDecimalValue;

    private SpecificationsUtil(String fieldName) {
        this.fieldName = fieldName;
    }

    public static SpecificationsUtil forField(String fieldName) {
        return new SpecificationsUtil(fieldName);
    }

    public SpecificationsUtil withString(String searchStringValue) {
        this.searchStringValue = searchStringValue;
        return this;
    }

    public SpecificationsUtil withInteger(Integer searchIntegerValue) {
        this.searchIntegerValue = searchIntegerValue;
        return this;
    }

    public SpecificationsUtil withLong(Long searchLongValue) {
        this.searchLongValue = searchLongValue;
        return this;
    }

    public SpecificationsUtil withBigDecimal(BigDecimal searchBigDecimalValue) {
        this.searchBigDecimalValue = searchBigDecimalValue;
        return this;
    }

    public Specification<Object> build() {
        return (root, query, builder) -> {
            if (fieldName.contains(".")) {
                // If the field name contains ".", it implies a nested property
                String[] nestedFields = fieldName.split("\\.");
                Join<Object, Object> join = root.join(nestedFields[0], JoinType.INNER);
                for (int i = 1; i < nestedFields.length - 1; i++) {
                    join = join.join(nestedFields[i], JoinType.INNER);
                }

                if (searchStringValue != null) {
                    return builder.like(builder.lower(join.get(nestedFields[nestedFields.length - 1])),
                            "%" + searchStringValue.toLowerCase() + "%");
                } else if (searchIntegerValue != null) {
                    return builder.equal(join.get(nestedFields[nestedFields.length - 1]), searchIntegerValue);
                } else if (searchLongValue != null) {
                    return builder.equal(join.get(nestedFields[nestedFields.length - 1]), searchLongValue);
                } else if (searchBigDecimalValue != null) {
                    return builder.equal(join.get(nestedFields[nestedFields.length - 1]), searchBigDecimalValue);
                } else {
                    // Handle other types if needed
                    return null;
                }
            } else {
                // Non-nested property
                if (searchStringValue != null) {
                    return builder.like(builder.lower(root.get(fieldName)),
                            "%" + searchStringValue.toLowerCase() + "%");
                } else if (searchIntegerValue != null) {
                    return builder.equal(root.get(fieldName), searchIntegerValue);
                } else if (searchLongValue != null) {
                    return builder.equal(root.get(fieldName), searchLongValue);
                } else if (searchBigDecimalValue != null) {
                    return builder.equal(root.get(fieldName), searchBigDecimalValue);
                } else {
                    // Handle other types if needed
                    return null;
                }
            }
        };
    }
}
