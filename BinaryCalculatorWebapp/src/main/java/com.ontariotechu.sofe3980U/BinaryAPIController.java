package com.ontariotechu.sofe3980U;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller providing APIs for binary operations such as addition,
 * multiplication, and bitwise operations (AND, OR).
 */
@RestController
public class BinaryAPIController {

    /**
     * Adds two binary numbers and returns the result as a string.
     */
    @GetMapping("/add")
    public String addBinary(@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
                            @RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {
        return Binary.add(new Binary(operand1), new Binary(operand2)).getValue();
    }

    /**
     * Adds two binary numbers and returns the result in JSON format.
     */
    @GetMapping("/add_json")
    public BinaryAPIResult
    addBinaryJSON(@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
                  @RequestParam(name = "operand2", required = false, defaultValue = "") String operand2){
        Binary num1 = new Binary(operand1);
        Binary num2 = new Binary(operand2);
        return new BinaryAPIResult(num1, "add", num2, Binary.add(num1, num2));
    }

    /**
     * Multiplies two binary numbers and returns the result as a string.
     */
    @GetMapping("/multiply")
    public String
    multiplyBinary(@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
                   @RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {
        return Binary.multiply(new Binary(operand1), new Binary(operand2)).getValue();
    }

    /**
     * Multiplies two binary numbers and returns the result in JSON format.
     */
    @GetMapping("/multiply_json")
    public BinaryAPIResult
    multiplyBinaryJSON(@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
                       @RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {
        Binary num1 = new Binary(operand1);
        Binary num2 = new Binary(operand2);
        return new BinaryAPIResult(num1, "multiply", num2, Binary.multiply(num1, num2));
    }

    /**
     * Performs bitwise AND on two binary numbers and returns the result as a string.
     */
    @GetMapping("/and")
    public String
    andBinary(@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
              @RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {
        return Binary.and(new Binary(operand1), new Binary(operand2)).getValue();
    }

    /**
     * Performs bitwise AND on two binary numbers and returns the result in JSON format.
     */
    @GetMapping("/and_json")
    public BinaryAPIResult
    andBinaryJSON(@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
                  @RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {
        Binary num1 = new Binary(operand1);
        Binary num2 = new Binary(operand2);
        return new BinaryAPIResult(num1, "and", num2, Binary.and(num1, num2));
    }

    /**
     * Performs bitwise OR on two binary numbers and returns the result as a string.
     */
    @GetMapping("/or")
    public String
    orBinary(@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
             @RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {
        return Binary.or(new Binary(operand1), new Binary(operand2)).getValue();
    }

    /**
     * Performs bitwise OR on two binary numbers and returns the result in JSON format.
     */
    @GetMapping("/or_json")
    public BinaryAPIResult
    orBinaryJSON(@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
                 @RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {
        Binary num1 = new Binary(operand1);
        Binary num2 = new Binary(operand2);
        return new BinaryAPIResult(num1, "or", num2, Binary.or(num1, num2));
    }
}
