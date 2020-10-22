package com.springboot.webflux.sse.service;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.webflux.sse.model.StockPrice;
import com.springboot.webflux.sse.utils.Utils;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

@Service
public class StockPriceService {

	@Autowired
	Utils utilities;

	public Flux<List<StockPrice>> getStockPriceData(List<StockPrice> stockPriceList) {

		Flux<Long> interval = Flux.interval(Duration.ofSeconds(3));
		interval.subscribe((i) -> stockPriceList.forEach(stock -> setStockPrice(stock)));
		Flux<List<StockPrice>> transactionFlux = Flux.fromStream(Stream.generate(() -> stockPriceList));
		return Flux.zip(interval, transactionFlux).map(Tuple2::getT2);
	}

	private StockPrice setStockPrice(StockPrice stock) {
		stock.setPrice(utilities.getRandomDoubleBetweenRange(1000, 5000));
		stock.setValue(utilities.getRandomDoubleBetweenRange(1000, 5000));
		stock.setChange(utilities.getRandomDoubleBetweenRange(10, 50));
		stock.setStatus(utilities.getStatus());
		return stock;
	}

}
