
public class LetterCrush {
	private char[][] grid;

	public static final char EMPTY = ' ';

	public LetterCrush(int width, int height, String initial) {
		grid = new char[height][width];

		int index = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (index < initial.length()) {
					grid[i][j] = initial.charAt(index++);
				} else {
					grid[i][j] = EMPTY;
				}
			}
		}
	}

	public String toString() {
		String output = "LetterCrush\n";
		for (int i = 0; i < grid.length; i++) {
			output += "|";
			for (int j = 0; j < grid[0].length; j++) {
				output += grid[i][j];
			}
			output += "|" + i + "\n";
		}
		output += "+";
		for (int j = 0; j < grid[0].length; j++) {
			output += j;
		}
		output += "+";
		return output;
	}

	public boolean isStable() {
		for (int i = 1; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == EMPTY && grid[i - 1][j] != EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	public void applyGravity() {
		for (int i = grid.length - 1; i >= 1; i--) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == EMPTY) {
					grid[i][j] = grid[i - 1][j];
					grid[i - 1][j] = EMPTY;
				}
			}
		}
	}

	public boolean remove(Line theLine) {
		int[] start = theLine.getStart();
		int[] end = new int[2];
		int length = theLine.length();
		if (theLine.isHorizontal()) {
			end[0] = start[0];
			end[1] = start[1] + length - 1;
		} else {
			end[0] = start[0] + length - 1;
			end[1] = start[1];
		}

		if (start[0] < 0 || start[1] < 0 || start[0] >= grid.length || start[1] >= grid[0].length) {
			return false;
		}
		if (end[0] < 0 || end[1] < 0 || end[0] >= grid.length || end[1] >= grid[0].length) {
			return false;
		}
		for (int i = start[0]; i <= end[0]; i++) {
			for (int j = start[1]; j <= end[1]; j++) {
				grid[i][j] = EMPTY;
			}
		}
		return true;
	}

	public String toString(Line theLine) {
		int[] start = theLine.getStart();
		int[] end = new int[2];
		int length = theLine.length();
		if (theLine.isHorizontal()) {
			end[0] = start[0];
			end[1] = start[1] + length - 1;
		} else {
			end[0] = start[0] + length - 1;
			end[1] = start[1];
		}

		String output = "LetterCrush\n";
		for (int i = 0; i < grid.length; i++) {
			output += "|";
			for (int j = 0; j < grid[0].length; j++) {
				if (i >= start[0] && i <= end[0] && j >= start[1] && j <= end[1]) {
					output += Character.toLowerCase(grid[i][j]);
				} else {
					output += grid[i][j];
				}
			}
			output += "|" + i + "\n";
		}
		output += "+";
		for (int j = 0; j < grid[0].length; j++) {
			output += j;
		}
		output += "+";
		return output;
	}

	public Line longestLine() {
		Line longLine = new Line(0, 0, true, 1);
		int largest = 0;
		for (int i = grid.length - 1; i >= 0; i--) {
			char letter = grid[i][0];
			int adjacent = 1;
			for (int j = 1; j < grid[0].length; j++) {
				if (grid[i][j] != EMPTY && grid[i][j] == letter) {
					adjacent++;

					if (adjacent > largest) {
						largest = adjacent;
						longLine = new Line(i, j - adjacent + 1, true, adjacent);
					}
				} else {
					letter = grid[i][j];
					adjacent = 1;
				}
			}
		}

		for (int j = 0; j < grid[0].length; j++) {
			char letter = grid[grid.length - 1][j];
			int adjacent = 1;
			for (int i = grid.length - 2; i >= 0; i--) {
				if (grid[i][j] != EMPTY && grid[i][j] == letter) {
					adjacent++;
					if (adjacent > largest) {
						largest = adjacent;
						longLine = new Line(i, j, false, adjacent - 1);
					}
				} else {
					letter = grid[i][j];
					adjacent = 1;
				}
			}
		}
		if (longLine.length() > 2) {
			return longLine;
		} else {
			return null;
		}

	}
	
	public void cascade() {
		Line line;
		while ((line = longestLine()) != null) {
			remove(line);
			applyGravity();
		}
		while (!isStable()) {
			System.out.println(toString());
			applyGravity();
		}
	}
}
