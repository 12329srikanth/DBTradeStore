package db.trade.store.util.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import db.trade.store.model.Trade;
import db.trade.store.util.TradeStoreUtil;
import db.trade.store.util.TradeValidatorUtil;

public class TradeValidatorUtilTest {

	@Test
	public void testUpdateTradeExpireFlag() {
		Map<String, List<Trade>> intitalTradeStore = TradeStoreUtil.createTradeStore();
		TradeValidatorUtil.updateTradeExpireFlag(intitalTradeStore);
		Collection<List<Trade>> values = intitalTradeStore.values();
		
		List<Trade> tradeList = new ArrayList<Trade>();
		for (List<Trade> list : values) {
			tradeList.addAll(list);
		}
		
		int updatedExpireFlagCount = 0;
		
		for (Trade trade : tradeList) {
			if(trade.getExpired().equals("Y")) {
				updatedExpireFlagCount++;
			}
		}
		
		assertEquals(2, updatedExpireFlagCount);
	}
}
