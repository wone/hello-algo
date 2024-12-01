package chapter_my_train;

import java.util.ArrayList;
import java.util.List;

public class BigNumberAddSubtract {

    public static class BigNumberAddition {
        public static String add(String num1, String num2) {
            // 将字符串转换为字符数组，方便从低位到高位遍历
            char[] charArray1 = num1.toCharArray();
            char[] charArray2 = num2.toCharArray();

            // 用于存储相加结果的列表
            List<Integer> resultList = new ArrayList<>();

            // 进位标志
            int carry = 0;

            // 从两个数的末尾开始遍历相加
            int i = charArray1.length - 1;
            int j = charArray2.length - 1;
            while (i >= 0 || j >= 0) {
                int sum = carry;
                if (i >= 0) {
                    sum += charArray1[i] - '0';
                    i--;
                }
                if (j >= 0) {
                    sum += charArray2[j] - '0';
                    j--;
                }

                // 处理进位
                carry = sum / 10;
                resultList.add(sum % 10);
            }

            // 如果最后还有进位，添加到结果列表
            if (carry > 0) {
                resultList.add(carry);
            }

            // 将结果列表转换为字符串
            StringBuilder resultBuilder = new StringBuilder();
            for (int k = resultList.size() - 1; k >= 0; k--) {
                resultBuilder.append(resultList.get(k));
            }

            return resultBuilder.toString();
        }
    }

    public static class BigNumberSubtraction {
        // 比较两个大数的大小，返回true如果num1 >= num2
        private static boolean compare(String num1, String num2) {
            if (num1.length() > num2.length()) {
                return true;
            } else if (num1.length() < num2.length()) {
                return false;
            } else {
                return num1.compareTo(num2) >= 0;
            }
        }

        public static String subtract(String num1, String num2) {
            boolean isNegative = false;
            if (!compare(num1, num2)) {
                // 如果num1小于num2，交换它们并标记结果为负
                String temp = num1;
                num1 = num2;
                num2 = temp;
                isNegative = true;
            }

            char[] charArray1 = num1.toCharArray();
            char[] charArray2 = num2.toCharArray();

            List<Integer> resultList = new ArrayList<>();

            // 借位标志，初始化为0
            int borrow = 0;

            int i = charArray1.length - 1;
            int j = charArray2.length - 1;
            while (i >= 0 || j >= 0) {
                int diff = borrow;
                if (i >= 0) {
                    diff += charArray1[i] - '0';
                    i--;
                }
                if (j >= 0) {
                    diff -= charArray2[j] - '0';
                    j--;
                }

                // 如果diff小于0，进行借位处理
                if (diff < 0) {
                    diff += 10;
                    borrow = -1;
                } else {
                    borrow = 0;
                }

                resultList.add(diff);
            }

            // 去除结果开头可能的多余0
            while (resultList.size() > 1 && resultList.get(resultList.size() - 1) == 0) {
                resultList.remove(resultList.size() - 1);
            }

            StringBuilder resultBuilder = new StringBuilder();
            if (isNegative) {
                resultBuilder.append('-');
            }
            for (int k = resultList.size() - 1; k >= 0; k--) {
                resultBuilder.append(resultList.get(k));
            }

            return resultBuilder.toString();
        }
    }

    public static void main(String[] args) {
        String num1 = "12345678901234567890";
        String num2 = "98765432109876543210";

        // 测试大数加法
        String additionResult = BigNumberAddition.add(num1, num2);
        System.out.println("加法结果: " + additionResult);

        // 测试大数减法，假设num1不小于num2
        String subtractionResult = BigNumberSubtraction.subtract(num1, num2);
        System.out.println("减法结果: " + subtractionResult);
    }
}
