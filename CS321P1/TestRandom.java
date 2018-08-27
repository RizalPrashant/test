
public class TestRandom {
public static void main(String[] args) {
	

	Cache cache = new Cache<>();
	cache.add("Book");
	cache.add("Copy");
	cache.add("Pen");
	cache.add("Book");
	cache.get("Copy");
	cache.getHitRate();
}
}
