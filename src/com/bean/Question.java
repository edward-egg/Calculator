package com.bean;

/**
 * @author 易正元  201540704671 java3
 */
public class Question {

	private int num1;
	private int num2;
	private double operator;
	private int result;

	public Question(int a, int b, double opt, int res) {
		this.num1 = a;
		this.num2 = b;
		this.operator = opt;
		this.result = res;
	}

	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}

	public double getOperator() {
		return operator;
	}

	public void setOperator(double operator) {
		this.operator = operator;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	private String operator() {
		return (Math.rint(operator)==0?"+":"-");
	}

	public String withAns() {
		String opt = operator();
		return (num1 + " " + opt + " " + num2 + " = " + result);
	}

	public String withoutAns() {

		String opt = operator();
		return num1 + " " + opt + " " + num2;
	}

}
