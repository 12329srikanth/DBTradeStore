package db.trade.store.service;

import java.util.List;
import java.util.Map;

import db.trade.store.exception.LowerVersionTradeException;
import db.trade.store.model.Trade;

public interface TradeStoreService {
	
	public abstract Map<String,List<Trade>> createTradeStore();
	public abstract Map<String, List<Trade>> updateTradeStore(Trade newTrade, Map<String, List<Trade>> intitalTradeStore) throws LowerVersionTradeException;
}
