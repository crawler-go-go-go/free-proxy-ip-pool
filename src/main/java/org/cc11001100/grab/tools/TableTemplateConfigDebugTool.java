package org.cc11001100.grab.tools;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;

/**
 * 表格布局配置调试工具
 * <p>
 * 暂时搁置
 *
 * @author CC11001100
 */
public class TableTemplateConfigDebugTool extends Application {

	// 输入配置文件
	private JTextArea input;
	// 输出抓取到的内容
	private JTextArea output;

	@Override
	public void start(Stage stage) throws Exception {
		URL url = getClass().getClassLoader().getResource("table-template-debug-tool.fxml");
		assert url != null;
		Parent root = FXMLLoader.load(url);

		Scene scene = new Scene(root);
		stage.setTitle("表格模板调试工具");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {

		launch(args);

	}


}
