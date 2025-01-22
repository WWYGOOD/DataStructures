package com.wwy.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    /**
     * 逆波兰表达式四则运算
     *
     * @param args
     */
    public static void main(String[] args) {
        String exp = "(3-1)*5";
        //将中缀表达式按字符存入list
        List<String> infixExpressionList = toInfixExpressionList(exp);
        //将中缀表达式转为逆波兰表达式
        List<String> strings = listParseSuffixExpressionList(infixExpressionList);
        System.out.println(strings);
        System.out.println(calCulate(strings));
    }

    /**
     * 将中缀表达式list转为逆波兰表达式
     */
    public static List<String> listParseSuffixExpressionList(List<String> str) {
        //初始化运算符s1栈
        Stack<String> s1 = new Stack<>();
        //初始化存储结果的s2栈，因为s2全程没有pop,所以就用list代替
        ArrayList<String> s2 = new ArrayList<>();
        for (String s : str) {
            //如果遇到操作数时，将其压s2;
            if (s.matches("\\d+")) {
                s2.add(s);
            } else {
                //如果s1为空，或栈顶运算符为左括号“”，则直接将此运算符入栈;
                if (s1.isEmpty() || s.equals("(")) {
                    s1.push(s);
                    //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                } else if (s.equals(")")) {
                    while (!s1.peek().equals("(")) {
                        s2.add(s1.pop());
                    }
                    s1.pop();
                } else {
                    //栈顶运算符的优先级高于当前字符，加入s2
                    while (s1.isEmpty() && operPriority(s1.peek()) > operPriority(s)) {
                        s2.add(s1.pop());
                    }
                    //否则加入s1
                    s1.push(s);
                }
            }
        }
        while (!s1.isEmpty()) {
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     * 将中缀表达式按字符存入list
     *
     * @param exp
     * @return
     */
    public static List<String> toInfixExpressionList(String exp) {
        //定义一个list存放中缀表达式对应的内容
        ArrayList<String> list = new ArrayList<>();
        int i = 0;//指针
        String str;//多位数拼接
        char c;//没遍历到一个字符，就存入
        do {
//            如果是一个非数字，加入到list
            if ((exp.charAt(i) < 48) || (exp.charAt(i) > 57)) {
                list.add(String.valueOf(exp.charAt(i)));
                i++;
            } else {
                //将str设为空
                str = "";
//                如果是数字，就要考虑多位数
                while (i < exp.length() && (exp.charAt(i)) >= 48 && (exp.charAt(i)) <= 57) {
                    str += exp.charAt(i);
                    i++;
                }
                list.add(str);
            }

        } while (i < exp.length());

        return list;
    }

    /**
     * 将逆波兰表达式存入list集合中
     *
     * @param exp
     * @return
     */
    private static List<String> getListString(String exp) {
        String[] exps = exp.split(" ");
        ArrayList<String> stringList = new ArrayList<>();
        for (String s : exps) {
            stringList.add(s);
        }
        return stringList;
    }

    /**
     * 计算逆波兰表达式的结果
     *
     * @param list
     * @return
     */
    private static int calCulate(List<String> list) {
        Stack<String> stringStack = new Stack<>();
        for (String l : list) {
            if (l.matches("\\d+")) {
                stringStack.push(l);
            } else {
                int num1 = Integer.parseInt(stringStack.pop());
                int num2 = Integer.parseInt(stringStack.pop());
                int res = 0;
                if (l.equals("+")) {
                    res = num2 + num1;
                } else if (l.equals("-")) {
                    res = num2 - num1;
                } else if (l.equals("*")) {
                    res = num2 * num1;
                } else if (l.equals("/")) {
                    res = num2 / num1;
                }
                stringStack.push(String.valueOf(res));

            }
        }
        return Integer.parseInt(stringStack.pop());
    }

    /**
     * 判断当前运算符的优先级
     *
     * @param oper
     * @return
     */
    public static int operPriority(String oper) {
        if (oper.equals("+") || oper.equals("-")) {
            return 0;
        } else if (oper.equals("*") || oper.equals("/")) {
            return 1;
        }
        return -1;
    }
}
