package com.company.app.core.tool.impl.testEntity;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TestLot {

	private Long id;
	private String name;
	private String price;
	private String discount;
	private ProductDescription productDescription;
	private List<ProductProperty> productPropertiesList;
}
