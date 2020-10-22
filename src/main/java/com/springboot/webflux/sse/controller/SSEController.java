package com.springboot.webflux.sse.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.webflux.sse.model.StockPrice;
import com.springboot.webflux.sse.service.StockPriceService;
import com.springboot.webflux.sse.utils.Utils;

import reactor.core.publisher.Flux;

@RestController
public class SSEController {

	private List<StockPrice> stockPriceList = new ArrayList<>();

	@Autowired
	Utils utils;

	@Autowired
	StockPriceService stockPriceService;

	@PostConstruct
	public void initializeStockObjects() {

		StockPrice stock1 = new StockPrice("Spring", utils.getRandomDoubleBetweenRange(2000, 10000),
				utils.getRandomDoubleBetweenRange(5, 15), utils.getRandomDoubleBetweenRange(2000, 10000),
				utils.getStatus());

		StockPrice stock2 = new StockPrice("Spring Boot", utils.getRandomDoubleBetweenRange(2000, 10000),
				utils.getRandomDoubleBetweenRange(5, 15), utils.getRandomDoubleBetweenRange(2000, 10000),
				utils.getStatus());

		StockPrice stock3 = new StockPrice("Spring WebFlux", utils.getRandomDoubleBetweenRange(2000, 10000),
				utils.getRandomDoubleBetweenRange(5, 15), utils.getRandomDoubleBetweenRange(2000, 10000),
				utils.getStatus());

		stockPriceList.add(stock1);
		stockPriceList.add(stock2);
		stockPriceList.add(stock3);
	}

	@RequestMapping(value = "/stockprice", method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<List<StockPrice>> getStockPrice() {
		return stockPriceService.getStockPriceData(stockPriceList);
	}

}
