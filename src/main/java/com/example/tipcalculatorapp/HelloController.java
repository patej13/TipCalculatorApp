package com.example.tipcalculatorapp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class HelloController {
    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent = NumberFormat.getPercentInstance();
    private BigDecimal tipPercentage = new BigDecimal(0.15);

    @FXML
    private TextField amountTextField;
    @FXML
    private Label tipPercentageLabel;
    @FXML
    private Slider tipPercentageSlider;
    @FXML
    private TextField tipTextField;
    @FXML
    private TextField totalTextField;

    @FXML
    private void calculateTotal() {
        try {
            BigDecimal amount = new BigDecimal(amountTextField.getText());
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);

            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
        } catch (NumberFormatException ex) {
            amountTextField.setText("");
            amountTextField.selectAll();
            amountTextField.requestFocus();
        }
    }
    public void initialize() {
        currency.setRoundingMode(RoundingMode.HALF_UP);

        tipPercentageSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            tipPercentage = BigDecimal.valueOf(newValue.intValue() / 100.0);
            tipPercentageLabel.textProperty().setValue(percent.format(tipPercentage));
            calculateTotal();
        }
        );
        tipPercentageLabel.setText(percent.format(tipPercentage));
        amountTextField.textProperty().addListener((observable, oldValue, newValue) -> calculateTotal());
    }
}
