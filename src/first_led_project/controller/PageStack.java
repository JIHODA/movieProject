package first_led_project.controller;

import java.util.Stack;

import javax.swing.JPanel;

public class PageStack {
    private Stack<JPanel> stack = new Stack<>();
    
    public void push(JPanel panel) {
        stack.push(panel);
    }
    
    public JPanel pop() {
        return stack.pop();
    }
    
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    
    public int size() {
        return stack.size();
    }
}