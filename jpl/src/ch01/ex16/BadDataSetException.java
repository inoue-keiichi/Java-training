package ch01.ex16;

import java.io.FileInputStream;
import java.io.IOException;

class BadDataSetException extends Exception {
	public String fileName;
	public IOException exception;

	BadDataSetException(String fileName, IOException exception) {
		this.fileName = fileName;
		this.exception = exception;
	}

	BadDataSetException() {
	}

	public static void main(String[] args) {
		MyUtilities m = new MyUtilities();
		try {
			double[] dataSet = m.getDataSet("fileName");
		} catch (BadDataSetException e) {
			System.out.println(e.exception);
		}

	}
}

class MyUtilities {
	public double[] getDataSet(String setName) throws BadDataSetException {
		String file = setName + ".dest";
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			return readDataSet(in);
		} catch (IOException e) {
			throw new BadDataSetException(file, e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				;
			}
		}
	}

	private double[] readDataSet(FileInputStream in) {
		return null;
	}
}
