package math.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class ExampleGeneratorServiceImpl implements ExampleGeneratorService {

    private final String[] operators = {"+", "-", "*", "/"};

    private final Random random = new Random();

    @Override
    public List<String> generate(int count) {
        List<String> examples = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            examples.add(generateExample());
        }
        return examples;
    }

    private String generateExample() {
        int leftOperand = random.nextInt(1024);
        int rightOperand = random.nextInt(1024);
        String operator = operators[random.nextInt(operators.length)];

        return "%d %s %d".formatted(leftOperand, operator, rightOperand);
    }
}
