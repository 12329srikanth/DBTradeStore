package db.trade.store.repository;

import java.util.List;
import java.util.Map;

import db.trade.store.model.Trade;

public interface TradeStoreRepository {
	public abstract Map<String,List<Trade>> createTradeStore();
	public abstract Map<String, List<Trade>> updateTradeStoreRepository(String tradeId, List<Trade> tradeList);
}
