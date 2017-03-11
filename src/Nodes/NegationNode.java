package Nodes;

import Util.SymbolTable;

/**
 * Created by ptnega on 10/03/2017.
 * Negation MerpNode
 */
public class NegationNode extends UnaryOperatorNode {

    /**
     * Constructor that sets the left child and sets the operator to the string _ The precedence is set to MULT_DIVIDE
     * @param child MerpNode that is the child of this node
     */
    public NegationNode(MerpNode child) {
        super(child, Precedence.MULT_DIVIDE, "_");
    }

    /**
     * Evaluates the node to determine its integer value
     * @param symbolTable the symbol table to use for variable processing
     * @return the integer value of this node
     */
    public int evaluate(SymbolTable symbolTable) {
        return -this.child.evaluate(symbolTable);
    }
}
