package ru.doza.calculator.webCalculator.service;

import org.springframework.stereotype.Service;
import ru.doza.calculator.webCalculator.models.Calculator;

@Service
public class CalculatorService {

    public double answer(Calculator model){

        String[] tokens = model.getExpression().split(" ");
        double result = Double.parseDouble(tokens[0]);
        for (int i = 1; i < tokens.length; i += 2) {
            char operator = tokens[i].charAt(0);
            double operand = Double.parseDouble(tokens[i + 1]);

            switch (operator) {
                case '+':
                    result += operand;
                    break;
                case '-':
                    result -= operand;
                    break;
                case '*':
                    result *= operand;
                    break;
                case '/':
                    result /= operand;
                    break;
                case '^':
                result = Math.pow(result, operand);
                break;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }
        return result;
    }
    public Calculator clear(Calculator model){
        model.setExpression(null);
        return model;
    }
}








