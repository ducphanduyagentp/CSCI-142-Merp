package Nodes;

import Util.Errors;
import Util.SymbolTable;

/**
 * Created by ptnega on 10/03/2017.
 * Square Root MerpNode
 */
public class SquareRootNode extends UnaryOperatorNode{

    /**
     * Constructor that sets the left child and sets the operator to the string @ The precedence is set to POWER
     * @param child MerpNode that is the child of this node
     */
    public SquareRootNode(MerpNode child) {
        super(child, Precedence.POWER, "@");
    }

    /**
     * Evaluates the node to determine its integer value Errors if the child evaluates to a negative number
     * @param symbolTable the symbol table to use for variable processing
     * @return the integer value of this node
     */
    public int evaluate(SymbolTable symbolTable) {
        int child = this.child.evaluate(symbolTable);
        if (child < 0) {
            Errors.error("Negative value", child);
        }

        return (int) Math.sqrt(child);
    }
}
