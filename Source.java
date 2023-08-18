class A extends Thread {
    String operation;
    int num1, num2;
    int result;

    public A(String operation, int num1, int num2) {
        this.operation = operation;
        this.num1 = num1;
        this.num2 = num2;
    }

    public void run() {
        switch (operation) {
            case "add":
                result = num1 + num2;
                break;
            case "subtract":
                result = num1 - num2;
                break;
            case "multiply":
                result = num1 * num2;
                break;
            case "divide":
                result = num1 / num2;
                break;
        }
    }

    public String getOperation() {
        return operation;
    }

    public int getResult() {
        return result;
    }
}

public class Source {
    public static void main(String[] args) {


        int num1 = Integer.parseInt(args[0]);
        int num2 = Integer.parseInt(args[1]);

        String[] operations = {"add", "subtract", "multiply", "divide"};
        A[] operationThreads = new A[operations.length];

        for (int i = 0; i < operations.length; i++) {
            operationThreads[i] = new A(operations[i], num1, num2);
            operationThreads[i].start();
        }

        for (A operationThread : operationThreads) {
            try {
                operationThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(operationThread.getOperation() + " result: " + operationThread.getResult());
        }
    }
}
