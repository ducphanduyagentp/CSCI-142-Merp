package Nodes;

/**
 * Created by ptnega on 10/03/2017.
 * Abstract class to represent a boolean operator Has a left/right child, precedence, and operator. Assumes non-zero values are true, zero is false.
 */
public abstract class BooleanOperatorNode extends BinaryOperatorNode {

    /**
     * Constructor for Boolean operation nodes The precedence is set to BOOLEAN
     * @param left the left child for this operation
     * @param right the right child for this operation
     * @param operator the string representing the operation for this node
     */
    public BooleanOperatorNode(MerpNode left, MerpNode right, String operator) {
        super(left, right, Precedence.BOOLEAN, operator);
    }

    /**
     * Returns the precedence of this node
     * @return returns the precedence of BOOLEAN
     */
    @Override
    public int getPrecedence() {
        return Precedence.BOOLEAN.getPrecedence();
    }
}
