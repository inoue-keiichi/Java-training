package main;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

import main.value.ReflectionService;

public class ArgText extends JTextField {
	final ReflectionService reflectionService = ReflectionService.getInstance();

	public ArgText(final int num) {
		super(num);
		// this.setTransferHandler(new TransferHandler("text"));
		this.setTransferHandler(new InstanceTransferHandler(this));
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
		 * ドロップされたファイルを受け取る
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
				// final Object instance = reflectionService.getInstances().get(key);
				field.setText("${" + key + "}");
			} catch (UnsupportedFlavorException | IOException e) {
				e.printStackTrace();
			}
			return true;
		}
	}

}
