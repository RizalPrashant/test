
public class PairOfDice {

	public Die die1;
	public Die die2;

	public PairOfDice(int sides) {
		this.die1 = new Die(sides);
		this.die2 = new Die(sides);
	}

	public PairOfDice(int sides, long seed1, long seed2) {
		this.die1 = new Die(sides, seed1);
		this.die2 = new Die(sides, seed2);
	}

	public int getFaceValue1() {

		return die1.getFaceValue();

	}

	public int getFaceValue2() {
		return die2.getFaceValue();
	}

	public int getTotal() {
		return die1.getFaceValue() + die2.getFaceValue();
	}

	public int roll() {
		int rollTotal = die1.roll() + die2.roll();
		return rollTotal;
	}

	public String toString() {
		return (getTotal() + "/(" + die1.getFaceValue() + "+" + die2.getFaceValue()+")");
	}
}
