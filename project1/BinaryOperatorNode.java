package Nodes;

/**
 * Created by ptnega on 10/03/2017.
 * Abstract class to represent a binary operator Has a left/right child, precedence, and operator
 */
public abstract class BinaryOperatorNode implements MerpNode {

    protected MerpNode leftChild;
    protected MerpNode rightChild;
    protected Precedence precedence;
    protected String operator;

    /**
     * Binary Node Constructor
     * @param leftChild MerpNode representing the left child
     * @param rightChild MerpNode representing the right child
     * @param precedence Precedence of the operator
     * @param operator String representing the operator
     */
    public BinaryOperatorNode(MerpNode leftChild, MerpNode rightChild, Precedence precedence, String operator) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.precedence = precedence;
        this.operator = operator;
    }

    /**
     * Setter for left child
     * @param leftChild The MerpNode to be set at this node's left child
     */
    public void setLeftChild(MerpNode leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * Setter for right child
     * @param rightChild The MerpNode to be set at this node's right child
     */
    public void setRightChild(MerpNode rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * Displays this node as prefix notation expression string
     * @return string representing the node as prefix notation
     */
    public String toPrefixString() {
        return this.operator + " " + this.leftChild.toPrefixString() + " " + this.rightChild.toPrefixString();
    }

    /**
     * Displays this node as infix notation expression string
     * @return string representing the node as infix notation
     */
    public String toInfixString() {
        return "(" + this.leftChild.toInfixString() + " " + this.operator + " " + this.rightChild.toInfixString() + ")";
    }

    /**
     * Displays this node as postfix notation expression string
     * @return string representing the node as postfix notation
     */
    public String toPostfixString() {
        return this.leftChild.toPostfixString() + " " + this.rightChild.toPostfixString() + " " + this.operator;
    }

    /**
     * Returns the precedence of this node
     * @return returns the precedence of CONSTANT
     */
    public int getPrecedence() {
        if (this.operator.equals("+") || this.operator.equals("-")) {
            return Precedence.ADD_SUBTRACT.getPrecedence();
        } else if (this.operator.equals("*") || this.operator.equals("//")) {
            return Precedence.MULT_DIVIDE.getPrecedence();
        } else if (this.operator.equals("^")) {
            return Precedence.POWER.getPrecedence();
        } else if (this.operator.equals(">") || this.operator.equals(">=") || this.operator.equals("<") || this.operator.equals("<=") || this.operator.equals("==") || this.operator.equals("!=")) {
            return Precedence.BOOLEAN.getPrecedence();
        }
        return -1;
    }

    /**
     * determines if the node is an operation node
     * @return true if an operation node, false otherwise
     */
    public boolean isOperation() {
        return this.operator.equals("+") || this.operator.equals("-") || this.operator.equals("*") || this.operator.equals("//") || this.operator.equals("^") || this.operator.equals(">") || this.operator.equals(">=") || this.operator.equals("<") || this.operator.equals("<=") || this.operator.equals("==") || this.operator.equals("!=");
    }

    /**
     * Determines the node type
     * @return the type of this node
     */
    public NodeType getNodeType() {
        return NodeType.BinaryOperation;
    }
}
