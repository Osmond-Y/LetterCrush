
public class Line {
	private int[] start;
	private int[] end;
	
	public Line(int row, int col, boolean horizontal, int length) {
		start = new int[] {row, col};
		
		if (horizontal) {
			end = new int[] {row, col + length - 1};
		} else {
			end = new int[] {row + length - 1, col};
		}
	}
	
	public int[] getStart() {
		return new int[] {start[0], start[1]};
	}
	
	public int length() {
		if (start[0] == end[0]) {
			return end[1] - start[1] + 1;
		} else {
			return end[0] - start[0] + 1;
		}
	}
	
	public boolean isHorizontal() {
		return start[0] == end[0];
	}
	
	public boolean inLine(int row, int col) {
		if (isHorizontal()) {
			if (row >= start[0] && row <= end[0] && col == start[1]) {
				return true;
			}
		} else {
			if (col >= start[1] && col <= end[1] && row == start[0]) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		return String.format("Line:[%d, %d] - > [%d, %d]", start[0], start[1], end[0], end[1]);
	}
}
