
public class Main {

	public static void main(String[] args) {
		LetterCrush crush = new LetterCrush(6, 5, "BBAAAABACCCACABBCACAAAACBABBBC");
		Line line = crush.longestLine();
		System.out.println(crush);
		System.out.println(line);
		System.out.println(crush.remove(line));
		System.out.println(crush);

		crush.cascade();

		line = crush.longestLine();
		System.out.println(crush.toString());
		System.out.println(line);
		System.out.println(crush.remove(line));

		line = crush.longestLine();
		System.out.println(crush);
		System.out.println(line);
		System.out.println(crush.remove(line));
		
		line = crush.longestLine();
		System.out.println(crush);
		System.out.println(line);
		System.out.println(crush.remove(line));
		
		line = crush.longestLine();
		System.out.println(crush);
		System.out.println(line);
		System.out.println(crush.remove(line));
		
		System.out.println(crush);
		line = crush.longestLine();
		System.out.println(crush);
		System.out.println(line);
	}

}
