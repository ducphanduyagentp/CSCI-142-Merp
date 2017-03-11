package Nodes;

import Util.SymbolTable;

import java.util.DoubleSummaryStatistics;

/**
 * Created by ptnega on 10/03/2017.
 * Power Node
 */
public class PowerNode extends BinaryOperatorNode {

    /**
     * Constructor that sets the left/right children and sets the operator to the string ^ The precedence is set to POWER
     * @param left the MerpNode representing the base
     * @param right the MerpNode representing the power
     */
    public PowerNode(MerpNode left, MerpNode right) {
        super(left, right, Precedence.POWER, "^");
    }

    /**
     * Evaluates the node to determine its integer value
     * @param symbolTable the symbol table to use for variable processing
     * @return the integer value of this node
     */
    public int evaluate(SymbolTable symbolTable) {
        return (int) Math.pow(this.leftChild.evaluate(symbolTable), this.rightChild.evaluate(symbolTable));
    }
}
