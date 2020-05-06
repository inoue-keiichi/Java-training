package ch20.ex03;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class Main {
	public static void main(String[] args) {
		//String str = args[0];
		String str = "password";
		System.out.println("Origin");
		for (byte b : str.getBytes()) {
			System.out.print(b);
			System.out.print(" ");
		}
		System.out.println();

		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			// エンコード
			OutputStream eos = null;
			try {
				eos = new EncryptOutputStream(baos);
				for (byte b : str.getBytes()) {
					eos.write(b);
				}
				// エンコード結果表示
				System.out.println("Encrypt");
				for (byte b : baos.toByteArray()) {
					System.out.print(b);
					System.out.print(" ");
				}
				System.out.println();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (Objects.isNull(eos)) {
					return;
				}
				try {
					eos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			// デコード
			InputStream in = new ByteArrayInputStream(baos.toByteArray());
			InputStream dis = null;
			int c;
			try {
				dis = new DecryptInputStream(in);
				// デコード表示
				System.out.println("Decrypt");
				while ((c = dis.read()) != -1) {
					System.out.print(c);
					System.out.print(" ");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				System.out.println();
				if (Objects.isNull(dis)) {
					return;
				}
				try {
					dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} finally {
			if (Objects.isNull(baos)) {
				return;
			}
			try {
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
