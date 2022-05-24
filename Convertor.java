
package project3csc4101;
import java.util.Stack;
/**
 *
 * @author pastu
 */
public class Convertor {

    
    static int preced(String ch)
    {
       switch (ch)
        {
        case "+", "-" -> {
            return 1;
            }
        case "*", "/" -> {
            return 2;
            }
        }
        return -1;
    }
    
    public static String convertToPostfix(String expression){
        Stack <String> stack = new Stack<>();
        stack.push("#");
        String output = "";
        String[] postFix = expression.split(" ");
        for (String element : postFix){
            //System.out.println(element);
            char ch = element.charAt(0);
            if (Character.isLetterOrDigit(ch))
                output = output + element + ' ';
            
            else if (ch == '(')
                stack.push(element);
            
            else if (ch == ')'){
                while (!stack.isEmpty() && !"(".equals(stack.peek())){
                    output = output + stack.pop() + " ";    
                }
                stack.pop();
            }
            
            else{
                while (!stack.isEmpty() && preced(element) <= preced(stack.peek())){
                    output = output + stack.pop() + " ";
                }
                stack.push(element);
            }              
        }
        while(!stack.isEmpty()){
            output = output + stack.pop() + ' ';        
        }
        
        if ((output != null) && (output.length() > 0)) {
            output = output.substring(0, output.length() - 2);
        }
    return output;
    }


public static String evaluatePostfix(String expression){
        Stack <String> stack = new Stack<>();
        stack.push("#");
        String[] postFix = expression.split(" ");
        for (String element : postFix){
            //System.out.println(element);
            char ch = element.charAt(0);
            if (Character.isDigit(ch))
                stack.push(element);
             else
            {
                String val1Str = stack.pop();
                String val2Str = stack.pop();
                int val1 = Integer.parseInt(val1Str);
                int val2 = Integer.parseInt(val2Str);
                int result;
                String resultStr;
                
                switch(element)
                {
                    case "+":
                        result= val2+val1;
                        resultStr= String.valueOf(result);
                        stack.push(resultStr);
                    break;
                     
                    case "-":
                        result= val2-val1;
                        resultStr= String.valueOf(result);
                        stack.push(resultStr);
                    break;
                     
                    case "/":
                        result= val2/val1;
                        resultStr= String.valueOf(result);
                        stack.push(resultStr);
                    break;
                     
                    case "*":
                        result= val2*val1;
                        resultStr= String.valueOf(result);
                        stack.push(resultStr);
                    break;
                }
            }
        }
       return stack.pop();
    }




    public static void main(String[] args) {
        
       
        String infixExpr1 = "( 1 + 2 ) / 4 - 5 * 6";
        String infixExpr2 = "( 4 / 2 + 2 ) + ( 5 - 4 ) * 6";
        String infixExpr3 = "14 / 7 + ( 3 - 2 )";
        
        String postfixExpr1 = Convertor.convertToPostfix(infixExpr1);
        String postfixExpr2 = Convertor.convertToPostfix(infixExpr2);
        String postfixExpr3 = Convertor.convertToPostfix(infixExpr3);
        
        System.out.println(infixExpr1 + " => " + postfixExpr1 + " = " + Convertor.evaluatePostfix(postfixExpr1));
        System.out.println(infixExpr2 + " => " + postfixExpr2 + " = " + Convertor.evaluatePostfix(postfixExpr2));
        System.out.println(infixExpr3 + " => " + postfixExpr3 + " = " + Convertor.evaluatePostfix(postfixExpr3));
        
        
    }
    
}
