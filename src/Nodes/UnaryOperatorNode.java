package Nodes;

/**
 * Created by ptnega on 10/03/2017.
 * Abstract class to represent a unary operator Unary operators only have a single child
 */
public abstract class UnaryOperatorNode implements MerpNode{
    protected MerpNode child;
    protected String operator;
    protected Precedence precedence;

    public UnaryOperatorNode(MerpNode child, Precedence precedence, String operator) {
        this.child = child;
        this.precedence = precedence;
        this.operator = operator;
    }

    /**
     * Sets the child of this node
     * @param child the MerpNode representing the child
     */
    public void setChild(MerpNode child) {
        this.child = child;
    }

    /**
     * Displays this node as prefix notation expression string
     * @return string representing the node as prefix notation
     */
    public String toPrefixString() {
        return this.operator + " " + this.child.toPrefixString();
    }

    /**
     * Displays this node as infix notation expression string
     * @return string representing the node as infix notation
     */
    public String toInfixString() {
        return "(" + this.operator + " " + this.child.toInfixString() + ")";
    }

    /**
     * Displays this node as postfix notation expression string
     * @return string representing the node as postfix notation
     */
    public String toPostfixString() {
        return this.child.toPostfixString() + " " + this.operator;
    }

    /**
     * Returns the precedence of this node
     * @return returns the precedence as an int value
     */
    public int getPrecedence() {
        return this.precedence.getPrecedence();
    }

    /**
     * determines if the node is an operation node
     * @return true if an operation node, false otherwise
     */
    public boolean isOperation() {
        return this.operator.equals("_") || this.operator.equals("@") || this.operator.equals("|");
    }

    /**
     * Determines the node type
     * @return the type of this node
     */
    public NodeType getNodeType() {
        return NodeType.UnaryOperation;
    }

}
