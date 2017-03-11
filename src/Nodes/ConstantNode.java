package Nodes;

import Util.SymbolTable;

/**
 * Created by ptnega on 10/03/2017.
 * Constant MerpNode
 */
public class ConstantNode implements MerpNode {

    private int value;

    /**
     * Constructor that sets the value of this node
     * @param value integer value of this node
     */
    public ConstantNode(int value) {
        this.value = value;
    }

    /**
     * Evaluates the node to determine its integer value
     * @param symbolTable the symbol table to use for variable processing
     * @return the integer value of this node
     */
    public int evaluate(SymbolTable symbolTable) {
        return this.value;
    }

    /**
     * Displays this node as prefix notation expression string
     * @return string representing the node as prefix notation
     */
    public String toPrefixString() {
        return "" + this.value;
    }

    /**
     * Displays this node as infix notation expression string
     * @return string representing the node as infix notation
     */
    public String toInfixString() {
        return "" + this.value;
    }

    /**
     * Displays this node as postfix notation expression string
     * @return string representing the node as postfix notation
     */
    public String toPostfixString() {
        return "" + this.value;
    }

    /**
     * Returns the precedence of this node
     * @return returns the precedence of CONSTANT
     */
    public int getPrecedence() {
        return Precedence.CONSTANT.getPrecedence();
    }

    /**
     * determines if the node is an operation node
     * @return true if an operation node, false otherwise
     */
    public boolean isOperation() {
        return false;
    }

    /**
     * Determines the node type
     * @return the type of this node
     */
    public NodeType getNodeType() {
        return NodeType.Constant;
    }
}
