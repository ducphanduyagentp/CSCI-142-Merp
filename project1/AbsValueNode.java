package Nodes;

import Util.SymbolTable;

import static Nodes.Precedence.MULT_DIVIDE;

/**
 * Created by ptnega on 10/03/2017.
 * Absolute Value MerpNode
 */
public class AbsValueNode extends UnaryOperatorNode{

    /**
     * Constructor that sets the left child and sets the operator to the string | The precedence is set to MULT_DIVIDE
     * @param child MerpNode that is the child of this node
     */
    public AbsValueNode(MerpNode child) {
        super(child, MULT_DIVIDE, "|");
    }

    /**
     * Evaluates the node to determine its integer value
     * @param symbolTable the symbol table to use for variable processing
     * @return the integer value of this node
     */
    public int evaluate(SymbolTable symbolTable) {
        return Math.abs(this.child.evaluate(symbolTable));
    }
}
