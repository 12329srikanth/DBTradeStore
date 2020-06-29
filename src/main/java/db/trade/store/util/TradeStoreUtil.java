package db.trade.store.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.trade.store.model.Trade;

public class TradeStoreUtil {

	private static Map<String,List<Trade>> tradeStoreMap = new HashMap<>();
	
	 static {
		 List<Trade> trades = new ArrayList<>();
		 
		 trades.add(new Trade("T1",1,"CP-1","B1",LocalDate.parse("20/05/2020",DateTimeFormatter.ofPattern("dd/MM/yyyy")),LocalDate.parse("29/06/2020",DateTimeFormatter.ofPattern("dd/MM/yyyy")),"N"));
		 tradeStoreMap.put("T1", trades);
		 
		 trades = new ArrayList<>();
		 trades.add(new Trade("T2",2,"CP-2","B1",LocalDate.parse("20/05/2021",DateTimeFormatter.ofPattern("dd/MM/yyyy")),LocalDate.parse("29/06/2020",DateTimeFormatter.ofPattern("dd/MM/yyyy")),"N"));
		 trades.add(new Trade("T2",1,"CP-1","B1",LocalDate.parse("20/05/2021",DateTimeFormatter.ofPattern("dd/MM/yyyy")),LocalDate.parse("14/03/2015",DateTimeFormatter.ofPattern("dd/MM/yyyy")),"N"));
		 
		 tradeStoreMap.put("T2", trades);
		 
		 trades = new ArrayList<>();
		 trades.add(new Trade("T3",3,"CP-3","B2",LocalDate.parse("20/05/2014",DateTimeFormatter.ofPattern("dd/MM/yyyy")),LocalDate.parse("29/06/2020",DateTimeFormatter.ofPattern("dd/MM/yyyy")),"Y"));
		 
		 tradeStoreMap.put("T3", trades); 
	 }
	 
	 /**
	  * @return In-Memory trade store 
	  */
	 public static Map<String,List<Trade>> createTradeStore(){
		 
		return tradeStoreMap;
	 }
	 
	 public static Map<String,List<Trade>> updateTradeStore(String tradeId,List<Trade> newTrades){
		tradeStoreMap.put(tradeId, newTrades);
		return tradeStoreMap;
		 
	 }
}
