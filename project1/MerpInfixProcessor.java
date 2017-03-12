package Processors;

import Nodes.*;
import Util.Errors;

import java.util.Stack;

/**
 * Created by ptnega on 10/03/2017.
 * Class to process Merp expressions using infix notation
 */
public class MerpInfixProcessor extends MerpProcessor {
    public MerpInfixProcessor() {
        this.tree = null;
    }

    /**
     * Constructs and assigns a Merp tree from the provided list of MerpNode tokens using infix notation
     * @param tokens list of IerpNodes used to create the pares tree
     */
    public void constructTree(java.util.ArrayList<java.lang.String> tokens) {
        Stack<MerpNode> st1 = new Stack<>();
        Stack<MerpNode> st2 = new Stack<>();
        for (String s: tokens) {
            if (isNumeric(s)) {
                st1.push(new ConstantNode(Integer.parseInt(s)));
            } else if (isVariable(s)) {
                st1.push(new VariableNode(s));
            } else {
                MerpNode u = null;

                if (s.equals("+")) {
                    u = new AdditionNode(null, null);
                } else if (s.equals("-")) {
                    u = new SubtractionNode(null, null);
                } else if (s.equals("*")) {
                    u = new MultiplicationNode(null, null);
                } else if (s.equals("//")) {
                    u = new DivisionNode(null, null);
                } else if (s.equals("^")) {
                    u = new PowerNode(null, null);
                } else if (s.equals(">")) {
                    u = new GreaterThanNode(null, null);
                } else if (s.equals(">=")) {
                    u = new GreaterThanEqualNode(null, null);
                } else if (s.equals("<")) {
                    u = new LessThanNode(null, null);
                } else if (s.equals("<=")) {
                    u = new LessThanEqualNode(null, null);
                } else if (s.equals("==")) {
                    u = new EqualityNode(null, null);
                } else if (s.equals("!=")) {
                    u = new NotEqualityNode(null, null);
                } else if (s.equals("@")) {
                    u = new SquareRootNode(null);
                } else if (s.equals("_")) {
                    u = new NegationNode(null);
                } else if (s.equals("|")) {
                    u = new AbsValueNode(null);
                } else {
                    Errors.error("Invalid Merp Expression", s);
                }

                while(!st2.empty()) {
                    MerpNode v = st2.peek();

                    if (u.getNodeType() == MerpNode.NodeType.UnaryOperation || u.getPrecedence() == Precedence.POWER.getPrecedence()) {
                        break;
                    }

                    if (u.getPrecedence() >= v.getPrecedence()) {
                        st1.push(st2.pop());
                    } else {
                        break;
                    }
                }
                st2.push(u);
            }
        }
        while(!st2.empty()) {
            st1.push(st2.pop());
        }

        Stack<MerpNode> st = new Stack<>();
        while(!st1.empty()) {
            st.push(st1.pop());
        }

        this.tree = processStack(st);
    }

    /**
     * Processes an stack of IerpNodes to create a Ierp Parse Tree
     * @param stack the list of nodes to process
     * @return the root of the parse tree
     */
    private MerpNode processStack(java.util.Stack<MerpNode> stack) {
        Stack<MerpNode> st = new Stack<>();

        while(!stack.empty()) {
            MerpNode u = stack.pop();
            if (u.getNodeType() == MerpNode.NodeType.Constant || u.getNodeType() == MerpNode.NodeType.Variable) {
                st.push(u);
            } else if (u.getNodeType() == MerpNode.NodeType.BinaryOperation) {
                if (st.size() < 2) {
                    Errors.error("Unexpected token when parsing the Merp expression", "");
                }
                MerpNode right = st.pop();
                MerpNode left = st.pop();
                ((BinaryOperatorNode) u).setLeftChild(left);
                ((BinaryOperatorNode) u).setRightChild(right);
                st.push(u);
            } else if (u.getNodeType() == MerpNode.NodeType.UnaryOperation) {
                if (st.empty()) {
                    Errors.error("Unexpected token when parsing the Merp expression", "");
                }
                MerpNode child = st.pop();
                ((UnaryOperatorNode) u).setChild(child);
                st.push(u);
            }
        }
        if (st.size() != 1) {
            Errors.error("Unexpected token when parsing the Merp expression", "");
        }
        return st.pop();
    }
}
