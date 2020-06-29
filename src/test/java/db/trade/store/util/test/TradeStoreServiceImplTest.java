package db.trade.store.util.test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import db.trade.store.exception.LowerVersionTradeException;
import db.trade.store.model.Trade;
import db.trade.store.repository.TradeStoreRepository;
import db.trade.store.repository.TradeStoreRepositoryImpl;
import db.trade.store.service.TradeStoreService;
import db.trade.store.service.TradeStoreServiceImpl;
import db.trade.store.util.TradeStoreUtil;

public class TradeStoreServiceImplTest {

	/**
	 * 1.During transmission if the lower version is being received by the store it will reject the trade 
	 * and throw an exception
	 */
	@Test
	public void testUpdateTradeStoreForLowerVerionTrade() {
		 Map<String, List<Trade>> intitalTradeStore = TradeStoreUtil.createTradeStore();
		 Trade newTrade =  new Trade("T2",1,"CP-1","B1",LocalDate.parse("20/05/2021",DateTimeFormatter.ofPattern("dd/MM/yyyy")),LocalDate.parse("14/03/2015",DateTimeFormatter.ofPattern("dd/MM/yyyy")),"N");
		
		 TradeStoreRepository tradeStoreRepository = new TradeStoreRepositoryImpl();
		 TradeStoreService tradeStoreService = new TradeStoreServiceImpl(tradeStoreRepository);
		 
		 assertThrows(LowerVersionTradeException.class, ()->tradeStoreService.updateTradeStore(newTrade, intitalTradeStore));
	}
}
