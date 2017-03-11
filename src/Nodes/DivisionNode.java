package Nodes;

import Util.SymbolTable;

/**
 * Created by ptnega on 10/03/2017.
 * Division Node
 */
public class DivisionNode extends BinaryOperatorNode {

    /**
     * Constructor that sets the left/right children and sets the operator to the string // The precedence is set to MULT_DIVIDE
     * @param left the MerpNode representing the left child
     * @param right the MerpNode representing the right child
     */
    public DivisionNode(MerpNode left, MerpNode right) {
        super(left, right, Precedence.MULT_DIVIDE, "//");
    }

    /**
     * Evaluates the node to determine its integer value
     * @param symbolTable the symbol table to use for variable processing
     * @return the integer value of this node
     */
    public int evaluate(SymbolTable symbolTable) {
        return this.leftChild.evaluate(symbolTable) / this.rightChild.evaluate(symbolTable);
    }
}
