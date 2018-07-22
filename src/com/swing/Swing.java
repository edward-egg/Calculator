package com.swing;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.code.mathWork;
import com.bean.Question;

/**
 * @author 易正元  201540704671 java3
 */
public class Swing {

	mathWork homework = new mathWork();
	List<Question> list;
	boolean isShowAnswers = false;
	MyJPanel jPanel;

	/**
	* 定义边框
	* */
	public void start() {

		JFrame frame = new JFrame("数学题");
		frame.setSize(300, 250);
		frame.setLocationRelativeTo(frame);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		placeComponents(panel);
		frame.add(panel);

		frame.setVisible(true);
	}
	/**
	 * 按钮与输入框
	 * */
	private void placeComponents(JPanel panel) {

		panel.setLayout(null);

		JLabel questionNum = new JLabel("生成数量");
		JTextField textField = new JTextField(20);
		JButton button = new JButton("生成");

		questionNum.setBounds(10, 20, 80, 25);
		panel.add(questionNum);

		textField.setBounds(80, 20, 100, 25);
		panel.add(textField);

		//设置键盘监听器
		textField.addKeyListener(new java.awt.event.KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				int k = e.getKeyCode();
				if (k == e.VK_ENTER) {
					button.doClick();
				}
			}
		});

		button.setBounds(90, 80, 80, 25);
		panel.add(button);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = textField.getText();
				if (text != null) {
					int qNum = Integer.valueOf(text);
					showNewWindow(qNum);
				}

			}
		});
	}

	private void showNewWindow(int qNum) {
		if (jPanel == null) {
			jPanel = new MyJPanel(qNum);
		} else {
			jPanel.dispose();
			list.clear();
			isShowAnswers = false;
			jPanel = new MyJPanel(qNum);
		}
	}

	/**
	 * 生成不带答案的数学题
	 */
	private void createQuestions(int number) {
		list = homework.calculate(number);
	}

	/**
	 * 生成带答案的数学题
	 */
	private String getWithAnswers() {
		String qList = "";
		for (int i = 0; i < list.size(); i++) {
			if ((i + 1) % 3 != 0) {
				qList += i + 1 + ": " + list.get(i).withAns() + "  |  ";
			} else {
				qList += i + 1 + ": " + list.get(i).withAns() + "\n";
			}

		}
		return qList;
	}

	/**
	 * 生成不带答案的数学题
	 */
	private String getWithoutAnswers() {
		String qList = "";
		
		for (int i = 0; i < list.size(); i++) {
			if ((i + 1) % 3 != 0) {
				qList += i + 1 + ": " + list.get(i).withoutAns() + "  |  ";
			} else {
				qList += i + 1 + ": " + list.get(i).withoutAns() + "\n";
			}
		}
		return qList;
	}

	private class MyJPanel extends JFrame {

		public MyJPanel(int number) throws HeadlessException {

			JPanel p1 = new JPanel(new GridLayout(1, 0, 15, 15));
			JPanel p3 = new JPanel(new GridLayout(1, 0));
			JTextArea textArea1 = new JTextArea();
			JScrollPane scrollPane1 = new JScrollPane(textArea1);
			JButton button = new JButton("答案");
            button.setBounds(90, 80, 80, 25);

			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (isShowAnswers) {
						textArea1.setText(getWithoutAnswers());
						isShowAnswers = false;
					} else {
						textArea1.setText(getWithAnswers());
						isShowAnswers = true;
					}

				}
			});

			createQuestions(number);
			textArea1.setText(getWithoutAnswers());
			textArea1.setEditable(false);
			p1.add(scrollPane1);
			p3.add(button);

			add(p1);
			add(p3);
			setLayout(new GridLayout(2, 1));
			setTitle("数学题");
			setSize(400, 400);
			setVisible(true);
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		}

	}

}