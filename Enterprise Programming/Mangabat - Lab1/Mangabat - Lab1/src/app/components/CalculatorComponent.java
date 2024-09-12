package app.components;

import org.springframework.stereotype.Component;

@Component
public class CalculatorComponent {
	public double add(double first, double second) {
		return first+second;
	}
	public double subtract(double first, double second) {
		return first-second;
	}
	public double multiply(double first, double second) {
		return first*second;
	}
	public double divide(double first, double second){
		return first/second;
	}
	public double sqrt(double num) {
		return Math.sqrt(num);
	}

}
