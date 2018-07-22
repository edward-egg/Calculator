package com.code;

import java.util.ArrayList;
import java.util.List;
import com.bean.Question;

/**
 * @author 易正元  201540704671 java3
 */
public class mathWork {
	int num1;
	int num2;
	double operator;
	int result;
	List<Question> list = new ArrayList<>();//题目
	List<Integer> answers = new ArrayList<>();//存储答案

	/**
	 *生成数学题
	 */
	public List<Question> calculate(int qNum) {

		for (;;) {
			num1 = (int) (Math.random() * 100);
			num2 = (int) (Math.random() * 100);
			operator = Math.random();
			boolean isNotRepeat;

			if (Math.rint(operator) == 0) {
				result = num1 + num2;
				if (result <= 100) {

					Question question = new Question(num1, num2, operator, result);
					isNotRepeat = validate(question);
					if (isNotRepeat) {
						list.add(question);
						qNum--;
					}
					answers.add(result);
				}
			} else {
				result = num1 - num2;
				if (result >= 0) {

					Question question = new Question(num1, num2, operator, result);
					isNotRepeat = validate(question);
					if (isNotRepeat) {
						list.add(question);
						qNum--;
					}
					answers.add(result);
				}
			}
			if (qNum == 0) {
				break;
			}
		}
		return list;
	}

	/**
	 * 验证是否有重复题目
	 */
	private boolean validate(Question newQuestion) {

		int newResult = newQuestion.getResult();

		for (int i = 0; i < list.size(); i++) {

			Question oldQuestion = list.get(i);
			int oldResult = oldQuestion.getResult();

			if (newResult == oldResult) {
				if (newQuestion.getOperator() == 0) {
					if (newQuestion.getNum1() == oldQuestion.getNum1() || newQuestion.getNum1() == oldQuestion.getNum2()) {
						return false;
					}
				} else {
					if (newQuestion.getNum1() == oldQuestion.getNum1()) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public List<Integer> getAnswers() {
		
		return answers;
		
	}
}
