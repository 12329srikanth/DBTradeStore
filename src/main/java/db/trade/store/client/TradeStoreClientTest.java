package db.trade.store.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import db.trade.store.exception.LowerVersionTradeException;
import db.trade.store.model.Trade;
import db.trade.store.repository.TradeStoreRepository;
import db.trade.store.repository.TradeStoreRepositoryImpl;
import db.trade.store.service.TradeStoreService;
import db.trade.store.service.TradeStoreServiceImpl;

public class TradeStoreClientTest {

	public static void main(String[] args) {
		
		System.out.println("----------Initial Trade store details----------");
		
		TradeStoreRepository tradeStoreRepository = new TradeStoreRepositoryImpl();
		TradeStoreService tradeStoreService = new TradeStoreServiceImpl(tradeStoreRepository);
		Map<String, List<Trade>> intitalTradeStore = tradeStoreService.createTradeStore();
		
		intitalTradeStore.forEach((tradeId,trades)->{
			System.out.println("Trade_Id:"+tradeId);
			System.out.println(trades);
		});
		
		System.out.println("\n\n");
		Trade newTrade;
	
		Map<String, List<Trade>> updateTradeStore = null;
		//CASE-1.This trade will throw Exception because received version is lower than Store Version
        System.out.println("--------Adding new trade in trade Store-------------");
		 newTrade =  new Trade("T2",1,"CP-1","B1",LocalDate.parse("20/05/2021",DateTimeFormatter.ofPattern("dd/MM/yyyy")),LocalDate.parse("14/03/2015",DateTimeFormatter.ofPattern("dd/MM/yyyy")),"N");
		 System.out.println(newTrade);
		try {
			 updateTradeStore = tradeStoreService.updateTradeStore(newTrade,intitalTradeStore);

		} catch (LowerVersionTradeException e) {
			System.out.println(e);
		}
		System.out.println("\n");
		//CASE-2.Store should not allow the trade which has less maturity date then today date.
		System.out.println("--------Adding new trade in trade Store-------------");
		newTrade = new Trade("T1",2,"CP-1","B1",LocalDate.parse("20/05/2020",DateTimeFormatter.ofPattern("dd/MM/yyyy")),LocalDate.parse("28/06/2020",DateTimeFormatter.ofPattern("dd/MM/yyyy")),"N");
		System.out.println(newTrade);
		try {
			updateTradeStore = tradeStoreService.updateTradeStore(newTrade,intitalTradeStore);
		} catch (LowerVersionTradeException e) {
			System.out.println(e);
		}
		
		System.out.println("\n");
		//CASE-3.This trade will be added in store because received version is grater than store version
		System.out.println("--------Adding new trade in trade Store-------------");
		newTrade = new Trade("T1",2,"CP-1","B1",LocalDate.parse("20/07/2020",DateTimeFormatter.ofPattern("dd/MM/yyyy")),LocalDate.parse("28/06/2020",DateTimeFormatter.ofPattern("dd/MM/yyyy")),"N");
		System.out.println(newTrade);
		try {
			updateTradeStore = tradeStoreService.updateTradeStore(newTrade,intitalTradeStore);
		} catch (LowerVersionTradeException e) {
			System.out.println(e);
		}
		
		System.out.println("\n");
	    System.out.println("----------Finally updated Trade store details----------");
			
		updateTradeStore.forEach((tradeId,trades)->{
			System.out.println("Trade_Id:"+tradeId);
			System.out.println(trades);
		});
	}
}
