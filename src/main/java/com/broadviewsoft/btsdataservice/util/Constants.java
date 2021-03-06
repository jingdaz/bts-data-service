package com.broadviewsoft.btsdataservice.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Constants {
	private static Log logger = LogFactory.getLog(Constants.class);

	public final static long DEFAULT_ACCOUNT_NUMBER = 93345;
	public final static double COMMISSION_FEE = 4.95;

	public final static boolean PROD_MODE = false;

	public final static String DOB_PATTERN = "mm/dd/yyyy";
	public final static String HOLIDAY_PATTERN = "mm/dd/yyyy";

	public final static String FILENAME_CONNECTOR = "_0020";
	public final static String CSV_SEPARATOR = ",";
	public final static String PATH_SEPARATOR = "/";
	public final static String STOCK_DATA_FILE_EXTENSION = ".csv";
	public final static String STOCK_DATA_FILE_GF_PREFIX = "GF_";
	public final static String STOCK_DATA_FILE_BVS_PREFIX = "";

//	public final static String STOCK_DATA_FILE_HEADER = "Timestamp,Open,High,Low,Close";
	public final static String STOCK_DATA_FILE_HEADER = "Date,Open,High,Low,Close";
//	public final static String CSV_FILE_HEADER ="Timestamp,Open,High,Low,Close,Rsi,Cci,Volume";
	public static final String NEW_FILE_HEADER = "Timestamp,Open,High,Low,Close,Rsi,Cci";

	public final static int MINUTE_IN_SECONDS = 60;
	public final static long MINUTE_IN_MILLI_SECONDS = 60 * 1000;
	public final static long HOUR_IN_MILLI_SECONDS = 60 * 60 * 1000;
	public final static long DAY_IN_MILLI_SECONDS = 24 * 60 * 60 * 1000;

	// 9:30 AM
	public final static long MARKET_OPEN_TIME = 34200000;
	// 4:00 PM
	public final static long MARKET_CLOSE_TIME = 57600000;

	public final static int STATUS_QUEUE_DEPTH = 4;
	public final static int STATUS_INTERVAL = 36;
	public final static int DEFAULT_QUANTITY = 10;

	/* RSI oversold */
	public static final double RSI_OVERSOLD_LIMIT = 30;

	/* RSI overbought */
	public static final double RSI_OVERBOUGHT_LIMIT = 70;

	public static final int CHART_CCI_SHOW_HISTORY = 48;

	/* RSI Range */
	public static final double RSI_MAX_LIMIT = 100;
	public static final double RSI_MIN_LIMIT = 0;

	/* CCI Range */
	public static final double CCI_MAX_LIMIT = 300;
	public static final double CCI_MIN_LIMIT = -300;

	public final static double CCI_TOP_DIVERGENCE = 100;
	public final static double CCI_BOTTOM_DIVERGENCE = -100;

	public final static double CCI_WEAKEST_LIMIT = 25;
	public final static double CCI_STRONGEST_LIMIT = -25;

	/* CCI should be < 200 when considering a sell from over-bought */
	public static final double CCI_TOP_SELL_LIMIT = 200;

	/* CCI should be > -120 when considering a buy from over-sold */
	public static final double CCI_BOTTOM_BUY_LIMIT = -120;

	/* CCI picks up with big slope */
	public static final double CCI_ZERO_AXIS_LIMIT = 200;

	/* Cross up CCI difference factor limit */
	public final static double CCI_CROSS_UP_DIFF_FACTOR_LIMIT = 1.10;

	public final static double CCI_CROSS_DOWN_DIFF_FACTOR_LIMIT = 1.10;

	/* Super low open */
	public static final int MARKET_OPEN_TIME_IN_MINS = 570;
	public static final int CCI_SUPER_OPEN_END_TIME = 575;
	public static final int CCI_SUPER_OPEN_LIMIT_TIME = 630;
	public static final double CCI_SUPER_LOW_LIMIT = -270;
	/* Super high open */
	public static final double CCI_SUPER_HIGH_LIMIT = 270;

	/* Alert */
	public static final double STOCK_PRICE_UP_ALERT = 0.02;
	public static final double STOCK_PRICE_DOWN_ALERT = -0.02;

	/* CCI Constant */
	public static final double CCI_FACTOR = 0.015;
	public static final double RSI_FACTOR = 100;
	public static final int RSI_INTERVAL = 14;
	public static final int CCI_INTERVAL = 20;

	/* Valid CCI difference limit to avoid cross-day */
	public static final double CCI_DIFF_LIMIT = 250;

	/* Price cross up or down */
	public static final double PRICE_CROSS_UP_PRELOW_FACTOR = -0.06;
	public static final double PRICE_CROSS_UP_CURITEM_FACTOR = 0.05;
	public static final long PRICE_CROSS_UP_TIME_INTERVAL = 60 * 60 * 1000;

	public static final double PRICE_CROSS_DOWN_PREHIGH_FACTOR = 0.05;
	public static final double PRICE_CROSS_DOWN_CURITEM_FACTOR = -0.04;
	public static final long PRICE_CROSS_DOWN_TIME_INTERVAL = 60 * 60 * 1000;

	public final static String[] MARKET_HOLIDAYS = { "01/01/2020", "01/20/2020", "02/17/2020", "04/10/2020",
			"05/25/2020", "07/03/2020", "09/07/2020", "11/26/2020", "12/25/2020" };
	public final static String[] MARKET_HALF_DAYS = { "12/24/2020" };

	public final static String[] STOCKS_WITH_DATA = { "AAPL"/* , "NUGT" */ };
	public final static int INIT_CASH_AMOUNT = 20000;
	public final static String[] INIT_STOCK_SYMBOLS = { "AAPL"/* , "NUGT" */ };
	public final static int[] INIT_STOCK_VOLUMES = { 0, 0 };

	public final static double PRICE_HIGHER_FACTOR = 1.01;
	public final static double PRICE_LOWER_FACTOR = 0.99;

	public final static double LOCKWIN_PRE_HIGH_FACTOR = 0.995;
	public final static double LOCKWIN_PRE_CLOSE_FACTOR = 1.02;
	public final static double LOCKWIN_CUR_OPEN_FACTOR = 1.005;
	public final static double STOPLOSS_CUR_OPEN_FACTOR = 0.99;
	public static final boolean OVERNIGHT_ONLY = false;

	// Hour in day (0-23)
	public final static String STOCK_PRICE_TIMESTAMP_PATTERN = "M/d/yyyy h:mm:ss a";
	public final static DateFormat STOCK_PRICE_TIMESTAMP_FORMATTER = new SimpleDateFormat(
			STOCK_PRICE_TIMESTAMP_PATTERN);

	// public final static String MARKET_START_TIME = "09:30 AM";
	// public final static String MARKET_END_TIME = "04:00 PM";

	// Percentage pattern
	public final static String PERCENTAGE_PATTERN = "#.#%";
	public final static NumberFormat PERCENTAGE_FORMATTER = new DecimalFormat(PERCENTAGE_PATTERN);
	
	public static final String UNIX_TIMESTAMP_PREFIX = "a";

	public final static String MARKET_HOURS_PATTERN = "HH:mm";
	public final static DateFormat MARKET_HOURS_FORMATTER = new SimpleDateFormat(MARKET_HOURS_PATTERN);

	public final static String TRADE_DATE_PATTERN = "MM/dd/yyyy hh:mm:ss a";
	public final static DateFormat TRADE_DATE_FORMATTER = new SimpleDateFormat(TRADE_DATE_PATTERN);
	public final static DateFormat HOLIDAY_DATE_FORMATTER = new SimpleDateFormat(HOLIDAY_PATTERN);

	public final static String TX_DATE_PATTERN = "M/d/yy HH:mm";
	public final static DateFormat TX_DATE_FORMATTER = new SimpleDateFormat(TX_DATE_PATTERN);

	public final static String STOCK_PRICE_PATTERN = "###0.00";
	public final static NumberFormat STOCK_PRICE_FORMATTER = new DecimalFormat(STOCK_PRICE_PATTERN);

	// public final static String STOCK_VOLUME_PATTERN = "#,##0";
	public final static String STOCK_VOLUME_PATTERN = "0";
	public final static NumberFormat STOCK_VOLUME_FORMATTER = new DecimalFormat(STOCK_VOLUME_PATTERN);

	
	
	public final static String HISTORY_DATA_FILE_PATH = "C:/workspaces/daytrader/DayTrader/resources/rawdata/AAPL/2020/";
	// sample link -
	// http://www.google.com/finance/getprices?p=1d&f=d,o,h,l,c,v&df=cpct&i=60&q=UVXY
	public final static String HISTORY_DATA_GOOGLE_SITE = "http://www.google.com/finance/getprices?p=1d&f=d,o,h,l,c,v&df=cpct";

	public static final double[] PREDICT_OPEN_FACTORS = { 0.10, 0.05, 0.02, 0, -0.02, -0.05, -0.10 };

	public static final double PROTECTION_STOP_PRICE = 0.98;

	public static final double PROTECTION_LIMIT_PRICE = 0.98;

	public static final double STOP_ORDER_TRAILING_FACTOR = 1.02;
	public static final double STOP_ORDER_LOCKWIN_FACTOR = 1.03;

	public static final boolean HUMAN_STRATEGY_ENABLED = true;

	/* Constants that are used for AlphaVantage data */
	public static final String ALPHAVANTAGE_JSON_TIMESTAMP_PATTERN = "yyyy-mm-dd hh:mm:ss";
	public static final String ALPHAVANTAGE_CSV_DAYWEEK_TIMESTAMP_PATTERN = "yyyy-MM-dd";
	public static final String ALPHAVANTAGE_CSV_INTRADAY_TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public static final String TIME_SERIERS_OLD = "\"Time Series\": {";
	public static final String TIME_SERIERS_NEW = "\"Time Series\": [";
	// timestamp value
	public static final String TIMESTAMP_PREFIX_OLD = "        \"2020";
	public static final String TIMESTAMP_PREFIX_NEW = "        {   \"0. timestamp\": \"2020";
	// timestamp value
	public static final String TIMESTAMP_VALUE_OLD = ":00\": {";
	public static final String TIMESTAMP_VALUE_NEW = ":00\",";
	// timeseries bracket ending
	public static final String TIME_SERIES_BRACKET_ENDING_OLD = "    },";
	public static final String TIME_SERIES_BRACKET_ENDING_NEW = "    BRACKETCOLON]";
	// timeseries bracket ending
	public static final String TIME_SERIES_TAB_BRACKET_OLD = "        }";
	public static final String TIME_SERIES_TAB_BRACKET_NEW = "        CUREBRACKET";
	// timeseries array ending
	public static final String TIME_SERIES_ENDING_OLD = "    }";
	public static final String TIME_SERIES_ENDING_NEW = "    ]";
	// timeseries unified style
	public static final String LEFT_BRACKET_OLD = "(";
	public static final String LEFT_BRACKET_NEW = "LEFTBRACKET";
	public static final String RIGHT_BRACKET_OLD = ")";
	public static final String RIGHT_BRACKET_NEW = "RIGHTBRACKET";
	public static final String TIME_SERIES_PERIOD_OLD = "Time Series LEFTBRACKET\\d{1,2}minRIGHTBRACKET";
	public static final String TIME_SERIES_PERIOD_NEW = "Time Series";

	public static List<Date> MARKET_CLOSE_DAYS = new ArrayList<Date>();
	public static List<Date> MARKET_CLOSE_EARLY_DAYS = new ArrayList<Date>();

	static {
		for (int i = 0; i < MARKET_HOLIDAYS.length; i++) {
			try {
				MARKET_CLOSE_DAYS.add(Constants.HOLIDAY_DATE_FORMATTER.parse(MARKET_HOLIDAYS[i]));
			} catch (ParseException e) {
				logger.error("Error when parsing market holidays.");
			}
		}
		for (int i = 0; i < MARKET_HALF_DAYS.length; i++) {
			try {
				MARKET_CLOSE_EARLY_DAYS.add(Constants.HOLIDAY_DATE_FORMATTER.parse(MARKET_HALF_DAYS[i]));
			} catch (ParseException e) {
				logger.error("Error when parsing market half-days.");
			}
		}
	}
}
