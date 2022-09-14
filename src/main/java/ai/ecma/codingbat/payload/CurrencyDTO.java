package ai.ecma.codingbat.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.annotation.Generated;
import java.io.Serializable;

@Getter
public class CurrencyDTO implements Serializable {

	@JsonProperty("id")
	private int id;

	@JsonProperty("Code")
	private String code;

	@JsonProperty("Ccy")
	private String ccy;

	@JsonProperty("CcyNm_RU")
	private String ccyNmRU;

	@JsonProperty("CcyNm_UZ")
	private String ccyNmUZ;

	@JsonProperty("CcyNm_UZC")
	private String ccyNmUZC;

	@JsonProperty("CcyNm_EN")
	private String ccyNmEN;

	@JsonProperty("Nominal")
	private String nominal;

	@JsonProperty("Rate")
	private String rate;

	@JsonProperty("Diff")
	private String diff;

	@JsonProperty("Date")
	private String date;
}