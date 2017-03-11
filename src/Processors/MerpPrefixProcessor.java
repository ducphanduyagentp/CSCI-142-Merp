package Processors;

import Nodes.*;
import Util.Errors;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ptnega on 10/03/2017.
 * Class to process Merp expressions using prefix notation
 */
public class MerpPrefixProcessor extends MerpProcessor{

    public MerpPrefixProcessor() {

    }

    /**
     * Constructs and assigns a Merp tree from the provided list of MerpNode tokens using prefix notation
     * @param tokens list of IerpNodes used to create the pares tree
     */
    public void constructTree(java.util.ArrayList<java.lang.String> tokens) {
        this.tree = constructTreeHelper(tokens);
    }

    /**
     * Helper to recursively construct the parse tree
     * @param tokens list of IerpNodes to process
     * @return current root of the parse tree
     */
    private MerpNode constructTreeHelper(java.util.ArrayList<java.lang.String> tokens) {
        if (tokens.size() == 0) {
            return null;
        }
        if (isNumeric(tokens.get(0))) {
            return new ConstantNode(Integer.parseInt(tokens.get(0)));
        } else if (isVariable(tokens.get(0))) {
            return new VariableNode(tokens.get(0));
        } else {
            MerpNode u = null;

            if (tokens.get(0).equals("+")) {
                u = new AdditionNode(null, null);
            } else if (tokens.get(0).equals("-")) {
                u = new SubtractionNode(null, null);
            } else if (tokens.get(0).equals("*")) {
                u = new MultiplicationNode(null, null);
            } else if (tokens.get(0).equals("//")) {
                u = new DivisionNode(null, null);
            } else if (tokens.get(0).equals("^")) {
                u = new PowerNode(null, null);
            } else if (tokens.get(0).equals(">")) {
                u = new GreaterThanNode(null, null);
            } else if (tokens.get(0).equals(">=")) {
                u = new GreaterThanEqualNode(null, null);
            } else if (tokens.get(0).equals("<")) {
                u = new LessThanNode(null, null);
            } else if (tokens.get(0).equals("<=")) {
                u = new LessThanEqualNode(null, null);
            } else if (tokens.get(0).equals("==")) {
                u = new EqualityNode(null, null);
            } else if (tokens.get(0).equals("!=")) {
                u = new NotEqualityNode(null,null);
            } else if (tokens.get(0).equals("_")) {
                u = new NegationNode(null);
            } else if (tokens.get(0).equals("@")) {
                u = new SquareRootNode(null);
            } else if (tokens.get(0).equals("|")) {
                u = new AbsValueNode(null);
            } else {
                Errors.error("Invalid Merp Expression: ", tokens.get(0));
            }
            if (tokens.size() > 1) {
                MerpNode left = constructTreeHelper(new ArrayList<>(tokens.subList(1, tokens.size())));
                if (u instanceof BinaryOperatorNode) {
                    ((BinaryOperatorNode) u).setLeftChild(left);
                    if (left != null) {
                        ArrayList<String> leftPrefix = new ArrayList<>(Arrays.asList(left.toPrefixString().trim().split(" ")));
                        if (tokens.size() > leftPrefix.size()) {
                            MerpNode right = constructTreeHelper(new ArrayList<>(tokens.subList(1 + leftPrefix.size(), tokens.size())));
                            ((BinaryOperatorNode) u).setRightChild(right);
                        }
                    }
                } else if (u instanceof UnaryOperatorNode) {
                    ((UnaryOperatorNode) u).setChild(left);
                }
            }
            return u;
        }
    }
}
