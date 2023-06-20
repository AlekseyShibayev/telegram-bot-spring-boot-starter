package com.company.app.core.tool.json.testEntity;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductDescription {

    private String composition;
    private String description;
}
