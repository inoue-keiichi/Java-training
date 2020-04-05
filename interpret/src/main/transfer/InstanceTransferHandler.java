package main.transfer;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.Objects;

import javax.swing.JTextField;
import javax.swing.TransferHandler;

import main.generator.PrintGenerator;

public class InstanceTransferHandler extends TransferHandler {
	final JTextField field;
	final PrintGenerator instanceField;

	public InstanceTransferHandler(final PrintGenerator instanceField, final JTextField field) {
		this.field = field;
		this.instanceField = instanceField;
	}

	/**
	 * ドロップされたものを受け取るか判断 (ファイルのときだけ受け取る)
	 */
	@Override
	public boolean canImport(TransferSupport support) {
		if (!support.isDrop()) {
			// ドロップ操作でない場合は受け取らない
			return false;
		}

		if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			// ドロップされたのがStringでない場合は受け取らない
			return false;
		}

		return true;
	}

	/**
	 * ドロップされたインスタンス名を受け取る
	 */
	@Override
	public boolean importData(TransferSupport support) {
		// 受け取っていいものか確認する
		if (!canImport(support)) {
			return false;
		}

		// ドロップ処理
		Transferable t = support.getTransferable();
		try {
			// instanceKeyを受け取る
			String key = (String) t.getTransferData(DataFlavor.stringFlavor);
			// テキストにインスタンスキーを表示する
			field.setText("${" + key + "}");
			// PrintGeneratorを継承したクラスの場合はexecute()を実行する
			if (Objects.nonNull(instanceField)) {
				instanceField.execute();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return true;
	}
}