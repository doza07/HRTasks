package ru.doza.calculator.webCalculator.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.doza.calculator.webCalculator.models.Calculator;
import ru.doza.calculator.webCalculator.service.CalculatorService;

    @Controller
    public class CalculatorController {

        Calculator calculator = new Calculator();

        @Autowired
        private CalculatorService calculatorService;

        @RequestMapping("/calculator")
        public String getCalculatorPage(Model model) {
            model.addAttribute("calculator", calculator);
            return "calculator";
        }

        @RequestMapping(value = "/calculator", params = "answer", method = RequestMethod.POST)
        public String add(@ModelAttribute("calculator") Calculator calculator, Model model) {
            try {
                model.addAttribute("result", calculatorService.answer(calculator));
            }catch (Exception e){
                model.addAttribute("error", e.getMessage());
            }
            return "calculator";
        }

        @RequestMapping(value = "/calculator", params = "clear", method = RequestMethod.POST)
        public String clear(@ModelAttribute("calculator") Calculator calculator, Model model) {
            model.addAttribute("operationModel", calculatorService.clear(calculator));
            model.addAttribute("result", 0);
            return "calculator";
        }
    }



