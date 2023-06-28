package com.example.demo.Controller;

import com.example.demo.model.Bmi_zmienne;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bmi")
public class BmiController {

    @GetMapping
    public String showBmiForm(Model model) {
        model.addAttribute("bmiData", new Bmi_zmienne());
        return "Formularz";
    }

    @PostMapping
    public String calculateBmi(Bmi_zmienne bmiData, Model model) {
        double waga = bmiData.getWaga();
        double wzrost = bmiData.getWzrost();
        double bmi = calculateBmiValue(waga, wzrost);
        String bmiCategory = getBmiCategory(bmi);

        model.addAttribute("bmi", bmi);
        model.addAttribute("bmiCategory", bmiCategory);
        return "Wyniki";
    }

    private double calculateBmiValue(double waga, double wzrost) {
        // Obliczanie BMI
        double heightInMeters = wzrost / 100; // przeliczenie
        return waga / (heightInMeters * heightInMeters);
    }

    private String getBmiCategory(double bmi) {
        // Określanie kategorii BMI
        if (bmi < 16) {
            return "wygłodzenie";
        } else if (bmi <= 16.99 && bmi >= 16) {
            return "wychudzenie";
        } else if (bmi >= 17 && bmi <= 18.49) {
            return "niedowaga";
        }
          else if (bmi <= 24.99 && bmi>=18.5) {
            return "wartosc prawidlowa";
            } else if (bmi <= 29.99 && bmi>=25) {
                return "nadwaga";
            } else if (bmi >= 30 && bmi<=40) {
                return "otyłość 1 stopnia";
            } else {
                return "otyłość skrajna";
            }
        }
    }
