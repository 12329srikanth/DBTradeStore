package db.trade.store.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import db.trade.store.exception.LowerVersionTradeException;
import db.trade.store.model.Trade;
import db.trade.store.repository.TradeStoreRepository;
import db.trade.store.util.TradeValidatorUtil;

public class TradeStoreServiceImpl implements TradeStoreService {
	
	private TradeStoreRepository tradeStoreRepository;
	
	public TradeStoreServiceImpl(TradeStoreRepository tradeStoreRepository) {
		this.tradeStoreRepository = tradeStoreRepository;
	}

	@Override
	public Map<String, List<Trade>> createTradeStore() {
		return tradeStoreRepository.createTradeStore();
	}

	@Override
	public Map<String, List<Trade>> updateTradeStore(Trade newTrade, Map<String, List<Trade>> intitalTradeStore) throws LowerVersionTradeException {
		//Validation of Trades before updating into trade store
		
	    //Validation-2.Store should not allow the trade which has less maturity date then today date.
		if(newTrade.getMaturityDate().isBefore(LocalDate.now())) {
			System.out.println("As trade maturity date is less than today's date so trade is not going to add in store.");
			return intitalTradeStore;
		}
		
		List<Trade> tradeList = intitalTradeStore.get(newTrade.getTradeId());
		//if trade is new then update in store.
		if(tradeList == null) {
			tradeList = new ArrayList<Trade>();
			tradeList.add(newTrade);
		}else {
			/*Validation-1.The lower version is being received by the store it will reject the trade and throw an exception.
			If the version is same it will override/update the existing record*/
			tradeList = TradeValidatorUtil.rejectTradeIfForLowerVerionAndUpdateIfSameVersionTradeReceived(newTrade, tradeList);
		}
	
		//Validation-3.Store should automatically update the expire flag if in a store the trade crosses the maturity date.
		TradeValidatorUtil.updateTradeExpireFlag(intitalTradeStore);
		return tradeStoreRepository.updateTradeStoreRepository(newTrade.getTradeId(),tradeList);
	}
}
