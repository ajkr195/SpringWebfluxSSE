package com.springboot.webflux.sse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StockPrice {

	private String companyName;
	private String price;
	private String change;
	private String value;
	private String status;

}
