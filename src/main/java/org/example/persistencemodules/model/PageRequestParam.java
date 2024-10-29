package org.example.persistencemodules.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageRequestParam {
    private int pageNumber;
    private int pageSize;
    private String sortBy;
    private String direction;
}
