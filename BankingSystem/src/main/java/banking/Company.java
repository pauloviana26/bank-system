package banking;

import java.util.Random;

public class Company extends AccountHolder {
	private final String companyName;
	private final int taxId;

	private static final Random random = new Random();

	public Company(String companyName, int taxId) {
		super(random.nextInt());
		this.companyName = companyName;
		this.taxId = taxId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public int getTaxId() {
		return taxId;
	}
}
