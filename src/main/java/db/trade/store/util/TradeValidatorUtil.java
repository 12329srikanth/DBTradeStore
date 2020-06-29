package db.trade.store.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import db.trade.store.exception.LowerVersionTradeException;
import db.trade.store.model.Trade;

public class TradeValidatorUtil {

	/**
	 * During transmission if the lower version is being received by the store it will reject the
	 * trade and throw an exception. If the version is same it will override the existing record.
	 * @param newTrade has to validate
	 * @param tradeList updated List
	 * @return 
	 * @throws LowerVersionTradeException throws Exception if Lower Version trade received by store
	 */
	public static List<Trade> rejectTradeIfForLowerVerionAndUpdateIfSameVersionTradeReceived(Trade newTrade,
			List<Trade> tradeList) throws LowerVersionTradeException {
		Collections.sort(tradeList, (t1,t2)->t2.getVersion().compareTo(t1.getVersion()));
		if(tradeList.get(0).getVersion() == newTrade.getVersion()) {
			tradeList = new ArrayList<>();
			tradeList.set(0, newTrade);
		}
		if(tradeList.get(0).getVersion() > newTrade.getVersion()) {
			throw new LowerVersionTradeException("Trade_Id:"+tradeList.get(0).getTradeId()+" Received trade version:"+newTrade.getVersion()+" Which is lower than store trade verion:"+tradeList.get(0).getVersion());
		}else {
			System.out.println("This trade is successfully added in store.");
			tradeList.add(newTrade);
		}
		return tradeList;
	}

	/**
	 * Store should automatically update the expire flag if in a store the trade crosses the maturity date.
	 * @param intitalTradeStore trade store data
	 */
	public static void updateTradeExpireFlag(Map<String, List<Trade>> intitalTradeStore) {
		System.out.println("\n");
		Set<Entry<String, List<Trade>>> entrySet = intitalTradeStore.entrySet();
		for (Entry<String, List<Trade>> tradeEntry : entrySet) {
			List<Trade> trades = tradeEntry.getValue();
			
			ListIterator<Trade> listIterator = trades.listIterator();
			while (listIterator.hasNext()) {
				Trade trade = listIterator.next();
				if(trade.getMaturityDate().isBefore(LocalDate.now())) {
					System.out.println("Expire flag is updated for below in store:: ");
					trade.setExpired("Y");
					System.out.println(trade);
				}
			}
		}
	}
}
