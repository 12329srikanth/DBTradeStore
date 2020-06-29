package db.trade.store.util.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import db.trade.store.model.Trade;
import db.trade.store.util.TradeStoreUtil;

public class TradeStoreUtilTest {
	
	@Test
	public void testCreateTradeStore() {
		
		Map<String, List<Trade>> createTradeStore = TradeStoreUtil.createTradeStore();
		assertTrue(createTradeStore.size() == 3 );
		createTradeStore = null;
	}
	
	@Test
	public void testUpdateTradeStore() {
		
		Map<String, List<Trade>> createTradeStore = TradeStoreUtil.createTradeStore();
		Trade newTrade = new Trade("T4",1,"CP-4","B1",LocalDate.parse("25/05/2021",DateTimeFormatter.ofPattern("dd/MM/yyyy")),LocalDate.parse("29/06/2020",DateTimeFormatter.ofPattern("dd/MM/yyyy")),"N");
		List<Trade> newTrades = new ArrayList<Trade>();
		newTrades.add(newTrade);
		
		TradeStoreUtil.updateTradeStore("T4", newTrades);
		assertTrue(createTradeStore.size() == 4 );
		createTradeStore = null;
	}
}
