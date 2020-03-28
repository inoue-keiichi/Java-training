package main.array.member;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import javax.swing.JTextField;
import javax.swing.TransferHandler;

import main.Autowired;
import main.PrintGenerator;
import main.value.ReflectionService;

public class InstanceField extends PrintGenerator {
	final ReflectionService reflectionService = Autowired.reflectionService;

	public final JTextField text;
	private String key = null;

	public InstanceField() {
		text = new JTextField(8);
		text.setTransferHandler(new InstanceTransferHandler(text));
	}

	private class InstanceTransferHandler extends TransferHandler {
		final JTextField field;

		public InstanceTransferHandler(final JTextField field) {
			this.field = field;
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
				key = (String) t.getTransferData(DataFlavor.stringFlavor);
				// テキストにインスタンスキーを表示する
				field.setText("${" + key + "}");
				execute();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			return true;
		}
	}

	@Override
	public void execute() throws Throwable {
		// インスタンスが配列かオブジェクトかを知らせる
		final Object instance = reflectionService.getInstances().get(this.key);
		if (instance instanceof Object[]) {
			reflectionService.setInstanceType("Array");
		} else {
			reflectionService.setInstanceType("Object");
		}

		this.notifyObservers();
	}

	@Override
	public String getLog() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
