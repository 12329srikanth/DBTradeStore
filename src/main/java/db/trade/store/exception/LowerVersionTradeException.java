package db.trade.store.exception;

public class LowerVersionTradeException extends Exception {

	private static final long serialVersionUID = -2406475251953387868L;

	public LowerVersionTradeException(String message) {
		super(message);
	}
}
