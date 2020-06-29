package db.trade.store.repository;

import java.util.List;
import java.util.Map;

import db.trade.store.model.Trade;
import db.trade.store.util.TradeStoreUtil;

public class TradeStoreRepositoryImpl implements TradeStoreRepository {

	@Override
	public Map<String, List<Trade>> createTradeStore() {
		return TradeStoreUtil.createTradeStore();
	}

	@Override
	public Map<String, List<Trade>> updateTradeStoreRepository(String tradeId, List<Trade> tradeList) {
		return TradeStoreUtil.updateTradeStore(tradeId, tradeList);
	}
	
}
