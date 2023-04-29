package mainPackage;


import java.util.*;

public class SpringArray {
    
    // Returns the equivalent spring for a string expression of springs of unit stiffness
    public static Spring equivalentSpring(String springExpr) {
        Stack<Spring> stack = new Stack<>();
        
        for (int i = 0; i < springExpr.length(); i++) {
            char c = springExpr.charAt(i);
            if (c == '[') {
                stack.push(new Spring());
            } else if (c == '{') {
                stack.push(new Spring(1.0));
            } else if (c == ']') {
                Spring s1 = stack.pop();
                Spring s2 = stack.pop();
                stack.push(s1.inParallel(s2));
            } else if (c == '}') {
                Spring s1 = stack.pop();
                Spring s2 = stack.pop();
                stack.push(s1.inSeries(s2));
            }
        }
        
        return stack.pop();
    }
    
    // Returns the equivalent spring for a string expression of specified springs
    public static Spring equivalentSpring(String springExpr, Spring[] springs) {
        Map<Character, Spring> map = new HashMap<>();
        char nextSymbol = 'A';
        
        for (Spring spring : springs) {
            map.put(nextSymbol, spring);
            nextSymbol++;
        }
        
        Stack<Spring> stack = new Stack<>();
        
        for (int i = 0; i < springExpr.length(); i++) {
            char c = springExpr.charAt(i);
            if (c == '[') {
                stack.push(map.get(nextSymbol));
                nextSymbol++;
            } else if (c == '{') {
                stack.push(map.get(nextSymbol));
                nextSymbol++;
            } else if (c == ']') {
                Spring s1 = stack.pop();
                Spring s2 = stack.pop();
                stack.push(s1.inParallel(s2));
            } else if (c == '}') {
                Spring s1 = stack.pop();
                Spring s2 = stack.pop();
                stack.push(s1.inSeries(s2));
            }
        }
        
        return stack.pop();
    }
    
}

	        
