package database.characters;

public enum AttributePriority {
	HIGH(3),
	NORMAL(2),
	LOW(1);

	private int	factor;

	private AttributePriority(int factor) {
		this.factor = factor;
	}

	public int getFactor() {
		return this.factor;
	}

}
