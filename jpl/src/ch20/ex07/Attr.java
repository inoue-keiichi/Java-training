package ch20.ex07;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class Attr {

	public static void main(String[] args) throws IOException {
		Attr attr = new Attr("name", 999L);
		attr.writeData("/Users/inoue-keiichi/git/Java-training/jpl/src/ch20/ex05/attr.txt");
		Attr attr2 = new Attr("/Users/inoue-keiichi/git/Java-training/jpl/src/ch20/ex05/attr.txt");
		System.out.println(attr2.name + ": " + attr2.value);
	}

	private String name;
	private Object value;

	public Attr(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public Attr(String file) throws IOException {
		String type;
		try (InputStream fin = new FileInputStream(file); DataInputStream in = new DataInputStream(fin);) {
			this.name = in.readUTF();
			type = in.readUTF();
			if (Objects.equals("boolean", type)) {
				this.value = in.readBoolean();
			} else if (Objects.equals("char", type)) {
				this.value = in.readChar();
			} else if (Objects.equals("byte", type)) {
				this.value = in.readByte();
			} else if (Objects.equals("short", type)) {
				this.value = in.readShort();
			} else if (Objects.equals("int", type)) {
				this.value = in.readInt();
			} else if (Objects.equals("long", type)) {
				this.value = in.readLong();
			} else if (Objects.equals("float", type)) {
				this.value = in.readFloat();
			} else if (Objects.equals("double", type)) {
				this.value = in.readDouble();
			} else if (Objects.equals("String", type)) {
				this.value = in.readUTF();
			} else {
				throw new IllegalArgumentException();
			}
		} catch (EOFException e) {
		}
	}

	public void writeData(final String file) throws IOException {
		OutputStream fout = new FileOutputStream(file);
		DataOutputStream out = new DataOutputStream(fout);
		out.writeUTF(this.name);
		if (Objects.isNull(this.value)) {
			out.close();
			return;
		} else if (this.value instanceof Boolean) {
			out.writeUTF("boolean");
			out.writeBoolean((boolean) this.value);
		} else if (this.value instanceof Character) {
			out.writeUTF("char");
			out.writeChar((char) this.value);
		} else if (this.value instanceof Byte) {
			out.writeUTF("byte");
			out.writeByte((byte) this.value);
		} else if (this.value instanceof Short) {
			out.writeUTF("short");
			out.writeShort((short) this.value);
		} else if (this.value instanceof Integer) {
			out.writeUTF("int");
			out.writeInt((int) this.value);
		} else if (this.value instanceof Long) {
			out.writeUTF("long");
			out.writeLong((long) this.value);
		} else if (this.value instanceof Float) {
			out.writeUTF("float");
			out.writeFloat((float) this.value);
		} else if (this.value instanceof Double) {
			out.writeUTF("double");
			out.writeDouble((double) this.value);
		} else if (this.value instanceof String) {
			out.writeUTF("String");
			out.writeUTF((String) this.value);
		} else {
			out.flush();
			out.close();
			throw new IllegalArgumentException();
		}
		out.flush();
		out.close();
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	public Object setValue(Object newValue) {
		Object oldVal = value;
		value = newValue;
		return oldVal;
	}

	public String toString() {
		return name + " ='" + value + "'";
	}
}
