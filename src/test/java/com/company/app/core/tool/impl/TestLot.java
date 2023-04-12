package com.company.app.core.tool.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestLot {

	private Long id;
	private String name;
	private String price;
	private String discount;
//	private Price price;
//	private List<Desire> desires;
}
