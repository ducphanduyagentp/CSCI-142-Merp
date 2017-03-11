package Nodes;

import Util.SymbolTable;

/**
 * Created by ptnega on 10/03/2017.
 * Node representing the not equals operator
 */
public class NotEqualityNode extends BooleanOperatorNode {

    /**
     * Constructor that sets the left/right children and sets the operator to the string !=
     * @param left the MerpNode representing the left child
     * @param right the MerpNode representing the right child
     */
    public NotEqualityNode(MerpNode left, MerpNode right) {
        super(left, right, "!=");
    }

    /**
     * Evaluates the node to determine its integer value 1 for true, zero for false
     * @param symbolTable the symbol table to use for variable processing
     * @return the integer value of this node
     */
    public int evaluate(SymbolTable symbolTable) {
        return this.leftChild.evaluate(symbolTable) != this.rightChild.evaluate(symbolTable) ? 1 : 0;
    }
}
