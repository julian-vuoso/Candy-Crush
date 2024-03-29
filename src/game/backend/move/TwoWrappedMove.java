package game.backend.move;

import game.backend.Grid;

public class TwoWrappedMove extends Move {
	
	public TwoWrappedMove(Grid grid) {
		super(grid);
	}

	/* Se simplifico este metodo unificando las condiciones */
	@Override
	public void removeElements() {
		int currI = i1, currJ = j1;
		if (!(j1 < j2 || i1 < i2)) {
			currI = i2;
			currJ = j2;
		}
		if (i1 == i2){
			swipeGrid(currI, currJ, 1, 2);
		} else {
			swipeGrid(currI, currJ, 2, 1);
		}
	}

	/* Se agrego este metodo para simplificar el codigo
	*  original y evitar la repetición */
	private void swipeGrid (int currI, int currJ, int maxI, int maxJ){
		for (int i = Integer.max(currI - 1, 0); i <= Integer.min(currI + maxI, Grid.SIZE - 1); i++) {
			for (int j = Integer.max(currJ - 1, 0); j <= Integer.min(currJ + maxJ, Grid.SIZE - 1); j++) {
				clearContent(i, j);
			}
		}
	}
}
