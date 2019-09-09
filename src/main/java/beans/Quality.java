package beans;

public enum Quality {
	GOOD(1),
	MEDIUM(0),
	BAD(-1);

	private int degree;

	Quality(int degree) {
		this.degree = degree;
	}

	public boolean betterThan(Quality quality){
		return this.degree > quality.degree;
	}

}
