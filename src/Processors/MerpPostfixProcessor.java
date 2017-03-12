package Processors;

import Nodes.*;
import Util.Errors;

import java.util.Stack;

/**
 * Created by ptnega on 10/03/2017.
 * Class to process Merp expressions using postfix notation
 */
public class MerpPostfixProcessor extends MerpProcessor {

    public MerpPostfixProcessor() {
        this.tree = null;
    }

    /**
     * onstructs and assigns a Merp tree from the provided list of MerpNode tokens using postfix notation
     * @param tokens list of IerpNodes used to create the pares tree
     */
    public void constructTree(java.util.ArrayList<java.lang.String> tokens) {
        this.tree = constructTreeHelper(tokens);
    }

    /**
     * Helper to recursively contstruct the parse tree
     * @param tokens list of IerpNodes to process
     * @return current root of the parse tree
     */
    private MerpNode constructTreeHelper(java.util.ArrayList<java.lang.String> tokens) {
        Stack<MerpNode> st = new Stack<>();
        for (String s: tokens) {
            if (isVariable(s)) {
                st.push(new VariableNode(s));
            } else if (isNumeric(s)) {
                st.push(new ConstantNode(Integer.parseInt(s)));
            } else if ("|@_".indexOf(s.charAt(0)) == -1) {
                if (st.size() < 2) {
                    Errors.error("Unexpected token when parsing the Merp expression", "");
                }
                if (s.equals("+")) {
                    MerpNode right = st.pop();
                    MerpNode left = st.pop();
                    st.push(new AdditionNode(left, right));
                } else if (s.equals("-")) {
                    MerpNode right = st.pop();
                    MerpNode left = st.pop();
                    st.push(new SubtractionNode(left, right));
                } else if (s.equals("*")) {
                    MerpNode right = st.pop();
                    MerpNode left = st.pop();
                    st.push(new MultiplicationNode(left, right));
                } else if (s.equals("//")) {
                    MerpNode right = st.pop();
                    MerpNode left = st.pop();
                    st.push(new DivisionNode(left, right));
                } else if (s.equals("^")) {
                    MerpNode right = st.pop();
                    MerpNode left = st.pop();
                    st.push(new PowerNode(left, right));
                } else if (s.equals(">")) {
                    MerpNode right = st.pop();
                    MerpNode left = st.pop();
                    st.push(new GreaterThanNode(left, right));
                } else if (s.equals(">=")) {
                    MerpNode right = st.pop();
                    MerpNode left = st.pop();
                    st.push(new GreaterThanEqualNode(left, right));
                } else if (s.equals("<")) {
                    MerpNode right = st.pop();
                    MerpNode left = st.pop();
                    st.push(new LessThanNode(left, right));
                } else if (s.equals("<=")) {
                    MerpNode right = st.pop();
                    MerpNode left = st.pop();
                    st.push(new LessThanEqualNode(left, right));
                } else if (s.equals("==")) {
                    MerpNode right = st.pop();
                    MerpNode left = st.pop();
                    st.push(new EqualityNode(left, right));
                } else if (s.equals("!=")) {
                    MerpNode right = st.pop();
                    MerpNode left = st.pop();
                    st.push(new NotEqualityNode(left, right));
                } else {
                    Errors.error("Invalid Merp expression", s);
                }
            } else {
                if (st.empty()) {
                    Errors.error("Unexpected token when parsing the Merp expression", "");
                }
                MerpNode child = st.pop();
                if (s.equals("_")) {
                    st.push(new NegationNode(child));
                } else if (s.equals("@")) {
                    st.push(new SquareRootNode(child));
                } else if (s.equals("|")) {
                    st.push(new AbsValueNode(child));
                }
            }
        }
        if (st.size() != 1) {
            Errors.error("Unexpected token when parsing the Merp expression", "");
        }
        return st.pop();
    }
}
