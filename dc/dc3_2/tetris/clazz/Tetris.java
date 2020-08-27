package dc3_2.tetris.clazz;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class Tetris {
	private int[][] field = {
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
	};

	private final List<Tetrimino> tetriminos;

	public Tetris() {
		this.tetriminos = new ArrayList<>();
		final Tetrimino tetriminoI = new Tetrimino();
		final int[][][] minoI = {
				{
						{ 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }
				},
				{
						{ 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }
				}
		};
		tetriminoI.setMino(minoI);
		tetriminoI.setColor(Color.CYAN);
		this.tetriminos.add(tetriminoI);

		final Tetrimino tetriminoJ = new Tetrimino();
		final int[][][] minoJ = {
				{
						{ 0, 1, 0, 0 },
						{ 0, 1, 1, 1 },
						{ 0, 0, 0, 0 },
						{ 0, 0, 0, 0 },
				},
				{
						{ 0, 1, 1, 0 },
						{ 0, 1, 0, 0 },
						{ 0, 1, 0, 0 },
						{ 0, 0, 0, 0 },
				},
				{
						{ 1, 1, 1, 0 },
						{ 0, 0, 1, 0 },
						{ 0, 0, 0, 0 },
						{ 0, 0, 0, 0 }
				},
				{
						{ 0, 0, 1, 0 },
						{ 0, 0, 1, 0 },
						{ 0, 1, 1, 0 },
						{ 0, 0, 0, 0 }
				}
		};
		tetriminoJ.setMino(minoJ);
		tetriminoJ.setColor(Color.BLUE);
		this.tetriminos.add(tetriminoJ);

		final Tetrimino tetriminoL = new Tetrimino();
		final int[][][] minoL = {
				{
						{ 0, 1, 1, 1 },
						{ 0, 1, 0, 0 },
						{ 0, 0, 0, 0 },
						{ 0, 0, 0, 0 },
				},
				{
						{ 0, 1, 1, 0 },
						{ 0, 0, 1, 0 },
						{ 0, 0, 1, 0 },
						{ 0, 0, 0, 0 },
				},
				{
						{ 0, 0, 1, 0 },
						{ 1, 1, 1, 0 },
						{ 0, 0, 0, 0 },
						{ 0, 0, 0, 0 }
				},
				{
						{ 0, 1, 0, 0 },
						{ 0, 1, 0, 0 },
						{ 0, 1, 1, 0 },
						{ 0, 0, 0, 0 }
				}
		};
		tetriminoL.setMino(minoL);
		tetriminoL.setColor(Color.ORANGE);
		this.tetriminos.add(tetriminoL);

		final Tetrimino tetriminoO = new Tetrimino();
		final int[][][] minoO = {
				{
						{ 0, 1, 1, 0 },
						{ 0, 1, 1, 0 },
						{ 0, 0, 0, 0 },
						{ 0, 0, 0, 0 },
				}
		};
		tetriminoO.setMino(minoO);
		tetriminoO.setColor(Color.YELLOW);
		this.tetriminos.add(tetriminoO);

		final Tetrimino tetriminoS = new Tetrimino();
		final int[][][] minoS = {
				{
						{ 0, 1, 1, 0 },
						{ 1, 1, 0, 0 },
						{ 0, 0, 0, 0 },
						{ 0, 0, 0, 0 },
				},
				{
						{ 0, 1, 0, 0 },
						{ 0, 1, 1, 0 },
						{ 0, 0, 1, 0 },
						{ 0, 0, 0, 0 },
				}
		};
		tetriminoS.setMino(minoS);
		tetriminoS.setColor(Color.GREENYELLOW);
		this.tetriminos.add(tetriminoS);

		final Tetrimino tetriminoT = new Tetrimino();
		final int[][][] minoT = {
				{
						{ 0, 1, 0, 0 },
						{ 1, 1, 1, 0 },
						{ 0, 0, 0, 0 },
						{ 0, 0, 0, 0 },
				},
				{
						{ 0, 1, 0, 0 },
						{ 0, 1, 1, 0 },
						{ 0, 1, 0, 0 },
						{ 0, 0, 0, 0 },
				},
				{
						{ 0, 0, 0, 0 },
						{ 1, 1, 1, 0 },
						{ 0, 1, 0, 0 },
						{ 0, 0, 0, 0 },
				},
				{
						{ 0, 1, 0, 0 },
						{ 1, 1, 0, 0 },
						{ 0, 1, 0, 0 },
						{ 0, 0, 0, 0 },
				}
		};
		tetriminoT.setMino(minoT);
		tetriminoT.setColor(Color.BLUEVIOLET);
		this.tetriminos.add(tetriminoT);

		final Tetrimino tetriminoZ = new Tetrimino();
		final int[][][] minoZ = {
				{
						{ 0, 1, 1, 0 },
						{ 0, 0, 1, 1 },
						{ 0, 0, 0, 0 },
						{ 0, 0, 0, 0 },
				},
				{
						{ 0, 0, 1, 0 },
						{ 0, 1, 1, 0 },
						{ 0, 1, 0, 0 },
						{ 0, 0, 0, 0 },
				}
		};
		tetriminoZ.setMino(minoZ);
		tetriminoZ.setColor(Color.RED);
		this.tetriminos.add(tetriminoZ);
	}

	public int[][] getField() {
		return this.field;
	}

	public void setField(final int[][] field) {
		this.field = field;
	}

	public List<Tetrimino> getTetriminos() {
		return this.tetriminos;
	}
}
